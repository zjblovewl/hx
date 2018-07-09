package cn.com.base.job.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/3 上午10:14
 * @版本：V1.0
 */
public interface BaseJob extends Job {

    /**
     * @description 处理业务逻辑
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 10:16:39
     * @author:zhoujinbing
     */
    public void execute(JobExecutionContext context) throws JobExecutionException;
}
