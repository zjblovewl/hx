package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.WithdrawalsRecordDao;
import cn.com.hxfz.service.WithdrawalsRecordService;

/**  
 * @类功能说明：提现管理实现类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月21日 下午3:11:25 
 * @版本：V1.0  
 */
@Service("withdrawalsService")
public class WithdrawalsRecordServiceImpl implements WithdrawalsRecordService{

	static Logger logger = Logger.getLogger(WithdrawalsRecordServiceImpl.class.getName());
	
	@Autowired
	private WithdrawalsRecordDao withdrawalsRecordDao;

	@Override
	public List<Map<String, Object>> getWRList(Map<String, Object> param) {
		return withdrawalsRecordDao.queryWRList(param);
	}

	@Override
	public int getWRListCount(Map<String, Object> param) {
		return withdrawalsRecordDao.queryWRCount(param);
	}

	@Override
	public List<Map<String, Object>> getExportWRList(Map<String, Object> params) {
		return withdrawalsRecordDao.exportWRList(params);
	}

	@Override
	public void updateWRAuditStatus(Map<String, Object> param) {
		withdrawalsRecordDao.updateWRAuditById(param);
	}
	
}
