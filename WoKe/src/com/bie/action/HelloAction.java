package com.bie.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woke.bean.BaoMBean;
import com.woke.dao.BaoM;
import com.woke.dao.GuangG;
import com.woke.dao.User;
import com.woke.dao.WokeDao;

public class HelloAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware {
	private HttpServletRequest request;
	private HttpServletResponse responese;
	private ServletContext application;
	private String name;
	private String tel;
	private String address;
	private String brand;
	private BaoM bm;
	private String msg;
	private String id;
	private String datetime;
	private String pageid;
	
	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BaoM getBm() {
		return bm;
	}

	public void setBm(BaoM bm) {
		this.bm = bm;
	}

	public HttpServletResponse getResponese() {
		return responese;
	}

	public void setResponese(HttpServletResponse responese) {
		this.responese = responese;
	}

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.responese = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	public String addBao() throws Exception {
		BaoMBean bm = new BaoMBean();
		System.out.println("tel"+tel);
		String sql = "select * from baoming where tel='" + tel + "'";
		List<BaoM> list = BaoM.getData(sql);
		if(tel!=null && list.size()<=0 ) {
			msg = "报名成功";
			bm.addbaoming(name, tel, address, brand);
			}else {
				msg="该手机号已报名,请勿重复提交";
			}
	       ActionContext ac = ActionContext.getContext();
	       ac.getSession().put("id",pageid);
		return "index";
		
	}

}
