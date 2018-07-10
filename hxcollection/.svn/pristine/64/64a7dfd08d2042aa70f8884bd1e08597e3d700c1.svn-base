package cn.com.hxfz.controller;


import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hxfz.model.User;
import cn.com.hxfz.service.SysMenuService;
import cn.com.hxfz.service.SysRoleService;
import cn.com.hxfz.service.SysUserService;
import cn.com.hxfz.util.CipherUtil;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.ExcelModelNew;
import cn.com.hxfz.util.LogUtil;
import cn.com.hxfz.util.ServletHelp;
import cn.com.hxfz.util.StringUtil;
import cn.com.hxfz.util.StringUtils;
/**  
 * @类功能说明：登录  Controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：zhangxin  
 * @创建时间：2017年9月12日 上午11:20:03  
 * @版本：V1.0  
 */
@Controller
@RequestMapping("/user")
public class SysUserController {
	private SysUserService sysUserService;
	private SysRoleService sysRoleService;
	private SysMenuService sysMenuService;
	
	public SysUserService getSysUserService() {
		return sysUserService;
	}
	@Autowired
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}
	@Autowired
	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}
	@Autowired
	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}
	/**
	 * 
	 * 方法功能说明：  跳转登录页面
	 * 创建：2018年3月5日 by youwenbo   
	 * 参数： @param request
	 * 参数： @param response
	 * 参数： @return      
	 * return String     
	 * throws
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}
	
	/**  
	 * 方法功能说明：  登录
	 * 创建：2018年03月21日 by liugui
	 * 参数： @param request
	 * 参数： @param response
	 * 参数： @return      
	 * return String   1：登录成功！ 2:用户名不存在 3:密码输入错误 4:用户没有登录权限  
	 * throws  
	 */
	@RequestMapping("/login.do")
	@ResponseBody 
	public int login(HttpServletRequest request,HttpServletResponse response) {
		try {
			int result = 0;
			//创建session对象
            HttpSession session = request.getSession();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userName", name);
			map.put("password", password);
			User user = sysUserService.findUserByUserNameAndPassword(map);
			//
			if(user!=null){
				session.setAttribute("user", user);
				map.put("userId", user.getUserId());
				List<Map<String,Object>> roleList = sysUserService.getRoleListByUserId(map);
				if(roleList != null && roleList.size()>0){
					//获取当前登录人的权限
					JSONArray currUserPermission = sysMenuService.getCurrUserPermission(map);								            
		            session.setAttribute("userPermission", currUserPermission);
		            result = 1;
				}else{
					result = 4;
				}	
			}else{
				result = sysUserService.checkUserLoginInfo(map);
			}
			return result;
		}catch(Exception e) {
			System.err.println("用户登录信息获取失败，请重新登录");
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	* 方法功能说明： 退出登录
	* 创建：2018年03月27日 by liugui
 	* @param request the request
 	* @param response the response
 	* @return the string
 	*/
	@RequestMapping(value = "/logout.do")  
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		LogUtil.saveLog(request, "退出系统","");
		request.getSession().removeAttribute("user");	    
		request.getSession().removeAttribute("userPermission");		
	    return "redirect:/login.jsp";
	}
	/**
	 * 方法功能说明：用户查询
	 * 创建：2018年03月27日 by liugui
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getUserRecords.do")
	public void getUserRecords(HttpServletRequest request,HttpServletResponse response,
			Integer pageSize, Integer pageIndex,String userName,String loginName){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {		
			operation = "查看【用户管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("offset", pageIndex);
			paramsMap.put("limit", pageSize);	
			paramsMap.put("userName", userName);
			paramsMap.put("loginName", loginName);
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<Map<String, Object>> resultList = sysUserService.getUserRecords(params);
			int totalCount = sysUserService.getUserCount(params);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", resultList);
			map.put("total", totalCount);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}
	
	/**
	 * 方法功能说明：重置密码
	 * 创建：2018年03月27日 by liugui
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/refreshPassword.do")
	public void refreshPassword(HttpServletRequest request,HttpServletResponse response,String userId,String userName){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {		
			operation = "重置密码【用户管理】:"+userName;
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("userId", userId);		
			paramsMap.put("password", CipherUtil.generatePassword("123456"));//重置密码为:123456
			sysUserService.refreshPassword(paramsMap);
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "重置密码成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}
	
	/**
	 * 方法功能说明：查询用户信息
	 * 创建：2018年03月27日 by liugui
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getUserDetail.do")
	public void getUserDetail(HttpServletRequest request,HttpServletResponse response,String userId){
		try {					
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("userId", userId);			
			Map<String, Object> userMap = sysUserService.getUserDetail(paramsMap);			
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("result", userMap);
			JSONObject jsonObj = JSONObject.fromObject(map);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法功能说明：校验用户是否已存在
	 * 创建：2018年03月27日 by liugui
	 * @param request
	 * @param response
	 */	
	@RequestMapping(value="/checkSameUserName.do")
	@ResponseBody
	public int checkSameUserName(HttpServletRequest request,HttpServletResponse response,String userId,String userName){
		//result 0:校验通过,1:存在重复，校验不通过2:用户名不能为空3:系统异常
		int result = 1;
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId);
			paramsMap.put("userName",URLDecoder.decode(userName, "utf-8"));
			if(StringUtil.availableStr(userName)){
				Boolean flag = sysUserService.checkSameUserName(paramsMap);
				result = flag ? 0 : 1;
			}else{
				result = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 3;
		}
		return result;
	}
	/**
	 * 方法功能说明：校验登录名是否已存在
	 * 创建：2018年03月27日 by liugui
	 * @param request
	 * @param response
	 */	
	@RequestMapping(value="/checkSameLoginName.do")
	@ResponseBody
	public int checkSameLoginName(HttpServletRequest request,HttpServletResponse response,String userId,String loginName){
		//result 0:校验通过,1:存在重复，校验不通过2:登录名不能为空3:系统异常
		int result = 1;
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId);
			paramsMap.put("loginName",URLDecoder.decode(loginName, "utf-8"));
			if(StringUtil.availableStr(loginName)){
				Boolean flag = sysUserService.checkSameLoginName(paramsMap);
				result = flag ? 0 : 1;
			}else{
				result = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 3;
		}
		return result;
	}
	/**
	 * 方法功能说明： 校验邮箱是否已存在
	 * 创建：2018年03月27日 by liugui
	 * @param request
	 * @param response
	 */	
	@RequestMapping(value="/checkSameEmailName.do")
	@ResponseBody
	public int checkSameEmailName(HttpServletRequest request,HttpServletResponse response,String userId,String userEmail){
		//result 0:校验通过,1:存在重复，校验不通过2:邮件不能为空3:系统异常
		int result = 1;
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId);
			paramsMap.put("userEmail",URLDecoder.decode(userEmail, "utf-8"));
			if(StringUtil.availableStr(userEmail)){
				Boolean flag = sysUserService.checkSameEmailName(paramsMap);
				result = flag ? 0 : 1;
			}else{
				result = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 3;
		}
		return result;
	}
	/**
	 * @description 保存用户信息
	 * @method  saveOrUpdateUser
	 * @param @param request
	 * @param @param response
	 * @param @param userId
	 * @param @param userName
	 * @param @param userPwd
	 * @param @param loginName
	 * @param @param email
	 * @param @param roleIds
	 * @return void
	 * @date: 2018年4月4日 上午8:53:45
	 * @author:liugui
	 */
	@RequestMapping(value="/saveOrUpdateUser.do")
	public void saveOrUpdateUser(HttpServletRequest request,HttpServletResponse response,
			String userId,String userName,String userPwd,String loginName,String email,
			String roleIds,String imageIds){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			if(StringUtils.isNotEmpty(userId)){
				operation = "编辑【用户管理】："+userName;	
			}else{
				operation = "新增【用户管理】："+userName;	
			}
           				
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("userId", userId);
			paramsMap.put("userName", userName);
			paramsMap.put("password", userPwd);
			paramsMap.put("loginName", loginName);
			paramsMap.put("userEmail", email);
			paramsMap.put("roleIds", roleIds);	
			paramsMap.put("imageIds", imageIds);	
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			sysUserService.saveOrUpdateUser(params);			
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "保存成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}
	
	/**
	 * @description 删除用户信息
	 * @method  deleteUserById
	 * @param @param request
	 * @param @param response
	 * @param @param userIds
	 * @return void
	 * @date: 2018年4月4日 上午8:54:42
	 * @author:liugui
	 */
	@RequestMapping(value="/deleteUserByIds.do")
	public void deleteUserByIds(HttpServletRequest request,HttpServletResponse response,String userIds){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {
			operation = "删除【用户管理】";
			Map<String, Object> paramsMap = new HashMap<String, Object>();	
			paramsMap.put("userIds", userIds);		
			sysUserService.deleteUserById(paramsMap);			
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "删除成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}	
	
	/**
	 * 方法功能说明：校验原密码是否正确
	 * 创建：2017年10月20日 by chenchen  
	 * @param request
	 * @param response
	 */	
	@RequestMapping(value="/checkPassword.do")
	@ResponseBody
	public Boolean checkPassword(HttpServletRequest request,HttpServletResponse response,String userName,String profile_password){
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		try {		
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userName", URLDecoder.decode(userName, "utf-8"));
			paramsMap.put("password",profile_password);
			result = sysUserService.checkPassword(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}		
	
	/**
	 * 方法功能说明：修改用户密码
	 * 创建：2017年10月20日 by chenchen  
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/updatePassword.do")
	public void updatePassword(HttpServletRequest request,HttpServletResponse response,String old_password,String new_password,String userName){
		//operation 操作，errorMsg异常信息
		String operation = "",errorMsg = "";
		try {			
			operation = "修改密码【管理员信息】："+userName;				
           				
			Map<String, Object> paramsMap = new HashMap<String, Object>();											
			paramsMap.put("newPassword", new_password);
			paramsMap.put("userName", URLDecoder.decode(userName, "utf-8"));		
			sysUserService.updatePassword(paramsMap);			
			Map<String, Object> result = new HashMap<String, Object>();			
			result.put("success", true);			
			result.put("resultMsg", "修改成功");			
			JSONObject jsonObj = JSONObject.fromObject(result);		
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}finally{
			LogUtil.saveLog(request,operation,errorMsg);
		}
	}	
	/**
	 * @description 导出系统用户
	 * @method  exportUserdata
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @date: 2018年4月19日 下午2:53:14
	 * @author:liugui
	 */
	@RequestMapping(value="/exportUserdata.do")
	@ResponseBody
	public String exportUserdata(HttpServletRequest request,HttpServletResponse response,String userName,String loginName){
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();	
			paramsMap.put("userName",userName);
			paramsMap.put("loginName",loginName);
			Map<String, Object> params = CommUtils.convertDecode(paramsMap);
			List<Map<String, Object>> resultList = sysUserService.getUserRecordsToExport(params);
			int[] typeArr = new int[] {
					ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING,ExcelModelNew.TYPE_STRING
			};
			short[] columnWidthArr = new short[]{
					ExcelModelNew.WIDTH_4000,ExcelModelNew.WIDTH_4000,ExcelModelNew.WIDTH_6000,ExcelModelNew.WIDTH_6000
					};
			String[] headArr = new String[] { 
					"用户名","登录名","邮箱","创建时间"};
			String[] nameArr = new String[] { 
					"user_name","login_name","email","create_time"};
			ExcelModelNew excelModel = new ExcelModelNew(resultList);
			excelModel.setColumnTypeArr(typeArr);
			excelModel.setColumnHeadArr(headArr);
			excelModel.setColumnNameArr(nameArr);
			excelModel.setColumnWidthArr(columnWidthArr);
			excelModel.setSheetName("用户列表");
			try {										
				String fileName = "用户列表.xls";
				excelModel.exportExcel(fileName, response, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		/*	Map<String, Object> map = new HashMap<String, Object>();			
			map.put("result", resultList);	
			map.put("success", true);	
			JSONObject jsonObj = JSONObject.fromObject(map);
			ServletHelp.outRequestForJson(request, response, jsonObj.toString());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
