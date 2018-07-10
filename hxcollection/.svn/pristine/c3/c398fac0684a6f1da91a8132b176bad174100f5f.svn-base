package cn.com.hxfz.Interceptor;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.hxfz.util.Configuration;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hxfz.model.User;


public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		
		String 	url = request.getRequestURL().toString();
		//System.out.println("当前地址是："+url);
		if(url.indexOf("user/login.do")!=-1){
			return true;
		}
//		String getRequestJson = this.getRequestJson(request);
		HttpSession session = request.getSession(true);
		// session中获取用户名信息
		User user = (User)session.getAttribute("user");
		if(user!=null && user.getUserName()!=null && !"".equals(user.getUserName())){
			request.getSession().setAttribute("localIp", Configuration.getInstance().getValue("localIp"));
			return true;
		}
		//如果判断是 AJAX 请求,直接设置为session超时
		if( request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")  ) {
			response.setHeader("sessionStatus", "timeout");  
			response.sendError(518, "session timeout.");  
		} else {
          //不符合条件的，跳转到登录界面
			String urlStr = request.getRequestURL().substring(0, request.getRequestURL().length()-request.getRequestURI().length())+"/resources/login.jsp";
			response.sendRedirect(urlStr);
		}
		return false;
	}
	
	/**
	 * 获取参数中的json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getRequestJson(HttpServletRequest request) throws Exception {
		
		ServletInputStream sis;
		String jsonRequest = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//获得本机IP

			// log4j用的IP
			MDC.put("IP", ip);
			
			sis = request.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = 0;
			byte[] buf = new byte[4096];
			while((read = sis.read(buf)) > 0) {
				baos.write(buf, 0, read);
				baos.flush();
			}
			jsonRequest = new String(baos.toByteArray(), "UTF-8");
			sis.close();
			baos.close();
		} catch (IOException e) {
			e.getMessage();
		}
		
	
		return jsonRequest;
	}
}
