package cn.com.hxfz.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.hxfz.model.Order;
import cn.com.hxfz.service.OrderService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.ExcelModelNew;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
/**
 * 
 * @类功能说明：订单管理控制类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年5月8日 下午4:00:19 
 * @版本：V1.0
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	
	static Logger logger = Logger.getLogger(OrderController.class.getName());
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @description 获取订单列表
	 * @method  getOrderRecords
	 * @param @param request
	 * @param @param response
	 * @param @param paramsStr
	 * @return void
	 * @date: 2018年5月21日 下午2:02:49
	 * @author:liugui
	 */
	@RequestMapping(value="/getOrderRecords.do")
	public void getOrderRecords(HttpServletRequest request, HttpServletResponse response,
			Integer pageSize, Integer pageIndex, String startTime, String endTime, 
			String id, String orderCode, String cancelReason, String buyerName, String sellerName, 
			String orderType, Integer postage, Integer payMoney,Integer price, 
			Integer amount, String payMethod, String consignee, String contactInformation,
			String logisticsCompany, String logisticsCode, String refundReason,
			String receiveAddress, String orderStatus, Date orderGenerationTime,Date cancelTime, Date orderFinishTime,
			Date applyRefundTime, Date confirmRefundTime, String remark, String deFlag){
			//operation 操作，errorMsg异常信息
				String operation = "",errorMsg = "";
				Map<String, Object> param = new HashMap<String, Object>();
				try {
					 operation = "查看【交易订单】";
					 param.put("limit", pageSize);
					 param.put("offset", pageIndex);
					 param.put("id", id);
					 param.put("postage",postage);
					 param.put("payMoney",payMoney);
					 param.put("price",price);
					 param.put("amount",amount);
					 param.put("payMethod",payMethod);
					 param.put("consignee",consignee);
					 param.put("contactInformation",contactInformation); 
					 param.put("logisticsCompany",logisticsCompany);
					 param.put("logisticsCode",logisticsCode);
					 param.put("refundReason",refundReason);
					 param.put("cancelReason",cancelReason);
					 param.put("cancelTime",cancelTime);
					 param.put("receiveAddress",receiveAddress);
					 param.put("orderGenerationTime",orderGenerationTime); 
					 param.put("orderFinishTime",orderFinishTime);
					 param.put("applyRefundTime",applyRefundTime);
					 param.put("confirmRefundTime",confirmRefundTime);
					 param.put("remark",remark);
					 param.put("deFlag",deFlag);

			
		             //根据买家名称模糊搜索 
					 if(buyerName!=null && !buyerName.equals("")){
						 param.put("buyerName", URLDecoder.decode(buyerName, "UTF-8"));
					 }
		             //根据卖家名称模糊搜索 
					 if(sellerName!=null && !sellerName.equals("")){
						 param.put("sellerName", URLDecoder.decode(sellerName, "UTF-8"));
					 }
					 //根据订单号模糊搜索 
					 if(orderCode!=null && !orderCode.equals("")){
						 param.put("orderCode", URLDecoder.decode(orderCode, "UTF-8"));
					 }
					 //根据订单类型模糊搜索 1：普通订单 2：拍卖订单
					 if(orderType!=null && !orderType.equals("")){
						 param.put("orderType", URLDecoder.decode(orderType, "UTF-8"));
					 }
					 // 根据订单状态模糊搜索  1：待付款 2：待发货 3：待收货 4：待评论 5：已完成 6：待退款 7：已退款 8：取消订单
					 if(orderStatus!=null && !orderStatus.equals("")){
						 param.put("orderStatus", URLDecoder.decode(orderStatus, "UTF-8"));
					 }			 
					 /*
					  *  根据交易订单生成开始时间、结束时间进行时间段查询
		              */
		             if (startTime != null && !startTime.equals("")){
		                 logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
		            	 param.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
		             }
		             if (endTime != null && !endTime.equals("")){
		                 logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
		            	 param.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
		             }
					 
					 List<Map<String, Object>> resultList = orderService.getOrderRecords(param);
					 logger.info("订单所有信息："+resultList);
					 int total = orderService.getOrderCount(param);
					 Map<String, Object> resultMap = new HashMap<String, Object>();
					 resultMap.put("rows", resultList);
					 resultMap.put("total", total);
					 JSONObject jsonObj = JSONObject.fromObject(resultMap);
					 logger.info(jsonObj);
					 ServletHelp.outRequestForJson(request, response, jsonObj.toString());					 
				} catch (IOException e) {
					e.getMessage();
					e.printStackTrace();
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
	public static List<Map<String,Object>> bean2ListMap(List<Order> resultList){  
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
		    if (resultList != null && resultList.size() > 0) {
		    	Order bean = null;
			    Map<String,Object> map = null;
			    for (int i = 0,size = resultList.size(); i < size; i++) {  
			    	bean = resultList.get(i);  
			    	map = new HashMap<>(); 
				    //获取指定类（Person）的BeanInfo 对象  
				    BeanInfo beanInfo = Introspector.getBeanInfo(Order.class, Object.class);
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
	@RequestMapping(value="/saveOrUpdateOrder.do")
	public void saveOrUpdateSaleOrder(HttpServletRequest request,HttpServletResponse response,
			String paramsStr){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			Map<String, Object> paramsMap = (Map)JSON.parse(paramsStr);
			logger.info("收货地址："+paramsMap.get("receiveAddress"));
			operation = "编辑【订单】";	
			orderService.saveOrder(paramsMap);		
			
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
	
	
	@RequestMapping(value="/exportOrderRecord.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportOrderData(HttpServletRequest request,HttpServletResponse response,
			String buyerName, String sellerName, String orderCode, String orderType, String orderStatus,
			String startTime, String endTime){
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			String buyerNames = request.getParameter("buyerName");			//出参
			logger.info("前台传入的参数："+ buyerNames);
			 //根据买家名称模糊搜索 
			 if(buyerName!=null && !buyerName.equals("")){
				 paramsMap.put("buyerName", URLDecoder.decode(buyerName, "UTF-8"));
			 }
            //根据卖家名称模糊搜索 
			 if(sellerName!=null && !sellerName.equals("")){
				 paramsMap.put("sellerName", URLDecoder.decode(sellerName, "UTF-8"));
			 }
			 //根据订单号模糊搜索 
			 if(orderCode!=null && !orderCode.equals("")){
				 paramsMap.put("orderCode", URLDecoder.decode(orderCode, "UTF-8"));
			 }
			 //根据订单类型模糊搜索 1：普通订单 2：拍卖订单
			 if(orderType!=null && !orderType.equals("")){
				 paramsMap.put("orderType", URLDecoder.decode(orderType, "UTF-8"));
			 }
			 // 根据订单状态模糊搜索  1：待付款 2：待发货 3：待收货 4：待评论 5：已完成 6：待退款 7：已退款 8：取消订单
			 if(orderStatus!=null && !orderStatus.equals("")){
				 paramsMap.put("orderStatus", URLDecoder.decode(orderStatus, "UTF-8"));
			 }			 
			 /*
			  *  根据交易订单生成开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
	
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			logger.info("获取到的参数："+params);
			List<Map<String, Object>> resultList = orderService.getExportOrderList(params);
			
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,	
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING	
			};
			String[] headArr = new String[] {"订单","订单号","买家用户名","卖家用户名","订单类型","单价","数量","邮费","实付价格","支付方式","收货地址","收货人","联系方式","物流公司","物流单号","退款原因","订单生成时间","订单完成时间","申请退款时间","确认退款时间","订单取消原因","订单取消时间","买家备注","订单状态"};
			String[] nameArr = new String[] {"order","order_code","buyer_name","seller_name","order_type","price","amount","postage","pay_money","pay_method","receive_address","consignee","contact_information","logistics_company","logistics_code","refund_reason","order_generation_time","order_finish_time","apply_refund_time","confirm_refund_time","cancel_reason","cancel_time","remark","order_status"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("订单信息数据");
			try {										
				String fileName = "订单报表.xls";
				excelModel.exportExcel(fileName, response, request);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
