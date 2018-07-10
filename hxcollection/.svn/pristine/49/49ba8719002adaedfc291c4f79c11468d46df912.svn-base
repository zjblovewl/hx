package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.TradingFlowDao;
import cn.com.hxfz.service.TradingFlowService;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月16日 上午10:54:35 
 * @版本：V1.0  
 */
@Service("tradingFlowService")
public class TradingFlowImpl implements TradingFlowService{
	@Autowired
	private TradingFlowDao tradingFlowDao;

	@Override
	public List<Map<String, Object>> getTradingFlowList(
			Map<String, Object> param) {
		return tradingFlowDao.queryTradingFlowList(param);
	}

	@Override
	public int getTradingFlowListCount(Map<String, Object> param) {
		int total = tradingFlowDao.queryTradingFlowCount(param);
		return total;
	}

	@Override
	public List<Map<String, Object>> getExportTradeFlowList(
			Map<String, Object> params) {
		return tradingFlowDao.exportTradeFlowList(params);
	}
	
}
