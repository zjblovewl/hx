package cn.com.hxfz.task.quartz;

import cn.com.hxfz.service.TimeTaskService;
import cn.com.hxfz.util.ApplicationContextHelper;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;

import java.util.List;
import java.util.Map;

public class LoadTask {
	private static final Logger logger = (Logger) Logger
			.getLogger(LoadTask.class);

	private static QuartzUtil quartzUtil = QuartzUtil.getInstance();

	/***
	 * 
	 * 方法功能说明：初始化定时任务 创建：2017年7月17日 by chenchen 参数： @return return boolean
	 * throws
	 */
	public boolean initTask() {
		logger.info("进入定时任务初始化类...");
		TimeTaskService timeTaskService = (TimeTaskService) ApplicationContextHelper
				.getBean("timeTaskService");
		List<Map<String, Object>> resultList = timeTaskService.getTaskList();
		logger.info("查询所有已启动的定时任务："+resultList);
		do {
		
			String jobName = "";
			String className = "";
			String expression = "";
			String startTime = "";
			String endTime = "";

			boolean flag = true;
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {

					jobName = resultList.get(i).get("name").toString();
					className = resultList.get(i).get("classname").toString();
					expression = resultList.get(i).get("expression").toString();
					startTime = resultList.get(i).get("start_time").toString();
					endTime = resultList.get(i).get("end_time").toString();
					logger.info("startTime：" + startTime + " endTime："+ endTime);
					logger.info("类名：" + jobName + "类路径：" + className+ " 定时时间：" + expression);
					// 加载定时任务
					if (!AddJob(jobName, className, expression, startTime,
							endTime)) {
						logger.info("初始化定时任务成功...");
						flag = false;
						break;
					}
				}
				if (flag) {
					quartzUtil.startJobs();
//					quartzUtil.pauseJob(jobName, className, expression, "");
					logger.info("初始化定时任务成功...");
				} else {
					break;
				}
			}
			/*
			 * if(flag){ quartzUtil.startJobs(); logger.info("初始化定时任务成功...");
			 * }else{ break; }
			 */
			/*
			 * if (!AddJob("TrialCaseJob", "cn.com.hxfz.tdh.TrialCaseJob",
			 * "40 0 11 * * ?")) { logger.error("加载审判案件任务失败！"); break; } //
			 * 加载银行、车辆、房屋、股票任务 if (!AddJob("BCHSCasesJob",
			 * "cn.com.hxfz.tdh.BCHSJob", "0 31 12 * * ?")) {
			 * logger.error("加载银行、车辆、房屋、股票任务失败！"); break; }
			 */

			return true;
		} while (false);

		// 停止任务
		quartzUtil.shutdownJobs();
		// 删除任务
		quartzUtil.deleteJob("TestJob", "JobGroup", "TestJobJobTrigger",
				"TriggerGroup");
		/*
		 * quartzUtil.deleteJob("TrialCaseJob", "JobGroup",
		 * "TrialCaseJobTrigger", "TriggerGroup");
		 * quartzUtil.deleteJob("BCHSJob", "JobGroup", "BCHSJobTrigger",
		 * "TriggerGroup"); quartzUtil.deleteJob("MakeConJob", "JobGroup",
		 * "MakeConJobTrigger", "TriggerGroup");
		 * quartzUtil.deleteJob("CarCarseriesJob", "JobGroup",
		 * "CarCarseriesJobTrigger", "TriggerGroup");
		 */

		return false;
	}

	/***
	 * 
	 * 方法功能说明： 添加任务 创建：2017年3月14日 by chenchen 参数： @param jobName 参数： @param
	 * jobClass 参数： @param jobDataMap 参数： @param time 参数： @return return boolean
	 * throws
	 */
	private boolean AddJob(String jobName, String ClassName, String time,
			String startTime, String endTime) {

		JobDataMap jobDataMap = new JobDataMap();
		Class classItem = null;
		try {
			classItem = Class.forName(ClassName);
		} catch (ClassNotFoundException e) {
			logger.error("获取定时任务类失败：" + e.getMessage(), e);
			return false;
		}

		quartzUtil
				.addJobByTime(jobName, "JobGroup", jobName + "Trigger",
						"TriggerGroup", classItem, time, jobDataMap, startTime,
						endTime);

		return true;
	}
}
