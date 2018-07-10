package cn.com.hxfz.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
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

import cn.com.hxfz.model.HomePageAdvertisement;
import cn.com.hxfz.service.GoodsClassificationService;
import cn.com.hxfz.service.HomePageAdvertisementService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
import cn.com.hxfz.util.StringUtils;

/**
 * 
 * @类功能说明：藏城首页广告表Controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月23日 上午9:45:18 
 * @版本：V1.0
 */
@Controller
@RequestMapping("/advertisement")
public class HomePageAdvertisementController {
	private HomePageAdvertisementService homePageAdvertisementService;
	private GoodsClassificationService goodsClassificationService;
	public HomePageAdvertisementService getHomePageAdvertisementService() {
		return homePageAdvertisementService;
	}
	@Autowired
	public void setHomePageAdvertisementService(
			HomePageAdvertisementService homePageAdvertisementService) {
		this.homePageAdvertisementService = homePageAdvertisementService;
	}
	
	public GoodsClassificationService getGoodsClassificationService() {
		return goodsClassificationService;
	}
	@Autowired
	public void setGoodsClassificationService(
			GoodsClassificationService goodsClassificationService) {
		this.goodsClassificationService = goodsClassificationService;
	}
	/**
	 * @description 广告查询
	 * @method  getAdvertisementRecords
	 * @param @param request
	 * @param @param response
	 * @param @param paramsStr
	 * @return void
	 * @date: 2018年4月23日 上午9:53:24
	 * @author:liugui
	 */
	@RequestMapping(value="/getAdvertisementRecords.do")
	public void getAdvertisementRecords(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {		
			operation = "查看【广告查询】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();		
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			paramsMap.put("offset", paramJson.getInt("pageIndex"));
			paramsMap.put("limit", paramJson.getInt("pageSize"));	
			paramsMap.put("homepageName", paramJson.getString("homepage_name"));//广告标题
			paramsMap.put("serviceType", paramJson.getString("service_type"));//关联广告类型
			paramsMap.put("status", paramJson.getString("service_status"));//关联广告状态
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<HomePageAdvertisement> resultList = homePageAdvertisementService.getAdvertisementRecords(params);
			List<Map<String, Object>> resultList1= bean2ListMap(resultList);
			int totalCount = homePageAdvertisementService.getAdvertisementCount(params);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultList", resultList1);
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
	 * @description JavaBean转换为Map  
	 * @method  bean2ListMap
	 * @param @param resultList
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月20日 下午2:55:40
	 * @author:liugui
	 */
	public static List<Map<String,Object>> bean2ListMap(List<HomePageAdvertisement> resultList){  
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
		    if (resultList != null && resultList.size() > 0) {
		    	HomePageAdvertisement bean = null;
			    Map<String,Object> map = null;
			    for (int i = 0,size = resultList.size(); i < size; i++) {  
			    	bean = resultList.get(i);  
			    	map = new HashMap<>(); 
				    //获取指定类（Person）的BeanInfo 对象  
				    BeanInfo beanInfo = Introspector.getBeanInfo(HomePageAdvertisement.class, Object.class);
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
	 * @description 保存广告信息（新增、编辑）
	 * @method  saveOrUpdateAdver
	 * @param @param request
	 * @param @param response
	 * @param @param paramsStr
	 * @return void
	 * @date: 2018年4月23日 上午10:21:52
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateAdver.do")
	public void saveOrUpdateAdver(HttpServletRequest request,HttpServletResponse response,String paramsStr){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			if(StringUtils.isNotEmpty(paramJson.getString("id"))){
				operation = "编辑【广告信息】："+paramJson.getString("homepageName");	
			}else{
				operation = "新增【广告信息】："+paramJson.getString("homepageName");
			}
			
			// 将map转为Goods对象
			HomePageAdvertisement adver = (HomePageAdvertisement)JSONObject.toBean(paramJson, HomePageAdvertisement.class);
			homePageAdvertisementService.saveOrUpdateAdver(adver);	
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
	 * @description 删除广告信息
	 * @method  deleteAdvertisementByIds
	 * @param @param request
	 * @param @param response
	 * @param @param advIds
	 * @return void
	 * @date: 2018年4月23日 上午10:35:55
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteAdvertisementByIds.do")
	public void deleteAdvertisementByIds(HttpServletRequest request,HttpServletResponse response,String advIds){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			operation = "删除【首页广告管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("advIds", advIds);
			homePageAdvertisementService.deleteAdvertisementByIds(paramsMap);		
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
	public void deleteImageByIds(HttpServletRequest request,HttpServletResponse response,String params){
		JSONObject paramJson = JSONObject.fromObject(params);
		try {		
			Map<String, String> paramsMap = new HashMap<String, String>();											
			paramsMap.put("imageIds",  paramJson.getString("imageIds"));
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
	
	
	@RequestMapping(value="/updateStatus.do")
	public void updateStatus(HttpServletRequest request,HttpServletResponse response,String advIds,String status){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			operation = "更新【广告管理】状态";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("advIds", advIds);
			paramsMap.put("status", status);
			homePageAdvertisementService.updateStatus(paramsMap);		
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "更新成功");			
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
