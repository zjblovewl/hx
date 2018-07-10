package cn.com.hxfz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.RoleDao;
import cn.com.hxfz.model.Role;
import cn.com.hxfz.service.SysRoleService;
import cn.com.hxfz.util.CommUtils;
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * 方法功能说明：获取登录用户的角色信息
	 * 创建：2018年03月26日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public Role findRoleByRoleId(Map<String, Object> paramsMap){
		Role role = roleDao.findRoleByRoleId(paramsMap);
		return role;
	}
	
	/**
	 * 方法功能说明：根据角色id获取菜单列表
	 * 创建：2018年03月26日 by liugui
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getMenuListByRoleId(Map<String, Object> paramsMap){
		return roleDao.getMenuListByRoleId(paramsMap);
	}
	
	/**
	 * 方法功能说明：查询角色
	 * 创建：2018年04月03日 by liugui  
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> getRoleRecords(Map<String, Object> paramsMap){
		return roleDao.getRoleRecords(paramsMap);
	}
	/**
	 * @description 查询角色列表
	 * @method  getRoleList
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月4日 上午9:35:42
	 * @author:liugui
	 */
	public List<Map<String, Object>> getRoleList(){
		return roleDao.getRoleList();
	}
	/**
	 * 方法功能说明：查询角色数量
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public int getRoleCount(Map<String, Object> paramsMap){
		return roleDao.getRoleCount(paramsMap);
	}
	
	/**
	 * 方法功能说明：校验角色是否已存在
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public Boolean checkSameName(Map<String, Object> paramsMap){
		int count = 0;
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		//roleId不为空,则为编辑
		if(paramsMap.get("roleId")!=null && paramsMap.get("roleId") != ""){
			count = roleDao.checkEditRole(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}			
		}else{
			count = roleDao.checkAddRole(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}	
		}
		return result;
	}
	
	/**
	 * 方法功能说明：保存角色
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public void saveOrUpdateRole(Map<String, Object> paramsMap){		
		//roleId不为空,编辑
		if(paramsMap.get("roleId") != null && paramsMap.get("roleId") != ""){
			roleDao.updateRoleById(paramsMap);
		}else{
			String roleId = CommUtils.getUUID();
			paramsMap.put("roleId", roleId);
			roleDao.saveRole(paramsMap);
		}
	}
	
	/**
	 * 方法功能说明：删除角色
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public void deleteRoleById(Map<String, Object> paramsMap){
		roleDao.deleteRoleById(paramsMap);
		roleDao.deleteRoleMenuByRoleId(paramsMap);
		roleDao.deleteUserRoleByRoleId(paramsMap);
	}
	
	/**
	 * 方法功能说明：获取角色权限
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public List<Map<String, Object>> getRolePermission(Map<String, Object> paramsMap){
		return roleDao.getRolePermission(paramsMap);
	}
	
	/**
	 * 方法功能说明：获取权限菜单数据
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public JSONArray getMenuTreeRecords(){
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = null;
		setFirstMenu(jsonArr);
		List<Map<String, Object>> rootDirectoyList = roleDao.getMenuRootDirectoryRecords();
		if(rootDirectoyList != null && rootDirectoyList.size() > 0){
			for (int i = 0; i < rootDirectoyList.size(); i++) {
				jsonObj = new JSONObject();
				jsonObj.put("menuId", rootDirectoyList.get(i).get("id"));
				jsonObj.put("menuName", rootDirectoyList.get(i).get("menu_name"));
				jsonObj.put("parentId", rootDirectoyList.get(i).get("parent_id"));
				jsonObj.put("menuIcon", rootDirectoyList.get(i).get("menu_icon"));
				jsonArr.add(jsonObj);
				//递归
				setChild(rootDirectoyList.get(i).get("id"),jsonArr);
			}
		}
		return jsonArr;		 
	}
	/**
	 * @description 设置首级菜单
	 * @method  setFirstMenu
	 * @param @param jsonArr
	 * @return void
	 * @date: 2018年4月25日 下午3:14:33
	 * @author:liugui
	 */
	private  void setFirstMenu(JSONArray jsonArr) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("menuId", "0");
		jsonObj.put("menuName", "功能菜单");
		jsonObj.put("parentId", "0000000");
		jsonObj.put("menuIcon", "fa-meetup");
		jsonObj.put("open","true");
		jsonArr.add(jsonObj);
	}
	/**
	 * 
	 * @description 递归查询菜单子类并封装数据
	 * @method  setChild
	 * @param @param id
	 * @param @param jsonArr
	 * @param @return
	 * @return JSONArray
	 * @date: 2018年4月10日 下午7:11:04
	 * @author:liugui
	 */
	private JSONArray setChild(Object id, JSONArray jsonArr) {
		JSONObject jsonObj = null;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("menuId", id);
		List<Map<String, Object>> childMenuList = roleDao.getChildMenusByParentId(paramsMap);
		if(childMenuList != null && childMenuList.size() > 0){
			for (int j = 0; j < childMenuList.size(); j++) {
				jsonObj = new JSONObject();
				jsonObj.put("menuId", childMenuList.get(j).get("id"));
				jsonObj.put("menuName", childMenuList.get(j).get("menu_name"));
				jsonObj.put("parentId", childMenuList.get(j).get("parent_id"));
				jsonArr.add(jsonObj);
				setChild(childMenuList.get(j).get("id"),jsonArr);
			}
		}
		return jsonArr;
	}
	
	/**
	 * 方法功能说明：保存角色权限
	 * 创建：2018年04月03日 by liugui  
	 * @return
	 */
	public void saveRolePermission(Map<String, Object> paramsMap){
		//删除当前角色所有权限
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", paramsMap.get("roleId"));
		roleDao.deleteRoleMenuByRoleId(map);
		//重新设置当前角色的权限
		String checkedList = paramsMap.get("checkedList").toString();
		String[] checkedArr = checkedList.split(",");
		if(checkedArr != null && checkedArr.length > 0){
			for (int i = 0; i < checkedArr.length; i++) {
				map.put("id", CommUtils.getUUID());
				map.put("menuId", checkedArr[i]);
				roleDao.saveRoleAndMenu(map);
			}
		}
	}
	
}
