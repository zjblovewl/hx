package cn.com.base.job.config;

import java.util.List;
import java.util.Map;

import cn.com.base.job.service.QuartzService;
import cn.com.mobile.order.dao.OrderMobileMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @类功能说明：初始化定时任务
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/3 上午14:19
 * @版本：V1.0
 */
@Order(1)//解决启动时实现类执行优先级，越小越往前执行
@Component
public class InitQuartzCluster implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(InitQuartzCluster.class);

    @Autowired
    private QuartzService quartzService;
    
    @Autowired
    private OrderMobileMapper orderMobileMapper;

    /**
     * @description 程序启动时检查结拍场次定时任务，同事检查是否已经添加每日凌晨处理前一天的场次
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/3 14:40:32
     * @author:zhoujinbing
     */
    @Override
    public void run(String... args) throws Exception {    	
    	logger.info("初始化定时任务开始...");
    	quartzService.deleteJob("cn.com.base.job.job.DealUnPaymentOrderJob","unPaymentOrder");
    	quartzService.deleteJob("cn.com.base.job.job.DealUncollectedGoodsJob","uncollectedGoods");
    	
    	quartzService.addJob("cn.com.base.job.job.DealUnPaymentOrderJob","unPaymentOrder","0/10 * * * * ? ");
    	quartzService.addJob("cn.com.base.job.job.DealUncollectedGoodsJob","uncollectedGoods","0/10 * * * * ? ");    	    
        logger.info("初始化定时任务结束...");
    }
}

