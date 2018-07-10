package cn.com.hxfz.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.com.hxfz.service.TimeTaskService;
import cn.com.hxfz.task.quartz.BaseJob;
import cn.com.hxfz.task.quartz.QuartzUtil;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
import cn.com.hxfz.util.StringUtils;

/**
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: cn.com.hxfz.controller.usermanage
 * @作者: qiangxuan
 * @时间: 2018/3/16 15:16
 * @版本: V1.0
 */
@Controller
@RequestMapping(value = "/timeTask")
public class TimeTaskController {

	static Logger logger = Logger.getLogger(TimeTaskController.class.getName());
	private static QuartzUtil quartzUtil = QuartzUtil.getInstance();
    @Autowired
    private TimeTaskService timeTaskService;

    
    /**
     * 获取定时任务列表
     * 方法功能说明：  
     * 创建：@Date by qiangxuan
     * @参数： 
     * @return
     * @version 1.0         
     * @throws
     */
    @RequestMapping(value = "/queryTimeTaskList.do",method = { RequestMethod.POST, RequestMethod.GET})    
    public void queryTimeTaskList(HttpServletRequest request,HttpServletResponse response,
    		Integer pageSize, Integer pageIndex,String id, String name, String tGroup, String expression, String status, String classname, String description, String startTime, String endTime){
        Map<String, Object> param = new HashMap<String, Object>();
       
        try {
            param.put("limit",pageSize);
            param.put("offset",pageIndex);
            param.put("id", id);
            param.put("name",name);
            param.put("tGroup",tGroup);
            param.put("expression",expression);
            param.put("status",status);
            param.put("classname",classname);
            
            if (name != null && !name.equals("")) {
				param.put("name", URLDecoder.decode(name,"UTF-8"));
			}
            if (description != null && !description.equals("")) {
            	param.put("description",URLDecoder.decode(description,"UTF-8"));
			}
            if (startTime != null && !startTime.equals("")) {
            	param.put("startTime",URLDecoder.decode(startTime,"UTF-8"));
            }
            if (endTime != null && !endTime.equals("")) {
            	param.put("endTime",URLDecoder.decode(endTime,"UTF-8"));
            }
			List<Map<String, Object>> resultList= timeTaskService.getTimeTask(param);
			int total = timeTaskService.getTimeTaskCount(param);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("rows", resultList);
			resultMap.put("total", total);
			logger.info(resultList);
			logger.info(total);
			JSONObject jsonObj = JSONObject.fromObject(resultMap);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
    }

    

    /**
     * 
     * @description 新增、编辑定时任务
     * @method  saveOrUpdateTimeTask
     * @param @param request
     * @param @param response
     * @param @param id
     * @param @param name
     * @param @param tGroup
     * @param @param expression
     * @param @param status
     * @param @param classname
     * @param @param description
     * @param @param startTime
     * @param @param endTime
     * @param @param createTime
     * @param @param delFlag
     * @return void
     * @date: 2018年4月2日 下午10:26:46
     * @author:qiangxuan
     */
    @RequestMapping(value = "/addOrUpdateTimeTask.do",method = { RequestMethod.POST})
    @ResponseBody
    public void saveOrUpdateTimeTask(HttpServletRequest request,HttpServletResponse response){
    	
    	String data = request.getParameter("data");
    	String operation = "",errorMsg = "";
    	Map<String, Object> result = new HashMap<String, Object>();			
		result.put("success", true);			
		result.put("resultMsg", "保存成功");			
    	try {
			Map<String, Object> paramsMap = (Map)JSON.parse(data);										
    		
			String className = String.valueOf(paramsMap.get("classname"));
			try {
				Class.forName(className);
				timeTaskService.saveTimeTask(paramsMap);
			} catch (Exception e) {
				logger.error("", e);
				result.put("success", false);
				result.put("resultMsg", String.format("未找到类{%s}", className));	
			}
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
    }

    /**
     * 
     * @description 根据id删除定时任务
     * @method  deleteTimeTask
     * @param @param request
     * @param @param response
     * @return void
     * @date: 2018年4月4日 下午2:46:11
     * @author:qiangxuan
     */
    @RequestMapping(value = "/deleteTimeTaskById.do",method = { RequestMethod.POST})
    @ResponseBody
    public void deleteTimeTask(HttpServletRequest request,HttpServletResponse response){
    	//operation 操作，errorMsg异常信息
    	String operation = "",errorMsg = "";
    	String id = request.getParameter("ids");
    	try {
    		operation = "删除【定时任务管理】";
    		Map<String, Object> paramsMap = new HashMap<String, Object>();											
    		paramsMap.put("id", id);		
    		//通过id查询定时任务名称
    		String jobName  = timeTaskService.queryNameById(id);
    		
    		//通过id查询定时任务状态
    		String jobStatus = timeTaskService.queryStatusById(id);
    		int taskStatus = Integer.parseInt(jobStatus);
    		
			timeTaskService.deleteTimeTask(paramsMap);	
		    //调用删除定时任务方法，删除此定时任务
		    quartzUtil.deleteJob(jobName, "JobGroup", jobName + "Trigger",
		   					"TriggerGroup");
    		
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
     * @description 批量删除定时任务
     * @method  batchDelTimeTask
     * @param @param request
     * @param @param response
     * @return void
     * @date: 2018年4月8日 上午9:24:27
     * @author:qiangxuan
     */
	@RequestMapping(value = "/delTimeTaskBatch.do", method = { RequestMethod.POST })
	@ResponseBody
	public void batchDelTimeTask(HttpServletRequest request,HttpServletResponse response) {
		String operation = "", errorMsg = "";
		String id = request.getParameter("ids");
		String nameStr = request.getParameter("names");
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
		logger.info("批量删除定时任务id集合：" + deleteStr);
		try {
			operation = "批量删除【定时任务管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("id", deleteStr);
			timeTaskService.delMoreTimeTask(paramsMap);

    		if (StringUtils.isNotEmpty(nameStr)) {
				String[] names = nameStr.split(",");
				for (String jobName : names) {
					logger.info(String.format("pause time task [%s]", jobName));
					quartzUtil.deleteJob(jobName, "JobGroup", jobName + "Trigger", "TriggerGroup");
				}
    		}
			
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
	
	
    
    /**
     * 
     * @description： 启动定时任务，支持批量启动
     * @method:  startTimeTaskById
     * @param:   @param request
     * @param:   @param response
     * @return:  void
     * @throws ClassNotFoundException 
     * @date:    2018年4月26日 下午3:25:38 by qiangxuan
     */
    @RequestMapping(value = "/startTimeTaskById.do",method = { RequestMethod.POST})
    @ResponseBody
    private void startTimeTaskById(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException{
    	String operation ="", errorMsg = "";
    	StringBuilder sb = new StringBuilder("");
    	String data = request.getParameter("datas");
    	JSONArray platformList = JSON.parseArray(data);
    	Iterator<Object> itr = platformList.iterator();
    	while (itr.hasNext()) {
    		Map<String, String> map = (Map) itr.next();
    		String jobName = map.get("name"); 
    		String className = map.get("classname"); 
    		String startTimeStr = map.get("start_time"); 
    		String endTimesStr = map.get("end_time"); 
    		String time = map.get("expression"); 
    		
			quartzUtil.addJobByTime(jobName, "JobGroup", jobName
					+ "Trigger", "TriggerGroup",
					(Class<BaseJob>) Class.forName(className), time,
					new JobDataMap(), startTimeStr, endTimesStr);
			sb.append("'").append(map.get("id")).append("',");
    	}
    	// 启动暂停的定时任务
    	quartzUtil.startJobs();

    	if (sb.toString().endsWith(",")) {
    		String idString = sb.substring(0, sb.lastIndexOf(","));
    		
    		logger.info("启动定时任务id集合：" + idString);
    		Map<String, Object> paramsMap = new HashMap<String, Object>();
    		paramsMap.put("id", idString);
    		timeTaskService.startTimeTaskById(paramsMap);
    	}
    	
    	try {
    		Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("resultMsg", "启动成功");
			JSONObject jsonObj = JSONObject.fromObject(result);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LogUtil.saveLog(request, operation, errorMsg);
		}
    }
    
    
    /**
	 * 
	 * @description： 通过ID暂停定时任务，支持批量暂停
	 * @method:  pauseTimeTask
	 * @param:   @param request
	 * @param:   @param response
	 * @return:  void
	 * @date:    2018年4月26日 上午10:16:40 by qiangxuan
	 */
    @RequestMapping(value = "/pauseTimeTaskById.do",method = { RequestMethod.POST})
    @ResponseBody
	private void pauseTimeTask(HttpServletRequest request, HttpServletResponse response){
		String operation = "", errorMsg = "";
		String id = request.getParameter("ids");
		String nameStr = request.getParameter("names");
		
    	StringBuilder sb = new StringBuilder("");
		String pauseStr = "";
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
				pauseStr = sb.substring(0, sb.length() - 1);
			} else {
				//'1'
				pauseStr = "'" + id + "'";
			}
		}
		logger.info("暂停定时任务id集合：" + pauseStr);
		
		
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
    		paramsMap.put("id", pauseStr);	
    		timeTaskService.pauseTimeTaskById(paramsMap);
    		
    		if (StringUtils.isNotEmpty(nameStr)) {
				String[] names = nameStr.split(",");
				for (String jobName : names) {
					logger.info(String.format("pause time task [%s]", jobName));
					quartzUtil.pauseJob(jobName, "JobGroup", jobName + "Trigger", "TriggerGroup");
				}
    		}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("resultMsg", "暂停成功");
			JSONObject jsonObj = JSONObject.fromObject(result);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		} finally {
			LogUtil.saveLog(request, operation, errorMsg);
		}
	}
    /**
     * 
     * @description： 立即执行一次定时任务
     * @method:  onceRunTimeTask
     * @param:   @param request
     * @param:   @param response
     * @param:   @throws ClassNotFoundException
     * @return:  void
     * @date:    2018年4月28日 上午9:13:05 by qiangxuan
     */
	@RequestMapping(value = "/onceTimeTaskById.do",method = { RequestMethod.POST})
    @ResponseBody
    private void onceRunTimeTask(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException{
    	String operation = "", errorMsg = "";
		String id = request.getParameter("ids");
		String nameStr = request.getParameter("names");
		String clazznameStr = request.getParameter("clazznames");
		logger.info("只运行一次定时任务");
		
		try {
//			Map<String, Object> paramsMap = new HashMap<String, Object>();											
//    		paramsMap.put("id", onceStr);	
//    		timeTaskService.startTimeTaskById(paramsMap);
    		if (StringUtils.isNotEmpty(nameStr)) {
				String[] names = nameStr.split(",");
				String[] classnames = clazznameStr.split(",");
				for (int i = 0; i < names.length; i++) {
					String jobName = names[i];
					String className = classnames[i];
					quartzUtil.addJobSimple(jobName, "JobGroup", jobName + "Trigger", (Class<BaseJob>) Class.forName(className), new JobDataMap());
				}
    		}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("resultMsg", "立即执行一次成功");
			JSONObject jsonObj = JSONObject.fromObject(result);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		} finally {
			LogUtil.saveLog(request, operation, errorMsg);
		}
    }
    
    /**
     * 
     * @description： 校验定时任务名称唯一性
     * @method:  checkSameNickName
     * @param:   @param request
     * @param:   @param response
     * @param:   @param name
     * @param:   @return
     * @return:  Boolean
     * @date:    2018年4月26日 下午7:58:38 by qiangxuan
     */
    @RequestMapping(value="/checkSameTaskName.do")
	@ResponseBody
	public Boolean checkSameNickName(HttpServletRequest request,HttpServletResponse response, String name){
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		String id = request.getParameter("id");
		logger.info(id);
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("id", id);
			paramsMap.put("name",URLDecoder.decode(name, "utf-8"));
			result = timeTaskService.checkSameTaskName(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
