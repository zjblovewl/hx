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
			// 2����ȡ���ݿ�����
			con = (Connection) WokeConnection.getConnection();
			// 3����ȡ����������
			st = (Statement) con.createStatement();

			// 4��ִ��sql��ȡ�����
			rs = st.executeQuery(sql);

			// ���������н�������¼�ɹ�
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
