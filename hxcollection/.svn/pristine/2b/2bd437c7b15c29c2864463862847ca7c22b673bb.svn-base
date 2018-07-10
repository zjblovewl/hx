package cn.com.hxfz.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.OrderDao;
import cn.com.hxfz.service.OrderService;

/**
 * 
 * @类功能说明：藏品分类实现类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月10日 下午2:22:36 
 * @版本：V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	public List<Map<String, Object>> getOrderRecords(Map<String, Object> paramsMap) {
		return orderDao.getOrderRecords(paramsMap);
	}
	@Override
	public int getOrderCount(Map<String, Object> paramsMap) {
		return orderDao.getOrderCount(paramsMap);
	}
	@Override
	public void saveOrder(Map<String, Object> paramsMap) {
		orderDao.updateOrderById(paramsMap);
	}
	@Override
	public List<Map<String, Object>> getExportOrderList(
			Map<String, Object> params) {
		return orderDao.exportOrderRecord(params);
	}
}
