package com.bie.action;

import java.io.File;
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
import com.woke.bean.BaoMBean;
import com.woke.context.WeiXinApplicationContext;
import com.woke.dao.BaoM;
import com.woke.dao.GuangG;
import com.woke.dao.User;

public class LoginAction implements Action, ServletRequestAware, ServletResponseAware, ServletContextAware {
	private HttpServletRequest request;
	private HttpServletResponse responese;
	private ServletContext application;
	private String username;
	private String password;
	private String msg;
	private User u;
	private int id;
	private String name;
	private String BmId;
	private String tel;
	private String address;
	private String brand;
	private int pagesize = 10;
	private int totalpage;
	// private String img;
	private String title;
	private String centent;
	private String role;
	private String datetime;
	private File img;
	private String imgContentType;
	private String imgFileName;
	private String destPath;
	private File logo;
	private String logoContentType;
	private String logoFileName;
	private String destPath1;
	
	

	public String getDestPath1() {
		return destPath1;
	}

	public void setDestPath1(String destPath1) {
		this.destPath1 = destPath1;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCentent() {
		return centent;
	}

	public void setCentent(String centent) {
		this.centent = centent;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
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

	public String getBmId() {
		return BmId;
	}

	public void setBmId(String bmId) {
		BmId = bmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getUser() {
		return u;
	}

	public void setUser(User u) {
		this.u = u;
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

	public String login() throws Exception {
		String sql = "select * from user where username='" + username + "' and password = '" + password + "'";
		List<User> list = User.getData(sql);
		List<User> list1 = BaoMBean.getUserList();
		List<User> list2 = BaoMBean.getbaomingList();
		List<User> list3 = BaoMBean.getGuangGList();
		if (list != null && list.size() > 0) {
			int num = list1.size();
			int num1 = list2.size();
			int num2 = list3.size();
			request.getSession().setAttribute("num", num);
			request.getSession().setAttribute("num1", num1);
			request.getSession().setAttribute("num2", num2);
			request.getSession().setAttribute("url", WeiXinApplicationContext.DOMAIN_URL);
			request.getSession().setAttribute("user", list.get(0));
			return "index1";
		} else {
			msg = "用户名或者密码错误";
			return "login";
		}
	}

	public String getList() throws Exception {
		List<BaoM> list = new ArrayList<BaoM>();
		BaoMBean bm = new BaoMBean();
		list = bm.getbaomingList();
		String sql = "select * from baoming";
		List lt = BaoM.getData(sql);
		int number = lt.size();
		int PageS;
		if (number % pagesize == 0) {
			PageS = number / pagesize;
		} else {
			PageS = number / pagesize + 1;
		}
		request.setAttribute("PageS", PageS);
		request.getSession().setAttribute("number", number);
		ActionContext.getContext().put("BaoMList", list);
		return "lendresult";
	}

	public String getUserList() throws Exception {
		List<User> list = new ArrayList<User>();
		BaoMBean bm = new BaoMBean();
		list = bm.getUserList();
		String sql = "select * from user";
		List lt = User.getData(sql);
		int number = lt.size();
		int PageS;
		if (number % pagesize == 0) {
			PageS = number / pagesize;
		} else {
			PageS = number / pagesize + 1;
		}
		request.setAttribute("PageS", PageS);
		request.getSession().setAttribute("number", number);
		ActionContext.getContext().put("Userlist", list);
		return "userlist";
	}

	public String deleUser() throws Exception {
		BaoMBean bm = new BaoMBean();
		bm.deleteuser(BmId);
		return getUserList();
	}

	public String addUser() throws Exception {
		BaoMBean bm = new BaoMBean();
		bm.adduser(username, password, role);
		return getUserList();
	}

	public String addBM() throws Exception {
		BaoMBean bm = new BaoMBean();
		if(name!=null||tel!=null||address!=null||brand!=null) {
		bm.addbaoming(name, tel, address, brand);
		}
		System.out.println(tel);
		msg = "添加客户成功";
		return SUCCESS;
	}

	public String deleBaoM() throws Exception {
		BaoMBean bm = new BaoMBean();
		bm.deletebaoming(BmId);
		msg = "删除客户成功";
		return SUCCESS;
	}

	public String updateBaoM() throws Exception {
		BaoMBean bm = new BaoMBean();
		String s = String.valueOf(id);
		bm.updatebaoming(s, name, tel, address, brand);
		msg = "修改客户信息成功";
		return SUCCESS;
	}

	public String updateUser() throws Exception {
		BaoMBean bm = new BaoMBean();
		String s = String.valueOf(id);
		bm.updateuser(s, username, password, role);
		return getUserList();
	}

	public String updateGuangG() throws Exception {
		BaoMBean bm = new BaoMBean();
		String s = String.valueOf(id);
		bm.updateguang(s,logo, img, title, centent, datetime);
		System.out.println("imgFileName:" + imgFileName);
		System.out.println("logoFileName:" + logoFileName);
		return getGuangGList();
	}

	public String getGuangGList() throws Exception {
		List<GuangG> list = new ArrayList<GuangG>();
		BaoMBean bm = new BaoMBean();
		list = bm.getGuangGList();
		String sql = "select * from guanggao";
		List lt = GuangG.getData(sql);
		int numb = lt.size();
		int PageS;
		if (numb % pagesize == 0) {
			PageS = numb / pagesize;
		} else {
			PageS = numb / pagesize + 1;
		}
		request.setAttribute("PageS", PageS);
		request.setAttribute("url", WeiXinApplicationContext.DOMAIN_URL);
		request.getSession().setAttribute("numb", numb);
		ActionContext.getContext().put("GaungGList", list);
		return "GuangGlist";
	}

	public String addGuangG() throws Exception {
		BaoMBean bm = new BaoMBean();
		destPath = "E:\\eclipse-workspace\\WoKe\\WebContent\\img";
		File destFile = new File(destPath, imgFileName);
		FileUtils.copyFile(img, destFile);
		String img = imgFileName;
	
		 destPath1 = "E:\\eclipse-workspace\\WoKe\\WebContent\\logo";
		File destFile1 = new File(destPath1, logoFileName);
		FileUtils.copyFile(logo, destFile1);
		String logo = logoFileName;
		bm.addguanggao(logo, img, title, centent, datetime);
		return getGuangGList();
	}

	public String deleGuangG() throws Exception {
		BaoMBean bm = new BaoMBean();
		bm.deleteguanggao(BmId);
		return getGuangGList();
	}

}
