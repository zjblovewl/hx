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

import cn.com.hxfz.service.TradingFlowService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.ExcelModelNew;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;

/**  
 * @类功能说明：交易流水控制类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月16日 上午10:59:24 
 * @版本：V1.0  
 */
@Controller
@RequestMapping("/tradingFlow")
public class TradingFlowController {
	static Logger logger = Logger.getLogger(TradingFlowController.class.getName());
	
	@Autowired
	private TradingFlowService tradingFlowService;
	
	
	/**
	 * 
	 * @description： 查询交易流水渠道
	 * @method:  getTradingFlowList
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param pageSize
	 * @param:   @param pageIndex
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @param id
	 * @param:   @param userId
	 * @param:   @param nickName
	 * @param:   @param thirdPrecode
	 * @param:   @param thirdFlowcode
	 * @param:   @param channelFlowcode
	 * @param:   @param orderId
	 * @param:   @param orderCode
	 * @param:   @param transactionType
	 * @param:   @param payType
	 * @param:   @param transactionPrice
	 * @param:   @param transactionState
	 * @param:   @param createTime
	 * @return:  void
	 * @date:    2018年6月21日 上午10:31:42 by qiangxuan
	 */
	@RequestMapping(value="/getTradingFlowData.do")
	public void getTradingFlowList(HttpServletRequest request, HttpServletResponse response,
			Integer pageSize, Integer pageIndex, String startTime, String endTime, String id, String userId, 
			String nickName, String thirdPrecode, String thirdFlowcode,String channelFlowcode, String orderId, String orderCode, String transactionType, 
			String payType,Integer transactionPrice, String transactionState, Date createTime){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			 operation = "查看【交易流水】";
			 param.put("limit", pageSize);
			 param.put("offset", pageIndex);
			 param.put("id", id);
			 param.put("thirdFlowcode",thirdFlowcode);
			 param.put("thirdPrecode",thirdPrecode);
			 param.put("channelFlowcode",channelFlowcode);
			 param.put("payType",payType);
			 param.put("transactionPrice",transactionPrice);
			
			 /*
			  *  根据交易流水创建开始时间、结束时间进行时间段查询
              */
             if (startTime != null && !startTime.equals("")){
                 logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
            	 param.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
             }
             if (endTime != null && !endTime.equals("")){
                 logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
            	 param.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
             }
			 if(nickName!=null && !nickName.equals("")){
				 param.put("nickName", URLDecoder.decode(nickName, "UTF-8"));
			 }
			 if(orderCode!=null && !orderCode.equals("")){
				 param.put("orderCode", URLDecoder.decode(orderCode, "UTF-8"));
			 }
			 if(transactionType!=null && !transactionType.equals("")){
				 param.put("transactionType", URLDecoder.decode(transactionType, "UTF-8"));
			 }
			 if(transactionState!=null && !transactionState.equals("")){
				 param.put("transactionState", URLDecoder.decode(transactionState, "UTF-8"));
			 }			 
			 param.put("createTime",createTime);
			 List<Map<String, Object>> resultList = tradingFlowService.getTradingFlowList(param);
			 int total = tradingFlowService.getTradingFlowListCount(param);
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
	 * 
	 * @description： 导出交易流水记录至excel
	 * @method:  exportTradeFlowGata
	 * @param:   @param request
	 * @param:   @param transactionType
	 * @param:   @param payType
	 * @param:   @param transactionPrice
	 * @param:   @param transactionState
	 * @param:   @param createTime
	 * @param:   @return
	 * @return:  String
	 * @date:    2018年5月25日 下午2:27:15 by qiangxuan
	 */
	@RequestMapping(value="/exportTradeFlowData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportTradeFlowGata(HttpServletRequest request,HttpServletResponse response,String startTime, String endTime,String id, 
			String nickName, String thirdPrecode, String thirdFlowcode,String channelFlowcode, String orderId, String orderCode, String transactionType, 
			String payType,Integer transactionPrice, String transactionState, Date createTime){
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("id", id);
			paramsMap.put("thirdPrecode", thirdPrecode);
			paramsMap.put("thirdFlowcode", thirdFlowcode);
			paramsMap.put("channelFlowcode", channelFlowcode);
			paramsMap.put("payType", payType);
			paramsMap.put("transactionPrice", transactionPrice);
			paramsMap.put("createTime", createTime);
			 /*
			  *  根据交易流水创建开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
            if (nickName != null && !nickName.equals("")) {
            	paramsMap.put("nickName", URLDecoder.decode(nickName,"UTF-8"));
            }
            if (orderCode != null && !orderCode.equals("")) {
            	paramsMap.put("orderCode", URLDecoder.decode(orderCode,"UTF-8"));
            }
            if (transactionType != null && !transactionType.equals("")) {
            	paramsMap.put("transactionType", URLDecoder.decode(transactionType,"UTF-8"));
            }
            if (transactionState != null && !transactionState.equals("")) {
            	paramsMap.put("transactionState", URLDecoder.decode(transactionState,"UTF-8"));
            }
			
			
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			logger.info("获取到的参数："+params);
			List<Map<String, Object>> resultList = tradingFlowService.getExportTradeFlowList(params);
			logger.info("获取需要导出的交易流水数据："+resultList);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING			
			};
			String[] headArr = new String[] {"交易流水","用户昵称","第三方流水号","第三方预付码","渠道流水号","订单号","交易类型","支付方式","交易金额","交易状态","创建时间"};
			String[] nameArr = new String[] {"collector","nick_name","third_flowcode","third_precode","channel_flowcode","order_code","transaction_type","pay_type","transaction_price","transaction_state","create_time"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("所有交易流水数据");
			try {										
				String fileName = "所有交易流水数据.xls";
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
