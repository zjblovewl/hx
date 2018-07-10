package cn.com.hxfz.controller;

import java.io.IOException;
import java.net.URLDecoder;
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

import com.alibaba.fastjson.JSONArray;

import cn.com.hxfz.service.ChartService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.ExcelModelNew;
import cn.com.hxfz.util.ServletHelp;

/**  
 * @类功能说明：报表管理控制类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月10日 上午11:26:21 
 * @版本：V1.0  
 */

@Controller
@RequestMapping(value = "/chartTrend")
public class ChartController {
	static Logger logger = Logger.getLogger(ChartController.class.getName());
	
	@Autowired
	private ChartService chartService;
	/**
	 * 
	 * @description： 用户注册走势折线图
	 * @method:  queryUserRegSumTrend
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @return:  void
	 * @date:    2018年5月10日 上午11:27:59 by qiangxuan
	 */
	@RequestMapping(value = "/queryUserRegThrend.do",method = { RequestMethod.POST, RequestMethod.GET})    
	public void queryUserRegSumTrend(HttpServletRequest request, HttpServletResponse response, String startTime, String endTime){
		
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			 /**
             *  根据注册开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
            //查询用户注册数数据
			List<Map<String, Object>> resultList =  chartService.getUserRegSumList(paramsMap);
			
			logger.info("用户注册信息："+ resultList);
			Map<String, Object> resultMap = new HashMap<String, Object>();	
			resultMap.put("rows", JSONArray.toJSONString(resultList));
        	JSONObject jsonObj = JSONObject.fromObject(resultMap);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @description： 导出用户注册数据至excel
	 * @method:  exportUserTrendData
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @return
	 * @return:  String
	 * @date:    2018年5月10日 上午9:58:38 by qiangxuan
	 */
	@RequestMapping(value="/queryUserRegData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportUserTrendData(HttpServletRequest request,HttpServletResponse response, String startTime, String endTime){
		
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
            /**
             *  根据注册开始时间、结束时间进行时间段查询
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
			List<Map<String, Object>> resultList = chartService.getUserRegSumList(params);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING			
			};
			String[] headArr = new String[] {"用户注册趋势数据","注册时间","注册总数"};
			String[] nameArr = new String[] {"collector","registertime","sums"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("用户注册信息数据");
			try {										
				String fileName = "用户注册趋势数据.xls";
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
	
	
	
	
	/**
	 * 
	 * @description： 查询订单走势
	 * @method:  queryOrderSumTrend
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @throws ParseException
	 * @return:  void
	 * @date:    2018年5月11日 上午11:23:27 by qiangxuan
	 */
	@RequestMapping(value = "/queryOrderThrend.do",method = { RequestMethod.POST, RequestMethod.GET})    
	public void queryOrderSumTrend(HttpServletRequest request, HttpServletResponse response, String startTime, String endTime) {
		
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			 /**
             *  根据注册开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
            //查询订单走势总数数据
			List<Map<String, Object>> resultlist =  chartService.getOrderSumList(paramsMap);
			logger.info("订单数据："+ resultlist);
			Map<String, Object> resultMap = new HashMap<String, Object>();	
			resultMap.put("rows", JSONArray.toJSONString(resultlist));
			JSONObject jsonObj = JSONObject.fromObject(resultMap);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * @description： 导出订单走势数据至excel
	 * @method:  exportOrderList
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @return
	 * @return:  String
	 * @date:    2018年5月11日 上午11:29:32 by qiangxuan
	 */
	@RequestMapping(value="/queryOrderData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportOrderList(HttpServletRequest request,HttpServletResponse response, String startTime, String endTime){
		
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
            /**
             *  根据开始时间、结束时间进行时间段查询
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
			List<Map<String, Object>> resultList = chartService.getOrderSumList(paramsMap);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,			
			};
			String[] headArr = new String[] {"订单走势报表","交易订单总数","拍卖订单总数","时间",};
			String[] nameArr = new String[] {"collector","transactioncount","salecount","daytime"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("订单走势信息数据");
			try {										
				String fileName = "订单走势趋势数据.xls";
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
	
	/**
	 * 
	 * @description： 帖子增加走势
	 * @method:  queryCardAddTrend
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @return:  void
	 * @date:    2018年5月14日 上午10:19:30 by qiangxuan
	 */
	@RequestMapping(value = "/queryCardThrend.do",method = { RequestMethod.POST, RequestMethod.GET})    
	public void queryCardAddTrend(HttpServletRequest request, HttpServletResponse response, String startTime, String endTime) {
		
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			 /**
             *  根据开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
            //查询帖子增加走势数据
			List<Map<String, Object>> resultlist =  chartService.getCardAddList(paramsMap);
			logger.info("帖子数据："+ resultlist);
			Map<String, Object> resultMap = new HashMap<String, Object>();	
			resultMap.put("rows", JSONArray.toJSONString(resultlist));
			JSONObject jsonObj = JSONObject.fromObject(resultMap);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description： 导出帖子增加走势至excel
	 * @method:  exportCardList
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @return
	 * @return:  String
	 * @date:    2018年5月14日 上午10:20:00 by qiangxuan
	 */
	@RequestMapping(value="/queryCardData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportCardList(HttpServletRequest request,HttpServletResponse response, String startTime, String endTime){
		
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
            /**
             *  根据开始时间、结束时间进行时间段查询
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
			List<Map<String, Object>> resultList = chartService.getCardAddList(paramsMap);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,			
			};
			String[] headArr = new String[] {"帖子增长报表","发帖总数","交易帖总数","拍卖帖总数","时间",};
			String[] nameArr = new String[] {"collector","postcount","transactioncount","salecount","daytime"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("帖子增长走势信息数据");
			try {										
				String fileName = "帖子增长走势数据.xls";
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
	
	/**
	 * 
	 * @description： 交易帖、拍卖帖评论增长走势
	 * @method:  queryCommentAddTrend
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @return:  void
	 * @date:    2018年5月14日 上午10:58:11 by qiangxuan
	 */
	@RequestMapping(value = "/queryCommentThrend.do",method = { RequestMethod.POST, RequestMethod.GET})    
	public void queryCommentAddTrend(HttpServletRequest request, HttpServletResponse response, String startTime, String endTime) {
		
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			 /**
             *  根据开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
            //查询评论增加走势数据
			List<Map<String, Object>> resultlist =  chartService.getCommentAddList(paramsMap);
			logger.info("评论增长数据："+ resultlist);
			Map<String, Object> resultMap = new HashMap<String, Object>();	
			resultMap.put("rows", JSONArray.toJSONString(resultlist));
			JSONObject jsonObj = JSONObject.fromObject(resultMap);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @description： 导出评论增长走势数据至excel
	 * @method:  exportCommentList
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @return
	 * @return:  String
	 * @date:    2018年5月14日 上午10:58:48 by qiangxuan
	 */
	@RequestMapping(value="/queryCommentData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportCommentList(HttpServletRequest request,HttpServletResponse response, String startTime, String endTime){
		
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
            /**
             *  根据开始时间、结束时间进行时间段查询
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
			logger.info("获取前台请求的时间参数："+params);
			//查询交易帖、拍卖帖、评论增长走势数据
			List<Map<String, Object>> resultList = chartService.getCommentAddList(paramsMap);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,			
			};
			String[] headArr = new String[] {"评论增长报表","评论总数","交易帖评论总数","拍卖帖评论总数","评论时间",};
			String[] nameArr = new String[] {"collector","commentcount","transactioncount","salecount","commenttime"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("评论增长走势信息数据");
			try {										
				String fileName = "评论增长走势数据.xls";
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
