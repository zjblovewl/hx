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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.com.hxfz.model.Goods;
import cn.com.hxfz.service.GoodsClassificationService;
import cn.com.hxfz.service.GoodsService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.DateUtil;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
import cn.com.hxfz.util.StringUtils;
/**
 * 
 * @类功能说明：藏品类 Conroller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月12日 下午8:29:24 
 * @版本：V1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	static Logger logger = Logger.getLogger(GoodsController.class.getName());
	private GoodsService goodsService;
	private GoodsClassificationService goodsClassificationService;
	public GoodsService getGoodsService() {
		return goodsService;
	}
	@Autowired
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
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
	 * @description 交易帖查询
	 * @method  getTransGoodsRecords
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月13日 下午3:54:08
	 * @author:liugui
	 */
	@RequestMapping(value="/getTransGoodsRecords.do")
	public void getTransGoodsRecords(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
			//operation 操作，errorMsg异常信息
			String operation = "",errorMsg = "";
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			try {		
				operation = "查看【交易贴】";
				Map<String, Object> paramsMap = new HashMap<String, Object>();	
				paramsMap.put("offset", paramJson.getInt("pageIndex"));
				paramsMap.put("limit", paramJson.getInt("pageSize"));	
				paramsMap.put("goodsType", ONE);
				paramsMap.put("nick_name", paramJson.getString("nick_name"));
				paramsMap.put("goods_name", paramJson.getString("goods_name"));
				paramsMap.put("big_class_code",paramJson.getString("big_class_code"));
				paramsMap.put("small_class_code",paramJson.getString("small_class_code"));
				paramsMap.put("status",paramJson.getString("status"));
				paramsMap.put("first_step_start_time", paramJson.getString("first_step_start_time"));
				paramsMap.put("first_step_end_time", paramJson.getString("first_step_end_time"));
				Map<String, Object> params = CommUtils.convertDecode(paramsMap);
				List<Goods> resultList = goodsService.getGoodsRecords(params);
				List<Map<String, Object>> resultList1= bean2ListMap(resultList);
				logger.info(":::::"+resultList1);
				int totalCount = goodsService.getGoodsCount(params);
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
	 * 
	 * @description  拍卖贴查询
	 * @method  getSaleClassifiyRecords
	 * @param @param request
	 * @param @param response
	 * @param @param pageSize
	 * @param @param pageIndex
	 * @param @param className
	 * @return void
	 * @date: 2018年4月13日 下午4:48:10
	 * @author:liugui
	 */
	@RequestMapping(value="/getSaleGoodsRecords.do")
	public void getSaleClassifiyRecords(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		JSONObject paramJson = JSONObject.fromObject(paramsStr);
		try {		
			operation = "查看【拍卖贴】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("goodsType", TWO);
			paramsMap.put("nick_name", paramJson.getString("nick_name"));
			paramsMap.put("goods_name", paramJson.getString("goods_name"));
			paramsMap.put("big_class_code",paramJson.getString("big_class_code"));
			paramsMap.put("small_class_code",paramJson.getString("small_class_code"));
			paramsMap.put("status",paramJson.getString("status"));
			paramsMap.put("first_step_start_time", paramJson.getString("first_step_start_time"));
			paramsMap.put("first_step_end_time", paramJson.getString("first_step_end_time"));
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<Goods> resultList = goodsService.getGoodsRecords(params);
			List<Map<String, Object>> resultList1= bean2ListMap(resultList);
			int totalCount = goodsService.getGoodsCount(paramsMap);
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
	public static List<Map<String,Object>> bean2ListMap(List<Goods> resultList){  
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
		    if (resultList != null && resultList.size() > 0) {
		    	Goods bean = null;
			    Map<String,Object> map = null;
			    for (int i = 0,size = resultList.size(); i < size; i++) {  
			    	bean = resultList.get(i);  
			    	map = new HashMap<>(); 
				    //获取指定类（Person）的BeanInfo 对象  
				    BeanInfo beanInfo = Introspector.getBeanInfo(Goods.class, Object.class);
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
	 * @description 查看交易帖、拍卖贴详细
	 * @method  getGoodsDetail
	 * @param @param request
	 * @param @param response
	 * @param @param classifyId
	 * @return void
	 * @date: 2018年4月13日 下午4:49:08
	 * @author:liugui
	 */
	@RequestMapping(value="/getGoodsDetail.do")
	public void getClassifyDetail(HttpServletRequest request,HttpServletResponse response,String goodsId){
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("goodsId", goodsId);
			// 详细信息
			Goods  goods= goodsService.getGoodsDetail(paramsMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", goods);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 删除藏品贴信息
	 * @method  deleteGoodsByIds
	 * @param @param request
	 * @param @param response
	 * @param @param classId
	 * @return void
	 * @date: 2018年4月13日 下午5:03:13
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteGoodsByIds.do")
	public void deleteGoodsByIds(HttpServletRequest request,HttpServletResponse response,String goodsIds){
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("goodsIds", goodsIds);
			goodsService.deleteGoodsByIds(paramsMap);
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
	 * @description 保存交易贴信息
	 * @method  saveOrUpdateTransGoods
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月13日 下午5:05:54
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateTransGoods.do")
	public void saveOrUpdateTransGoods(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		JSONObject paramJson = JSONObject.fromObject(paramsStr);
		try {
			if(StringUtils.isNotEmpty(paramJson.getString("goodsId"))){
				operation = "编辑【交易帖配置】："+paramJson.getString("goodsName");	
			}else{
				operation = "新增【交易帖配置】："+paramJson.getString("goodsName");
			}
			// 将map转为Goods对象
			Goods goods = (Goods)JSONObject.toBean(paramJson, Goods.class);
			goods.setGoodsType(ONE);
			goodsService.saveOrUpdateGoods(goods);		
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
	 * @description 保存拍卖贴信息
	 * @method  saveOrUpdateSaleGoods
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月13日 下午5:36:35
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateSaleGoods.do")
	public void saveOrUpdateSaleGoods(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		logger.info("新增拍卖帖数据："+paramsStr);
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		JSONObject paramJson = JSONObject.fromObject(paramsStr);
		try {
			if(StringUtils.isNotEmpty(paramJson.getString("goodsId"))){
				operation = "编辑【交易帖配置】："+paramJson.getString("goodsName");	
			}else{
				operation = "新增【交易帖配置】："+paramJson.getString("goodsName");
			}
			  
			paramJson.put("endTime", DateUtil.format(paramJson.get("endTime").toString(),"yyyy-MM-dd HH:mm"));  
			// 将map转为Goods对象
			Goods goods = (Goods)JSONObject.toBean(paramJson, Goods.class);
			goods.setGoodsType(TWO);
			goodsService.saveOrUpdateGoods(goods);		
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
	 * @description 获取藏品评论列表
	 * @method  getGoodsComments
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月16日 下午1:49:55
	 * @author:liugui
	 */
	@RequestMapping(value="/getGoodsComments.do")
	public void getGoodsComments(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			operation = "查看【拍卖贴评论】";
			JSONObject paramJson = JSONObject.fromObject(paramsStr);
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("goodsId", paramJson.getString("id"));
			List<Map<String, Object>> resultList = goodsService.getGoodsComment(paramsMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultList", resultList);
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
	 * @description 更改藏品状态
	 * @method  updateGoodsStatus
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月16日 下午1:55:46
	 * @author:liugui
	 */
	@RequestMapping(value="/updateGoodsStatusByIds.do")
	public void updateGoodsStatusByIds(HttpServletRequest request,HttpServletResponse response,
			String id,String status){
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("goodsId", id);
			paramsMap.put("status", status);
			goodsService.updateGoodsStatusByIds(paramsMap);
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "更改藏品状态成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 删除评论
	 * @method  deleteGoodsCommentByIds
	 * @param @param request
	 * @param @param response
	 * @param @param id
	 * @return void
	 * @date: 2018年4月16日 下午4:52:37
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteGoodsCommentByIds.do")
	public void deleteGoodsCommentByIds(HttpServletRequest request,HttpServletResponse response,String ids){
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("commentIds", ids);
			goodsService.deleteGoodsCommentByIds(paramsMap);
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
	 * @description 获取拍卖结拍时间 
	 * @method  getAuctionSession
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @date: 2018年4月19日 上午10:31:03
	 * @author:liugui
	 */
	@RequestMapping(value="/getAuctionSession.do")
	public void getAuctionSession(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> resultList = goodsService.getAuctionSession();
			JSONObject jsonObj = JSONObject.fromObject(resultList);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 保存拍卖结拍时间 
	 * @method  saveAuctionSession
	 * @param @param request
	 * @param @param response
	 * @param @param params
	 * @return void
	 * @date: 2018年4月19日 上午10:40:10
	 * @author:liugui
	 */
	@RequestMapping(value="/saveAuctionSession.do")
	public void saveAuctionSession(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		JSONObject paramJson = JSONObject.fromObject(paramsStr);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("auctionSessions", paramJson.getString("auction_sessions"));
			paramsMap.put("days", paramJson.getInt("days"));
			goodsService.saveAuctionSession(paramsMap);		
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
	 * @description 获取藏品出价记录
	 * @method  getGoodsOfferRecord
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @date: 2018年4月20日 下午4:10:47
	 * @author:liugui
	 */
	@RequestMapping(value="/getGoodsOfferRecord.do")
	public void getGoodsOfferRecord(HttpServletRequest request,HttpServletResponse response,String goodsId){
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("goodsId", goodsId);
			List<Map<String, Object>> resultList = goodsService.getGoodsOfferRecord(paramsMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultList", resultList);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description 删除藏品出价记录
	 * @method  deleteGoodsOfferRecordByIds
	 * @param @param request
	 * @param @param response
	 * @param @param ids
	 * @return void
	 * @date: 2018年4月20日 下午4:10:32
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteGoodsOfferRecordByIds.do")
	public void deleteGoodsOfferRecordByIds(HttpServletRequest request,HttpServletResponse response,String ids){
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("offerIds", ids);
			goodsService.deleteGoodsOfferRecordByIds(paramsMap);
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
	 * @description 查询省市信息
	 * @method  queryAreaInfo
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @date: 2018年5月3日 下午2:28:58
	 * @author:liugui
	 */
	@RequestMapping(value="/queryAreaInfo.do")
	public void queryAreaInfo(HttpServletRequest request,HttpServletResponse response
			){
		try {		
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String,Object>> resultList = goodsService.queryAreaInfo();
			map.put("resultList", resultList);
			map.put("success", true);		
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
