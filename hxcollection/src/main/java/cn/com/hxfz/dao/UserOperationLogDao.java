package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

public interface UserOperationLogDao {

	List<Map<String, Object>> getUserOperationLogList(Map<String, Object> param);

	int getUserOperationLogCount(Map<String, Object> param);

}
