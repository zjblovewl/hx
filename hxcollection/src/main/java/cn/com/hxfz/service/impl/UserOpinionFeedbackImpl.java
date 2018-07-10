package cn.com.hxfz.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.UserOpinionFeedbackDao;
import cn.com.hxfz.service.UserOpinionFeedbackService;


@Service("userOpinionFeedbackService")
public class UserOpinionFeedbackImpl implements UserOpinionFeedbackService{
	
	static Logger logger = Logger.getLogger(UserOpinionFeedbackImpl.class.getName());
	
	@Autowired
	private UserOpinionFeedbackDao userOpinionFeedbackDao;
	

	@Override
	public List<Map<String, Object>> getUserOpinionFeedbackList(
			Map<String, Object> param) {
		 List<Map<String,Object>> rows = userOpinionFeedbackDao.getUserOpinionFeedbackList(param);  
		 logger.info("get user operation history:"+rows);
	     return rows;
	}

	@Override
	public int getUserOpinionFeedbackCount(Map<String, Object> param) {
		int total = userOpinionFeedbackDao.getUserOpinionFeedbackCount(param);
		return total;
	}

	@Override
	public void deleteOpinionFeedback(Map<String, Object> paramsMap) {
		userOpinionFeedbackDao.deleteOpinionFeedbackById(paramsMap);
	}

	@Override
	public void saveUserFC(Map<String, Object> paramsMap) {
		userOpinionFeedbackDao.updateUserFC(paramsMap);
	}
}
