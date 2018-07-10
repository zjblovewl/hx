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
import cn.com.hxfz.service.SellerComplaintService;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;

/**
 * @类功能说明：藏户投诉控制类
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：qiangxuan
 * @创建时间：2018年5月3日 下午2:52:03
 * @版本：V1.0
 */

@Controller
@RequestMapping(value = "/sellerComplaint")
public class SellerComplaintController {
	static Logger logger = Logger.getLogger(SellerComplaintController.class
			.getName());

	@Autowired
	private SellerComplaintService sellerComplaintService;

	/**
	 * 
	 * @description： 获取所有藏户投诉数据
	 * @method: queryGoodsComplaintList
	 * @param: @param request
	 * @param: @param response
	 * @param: @param pageSize
	 * @param: @param pageIndex
	 * @param: @param id
	 * @param: @param reportPeople
	 * @param: @param reportSeller
	 * @param: @param reportType
	 * @param: @param reportContent
	 * @param: @param dealStatus
	 * @param: @param reportTime
	 * @param: @param createTime
	 * @return: void
	 * @date: 2018年5月3日 下午3:57:56 by qiangxuan
	 */
	@RequestMapping(value = "/querySellerComplaintList.do",method = { RequestMethod.POST, RequestMethod.GET})
	public void querySellerComplaintList(HttpServletRequest request,
			HttpServletResponse response, Integer pageSize, Integer pageIndex,
			String id, String reportPeople, String reportSeller,
			String reportType, String reportContent, String dealStatus,
			Date reportTime, Date createTime, String remarks) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("limit", pageSize);
			param.put("offset", pageIndex);
			param.put("id", id);
			param.put("reportPeople", reportPeople);
			param.put("reportSeller", reportSeller);
			param.put("dealStatus", dealStatus);
			param.put("reportTime", reportTime);
			param.put("createTime", createTime);
			if (reportType != null && !reportType.equals("")) {
				param.put("reportType", URLDecoder.decode(reportType, "UTF-8"));
			}
			if (reportContent != null && !reportContent.equals("")) {
				param.put("reportContent",
						URLDecoder.decode(reportContent, "UTF-8"));
			}
			param.put("remarks", remarks);
			List<Map<String, Object>> resultList = sellerComplaintService
					.getSellerComplaintList(param);
			int total = sellerComplaintService.getSellerComplaintCount(param);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("rows", resultList);
			resultMap.put("total", total);
			JSONObject jsonObj = JSONObject.fromObject(resultMap);
			logger.info(jsonObj);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @description： 藏户投诉处理
	 * @method:  saveScInfo
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月7日 下午4:26:35 by qiangxuan
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/addSellerComInfo.do",method = { RequestMethod.POST})
	@ResponseBody
	public void saveScInfo(HttpServletRequest request, HttpServletResponse response, String paramsStr){
		JSONObject paramsMap = JSONObject.fromObject(paramsStr);
		String operation = "",errorMsg = "";
		try {
			sellerComplaintService.saveScInfo(paramsMap);
				
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
	
	
	/**
	 * 
	 * @description： 根据ID删除藏户投诉信息
	 * @method:  deleteSC
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月7日 下午4:16:51 by qiangxuan
	 */
	@RequestMapping(value = "/deleteSCById.do",method = { RequestMethod.POST})
	@ResponseBody
	public void deleteSC(HttpServletRequest request,HttpServletResponse response){
	    //operation 操作，errorMsg异常信息
	    String operation = "",errorMsg = "";
	    String id = request.getParameter("scIds");
	    try {
	    	operation = "删除【藏户投诉】";
	    	Map<String, Object> paramsMap = new HashMap<String, Object>();											
	    	paramsMap.put("id", id);		
	    	sellerComplaintService.deleteSC(paramsMap);			
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
	 * @description： 批量删除藏户投诉信息
	 * @method:  batchDelSC
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月7日 下午4:17:12 by qiangxuan
	 */
	@RequestMapping(value = "/delSCBatch.do", method = { RequestMethod.POST })
	@ResponseBody
	public void batchDelSC(HttpServletRequest request,HttpServletResponse response) {
		String operation = "", errorMsg = "";
		String id = request.getParameter("scIds");
		StringBuilder sb = new StringBuilder("");
		String deleteStr = "";
		if (id != null && !"".equals(id)) {
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				for (String i : ids) {
					sb.append("'");
					sb.append(i);
					sb.append("'");
					sb.append(",");
				}
				//'1','2','3'
				deleteStr = sb.substring(0, sb.length() - 1);
			} else {
				//'1'
				deleteStr = "'" + id + "'";
			}
		}
		try {
			operation = "批量删除【藏户投诉】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("id", deleteStr);
			sellerComplaintService.delMoreSC(paramsMap);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("resultMsg", "删除成功");
			JSONObject jsonObj = JSONObject.fromObject(result);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		} finally {
			LogUtil.saveLog(request, operation, errorMsg);
		}
	}
}
