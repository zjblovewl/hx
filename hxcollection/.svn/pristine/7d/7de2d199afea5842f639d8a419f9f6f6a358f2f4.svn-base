package cn.com.hxfz.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import cn.com.hxfz.service.WithdrawalsRecordService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.ExcelModelNew;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;

/**  
 * @类功能说明：提现管理控制类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月21日 下午2:34:40 
 * @版本：V1.0  
 */
@Controller
@RequestMapping(value = "/withdrawalsRecord")
public class WithdrawalsRecordController {
	
	static Logger logger = Logger.getLogger(WithdrawalsRecordController.class.getName());
	@Autowired
	private  WithdrawalsRecordService withdrawalsRecordService;
	
	
	@RequestMapping(value="/getWRData.do")
	public void getWRList(HttpServletRequest request, HttpServletResponse response,
			Integer pageSize, Integer pageIndex, String startTime, String endTime, String isLock, String id, String userId, 
			String nickName, String bankName, String branchName, String realName, String phone, String bankNo, 
			BigDecimal withdrawalsAmount, BigDecimal walletBalance, String auditState, Date updateTime, Date createTime){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			 operation = "查看【提现记录】";
			 param.put("limit", pageSize);
			 param.put("offset", pageIndex);
			 param.put("id", id);
			 param.put("bankName",bankName);
			 param.put("branchName",branchName);
			 param.put("realName",realName);
			 param.put("phone",phone);
			 param.put("bankNo",bankNo);
			 param.put("withdrawalsAmount",withdrawalsAmount);
			 param.put("walletBalance",walletBalance);
			 param.put("updateTime",updateTime);
			 param.put("createTime",createTime);
			 /*
			  *  根据提现记录创建开始时间、结束时间进行时间段查询
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
			 if(isLock!=null && !isLock.equals("")){
				 param.put("isLock", URLDecoder.decode(isLock, "UTF-8"));
			 }
			 if(auditState!=null && !auditState.equals("")){
				 param.put("auditState", URLDecoder.decode(auditState, "UTF-8"));
			 }			 

			 List<Map<String, Object>> resultList = withdrawalsRecordService.getWRList(param);
			 int total = withdrawalsRecordService.getWRListCount(param);
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
	
	
	@RequestMapping(value="/exportWRData.do",method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String exportWRGata(HttpServletRequest request,HttpServletResponse response,String startTime, String endTime,String isLock, String id, String userId, 
			String nickName, String bankName, String branchName, String realName, String phone, String bankNo, 
			BigDecimal withdrawalsAmount, BigDecimal walletBalance, String auditState, Date updateTime, Date createTime){
		try {			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("id", id);
			paramsMap.put("bankName", bankName);
			paramsMap.put("branchName", branchName);
			paramsMap.put("realName", realName);
			paramsMap.put("phone", phone);
			paramsMap.put("bankNo", bankNo);
			paramsMap.put("withdrawalsAmount", withdrawalsAmount);
			paramsMap.put("walletBalance", walletBalance);
			paramsMap.put("updateTime", updateTime);
			paramsMap.put("createTime", createTime);
			 /*
			  *  根据提现记录创建开始时间、结束时间进行时间段查询
             */
            if (startTime != null && !startTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(startTime,"UTF-8"));
                paramsMap.put("startTime", URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")){
                logger.info("转化后的日期："+URLDecoder.decode(endTime,"UTF-8"));
                paramsMap.put("endTime", URLDecoder.decode(endTime,"UTF-8"));
            }
			 if(nickName!=null && !nickName.equals("")){
				 paramsMap.put("nickName", URLDecoder.decode(nickName, "UTF-8"));
			 }
			 if(isLock!=null && !isLock.equals("")){
				 paramsMap.put("isLock", URLDecoder.decode(isLock, "UTF-8"));
			 }
			 if(auditState!=null && !auditState.equals("")){
				 paramsMap.put("auditState", URLDecoder.decode(auditState, "UTF-8"));
			 }			 
			
			
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			logger.info("获取到的参数："+params);
			List<Map<String, Object>> resultList = withdrawalsRecordService.getExportWRList(params);
			logger.info("获取需要导出的提现记录数据："+resultList);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING	
			};
			String[] headArr = new String[] {"交易流水","用户昵称","开户银行","支行名称","真实姓名","手机号","卡号","提现金额","钱包余额","审核状态","提现限制","创建时间"};
			String[] nameArr = new String[] {"collector","nick_name","bank_name","branch_name","real_name","phone","bank_no","withdrawals_amount","wallet_balance","audit_state","is_lock","create_time"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setSheetName("所有提现记录");
			try {										
				String fileName = "所有提现记录数据.xls";
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
	
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/updateWRAuditInfo.do",method = { RequestMethod.POST})
	@ResponseBody
	public void saveWRAuditInfo(HttpServletRequest request, HttpServletResponse response, String paramsStr){
		String operation = "",errorMsg = "";
		JSONObject paramsMap = JSONObject.fromObject(paramsStr);
		try {
			//根据id更改提现记录审核是否通过（hx_withdrawals_record   audit_state  1未通过，2：已通过）
			withdrawalsRecordService.updateWRAuditStatus(paramsMap);
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "审核成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}
	
}
