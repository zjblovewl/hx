package cn.com.hxfz.task.quartz;

import cn.com.hxfz.util.DateUtil;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 
 * @类功能说明：
 * @项目名称：  hxcollection
 * @包名：	 cn.com.hxfz.task.quartz
 * @类名：	 QuartzUtil.java
 * @公司名称：  南京华讯方舟通讯设备有限公司  
 * @作者：         qiangxuan  
 * @创建时间：   2018年4月26日 上午10:09:56 
 * @版本：         V1.0
 */
public class QuartzUtil {

	private static final Logger logger = Logger.getLogger(QuartzUtil.class);
	private static SchedulerFactory ssf = new StdSchedulerFactory();
	private final static String JOB_GROUP_NAME = "QUARTZ_JOBGROUP_NAME";
	private final static String TRIGGER_GROUP_NAME = "QUARTZ_TRIGGERGROUP_NAME";
	private static QuartzUtil quInstance = null;

	public static synchronized QuartzUtil getInstance() {
		if (null == quInstance) {
			quInstance = new QuartzUtil();
		}
		return quInstance;
	}

	/***
	 * 
	 * 方法功能说明：  添加定时任务
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数： @param jobName
	 * 参数： @param jobGroupName
	 * 参数： @param triggerName
	 * 参数： @param triggerGroupName
	 * 参数： @param jobClass
	 * 参数： @param time
	 * 参数： @param jobMap
	 * 参数： @param startTime
	 * 参数： @param EndTime      
	 * return void     
	 * throws
	 */
	public synchronized void addJobByTime(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName,
			Class<BaseJob> jobClass, String time, JobDataMap jobMap,
			String startTime, String EndTime) {

		Date startDate;
		Date endDate;

		if (null == startTime || 0 == startTime.length()) {
			logger.warn("开始时间未定义，系统将采用当日00:00:00作为启动时间");
			startDate = DateUtil.getTodayStartTime();
		} else {
			startDate = DateUtil.parseDateTime(startTime, "yyyy-MM-dd HH:mm:ss");
		}
		if (null == EndTime || 0 == EndTime.length()) {
			logger.warn("结束时间未定义，系统将采用当日23:59:59做为结束时间");
			endDate = DateUtil.getTodayEndTime();
		} else {
			endDate = DateUtil.parseDateTime(EndTime, "yyyy-MM-dd HH:mm:ss");
		}

		try {
			Scheduler sched = ssf.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(jobName, jobGroupName).setJobData(jobMap)
					.build();
			CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
					.withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(time))
					.startAt(startDate).endAt(endDate).build();

			sched.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			logger.error("添加定时任务异常！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * 方法功能说明：添加简单任务，没有开始结束时间点，只运行一次  
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数： @param jobName
	 * 参数： @param jobGroupName
	 * 参数： @param triggerName
	 * 参数： @param jobClass
	 * 参数： @param jobMap      
	 * return void     
	 * throws
	 */
	public synchronized void addJobSimple(String jobName, String jobGroupName,
			String triggerName, Class<BaseJob> jobClass, JobDataMap jobMap) {
		try {
			Scheduler sched = ssf.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(jobName, jobGroupName).setJobData(jobMap)
					.build();
			Trigger simpleTrigger = TriggerBuilder.newTrigger()
					.withIdentity(triggerName, jobGroupName).startNow().build();
			sched.scheduleJob(jobDetail, simpleTrigger);
		} catch (Exception e) {
			logger.error("添加简单定时任务异常！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * 方法功能说明： 修改定时任务时间  
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数： @param triggerName
	 * 参数： @param triggerGroupName
	 * 参数： @param time      
	 * return void     
	 * throws
	 */
	public void modifyJobTime(String triggerName, String triggerGroupName,
			String time) {
		try {
			Scheduler sched = ssf.getScheduler();
			TriggerKey triggerkey = new TriggerKey(triggerName,
					triggerGroupName);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerkey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = trigger;
				ct.getTriggerBuilder()
						.withSchedule(CronScheduleBuilder.cronSchedule(time))
						.build();
				sched.resumeTrigger(triggerkey);
			}
		} catch (Exception e) {
			logger.error("修改定时任务异常！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * 方法功能说明： 删除定时任务 
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数： @param jobName
	 * 参数： @param jobGroupName
	 * 参数： @param triggerName
	 * 参数： @param triggerGroupName      
	 * return void     
	 * throws
	 */
	public void deleteJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = ssf.getScheduler();
			TriggerKey triggerkey = new TriggerKey(triggerName,
					triggerGroupName);
			sched.pauseTrigger(triggerkey);
			sched.unscheduleJob(triggerkey);
			JobKey jobkey = new JobKey(jobName, jobGroupName);
			sched.deleteJob(jobkey);
		} catch (Exception e) {
			logger.error("删除定时任务异常！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * 方法功能说明：暂停定时任务  
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数： @param jobName
	 * 参数： @param jobGroupName
	 * 参数： @param triggerName
	 * 参数： @param triggerGroupName      
	 * return void     
	 * throws
	 */
	public void pauseJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = ssf.getScheduler();
			TriggerKey triggerkey = new TriggerKey(triggerName,
					triggerGroupName);
			sched.pauseTrigger(triggerkey);
			sched.unscheduleJob(triggerkey);
			JobKey jobkey = new JobKey(jobName, jobGroupName);
			sched.pauseJob(jobkey);
		} catch (Exception e) {
			logger.error("暂停定时任务异常！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * 方法功能说明：恢复定时任务  
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数： @param jobName
	 * 参数： @param jobGroupName
	 * 参数： @param triggerName
	 * 参数： @param triggerGroupName      
	 * return void     
	 * throws
	 */
	public void resumeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = ssf.getScheduler();
			TriggerKey triggerkey = new TriggerKey(triggerName,
					triggerGroupName);
			sched.pauseTrigger(triggerkey);
			sched.unscheduleJob(triggerkey);
			JobKey jobkey = new JobKey(jobName, jobGroupName);
			sched.resumeJob(jobkey);
		} catch (Exception e) {
			logger.error("恢复定时任务异常！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * 方法功能说明：开启全部定时任务  
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数：       
	 * return void     
	 * throws
	 */
	public synchronized void startJobs() {
		try {
			Scheduler sched = ssf.getScheduler();
			sched.start();
		} catch (Exception e) {
			logger.error("启动任务失败！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * 方法功能说明：停止全部定时任务  
	 * 创建：2017年3月14日 by chenxianxiao   
	 * 参数：       
	 * return void     
	 * throws
	 */
	public synchronized void shutdownJobs() {
		try {
			Scheduler sched = ssf.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			logger.error("停止任务失败！" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
