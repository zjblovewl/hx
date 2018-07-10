package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import cn.com.hxfz.model.Role;

public interface SysRoleService {
	/**
	 * 方法功能说明：获取登录用户的角色信息
	 * 创建：2018年03月22日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public Role findRoleByRoleId(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：根据角色id获取菜单列表
	 * 创建：2018年03月22日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getMenuListByRoleId(Map<String, Object> paramsMap);
	/**
	 * 方法功能说明：查询角色
	 * 创建：2018年04月01日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getRoleRecords(Map<String, Object> paramsMap);
	/**
	 * @description 查询角色列表
	 * @method  getRoleList
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月4日 上午9:35:42
	 * @author:liugui
	 */
	public List<Map<String, Object>> getRoleList();
	
	/**
	 * 方法功能说明：查询角色数量
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public int getRoleCount(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：校验角色是否已存在
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public Boolean checkSameName(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：保存角色
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public void saveOrUpdateRole(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：删除角色
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public void deleteRoleById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：获取角色权限
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public List<Map<String, Object>> getRolePermission(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：获取权限菜单数据
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public JSONArray getMenuTreeRecords();
	
	/**
	 * 方法功能说明：保存角色权限
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public void saveRolePermission(Map<String, Object> paramsMap);
}
