package cn.com.hxfz.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.hxfz.dao.UserLoginLogDao;
import cn.com.hxfz.service.UserLoginLogService;



@Service("userLoginLogService")
public class UserLoginLogImpl implements UserLoginLogService{
	
	static Logger logger = Logger.getLogger(UserLoginLogImpl.class.getName());
	
	@Autowired
	private UserLoginLogDao userLoginLogDao;
	

	@Override
	public List<Map<String, Object>> getUserLoginLogList(
			Map<String, Object> param) {
		 List<Map<String,Object>> rows = userLoginLogDao.getUserLoginLogList(param);  
		 logger.info("get user operation history:"+rows);
	     return rows;
	}

	@Override
	public int getUserLoginLogCount(Map<String, Object> param) {
		int total = userLoginLogDao.getUserLoginLogCount(param);
		return total;
	}

}
