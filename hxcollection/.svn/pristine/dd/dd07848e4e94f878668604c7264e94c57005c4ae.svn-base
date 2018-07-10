/**  
 * @类功能说明： 操作日志公共类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen
 * @创建时间：2017年10月16日 上午17:30:00
 * @版本：V1.0  
 */
package cn.com.hxfz.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.hxfz.model.Role;
import cn.com.hxfz.model.User;
import cn.com.hxfz.service.SysUserService;

public class LogUtil {
	static SysUserService userService = (SysUserService) ApplicationContextHelper.getBean("sysUserService");
	
	/**
	 * 保存操作日志
	 * @param request
	 * @param operation
	 */
	public static void saveLog(HttpServletRequest request,String operation,String errorMsg){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		User user = (User)request.getSession().getAttribute("user");
		/**
		 * 获取登录系统的ip
		 */
		String ip = "";
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println(">>>  : " + ip);
		paramsMap.put("id", CommUtils.getUUID());
		paramsMap.put("userName", user.getUserName());
		paramsMap.put("ipAddress", ip);
		paramsMap.put("operation", operation);
		paramsMap.put("errorMsg", errorMsg);
		userService.saveLog(paramsMap);
	}
}
