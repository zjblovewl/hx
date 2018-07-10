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

import cn.com.hxfz.service.CommissionRecordService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.ExcelModelNew;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;



/**  
 * @类功能说明：佣金记录控制类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月25日 上午10:40:52 
 * @版本：V1.0  
 */
@Controller
@RequestMapping("/commissionRecord")
public class CommissionRecordController {
	
static Logger logger = Logger.getLogger(CommissionRecordController.class.getName());
	
	@Autowired
	private CommissionRecordService commissionRecordService;
	
	/**
	 * 
	 * @description： 获取佣金记录数据
	 * @method:  getCommissionRecordList
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param pageSize
	 * @param:   @param pageIndex
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @param id
	 * @param:   @param nickName
	 * @param:   @param withdrawalsAmount
	 * @param:   @param bankNo
	 * @param:   @param bankCode
	 * @param:   @param realName
	 * @param:   @param commissionRate
	 * @param:   @param commissionAmount
	 * @param:   @param createTime
	 * @return:  void
	 * @date:    2018年5月25日 下午2:25:34 by qiangxuan
	 */
	@RequestMapping(value="/getCommissionData.do")
	public void getCommissionRecordList(HttpServletRequest request, HttpServletResponse response,
			Integer pageSize, Integer pageIndex, String startTime, String endTime, String id,  
			String nickName, Integer withdrawalsAmount, String bankNo, String bankName,String branchName,
			String realName, Integer commissionRate, Integer commissionAmount, Date createTime){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			 operation = "查看【佣金记录】";
			 param.put("limit", pageSize);
			 param.put("offset", pageIndex);
			 param.put("id", id);
			 param.put("withdrawalsAmount",withdrawalsAmount);
			 param.put("bankNo",bankNo);
			 param.put("bankName",bankName);
			 param.put("branchName",branchName);
			 param.put("realName",realName);
			 param.put("commissionRate",commissionRate);
			 param.put("commissionAmount",commissionAmount);
			 param.put("createTime",createTime);
			 /*
			  *  根据佣金记录创建开始时间、结束时间进行时间段查询
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
			 List<Map<String, Object>> resultList = commissionRecordService.getCommissionList(param);
			 int total = commissionRecordService.getCommissionListCount(param);
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
	 * @description： 导出佣金记录至excel
	 * @method:  exportCommissionGata
	 * @param:   @param request
	 * @param:   @param response
	 * @param:   @param startTime
	 * @param:   @param endTime
	 * @param:   @param nickName
	 * @param:   @param withdrawalsAmount
	 * @param:   @param bankNo
	 * @param:   @param bankCode
	 * @param:   @param realName
	 * @param:   @param commissionRate
	 * @param:   @param commissionAmount
	 * @param:   @param createTime
	 * @param:   @return
	 * @return:  String
	 * @date:    2018年5月25日 下午2:39:46 by qiangxuan
	 */
	@RequestMapping(value="/exportCommissionData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportCommissionGata(HttpServletRequest request, HttpServletResponse response,
			String startTime, String endTime, String nickName, Integer withdrawalsAmount, 
			String bankNo, String bankName,String branchName, String realName, Integer commissionRate, 
			Integer commissionAmount, Date createTime){
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("withdrawalsAmount", withdrawalsAmount);
			paramsMap.put("bankNo", bankNo);
			paramsMap.put("bankName", bankName);
			paramsMap.put("branchName", branchName);
			paramsMap.put("realName", realName);
			paramsMap.put("commissionRate", commissionRate);
			paramsMap.put("commissionAmount", commissionAmount);
			paramsMap.put("createTime", createTime);
			/*
			 *  根据佣金记录创建开始时间、结束时间进行时间段查询
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
			
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			logger.info("获取到的参数："+params);
			List<Map<String, Object>> resultList = commissionRecordService.getExportCommissionList(params);
			logger.info("获取需要导出的佣金记录数据："+resultList);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING		
			};
			String[] headArr = new String[] {"佣金记录","用户昵称","提现金额","真实姓名","卡号","开户银行","支行名称","佣金金额","佣金比例","创建时间"};
			String[] nameArr = new String[] {"collector","nick_name","withdrawals_amount","real_name","bank_no","bank_name","branch_name","commission_amount","commission_rate","create_time"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("佣金记录");
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
	
	/**
	 * 
	 * @description： 保存佣金比例配置信息
	 * @method:  saveCRConfig
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月28日 下午2:35:55 by qiangxuan
	 */
	@RequestMapping(value = "/saveCommissionRate.do",method = { RequestMethod.POST})
	@ResponseBody
	public void saveCRConfig(HttpServletRequest request, HttpServletResponse response){
		
		String data = request.getParameter("data");
		String operation = "",errorMsg = "";
		try {
			Map<String, Object> paramsMap = (Map)JSON.parse(data);
		
			commissionRecordService.saveCommissionConfig(paramsMap);
				
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
	
	
	@RequestMapping(value="/getCRInfo.do")
	public void getCommissionRecordList(HttpServletRequest request, HttpServletResponse response,
			String id, Double commissionRate){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			 operation = "查看【佣金比例】";
			 param.put("id", id);
			 param.put("commissionRate",commissionRate);
			 List<Map<String, Object>> resultList = commissionRecordService.getCRList(param);
			 Map<String, Object> resultMap = new HashMap<String, Object>();
			 resultMap.put("rows", resultList);
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
}
