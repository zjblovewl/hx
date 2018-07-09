/**     
 * @文件名称: StudentDao.java  
 * @类路径: 	cn.com.dao  
 * @描述: 	TODO  
 * @作者：	user 
 * @时间：	2018年1月2日 下午4:00:40  
 * @版本：V1.0     
 */
package cn.com.mobile.user.dao;



import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：  
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：user  
 * @创建时间：2018年1月2日 下午4:00:40  
 * @版本：V1.0  
 */
public interface UserDao {
	
	List<Object> getUserList(Map<String,String> paramMap);

	Map<String, Object> login(Map<String,String> paramMap);
	Map<String, Object> thirdLogin(Map<String,String> paramMap);
	
	void insertRegisterUser(Map<String,String> paramMap);
	
	void insertMessageRecord(Map<String,String> paramMap);
	
	List <Map<String, Object>>queryAreaInfo();
	
	void updateUserInfo(Map<String,String> paramMap);
	
	
	void forgotPwd(Map<String,String> paramMap);
	
	void saveAddress(Map<String,String> paramMap);
	
	void updateAddress(Map<String,String> paramMap);
	
	List<Object> selectAddressInfo(Map<String,String> paramMap);
	
	int selectCountByPhone(Map<String,String> paramMap);
	
	int selectCountByThirdId(Map<String,String> paramMap);
	
	void insertLoginLog(Map<String,String> paramMap);
	
	void updateLoginLogName(Map<String,String> paramMap);
	
	void updateUserLoginSum(Map<String,String> paramMap);
	
	void saveLog(Map<String, Object> paramsMap);
	
	void saveOpinionFeedbac(Map<String, String> paramsMap);
	
	void updateOpinionFeedbac(Map<String, String> paramsMap);
	
	void realnameAuthentication(Map<String, String> paramsMap);
	
	void thirdBindPhone(Map<String, String> paramsMap);

	/**
	 * @description 修改余额
	 * @method  
	 * @param  * @param null
	 * @return 
	 * @date: 2018/5/8 17:32:23
	 * @author:zhoujinbing
	 */
	void updateWalletBalance(@Param("user_id") String user_id, @Param("money")BigDecimal money);
}
