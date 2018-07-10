package cn.com.hxfz.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hxfz.service.SysMessageService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;

/**
 * 
 * @类功能说明：系统消息表  Controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月9日 下午2:44:52 
 * @版本：V1.0
 */
@Controller
@RequestMapping("/sysMessage")
public class SysMessageController {
	private SysMessageService messageService;
	
	public SysMessageService getMessageService() {
		return messageService;
	}
	@Autowired
	public void setMessageService(SysMessageService messageService) {
		this.messageService = messageService;
	}
	/**
	 * 
	 * @description 系统消息查询
	 * @method  getMessageRecords
	 * @param @param request
	 * @param @param response
	 * @param @param pageSize
	 * @param @param pageIndex
	 * @param @param mesTitle
	 * @param @param relateType
	 * @return void
	 * @date: 2018年4月9日 下午5:45:40
	 * @author:liugui
	 */
	@RequestMapping(value="/getMessageRecords.do")
	public void getMessageRecords(HttpServletRequest request,HttpServletResponse response,
			Integer pageSize, Integer pageIndex,String mesTitle,String platform){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {		
			operation = "查看【系统消息管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("offset", pageIndex);
			paramsMap.put("limit", pageSize);	
			paramsMap.put("mesTitle", mesTitle);//消息标题
			paramsMap.put("platform", platform);//推送平台类型
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<Map<String, Object>> resultList = messageService.getMessageRecords(params);
			int totalCount = messageService.getMessageCount(params);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", resultList);
			map.put("total", totalCount);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}
	/**
	 * 
	 * @description 查询消息信息
	 * @method  getMessageDetail
	 * @param @param request
	 * @param @param response
	 * @param @param mesId
	 * @return void
	 * @date: 2018年4月9日 下午5:46:37
	 * @author:liugui
	 */
	@RequestMapping(value="/getMessageDetail.do")
	public void getMessageDetail(HttpServletRequest request,HttpServletResponse response,String mesId){
		try {					
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("mesId", mesId);			
			List<Map<String,Object>> listMap = messageService.getMessageDetail(paramsMap);	
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("result", listMap);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 保存系统消息信息（新增、编辑）
	 * @method  saveOrUpdateMessage
	 * @param request
	 * @param response
	 * @param mesId 消息id
	 * @param platform 平台
	 * @param message_title  消息标题
	 * @param message_content  消息内容
	 * @param relate_type 关联类型
	 * @param relate_id 关联ID
	 * @param relate_url  链接
	 * @return void
	 * @date: 2018年4月8日 下午5:05:58
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateMessage.do")
	public void saveOrUpdateMessage(HttpServletRequest request,HttpServletResponse response,String mesId,String platform,
			String message_title,String message_content){
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("mesId", mesId);
			paramsMap.put("platform", platform);
			paramsMap.put("message_title", message_title);
			paramsMap.put("message_content", message_content);
			messageService.saveOrUpdateMessage(paramsMap);			
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "保存成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 删除系统消息
	 * @method  deleteMessageByIds
	 * @param @param request
	 * @param @param response
	 * @param @param mesIds
	 * @return void
	 * @date: 2018年4月9日 下午5:54:35
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteMessageByIds.do")
	public void deleteMessageByIds(HttpServletRequest request,HttpServletResponse response,String mesIds){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			operation = "删除【系统消息管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("mesIds", mesIds);
			messageService.deleteMessageByIds(paramsMap);		
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
}
