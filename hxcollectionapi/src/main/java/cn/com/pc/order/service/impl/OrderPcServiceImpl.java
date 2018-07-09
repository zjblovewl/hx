package cn.com.pc.order.service.impl;

import cn.com.base.util.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.base.constant.BaseConstant;
import cn.com.mobile.order.dao.OrderMobileMapper;
import cn.com.pc.order.service.OrderPcService;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月7日 下午3:20:54 
 * @版本：V1.0  
 */
@Service
public class OrderPcServiceImpl extends BaseLogger implements OrderPcService{

	@Autowired
    private BaseConstant baseConstant;
	
	@Autowired
	private OrderMobileMapper orderMobileMapper;
	
	
}
