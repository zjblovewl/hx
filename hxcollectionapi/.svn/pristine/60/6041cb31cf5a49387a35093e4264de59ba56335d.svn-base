package cn.com.base.job.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mobile.order.dao.OrderMobileMapper;
import cn.com.mobile.order.service.OrderMobileService;
import cn.com.util.ApplicationContextHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类功能说明：处理未收货订单JOB
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月30日 上午下午4:17:37 
 * @版本：V1.0
 */
public class DealUncollectedGoodsJob implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(DealUncollectedGoodsJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("DealUncollectedGoodsJob执行时间: " + new Date());
        OrderMobileService orderMobileService = (OrderMobileService)ApplicationContextHelper.getBean("orderMobileService");
        /*List<Map<String, Object>> list = orderMobileService.getOvertimeUnReceiveOrderRecords();*/        
        orderMobileService.batchConfirmCollectGoods();
    }
}
