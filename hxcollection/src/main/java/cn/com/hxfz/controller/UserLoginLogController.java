package cn.com.hxfz.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.hxfz.service.UserLoginLogService;
import cn.com.hxfz.util.ServletHelp;


/**
 * 
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: UserOperationLogController
 * @作者: qiangxuan
 * @时间: 2018年3月28日 上午9:38:19
 * @版本: V1.0
 */
@Controller
@RequestMapping(value = "/userLoginLog")
public class UserLoginLogController {
	
	static Logger logger = Logger.getLogger(UserLoginLogController.class.getName());
	
	@Autowired
	private UserLoginLogService userLoginLogService;
	
	/**
	 * 
	  * 方法功能说明： TODO 
	  * 创建：2018年3月28日 by qiangxuan
	  * 返回类型：void
	  * 参数： TODO
	 */
	 @RequestMapping(value = "/queryUserLoginLogList.do",method = { RequestMethod.POST, RequestMethod.GET})
	public void queryUserLoginLogList(HttpServletRequest request, HttpServletResponse response,
			Integer pageSize, Integer pageIndex, String id, String nickName, Integer loginNum, String version,String equipmentInformation, Date loginTime, String phone){
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			 param.put("limit", pageSize);
			 param.put("offset", pageIndex);
			 param.put("loginNum", loginNum);
			 param.put("version", version);
			 param.put("equipmentInformation", equipmentInformation);
			 param.put("loginTime",loginTime);
			 param.put("phone", phone);
			 if (id != null && !id.equals("")) {
				param.put("id", URLDecoder.decode(id,"UTF-8"));
			 }
			 if (nickName != null && !nickName.equals("")) {
				param.put("nickName", URLDecoder.decode(nickName,"UTF-8"));
			 }
			 List<Map<String, Object>> resultList = userLoginLogService.getUserLoginLogList(param);
			 int total = userLoginLogService.getUserLoginLogCount(param);
			 Map<String, Object> resultMap = new HashMap<String, Object>();
			 resultMap.put("rows", resultList);
			 resultMap.put("total", total);
			 JSONObject jsonObj = JSONObject.fromObject(resultMap);
			 ServletHelp.outRequestForJson(request, response, jsonObj.toString());			 
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		
		
	}
}
