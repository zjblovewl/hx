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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.hxfz.service.UserOpinionFeedbackService;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;


/**
 * 
 * @类功能说明：用户意见反馈控制类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年3月29日 上午10:27:20 
 * @版本：V1.0
 */
@Controller
@RequestMapping(value = "/userOpinionFeedback")
public class UserOpinionFeedbackController {
	
	static Logger logger = Logger.getLogger(UserOpinionFeedbackController.class.getName());
	
	@Autowired
	private UserOpinionFeedbackService userOpinionFeedbackService;
	
	/**
	 * 
	  * 方法功能说明： TODO 
	  * 创建：2018年3月28日 by qiangxuan
	  * 返回类型：void
	  * 参数： TODO
	 */
	@RequestMapping(value = "/queryUserOFList.do")
	public void queryUserOpinionFeedbackList(HttpServletRequest request, HttpServletResponse response,
			Integer pageSize, Integer pageIndex, String id, String nickName, String feedbackContent, Date submitTime, String customerserviceFeedback,String processingState){
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			 param.put("limit", pageSize);
			 param.put("offset", pageIndex);
			 param.put("id",id);
			 param.put("nickName",nickName);
			 param.put("submitTime",submitTime);
			 
			 if (feedbackContent != null && !feedbackContent.equals("")) {
				 param.put("feedbackContent",URLDecoder.decode(feedbackContent,"UTF-8"));
			 }
			 if (customerserviceFeedback != null && !customerserviceFeedback.equals("")) {
				 param.put("customerserviceFeedback",URLDecoder.decode(customerserviceFeedback,"UTF-8"));
			 }
			 if (processingState != null && !processingState.equals("")) {
				 param.put("processingState",URLDecoder.decode(processingState,"UTF-8"));
			 }
			 List<Map<String, Object>> resultList = userOpinionFeedbackService.getUserOpinionFeedbackList(param);
			 int total = userOpinionFeedbackService.getUserOpinionFeedbackCount(param);
			 Map<String, Object> resultMap = new HashMap<String, Object>();
			 resultMap.put("rows", resultList);
			 resultMap.put("total", total);
			 JSONObject jsonObj = JSONObject.fromObject(resultMap);
			 logger.info(jsonObj);
			 ServletHelp.outRequestForJson(request, response, jsonObj.toString());			 
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @description 根据ID删除用户意见与反馈
	 * @method  deleteOpinionFeedback
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @date: 2018年4月9日 上午9:47:16
	 * @author:qiangxuan
	 */
	 @RequestMapping(value = "/deleteOFById.do",method = { RequestMethod.POST})
	 @ResponseBody
	 public void deleteOpinionFeedback(HttpServletRequest request,HttpServletResponse response){
	    //operation 操作，errorMsg异常信息
	    String operation = "",errorMsg = "";
	    String id = request.getParameter("ids");
	    try {
	    	operation = "删除【用户意见反馈】";
	    	Map<String, Object> paramsMap = new HashMap<String, Object>();											
	    	paramsMap.put("id", id);		
	    	userOpinionFeedbackService.deleteOpinionFeedback(paramsMap);			
	    	Map<String, Object> result = new HashMap<String, Object>();			
	    	result.put("success", true);			
	    	result.put("resultMsg", "删除成功");			
	    	JSONObject jsonObj = JSONObject.fromObject(result);		
	    	ServletHelp.outRequestForJson(request, response, jsonObj.toString());
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	errorMsg = e.getMessage();
	    }finally{
	    	LogUtil.saveLog(request,operation,errorMsg);
	    }
	}
	 
	 
	 @RequestMapping(value = "/addOrUpdateUserFC.do",method = { RequestMethod.POST})
	 @ResponseBody
	 public void saveOrUpdateTimeTask(HttpServletRequest request, HttpServletResponse response){
			String data = request.getParameter("ids");
			String operation = "",errorMsg = "";
			try {
				Map<String, Object> paramsMap = (Map)JSON.parse(data);
				userOpinionFeedbackService.saveUserFC(paramsMap);
				Map<String, Object> result = new HashMap<String, Object>();			
				result.put("success", true);			
				result.put("resultMsg", "保存成功");			
				JSONObject jsonObj = JSONObject.fromObject(result);		
				ServletHelp.outRequestForJson(request, response, jsonObj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				LogUtil.saveLog(request,operation,errorMsg);
			}
		}
}
