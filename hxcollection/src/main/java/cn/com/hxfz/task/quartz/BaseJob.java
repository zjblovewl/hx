package cn.com.hxfz.task.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BaseJob implements Job{
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 System.out.println("\t\t"+BaseJob.class.getName()+":"+ System.currentTimeMillis());
	}

}
