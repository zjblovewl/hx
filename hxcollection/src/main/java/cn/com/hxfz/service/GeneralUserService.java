package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @类功能说明：用户管理业务接口层 
 * @项目名称：  hxcollection
 * @包名：	 cn.com.hxfz.service
 * @类名：	 GeneralUserService.java
 * @公司名称：  南京华讯方舟通讯设备有限公司  
 * @作者：         qiangxuan  
 * @创建时间：   2018年4月18日 下午4:56:49 
 * @版本：         V1.0
 */
public interface GeneralUserService {
	
	/**
	 * 
	 * 方法功能说明： 获取用户列表
	 * 方法名称：  getGeneralUser
	 * 参数：         @param param
	 * 参数：         @return
	 * 返回类型:  List<Map<String,Object>>
	 * 创建时间:  2018年4月18日 下午4:57:28 by qiangxuan
	 *
	 */
	public List<Map<String, Object>> getGeneralUser(Map<String, Object> param);
	/**
	 * 
	 * 方法功能说明： 获取用户数量
	 * 方法名称：  getGeneralUserCount
	 * 参数：         @param param
	 * 参数：         @return
	 * 返回类型:  int
	 * 创建时间:  2018年4月18日 下午4:59:52 by qiangxuan
	 */
	int getGeneralUserCount(Map<String, Object> param);
	/**
	 * 
	 * 方法功能说明： 修改用户
	 * 方法名称：  updateGeneralUser
	 * 参数：         @param paramsMap
	 * 返回类型:  void
	 * 创建时间:  2018年4月18日 下午5:00:22 by qiangxuan
	 */
	public void updateGeneralUser(Map<String, Object> paramsMap);
	/**
	 * 
	 * 方法功能说明： 根据用户id删除用户
	 * 方法名称：  deleteGeneralUser
	 * 参数：         @param paramsMap
	 * 返回类型:  void
	 * 创建时间:  2018年4月18日 下午5:00:30 by qiangxuan
	 */
	public void deleteGeneralUser(Map<String, Object> paramsMap);
	/**
	 * 
	 * 方法功能说明：  根据用户id批量删除用户
	 * 方法名称：  delMoreGeneralUser
	 * 参数：         @param paramsMap
	 * 返回类型:  void
	 * 创建时间:  2018年4月18日 下午5:01:20 by qiangxuan
	 */
	public void delMoreGeneralUser(Map<String, Object> paramsMap);
	/**
	 * 
	 * 方法功能说明： 新增用户
	 * 方法名称：  saveGeneralUser
	 * 参数：         @param paramsMap
	 * 返回类型:  void
	 * 创建时间:  2018年4月18日 下午5:01:43 by qiangxuan
	 */
	public void saveGeneralUser(Map<String, Object> paramsMap);
	/**
	 * 
	 * 方法功能说明： 判断用户名是否存在
	 * 方法名称：  checkSameUserName
	 * 参数：         @param paramsMap
	 * 参数：         @return
	 * 返回类型:  Boolean
	 * 创建时间:  2018年4月18日 下午5:02:03 by qiangxuan
	 */
	public Boolean checkSameUserName(Map<String, Object> paramsMap);
	/**
	 * 
	 * 方法功能说明： 判断手机号是否存在
	 * 方法名称：  checkSamePhone
	 * 参数：         @param paramsMap
	 * 参数：         @return
	 * 返回类型:  Boolean
	 * 创建时间:  2018年4月18日 下午5:02:45 by qiangxuan
	 */
	public Boolean checkSamePhone(Map<Object, String> paramsMap);
	/**
	 * 
	 * 方法功能说明： 获取导出用户
	 * 方法名称：  getUserList
	 * 参数：         @param map
	 * 参数：         @return
	 * 返回类型:  List<Map<String,Object>>
	 * 创建时间:  2018年4月18日 下午5:02:52 by qiangxuan
	 */
	public List<Map<String, Object>> getExportUserList(Map<String, Object> map);
	/**
	 * 
	 * 方法功能说明： 获取用户所有信息，公共用户信息，可调用
	 * 方法名称：  getCommonUserInfo
	 * 参数：         @param param
	 * 参数：         @return
	 * 返回类型:  List<Map<String,Object>>
	 * 创建时间:  2018年4月18日 下午5:02:59 by qiangxuan
	 */
	public List<Map<String, Object>> getCommonUserInfo(Map<String, Object> param);
	/**
	 * 
	 * 方法功能说明： 获取用户数量 公共用户数量
	 * 方法名称：  getCommonUserCount
	 * 参数：         @param param
	 * 参数：         @return
	 * 返回类型:  int
	 * 创建时间:  2018年4月18日 下午5:03:03 by qiangxuan
	 */
	int getCommonUserCount(Map<String, Object> param);
	
	
	/**
	 *
	 * 方法功能说明： 查询省市信息 创建：2018/04/02 by zhangjingjing throws
	 */
	public Map<String, Object> queryAreaInfo(Map<String, Object> param);

	
	/**
	 * 
	 * @description： 根据用户id查询此用户实名认证信息
	 * @method:  queryUserAuditById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年5月8日 下午2:32:06 by qiangxuan
	 */
	public List<Map<String, Object>> getUserAuthInfoById(Map<String, Object> param);
	
	/**
	 * 
	 * @description： 根据用户id保存此用户审核信息结果
	 * @method:  updateAuditInfo
	 * @param:   @param param
	 * @return:  void
	 * @date:    2018年5月8日 下午4:35:20 by qiangxuan
	 */
	void updateAuditInfo(Map<String, Object> param);
	/**
	 * 
	 * @description：  根据用户id查询此用户实名认证信息总数
	 * @method:  getUserAuditCount
	 * @param:   @param paramsMap
	 * @param:   @return
	 * @return:  int
	 * @date:    2018年5月8日 下午5:24:19 by qiangxuan
	 */
	int getUserAuditCount(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description： 根据id更改用户认证信息
	 * @method:  updateAuthenticationStatus
	 * @param:   @param param
	 * @return:  void
	 * @date:    2018年5月8日 下午7:31:53 by qiangxuan
	 */
	void updateAuthenticationStatus(Map<String, Object> param);
		
	
}
