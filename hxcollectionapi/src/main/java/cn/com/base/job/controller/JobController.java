package cn.com.base.job.controller;

import cn.com.base.job.service.QuartzService;
import cn.com.base.util.BaseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/3 上午10:30
 * @版本：V1.0
 */
@RestController
@RequestMapping(value="/job")
public class JobController extends BaseLogger{

//    @Autowired
//    private IJobAndTriggerService iJobAndTriggerService;

    @Autowired
    private QuartzService quartzService;

    /**
     * @description 创建定时任务
     * @method  addjob
     * @param  * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return void
     * @date: 2018/5/3 11:15:49
     * @author:zhoujinbing
     */
    @PostMapping(value="/addJob")
    public void addjob(@RequestParam(value="jobClassName")String jobClassName,
                       @RequestParam(value="jobGroupName")String jobGroupName,
                       @RequestParam(value="cronExpression")String cronExpression) throws Exception
    {
        quartzService.addJob(jobClassName, jobGroupName, cronExpression);
    }

    /**
     * @description 暂停定时任务
     * @method  pausejob
     * @param  * @param jobClassName
     * @param jobGroupName
     * @return void
     * @date: 2018/5/3 11:16:48
     * @author:zhoujinbing
     */
    @PostMapping(value="/pauseJob")
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        quartzService.pauseJob(jobClassName, jobGroupName);
    }

    /**
     * @description 重新启动定时任务
     * @method  resumejob
     * @param  * @param jobClassName
             * @param jobGroupName
     * @return void
     * @date: 2018/5/3 11:17:43
     * @author:zhoujinbing
     */
    @PostMapping(value="/resumeJob")
    public void resumeJob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        quartzService.resumeJob(jobClassName, jobGroupName);
    }

    /**
     * @description 更新定时任务
     * @method  rescheduleJob
     * @param  * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return void
     * @date: 2018/5/3 11:18:36
     * @author:zhoujinbing
     */
    @PostMapping(value="/rescheduleJob")
    public void rescheduleJob(@RequestParam(value="jobClassName")String jobClassName,
                              @RequestParam(value="jobGroupName")String jobGroupName,
                              @RequestParam(value="cronExpression")String cronExpression) throws Exception
    {
       quartzService.rescheduleJob(jobClassName, jobGroupName, cronExpression);
    }

    /**
     * @description 删除定时任务
     * @method  deletejob
     * @param  * @param jobClassName
     * @param jobGroupName
     * @return void
     * @date: 2018/5/3 11:19:13
     * @author:zhoujinbing
     */
    @PostMapping(value="/deleteJob")
    public void deleteJob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
       quartzService.deleteJob(jobClassName, jobGroupName);
    }


//    @GetMapping(value="/queryjob")
//    public Map<String, Object> queryjob(@RequestParam(value="pageNum")Integer pageNum, @RequestParam(value="pageSize")Integer pageSize)
//    {
//        PageInfo<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("JobAndTrigger", jobAndTrigger);
//        map.put("number", jobAndTrigger.getTotal());
//        return map;
//    }




}
