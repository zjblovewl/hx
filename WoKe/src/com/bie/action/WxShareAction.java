package com.bie.action;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.woke.api.JsApi;
import com.woke.api.WeiXinApi;
import com.woke.bean.BaoMBean;
import com.woke.context.WeiXinApplicationContext;
import com.woke.dao.BaoM;
import com.woke.dao.GuangG;
import com.woke.dao.User;
import com.woke.entity.JsTicket;
import com.woke.entity.Token;
import com.woke.util.WeiXinUtils;

public class WxShareAction implements Action, ServletRequestAware, ServletResponseAware, ServletContextAware {
	private HttpServletRequest request;
	private HttpServletResponse responese;
	private ServletContext application;
	private String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String execute() throws Exception {

		return null;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.responese = response;

	}

	public void setServletContext(ServletContext application) {
		this.application = application;

	}

	/**
	 * 跳转至微信分享页面
	 * @description 
	 * @method  WxSharePage
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @date: 2018年7月6日 下午3:45:48
	 * @author:zhoujinbing
	 */
	public String WxSharePage() throws Exception {
		request.setAttribute("id", this.getId());
		
		String appid = WeiXinApplicationContext.APP_ID;//微信公众号APPID
		String secret = WeiXinApplicationContext.APP_SECRET;//微信公众号SECRET
		
		//获取access
		Token token = WeiXinApi.token(appid, secret);
		System.out.println("access_token:" + token.getAccess_token());
		
		JsTicket ticket = JsApi.getJSTicketByAccessToken(token.getAccess_token());
		System.out.println("js_ticket:" + ticket.getTicket());
		
		String nonce_str = WeiXinUtils.create_nonce_str();
        String timestamp = WeiXinUtils.create_timestamp();
        
        String string1;
        String signature = "";
        String strBackUrl = WeiXinApplicationContext.DOMAIN_URL + "/WoKe/WxSharePage";
        if(request.getQueryString() != null)
        {
            strBackUrl += "?" + (request.getQueryString()); 
        }
        
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + ticket.getTicket() +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + strBackUrl;
        System.out.println( "加密参数:" + string1 );

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes("UTF-8"));
        signature = WeiXinUtils.byteToHex(crypt.digest());
        System.out.println( "签名过后:" + signature );
        
        request.setAttribute( "appId", appid );
        request.setAttribute( "timestamp", timestamp );
        request.setAttribute( "nonceStr", nonce_str );
        request.setAttribute( "signature", signature );
        request.setAttribute("url", WeiXinApplicationContext.DOMAIN_URL);
		
		return "index";
	}

}
