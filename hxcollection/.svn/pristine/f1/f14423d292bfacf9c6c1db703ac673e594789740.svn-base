package cn.com.hxfz.task.servicetask;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.com.hxfz.task.quartz.BaseJob;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年4月3日 下午3:19:53 
 * @版本：V1.0  
 */
public class TestJob12 extends BaseJob{
	 private static final Logger logger = LogManager.getLogger(TestJob12.class);

	    @Override
	    public void execute(JobExecutionContext arg0) throws JobExecutionException {
	        Date date = new Date();
	        System.out.println("测试定时任务TestJob12："+date);  
	    }
}
