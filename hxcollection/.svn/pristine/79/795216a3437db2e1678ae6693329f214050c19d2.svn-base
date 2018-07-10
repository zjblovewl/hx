package cn.com.hxfz.task.servicetask;

import cn.com.hxfz.task.quartz.BaseJob;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class TestJob10 extends BaseJob {
    private static final Logger logger = LogManager.getLogger(TestJob10.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Date date = new Date();
        System.out.println("测试定时任务TestJob10："+date);  
    }
}
