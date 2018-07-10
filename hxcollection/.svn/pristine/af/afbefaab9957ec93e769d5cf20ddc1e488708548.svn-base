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

import cn.com.hxfz.service.GoodsComplaintService;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;

/**
 * @类功能说明：藏品投诉控制类
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：qiangxuan
 * @创建时间：2018年5月3日 下午2:49:16
 * @版本：V1.0
 */
@Controller
@RequestMapping(value = "/goodsComplaint")
public class GoodsComplaintController {
	static Logger logger = Logger.getLogger(GoodsComplaintController.class
			.getName());

	@Autowired
	private GoodsComplaintService goodsComplaintService;

	/**
	 * 
	 * @description： 获取所有藏品投诉数据
	 * @method: queryGoodsComplaintList
	 * @param: @param request
	 * @param: @param response
	 * @param: @param pageSize
	 * @param: @param pageIndex
	 * @param: @param id
	 * @param: @param reportPeople
	 * @param: @param reportObject
	 * @param: @param reportType
	 * @param: @param reportContent
	 * @param: @param dealStatus
	 * @param: @param reportTime
	 * @param: @param createTime
	 * @return: void
	 * @date: 2018年5月3日 下午3:49:42 by qiangxuan
	 */
	@RequestMapping(value = "/queryGoodsComplaintList.do",method = { RequestMethod.POST, RequestMethod.GET})
	public void queryGoodsComplaintList(HttpServletRequest request,
			HttpServletResponse response, Integer pageSize, Integer pageIndex,
			String id, String reportPeople, String reportObject,
			String reportType, String reportContent, String dealStatus,
			Date reportTime, Date createTime, String remarks) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("limit", pageSize);
			param.put("offset", pageIndex);
			param.put("id", id);
			param.put("reportPeople", reportPeople);
			param.put("reportObject", reportObject);
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
			List<Map<String, Object>> resultList = goodsComplaintService
					.getGoodsComplaintList(param);
			
			int total = goodsComplaintService.getGoodsComplaintListCount(param);
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
	 * @description： 投诉处理
	 * @method:  saveGcInfo
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月7日 下午4:25:21 by qiangxuan
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addGoodsComInfo.do",method = { RequestMethod.POST})
	@ResponseBody
	public void saveGcInfo(HttpServletRequest request, HttpServletResponse response, String paramsStr){
		String operation = "",errorMsg = "";
		JSONObject paramsMap = JSONObject.fromObject(paramsStr);
		try {
	
			goodsComplaintService.saveGcInfo(paramsMap);

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
	 * @description： 根据ID删除藏品投诉
	 * @method:  deleteGC
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月7日 下午4:16:03 by qiangxuan
	 */
	@RequestMapping(value = "/deleteGCById.do",method = { RequestMethod.POST})
	@ResponseBody
	public void deleteGC(HttpServletRequest request,HttpServletResponse response){
	    //operation 操作，errorMsg异常信息
	    String operation = "",errorMsg = "";
	    String id = request.getParameter("gcIds");
	    try {
	    	operation = "删除【藏品投诉】";
	    	Map<String, Object> paramsMap = new HashMap<String, Object>();											
	    	paramsMap.put("id", id);		
	    	goodsComplaintService.deleteGC(paramsMap);			
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
	 * @description： 批量删除藏品投诉
	 * @method:  batchDelGC
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年5月7日 下午4:15:34 by qiangxuan
	 */
	@RequestMapping(value = "/delGCBatch.do", method = { RequestMethod.POST })
	@ResponseBody
	public void batchDelGC(HttpServletRequest request,HttpServletResponse response) {
		String operation = "", errorMsg = "";
		String id = request.getParameter("gcIds");
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
			operation = "批量删除【藏品投诉】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("id", deleteStr);
			goodsComplaintService.delMoreGC(paramsMap);
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
