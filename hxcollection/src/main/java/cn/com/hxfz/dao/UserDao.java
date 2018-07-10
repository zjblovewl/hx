package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.User;



public interface UserDao {
	/**
	 * 方法功能说明：登录
	 * 创建：2018年03月21日 by liugui  
	 * @param paramsMap
	 * @return
	 */
	public User findUserByUserNameAndPassword(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：登录
	 * 创建：2018年03月21日 by liugui  
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getRoleListByUserId(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：保存日志
	 * 创建：2018年03月27日 by liugui  
	 * @param paramsMap
	 * @return
	 */
	public void saveLog(Map<String, Object> paramsMap);
	/**
	 * 方法功能说明：查询用户
	 * 创建：2018年03月27日 by liugui  
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getUserRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 导出用户
	 * @method  getUserRecordsToExport
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月2日 上午9:22:54
	 * @author:liugui
	 */
	public List<Map<String, Object>> getUserRecordsToExport(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：查询用户数量
	 * 创建：2018年03月27日 by liugui  
	 * @return
	 */
	public int getUserCount(Map<String, Object> params);
	
	/**
	 * 方法功能说明：重置密码
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public void refreshPassword(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：查询用户详细信息
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public Map<String, Object> getUserDetail(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：新增校验用户是否已存在
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public int checkAddUser(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：编辑校验用户是否已存在
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public int checkEditUser(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：新增校验登录名是否已存在
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public int checkAddLoginName(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：编辑校验登录名是否已存在
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public int checkEditLoginName(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：新增校验邮箱是否已存在
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public int checkAddEmail(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：编辑校验邮箱是否已存在
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public int checkEditEmail(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：保存用户
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public void saveUser(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：修改用户
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public void updateUserById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：删除用户
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public void deleteUserById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：修改用户密码
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	public void updatePassword(Map<String, Object> paramsMap);
	
	/**
	 * @description 保存用户与角色关系
	 * @method  saveUserAndRole
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月3日 下午5:01:44
	 * @author:liugui
	 */
	public void saveUserAndRole(Map<String, Object> paramsMap);
	/**
	 * @description 删除用户与角色关系
	 * @method  deleteUserAndRoleById
	 * @param 
	 * @return void
	 * @date: 2018年4月4日 上午9:24:12
	 * @author:liugui
	 */
	public void deleteUserAndRoleById(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description  删除用户与角色关系
	 * @method  deleteUserRoleById
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月8日 下午3:40:52
	 * @author:liugui
	 */
	public void deleteUserRoleById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：修改用户密码
	 * 创建：2018年03月27日 by liugui    
	 * @return
	 */
	/**
	 * @description 验证用户名是否存在
	 * @method  checkUserName
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月24日 下午2:00:58
	 * @author:liugui
	 */
	public int checkUserName(Map<String, Object> paramsMap);
	/**
	 * @description 验证用户密码是否正确
	 * @method  checkUserPwd
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月24日 下午2:01:50
	 * @author:liugui
	 */
	public int checkUserPwd(Map<String, Object> paramsMap);
	
}