package com.woke.bean;

import java.io.File;
import java.util.List;

import com.woke.dao.WokeDao;
import com.woke.dao.BaoM;
import com.woke.dao.GuangG;
import com.woke.dao.User;

public class BaoMBean {

	public static User login(String username, String password) {
		String sql = "select * from user where username='" + username + "' and password = '" + password + "'";
		List<User> list = User.getData(sql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	public static GuangG getGuangGByid(int id) {
		String sql = "select * from guanggao where id="+id;
		return GuangG.getGuangG(sql);
	}
	public static void addCount(int count,int id) {
		int d=++count;
		String sql ="update guanggao set count='"+d+"' where id='"+id+"'";
		GuangG.addCount(sql);
	}
	public static List getbaomingList() {
		String sql = "select * from baoming order by id";
		return BaoM.getData(sql);
	}

	public static List getUserList() {
		String sql = "select * from user order by id";
		return User.getData(sql);
	}

	public static List getGuangGList() {
		String sql = "select * from guanggao";
		return GuangG.getData(sql);
	}

	public static List getbaomingById(String id) {
		String sql = "select * from baoming where id = " + id;
		String sql1 = "select * from baoming";
		return BaoM.getData(sql);
	}

	public static void addbaoming(String name, String tel, String address, String brand) {
		String sql = "insert into baoming(name,tel,address,brand) values('" + name + "','" + tel + "','" + address
				+ "','" + brand + "')";
		WokeDao.updateMySqlData(sql);
	}

	public static void adduser(String username, String password, String role) {
		String sql = "insert into user(username,password,role) values('" + username + "','" + password + "','" + role
				+ "')";
		WokeDao.updateMySqlData(sql);
	}

	public static void addguanggao(String logo,String img, String title, String centent, String datetime) {
		String sql = "insert into guanggao(logo,img,title,centent,datetime) values('"+logo+"','" + img + "','" + title + "','" + centent
				+ "','" + datetime + "')";
		WokeDao.updateMySqlData(sql);
	}

	public static void deletebaoming(String id) {
		String sql = "delete from baoming where id=" + id;
		WokeDao.updateMySqlData(sql);
	}

	public static void deleteuser(String id) {
		String sql = "delete from user where id=" + id;
		WokeDao.updateMySqlData(sql);
	}

	public static void deleteguanggao(String id) {
		String sql = "delete from guanggao where id=" + id;
		WokeDao.updateMySqlData(sql);
	}

	public static void updatebaoming(String id, String name, String tel, String address, String brand) {
		String sql = "update baoming set name='" + name + "',tel='" + tel + "',address='" + address + "',brand='"
				+ brand + "' where id=" + id;
		WokeDao.updateMySqlData(sql);
	}

	public static void updateuser(String id, String username, String password, String role) {
		String sql = "update user set username='" + username + "',password='" + password + "',role='" + role
				+ "' where id=" + id;
		WokeDao.updateMySqlData(sql);
	}

	public static void updateguang(String id,File logo, File img, String title, String centent, String datetime) {
		String sql = "update guanggao set logo='"+logo+"',img='" + img + "',title='" + title + "',centent='" + centent + "',datetime='"
				+ datetime + "' where id=" + id;
		WokeDao.updateMySqlData(sql);
	}

	public static boolean checkbaoming(String name) {
		String sql = "select * from baoming where name='" + name + "'";
		List<BaoM> list = BaoM.getData(sql);
		if (list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static List selectBM(String name) {
		String sql = "select * from baoming where 1=1";
		if (!name.equals("")) {
			sql += " and name like '%" + name + "%'";
		}
		return BaoM.getData(sql);
	}

	public static List selectGuangG(String id) {
		String sql = "select * from guanggao where 1=1";
		if (!id.equals("")) {
			sql += " and id =" + id;
		}
		return GuangG.getData(sql);
	}

}
