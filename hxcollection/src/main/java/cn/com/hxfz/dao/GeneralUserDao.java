package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：用户管理数据访问接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年4月2日 上午11:32:44 
 * @版本：V1.0  
 */
public interface GeneralUserDao {
	
	List <Map<String, Object>>queryAreaInfo(Map<String, Object> param);
	
	List<Map<String,Object>>  getGeneralUser(Map<String, Object> param);

	int getGeneralUserCount(Map<String, Object> param);

	void updateGeneralUserById(Map<String, Object> paramsMap);

	void saveGeneralUser(Map<String, Object> paramsMap);

	void deleteGeneralUserById(Map<String, Object> paramsMap);

	void delMoreGeneralUser(Map<String, Object> paramsMap);

	int checkEditUser(Map<String, Object> paramsMap);

	int checkAddUser(Map<String, Object> paramsMap);

	int checkEditPhone(Map<Object, String> paramsMap);

	int checkAddPhone(Map<Object, String> paramsMap);

	List<Map<String, Object>> getExportUserList(Map<String, Object> map);

	List<Map<String, Object>> getCommonUserInfo(Map<String, Object> param);

	int getCommonUserCount(Map<String, Object> param);

	void updateUserReceiveAddressById(Map<String, Object> paramsMap);

	List<Map<String, Object>> getUserAuthInfoById(Map<String, Object> param);

	void updateUserAuditInfoById(Map<String, Object> param);

	int getUserAuditInfoCount(Map<String, Object> paramsMap);

	void updateUserAnthById(Map<String, Object> param);
	
}
