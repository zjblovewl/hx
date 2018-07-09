package cn.com.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.base.constant.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExcuteTimeInterceptor extends HandlerInterceptorAdapter {
	final static Logger log = LoggerFactory
			.getLogger(ExcuteTimeInterceptor.class);
	
	private NamedThreadLocal<Date>  startTimeThreadLocal = new NamedThreadLocal<Date>("StopWatch-StartTime");  
	private static DateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss:SSS");

	@Override
	public boolean preHandle(HttpServletRequest rquest, HttpServletResponse response,
			Object obj) throws Exception {
        startTimeThreadLocal.set(new Date());//线程绑定变量（该数据只有当前请求的线程可见）
        return true;//继续流程  
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
		Date endTime = new Date();//2、结束时间  
        Date beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
        long consumeTime = endTime.getTime() - beginTime.getTime();//3、消耗的时间
        log.info("请求:" + request.getRequestURI() + "，请求处理开始时间：" + sdf.format(beginTime) + "，请求处理结束时间：" + sdf.format(endTime)
				+ "，耗时：" + consumeTime + "毫秒");
	}
}
