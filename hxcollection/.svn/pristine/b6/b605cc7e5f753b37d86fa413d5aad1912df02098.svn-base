package cn.com.hxfz.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
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

import cn.com.hxfz.service.OperationLogService;
import cn.com.hxfz.util.ServletHelp;

/**
 * 
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: OperationLogController
 * @作者: qiangxuan
 * @时间: 2018年3月23日 下午2:56:11
 * @版本: V1.0
 */

@Controller
@RequestMapping(value = "/operationLog")
public class OperationLogController {
	 static Logger logger = Logger.getLogger(OperationLogController.class.getName());
	 
	 @Autowired
	 private OperationLogService operationLogService;
	 
	 /**
	  * 
	  * 方法功能说明：  获取操作日志列表
	  * 创建：@Date by qiangxuan
	  * @参数： 
	  * @return
	  * @version 1.0         
	  * @throws
	  */
	 @RequestMapping(value = "/queryOperationLogList.do",method = { RequestMethod.POST, RequestMethod.GET})
	 public void queryOperationLogList(HttpServletRequest request,HttpServletResponse response, 
			 Integer pageSize, Integer pageIndex, String userName,String ipAdress,String operation,Date createTime,String errorMsg){
		Map<String, Object> param = new HashMap<String, Object>();
		 try {
			 param.put("limit", pageSize);
			 param.put("offset", pageIndex);
			 param.put("userName",userName);
			 param.put("ipAdress", ipAdress);
			 param.put("createTime",createTime);
			 param.put("errorMsg", errorMsg);
			 if (operation != null && !operation.equals("")) {
				 param.put("operation", URLDecoder.decode(operation,"UTF-8"));
			 }
			 
			 List<Map<String, Object>> resultList = operationLogService.getOperationLogList(param);
			 int total = operationLogService.getOperationLogCount(param);
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
