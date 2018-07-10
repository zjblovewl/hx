package cn.com.hxfz.service.impl;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.OperationLogDao;
import cn.com.hxfz.service.OperationLogService;


/**
 * 
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: OperationLogImpl
 * @作者: qiangxuan
 * @时间: 2018年3月23日 下午3:21:01
 * @版本: V1.0
 */
@Service("operationLogService")
public class OperationLogImpl implements OperationLogService{
	
	@Autowired
	private OperationLogDao operationLogDao;
	

//	@Override
//	public Map<String, Object> getOperationLog(Map<String, Object> param) {
//		 Map<String,Object> result = new HashMap<String,Object>();
//	     int total = operationLogDao.getOperationLog(null).size();
//	     List<Map<String,Object>> rows = operationLogDao.getOperationLog(param);
//	     result.put("total",total);
//	     result.put("rows",rows);
//	     System.out.println(result);
//	     return result;
//	}
	
	/**
	 * 获取操作日志总数量
	 */
	@Override
	public int getOperationLogCount(Map<String, Object> param) {
		int total = operationLogDao.getOperationLogCount(param);
		return total;
	}

	/**
	 * 获取操作日志列表
	 */
	@Override
	public List<Map<String, Object>> getOperationLogList(
			Map<String, Object> param) {
		 List<Map<String,Object>> rows = operationLogDao.getOperationList(param);  
	     return rows;
	}

}
