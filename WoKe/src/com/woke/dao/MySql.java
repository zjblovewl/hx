package com.woke.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MySql {
	public static int getNums(String sql) {
		int r = 0;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 2、获取数据库连接
			con = (Connection) WokeConnection.getConnection();
			// 3、获取语句分析容器
			st = (Statement) con.createStatement();

			// 4、执行sql获取结果集
			rs = st.executeQuery(sql);

			// 如果结果集有结果，则登录成功
			if (rs.next()) {
				r = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, rs);
		}

		return r;
	}
}
