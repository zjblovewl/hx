package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.CommissionRecordDao;
import cn.com.hxfz.service.CommissionRecordService;
import cn.com.hxfz.util.CommonUtils;

/**  
 * @类功能说明：佣金记录业务实现类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月25日 上午10:42:56 
 * @版本：V1.0  
 */
@Service("commissionRecordService")
public class CommissionRecordServiceImpl implements CommissionRecordService{
	
	static Logger logger = Logger.getLogger(CommissionRecordServiceImpl.class.getName());
	@Autowired
	private CommissionRecordDao commissionRecordDao;
	
	@Override
	public List<Map<String, Object>> getCommissionList(
			Map<String, Object> param) {
		return commissionRecordDao.queryCommissionList(param);
	}

	@Override
	public int getCommissionListCount(Map<String, Object> param) {
		int total = commissionRecordDao.queryCommissionCount(param);
		return total;
	}

	@Override
	public List<Map<String, Object>> getExportCommissionList(
			Map<String, Object> params) {
		return commissionRecordDao.exportCommissionList(params);
	}

	@Override
	public void saveCommissionConfig(Map<String, Object> paramsMap) {
		
		int total = commissionRecordDao.queryCommissionRate(paramsMap);
		if (total == 1) {
			commissionRecordDao.updateCommissionRate(paramsMap);
		}else {
			String id = CommonUtils.getUUID();
			paramsMap.put("id", id);
			commissionRecordDao.addCommissionRate(paramsMap);
		}
	}

	@Override
	public List<Map<String, Object>> getCRList(Map<String, Object> param) {
		return commissionRecordDao.queryCRList(param);
	}
}
