package cn.com.hxfz.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.controller.UserOperationLogController;
import cn.com.hxfz.dao.UserOperationLogDao;
import cn.com.hxfz.service.UserOperationLogService;


@Service("userOperationLogService")
public class UserOperationLogImpl implements UserOperationLogService{
	
	static Logger logger = Logger.getLogger(UserOperationLogImpl.class.getName());
	
	@Autowired
	private UserOperationLogDao userOperationLogDao;
	

	@Override
	public List<Map<String, Object>> getUserOperationLogList(
			Map<String, Object> param) {
		 List<Map<String,Object>> rows = userOperationLogDao.getUserOperationLogList(param);  
		 logger.info("get user operation history:"+rows);
	     return rows;
	}

	@Override
	public int getUserOperationLogCount(Map<String, Object> param) {
		int total = userOperationLogDao.getUserOperationLogCount(param);
		return total;
	}

}
