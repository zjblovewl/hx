package com.woke.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ��ݿ����
 * 
 * @author Administrator
 * 
 */
public class WokeConnection {

	/**
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://39.108.85.212:3306/woke?characterEncoding=utf-8", "jack", "www..1994");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	/**
	 * @param con
	 * @param st
	 * @param rs
	 */
	public static void closeMySql(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
