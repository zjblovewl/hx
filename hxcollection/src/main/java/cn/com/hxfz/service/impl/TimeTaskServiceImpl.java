package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.TimeTaskDao;
import cn.com.hxfz.service.TimeTaskService;
import cn.com.hxfz.task.quartz.BaseJob;
import cn.com.hxfz.task.quartz.QuartzUtil;
import cn.com.hxfz.util.CommonUtils;
import cn.com.hxfz.util.DateUtil;
import cn.com.hxfz.util.StringUtils;


/**
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: cn.com.hxfz.service.impl
 * @作者: qiangxuan
 * @时间: 2018/3/16 15:26
 * @版本: V1.0
 */
@Service("timeTaskService")
public class TimeTaskServiceImpl implements TimeTaskService{
	
	static Logger logger = Logger.getLogger(TimeTaskServiceImpl.class.getName());
	private static QuartzUtil quartzUtil = QuartzUtil.getInstance();
    @Autowired
    private TimeTaskDao timeTaskDao;


    @Override
    public List<Map<String, Object>> getTaskList() {
        List<Map<String, Object>> resultList = timeTaskDao.getTimeTaskList();
        return resultList;
    }
    
    @Override
    public List<Map<String, Object>> getTimeTask(Map<String, Object> param) {       
        List<Map<String,Object>> rows = timeTaskDao.getTimeTask(param);  
        return rows;
    }
    
	@Override
	public int getTimeTaskCount(Map<String, Object> param) {
		int total = timeTaskDao.getTimeTaskCount(param);
		return total;
	}

    /**
     * 新增定时任务业务层
     * @throws ClassNotFoundException 
     */
	@Override
	public void saveTimeTask(Map<String, Object> paramsMap)
			throws ClassNotFoundException {
		logger.info(paramsMap.get("id"));

		String startTimeStr = (String) paramsMap.get("startTime");
		paramsMap.put("startTime", DateUtil.format(startTimeStr));

		String endTimeStr = (String) paramsMap.get("endTime");
		paramsMap.put("endTime", DateUtil.format(endTimeStr));

		String jobName = String.valueOf(paramsMap.get("name"));
		String className = String.valueOf(paramsMap.get("classname"));
		String time = String.valueOf(paramsMap.get("expression"));
		String status = String.valueOf(paramsMap.get("status"));
		int taskStatus = Integer.parseInt(status);

		logger.info("——————————————————————————编辑定时任务时，获取的状态：" + status);
		if (paramsMap.get("id") != null && StringUtils.isNotEmpty(paramsMap.get("id").toString())) {
			if (taskStatus == 0) {
				timeTaskDao.updateTimeTaskById(paramsMap);
				
//				logger.info(String.format("delete task jobName={%s}", jobName));
//				
//				quartzUtil.deleteJob(jobName, "JobGroup", jobName + "Trigger",
//						"TriggerGroup");
			} else {
				timeTaskDao.updateTimeTaskById(paramsMap);
				logger.info(String.format("delete task jobName={%s}", jobName));
				quartzUtil.deleteJob(jobName, "JobGroup", jobName + "Trigger",
						"TriggerGroup");
				logger.info(String.format("add task jobName={%s}", paramsMap));
				quartzUtil.addJobByTime(jobName, "JobGroup", jobName
						+ "Trigger", "TriggerGroup",
						(Class<BaseJob>) Class.forName(className), time,
						new JobDataMap(), startTimeStr, endTimeStr);
			}

		} else {
			String id = CommonUtils.getUUID();
			paramsMap.put("id", id);
			paramsMap.put("delflag", "0");
			if (taskStatus == 0) {
				timeTaskDao.saveTimeTask(paramsMap);
				logger.info(String.format("add task jobName={%s}", paramsMap));
			} else {
				timeTaskDao.saveTimeTask(paramsMap);
				logger.info(String.format("add task jobName={%s}", paramsMap));
				quartzUtil.addJobByTime(jobName, "JobGroup", jobName
						+ "Trigger", "TriggerGroup",
						(Class<BaseJob>) Class.forName(className), time,
						new JobDataMap(), startTimeStr, endTimeStr);
			}

		}

	}
    /**
     * 编辑定时任务业务层
     */
    @Override
    public void updateTimeTask(Map<String, Object> paramsMap) {
        timeTaskDao.updateTimeTaskById(paramsMap);
    }
    
    /**
     * 删除定时任务业务层
     */
    @Override
    public void deleteTimeTask(Map<String, Object> paramsMap) {
        timeTaskDao.deleteTimeTaskById(paramsMap);
    }
    /**
     * 批量删除定时任务
     */
	@Override
	public void delMoreTimeTask(Map<String, Object> paramsMap) {
		
		timeTaskDao.delMoreTimeTask(paramsMap);
	}
	/**
	 * 暂停定时任务
	 */
	@Override
	public void pauseTimeTaskById(Map<String, Object> paramsMap) {
//		quartzUtil.pauseJob(jobName, jobGroupName, triggerName, triggerGroupName);
		timeTaskDao.pauseTimeTaskById(paramsMap);
	}
	/**
	 * 启动定时任务
	 */
	@Override
	public void startTimeTaskById(Map<String, Object> paramsMap) {
		timeTaskDao.startTimeTaskById(paramsMap);
	}
	/**
	 * 定时任务名称校验  任务名为唯一标识，不可相同
	 */
	@Override
	public Boolean checkSameTaskName(Map<String, Object> paramsMap) {
		int count = 0;
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		//Id不为空,则为编辑
		if((paramsMap.get("id") != null && StringUtils.isNotEmpty(paramsMap.get("id").toString()))){
			count = timeTaskDao.checkEditTask(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}			
		}else{
			count = timeTaskDao.checkAddTask(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}	
		}
		return result;
	}
	/**
	 * 通过id 查询定时任务名称
	 */
	@Override
	public String queryNameById(String id) {
		return timeTaskDao.queryNameById(id);
	}

	@Override
	public String queryStatusById(String id) {
		return timeTaskDao.queryStatusById(id);
	}



}