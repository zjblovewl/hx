package cn.com.hxfz.task.servicetask;

import cn.com.hxfz.task.quartz.BaseJob;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJob1 extends BaseJob {
    private static final Logger logger = LogManager.getLogger(TestJob1.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Date date = new Date();
        System.out.println("**************测试定时任务TestJob1："+date);  
    }
}
