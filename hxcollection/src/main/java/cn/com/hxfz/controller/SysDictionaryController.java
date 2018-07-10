package cn.com.hxfz.controller;


import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.hxfz.model.Dictionary;
import cn.com.hxfz.service.SysDictionaryService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
import cn.com.hxfz.util.StringUtil;
/**
 * 
 * @类功能说明：字典表  Controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年3月30日 下午2:28:34 
 * @版本：V1.0
 */
@Controller
@RequestMapping("/dictionary")
public class SysDictionaryController {
	private SysDictionaryService dictionaryService;
	
	
	public SysDictionaryService getDictionaryService() {
		return dictionaryService;
	}
	@Autowired
	public void setDictionaryService(SysDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	/**
	 * 
	 * @description ：字典查询
	 * @method  getDictionaryRecords
	 * @param request
	 * @param response
	 * @param pageSize
	 * @param pageIndex
	 * @param dicName : 描述
	 * @param dicValue : 类型
	 * @return void
	 * @date: 2018年4月2日 上午9:06:18
	 * @author:liugui
	 */
	@RequestMapping(value="/getDictionaryRecords.do")
	public void getDictionaryRecords(HttpServletRequest request,HttpServletResponse response,
			Integer pageSize, Integer pageIndex,String dicName,String dicValue){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {		
			operation = "查看【字典管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("offset", pageIndex);
			paramsMap.put("limit", pageSize);	
			paramsMap.put("dicName", dicName);//描述
			paramsMap.put("dicValue", dicValue);//类型
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<Map<String, Object>> resultList = dictionaryService.getDictionaryRecords(params);
			int totalCount = dictionaryService.getDictionaryCount();
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
	 * @description 查询字典信息
	 * @method  getDictionaryDetail
	 * @param @param request
	 * @param @param response
	 * @param @param userId
	 * @return void
	 * @date: 2018年4月8日 下午4:36:20
	 * @author:liugui
	 */
	@RequestMapping(value="/getDictionaryDetail.do")
	public void getDictionaryDetail(HttpServletRequest request,HttpServletResponse response,String dicId){
		try {					
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("dicId", dicId);			
			Dictionary dictionary = dictionaryService.getDictionaryDetail(paramsMap);		
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("result", dictionary);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 保存字典信息（新增、编辑）
	 * @method  saveOrUpdateDictionary
	 * @param @param request
	 * @param @param response
	 * @param @param dicId 字典id
	 * @param @param dic_name 描述
	 * @param @param dic_value  类型
	 * @return void
	 * @date: 2018年4月8日 下午5:05:58
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateDictionary.do")
	public void saveOrUpdateDictionary(HttpServletRequest request,HttpServletResponse response,String dicId,String dic_name,
			String dic_value,String pId){
		try {
           				
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("dicId", dicId);
			paramsMap.put("dicName", dic_name);
			paramsMap.put("dicValue", dic_value);
			paramsMap.put("pId", pId);
			dictionaryService.saveOrUpdateDictionary(paramsMap);			
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
	 * @description 删除字典信息
	 * @method  deleteDictionaryByIds
	 * @param @param request
	 * @param @param response
	 * @param @param dicIds
	 * @return void
	 * @date: 2018年4月9日 上午9:16:17
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteDictionaryByIds.do")
	public void deleteDictionaryByIds(HttpServletRequest request,HttpServletResponse response,String dicIds){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			operation = "删除【字典管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("dicIds", dicIds);
			dictionaryService.deleteDictionaryByIds(paramsMap);			
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
	

	/**
	 * 
	 * @description 查询字典键值对列表
	 * @method  getKeyAndValRecords
	 * @param @param request
	 * @param @param response
	 * @param @param pId
	 * @return void
	 * @date: 2018年4月9日 上午10:13:53
	 * @author:liugui
	 */
	@RequestMapping(value="/getKeyAndValRecords.do")
	public void getKeyAndValRecords(HttpServletRequest request,HttpServletResponse response,String pId){
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("pId", pId);		
			List<Map<String, Object>> resultList = dictionaryService.getKeyAndValueRecords(paramsMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultList", resultList);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 保存字典键值对信息（新增、编辑）
	 * @method  saveOrUpdateKeyAndVal
	 * @param @param request
	 * @param @param response
	 * @param @param dicId
	 * @param @param dic_name
	 * @param @param dic_value
	 * @param @param p_id
	 * @param @param sort
	 * @return void
	 * @date: 2018年4月9日 上午10:25:27
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateKeyAndVal.do")
	public void saveOrUpdateKeyAndVal(HttpServletRequest request,HttpServletResponse response,String dicId,String dic_name,
			String dic_value,String p_id,String sort){
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("dicId", dicId);
			paramsMap.put("dicName", dic_name);
			paramsMap.put("dicValue", dic_value);
			paramsMap.put("pId", p_id);
			paramsMap.put("sort", Integer.parseInt(sort));
			dictionaryService.saveOrUpdateKeyAndVal(paramsMap);			
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
	 * @description 根据字典key获取数据字典列表
	 * @method  getDicListByDicKey
	 * @param @param request
	 * @param @param response
	 * @param @param key
	 * @return void
	 * @date: 2018年4月16日 下午5:49:12
	 * @author:liugui
	 */
	@RequestMapping(value="/getDicListByDicKey.do")
	public void getDicListByDicKey(HttpServletRequest request,HttpServletResponse response,String key,String isAll){
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("pic_value", key);	
			paramsMap.put("isAll", isAll);
			List<Map<String, Object>> resultList = dictionaryService.getDicListByDicKey(paramsMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultList", resultList);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 校验字典CODE是否已存在
	 * @method  checkDicCode
	 * @param @param request
	 * @param @param response
	 * @param @param paramsStr
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月24日 下午8:28:05
	 * @author:liugui
	 */
	@RequestMapping(value="/checkDicCode.do")
	@ResponseBody
	public Boolean checkDicCode(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		try {		
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			Map<String, Object> json = (Map<String, Object>) JSON.parse(paramsStr);
			Map<String, Object> paramsMap = CommUtils.convertDecode(json);
			String dicValue = URLDecoder.decode(paramJson.getString("dic_value"), "utf-8");
			if(StringUtil.availableStr(dicValue)){
				result = dictionaryService.checkDicCode(paramsMap);
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
}
