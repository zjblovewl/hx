package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.ChartDao;
import cn.com.hxfz.service.ChartService;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月10日 下午2:40:01 
 * @版本：V1.0  
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService{
	
	@Autowired
	private ChartDao chartDao;
	
	@Override
	public List<Map<String, Object>> getOrderSumList(Map<String, Object> paramsMap) {
		return chartDao.queryOrderSumList(paramsMap);
	}

	@Override
	public List<Map<String, Object>> getCardAddList(
			Map<String, Object> paramsMap) {
		return chartDao.queryCardAddList(paramsMap);
	}

	@Override
	public List<Map<String, Object>> getCommentAddList(
			Map<String, Object> paramsMap) {
		return chartDao.queryCommentAddList(paramsMap);
	}

	@Override
	public List<Map<String, Object>> getUserRegSumList(
			Map<String, Object> paramsMap) {
		return chartDao.getUserRegTrendData(paramsMap);
	}


}
