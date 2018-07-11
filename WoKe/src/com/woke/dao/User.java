package com.woke.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public User() {
		super();
	}

	public User(int id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	
	
	public static List<User> getData(String sql) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {

			con = WokeConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");
				User u = new User(id, username, password, role);
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, rs);
		}
		return list;
	}
}
