package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.Menu;
import net.sf.json.JSONArray;

public interface SysMenuService {

	/**
	 * 方法功能说明：获取当前登录人的权限
	 * 创建：2018年03月26日 by liugui 
	 * @return
	 */
	public JSONArray getCurrUserPermission(Map<String, Object> paramsMap);
	/**
	 * 方法功能说明：查询菜单
	 * 创建：2018年03月27日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getMenuRecords(Map<String, Object> paramsMap);
	
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
	 * 方法功能说明：获取菜单详细信息
	 * 创建：2018年03月27日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public Map<String, Object> getMenuDetail(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：校验菜单是否已存在 同一级别下的资源名称不能相同
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public Boolean checkSameMenuName(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：保存菜单
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public void saveOrUpdateMenu(Map<String, Object> paramsMap);
	
	/**
	 * 方法功能说明：删除菜单
	 * 创建：2018年03月27日 by liugui
	 * @return
	 */
	public void deleteMenuById(Map<String, Object> paramsMap);
}
