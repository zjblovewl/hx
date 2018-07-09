package cn.com.base.job.service;

import cn.com.model.job.JobAndTrigger;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @类功能说明：定时任务接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/3 上午11:04
 * @版本：V1.0
 */
public interface QuartzService {

    /**
     * @description 创建定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:05:44
     * @author:zhoujinbing
     */
    public void addJob(String jobClassName, String jobGroupName, String cronExpression);

    /**
     * @description 删除定时任务
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/5/3 11:06:23
     * @author:zhoujinbing
     */
    public void deleteJob(String jobClassName, String jobGroupName);

    /**
     * @description 暂停定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:07:17
     * @author:zhoujinbing
     */
    public void pauseJob(String jobClassName, String jobGroupName);

    /**
     * @description 重新启动定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:08:15
     * @author:zhoujinbing
     */
    public void resumeJob(String jobClassName, String jobGroupName);

    /**
     * @description 更新定时任务
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 11:08:48
     * @author:zhoujinbing
     */
    public void rescheduleJob(String jobClassName, String jobGroupName, String cronExpression);

    /**
     * @description 分页查询Job
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 15:13:29
     * @author:zhoujinbing
     */
    public PageInfo<JobAndTrigger> getJobList(Integer page, Integer size);
}
