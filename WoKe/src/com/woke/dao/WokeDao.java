package com.woke.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * woke��Ŀ�����ݿ�������÷����ķ�װ��
 * 
 * @author Administrator
 *
 */
public class WokeDao {

	/*
	 * ����(���,�޸�,ɾ��)
	 */

	public static void updateMySqlData(String sql) {
		Connection con = null;
		Statement st = null;
		try {
			// ��ȡ��������
			con = WokeConnection.getConnection();

			// ��ȡ����������
			st = con.createStatement();

			// ִ��sql���
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, null);
		}
	}
}
