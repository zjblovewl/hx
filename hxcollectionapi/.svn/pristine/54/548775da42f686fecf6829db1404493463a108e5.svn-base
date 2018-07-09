/**  
 * @类功能说明： 操作日志公共类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：jingjing
 * @创建时间：2018年04月12日 上午17:30:00
 * @版本：V1.0  
 */
package cn.com.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import cn.com.mobile.user.service.UserService;

public class LogUtil {
	static UserService userService = (UserService) ApplicationContextHelper.getBean("userService");
	
	/**
	 * 保存操作日志
	 * @param request
	 * @param operation
	 */
	public static void saveLog(HttpServletRequest request,String operation,String errorMsg){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String userName = (String) request.getSession().getAttribute("userName");
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
		paramsMap.put("id", NumberUtil.getSysJournalNo(32, false));
		paramsMap.put("userName", userName);
		paramsMap.put("ipAddress", ip);
		paramsMap.put("operation", operation);
		paramsMap.put("errorMsg", errorMsg);
		userService.saveLog(paramsMap);
	}
}
