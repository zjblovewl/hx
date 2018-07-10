package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.Menu;

public interface MenuDao {
	/**
	 * 方法功能说明：根据id查询菜单信息
	 * 创建：2018年03月21日 by liugui  
	 * @param paramsMap
	 * @return
	 */
	public Menu getMenuById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：查询菜单
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public List<Map<String, Object>> getMenuList(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：获取菜单详细信息
	 * 创建：2018年03月27日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public Map<String, Object> getMenuDetail(Map<String, Object> paramsMap);
	
	
	/**
	 * 方法功能说明：查询菜单数量
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public int getMenuCount();
	
	/**
	 * 方法功能说明：获取所有一级菜单数据
	 * 创建：2018年03月27日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public List<Menu> getAllMenuRecords(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：新增校验菜单是否已存在 同一级别下的资源名称不能相同
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public int checkAddMenu(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：编辑校验菜单是否已存在 同一级别下的资源名称不能相同
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public int checkEditMenu(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：保存菜单
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public void saveMenu(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：修改菜单
	 * 创建：2018年03月27日 by liugui  
	 * @return
	 */
	public void updateMenuById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：删除菜单
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public void deleteMenuById(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：删除角色菜单关系
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public void deleteRoleAndMenuByMenuId(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：根据菜单id获取子节点
	 * 创建：2018年03月27日 by liugui 
	 * @return
	 */
	public List<Map<String, Object>> getChildMenuByMenuId(Map<String, Object> paramsMap);
	
 }
