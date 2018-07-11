package com.woke.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * woke项目的数据库操作公用方法的封装类
 * 
 * @author Administrator
 *
 */
public class WokeDao {

	/*
	 * 更新(添加,修改,删除)
	 */

	public static void updateMySqlData(String sql) {
		Connection con = null;
		Statement st = null;
		try {
			// 获取数据连接
			con = WokeConnection.getConnection();

			// 获取语句分析容器
			st = con.createStatement();

			// 执行sql语句
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, null);
		}
	}
}
