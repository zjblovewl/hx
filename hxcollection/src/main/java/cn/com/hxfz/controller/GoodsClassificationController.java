package cn.com.hxfz.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
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

import cn.com.hxfz.model.GoodsClassification;
import cn.com.hxfz.service.GoodsClassificationService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
import cn.com.hxfz.util.StringUtil;
import cn.com.hxfz.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @类功能说明：藏品分类表  Controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月10日 上午10:16:07 
 * @版本：V1.0
 */
@Controller
@RequestMapping("/goodsClassification")
public class GoodsClassificationController {
	private GoodsClassificationService goodsClassificationService;
	public GoodsClassificationService getGoodsClassificationService() {
		return goodsClassificationService;
	}
	@Autowired
	public void setGoodsClassificationService(
			GoodsClassificationService goodsClassificationService) {
		this.goodsClassificationService = goodsClassificationService;
	}
	
	public final static String ONE = "1";
	public final static String TWO = "2";
	/**
	 * 
	 * @description 获取藏品交易分类树
	 * @method  getTransClassifiyRecords
	 * @param @param request
	 * @param @param response
	 * @param @param className 分类名称
	 * @return void
	 * @date: 2018年4月10日 上午10:00:37
	 * @author:liugui
	 */
	@RequestMapping(value="/getTransClassifiyRecords.do")
	public void getTransClassifiyRecords(HttpServletRequest request,HttpServletResponse response,
			Integer pageSize, Integer pageIndex,String className){
		//operation 操作，errorMsg异常信息
			String operation = "",errorMsg = "";
			try {		
				operation = "查看【藏品交易分类树】";
				Map<String, Object> paramsMap = new HashMap<String, Object>();	
				paramsMap.put("className", className);
				paramsMap.put("classType", ONE);
				Map<String, Object> params = CommUtils.convertDecode(paramsMap);
				List<GoodsClassification> resultList = goodsClassificationService.getClassifyRecords(params);
				List<Map<String, Object>> resultList1= bean2ListMap(resultList);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("resultList", resultList1);
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
	 * @description 获取藏品拍卖分类树
	 * @method  getSaleClassifiyRecords
	 * @param @param request
	 * @param @param response
	 * @param @param className 分类名称
	 * @return void
	 * @date: 2018年4月10日 上午10:03:42
	 * @author:liugui
	 */
	@RequestMapping(value="/getSaleClassifiyRecords.do")
	public void getSaleClassifiyRecords(HttpServletRequest request,HttpServletResponse response,
			Integer pageSize, Integer pageIndex,String className){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {		
			operation = "查看【藏品拍卖分类树】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("className", className);
			paramsMap.put("classType", TWO);
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<GoodsClassification> resultList = goodsClassificationService.getClassifyRecords(params);
			List<Map<String, Object>> resultList1= bean2ListMap(resultList);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultList", resultList1);
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
	 * @description JavaBean转换为Map  
	 * @method  bean2ListMap
	 * @param @param resultList
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月20日 下午2:55:40
	 * @author:liugui
	 */
	public static List<Map<String,Object>> bean2ListMap(List<GoodsClassification> resultList){  
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
		    if (resultList != null && resultList.size() > 0) {
		    	GoodsClassification bean = null;
			    Map<String,Object> map = null;
			    for (int i = 0,size = resultList.size(); i < size; i++) {  
			    	bean = resultList.get(i);  
			    	map = new HashMap<>(); 
				    //获取指定类（Person）的BeanInfo 对象  
				    BeanInfo beanInfo;
					beanInfo = Introspector.getBeanInfo(GoodsClassification.class, Object.class);
				    //获取所有的属性描述器  
				    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();  
				    for(PropertyDescriptor pd:pds){  
				    	String key = pd.getName();  
				        Method getter = pd.getReadMethod();
				        Object value = getter!=null ? getter.invoke(bean) : null; 
				        if(key.indexOf("Time") != -1  && value != null){
				        	value = CommUtils.dateToStrLong(value);  
				        }
				        map.put(key, value); 
				    }  
				    list.add(map);  
			    }
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return list;  
	}  
	
	/**
	 * @description 查看分类详细信息
	 * @method  getClassifyDetail
	 * @param @param request
	 * @param @param response
	 * @param @param classifyId 分类ID
	 * @return void
	 * @date: 2018年4月3日 下午1:52:35
	 * @author:liugui
	 */
	@RequestMapping(value="/getClassifyDetail.do")
	public void getClassifyDetail(HttpServletRequest request,HttpServletResponse response,String classifyId){
		try {		
			Map<String, String> paramsMap = new HashMap<String, String>();											
			paramsMap.put("classifyId", classifyId);
			//分类详细信息
			GoodsClassification  goodsClassification= goodsClassificationService.getGoodsClassifyDetail(paramsMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", goodsClassification);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 删除藏品分类信息
	 * @method  deleteClassifyByIds
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月10日 上午10:48:14
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteClassifyByIds.do")
	public void deleteClassifyByIds(HttpServletRequest request,HttpServletResponse response,String params){
		JSONObject paramJson = JSONObject.fromObject(params);
		try {		
			Map<String, String> paramsMap = new HashMap<String, String>();											
			paramsMap.put("classId",  paramJson.getString("classId"));
			paramsMap.put("imageIds",  paramJson.getString("imageIds"));
			paramsMap.put("classType",  paramJson.getString("classType"));
			goodsClassificationService.deleteClassifyByIds(paramsMap);
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "删除成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @description 校验交易藏品分类是否已存在
	 * @method  checkSameClassName
	 * @param @param request
	 * @param @param response
	 * @param @param parent_code
	 * @param @param class_id
	 * @param @param class_name
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月10日 下午3:54:08
	 * @author:liugui
	 */
	@RequestMapping(value="/checkSameTransClassName.do")
	@ResponseBody
	public Boolean checkSameTransClassName(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//result true:校验通过,false:校验不通
		JSONObject paramJson = JSONObject.fromObject(paramsStr);
		Map<String, Object> json = (Map<String, Object>) JSON.parse(paramsStr);
		Boolean result = false;
		try {		
			Map<String, Object> paramsMap = CommUtils.convertDecode(json);
			String className = URLDecoder.decode(paramJson.getString("class_name"), "utf-8");
			paramsMap.put("class_type", ONE);
			if(StringUtil.availableStr(className)){
				result = goodsClassificationService.checkSameClassName(paramsMap);
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @description 校验拍卖藏品分类是否已存在
	 * @method  checkSameClassName
	 * @param @param request
	 * @param @param response
	 * @param @param parent_code
	 * @param @param class_id
	 * @param @param class_name
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月10日 下午3:54:08
	 * @author:liugui
	 */
	@RequestMapping(value="/checkSameSaleClassName.do")
	@ResponseBody
	public Boolean checkSameSaleClassName(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//result true:校验通过,false:校验不通
		JSONObject paramJson = JSONObject.fromObject(paramsStr);
		Map<String, Object> json = (Map<String, Object>) JSON.parse(paramsStr);
		Boolean result = false;
		try {		
			Map<String, Object> paramsMap = CommUtils.convertDecode(json);
			String className = URLDecoder.decode(paramJson.getString("class_name"), "utf-8");
			paramsMap.put("class_type", TWO);
			if(StringUtil.availableStr(className)){
				result = goodsClassificationService.checkSameClassName(paramsMap);
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @description 保存交易分类信息
	 * @method  saveOrUpdateTransClass
	 * @param @param request
	 * @param @param response
	 * @param @param class_id
	 * @param @param class_code
	 * @param @param class_name
	 * @param @param parent_code
	 * @param @param sort
	 * @param @param describe
	 * @param @param pc_image_ids
	 * @param @param mobile_image_ids
	 * @return void
	 * @date: 2018年4月10日 下午4:04:24
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateTransClass.do")
	public void saveOrUpdateTransClass(HttpServletRequest request,HttpServletResponse response,
			String class_id,String class_code,String class_name,String parent_code,
			String sort,String describe,String image_ids){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			if(StringUtils.isNotEmpty(class_id)){
				operation = "编辑【交易分类配置】："+class_name;	
			}else{
				operation = "新增【交易分类配置】："+class_name;
			}
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("classId", class_id);
			paramsMap.put("classType", ONE);
			paramsMap.put("classCode", class_code);
			paramsMap.put("className", class_name);
			paramsMap.put("parentCode", parent_code);
			paramsMap.put("sort", Integer.parseInt(sort));
			paramsMap.put("describe", describe);
			paramsMap.put("imageIds", image_ids);
			goodsClassificationService.saveOrUpdateClass(paramsMap);			
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "保存成功");			
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
	 * @description 保存拍卖分类信息
	 * @method  saveOrUpdateTSaleClass
	 * @param @param request
	 * @param @param response
	 * @param @param class_id
	 * @param @param class_code
	 * @param @param class_name
	 * @param @param parent_code
	 * @param @param sort
	 * @param @param describe
	 * @param @param image_ids
	 * @return void
	 * @date: 2018年4月10日 下午4:10:02
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateSaleClass.do")
	public void saveOrUpdateSaleClass(HttpServletRequest request,HttpServletResponse response,
			String class_id,String class_code,String class_name,String parent_code,
			String sort,String describe,String image_ids){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			if(StringUtils.isNotEmpty(class_id)){
				operation = "编辑【拍卖分类配置】："+class_name;	
			}else{
				operation = "新增【拍卖分类配置】："+class_name;
			}
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("classId", class_id);
			paramsMap.put("classType", TWO);
			paramsMap.put("classCode", class_code);
			paramsMap.put("className", class_name);
			paramsMap.put("parentCode", parent_code);
			paramsMap.put("sort", Integer.parseInt(sort));
			paramsMap.put("describe", describe);
			paramsMap.put("imageIds", image_ids);
			goodsClassificationService.saveOrUpdateClass(paramsMap);			
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "保存成功");			
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
	 * @description 校验交易藏品分类CODE是否已存在
	 * @method  checkSameTransClassCode
	 * @param @param request
	 * @param @param response
	 * @param @param paramsStr
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月12日 下午4:15:59
	 * @author:liugui
	 */
	@RequestMapping(value="/checkSameTransClassCode.do")
	@ResponseBody
	public Boolean checkSameTransClassCode(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		try {		
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			Map<String, Object> json = (Map<String, Object>) JSON.parse(paramsStr);
			Map<String, Object> paramsMap = CommUtils.convertDecode(json);
			String classCode = URLDecoder.decode(paramJson.getString("class_code"), "utf-8");
			paramsMap.put("class_type", ONE);
			if(StringUtil.availableStr(classCode)){
				result = goodsClassificationService.checkSameClassCode(paramsMap);
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @description 校验拍卖藏品分类CODE是否已存在
	 * @method  checkSameSaleClassCode
	 * @param @param request
	 * @param @param response
	 * @param @param paramsStr
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月12日 下午4:17:10
	 * @author:liugui
	 */
	@RequestMapping(value="/checkSameSaleClassCode.do")
	@ResponseBody
	public Boolean checkSameSaleClassCode(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		try {		
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			Map<String, Object> json = (Map<String, Object>) JSON.parse(paramsStr);
			Map<String, Object> paramsMap = CommUtils.convertDecode(json);
			String classCode = URLDecoder.decode(paramJson.getString("class_code"), "utf-8");
			paramsMap.put("class_type", TWO);
			if(StringUtil.availableStr(classCode)){
				result = goodsClassificationService.checkSameClassCode(paramsMap);
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	/**
	 * @description 获取藏品大小类下拉框值
	 * @method  getGoodsClass
	 * @param @param request
	 * @param @param response
	 * @param @param classType
	 * @return void
	 * @date: 2018年4月17日 上午10:17:52
	 * @author:liugui
	 */
	@RequestMapping(value="/getGoodsClass.do")
	public void getGoodsClass(HttpServletRequest request,HttpServletResponse response,
			String class_type,String big_class_code,String isAll){
		try {		
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtil.availableStr(class_type)){
				Map<String, Object> paramsMap = new HashMap<String, Object>();											
				paramsMap.put("classType", class_type);
				paramsMap.put("bigClassCode", big_class_code);
				paramsMap.put("isAll", isAll);
				List<Map<String,Object>> resultList = goodsClassificationService.getGoodsClass(paramsMap);
				map.put("resultList", resultList);
				map.put("success", true);		
			}else{
				map.put("success", false);			
				map.put("resultMsg", "分类类型不能为空！");
			}
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description 删除图片
	 * @method  deleteImageByIds
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月18日 下午4:58:56
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteImageByIds.do")
	public void deleteImageByIds(HttpServletRequest request,HttpServletResponse response,String imageIds){
		try {		
			Map<String, String> paramsMap = new HashMap<String, String>();											
			paramsMap.put("imageIds", imageIds);
			goodsClassificationService.deleteImageByIds(paramsMap);
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "删除成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}