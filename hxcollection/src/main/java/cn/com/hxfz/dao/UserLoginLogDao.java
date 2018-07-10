package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

public interface UserLoginLogDao {

	List<Map<String, Object>> getUserLoginLogList(Map<String, Object> param);

	int getUserLoginLogCount(Map<String, Object> param);
	
}
