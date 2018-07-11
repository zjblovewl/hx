package com.bie.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.woke.bean.BaoMBean;
import com.woke.context.WeiXinApplicationContext;
import com.woke.dao.BaoM;
import com.woke.dao.GuangG;
import com.woke.dao.User;

public class SelectAction implements Action, ServletRequestAware {
	private String name;
	private String id;
	private HttpServletRequest request;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String selectBaoM() throws Exception {
		List<BaoM> list = BaoMBean.selectBM(name);
		ActionContext.getContext().put("BaoMList", list);
		return "lendresult";
	}

	public String selectGuangG() throws Exception {
		List<GuangG> list = BaoMBean.selectGuangG(id);
		ActionContext.getContext().put("GaungGList", list);
		request.setAttribute("url", WeiXinApplicationContext.DOMAIN_URL);
		return "GuangGlist";
	}

}
