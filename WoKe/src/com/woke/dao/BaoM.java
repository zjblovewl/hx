package com.woke.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaoM {
	private int id;
	private String name;
	private String tel;
	private String address;
	private String brand;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public BaoM() {

	}

	public BaoM(int id, String name, String tel, String address, String brand) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.brand = brand;
	}

	
	
	
	
	public List<BaoM> selectLend(int pageNow, int pageSize){

		List<BaoM> list = new ArrayList<BaoM>();
		Connection conn = WokeConnection.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			int startIndex =0;
			int endIndex = pageSize;
			String sql = "select  * from baoming ";
			String sw = " where " + " 1=1 " + " order by id Desc ";
			// sql +=" where " + strWhere ;
			if (pageNow > 1) {
				startIndex = pageSize * (pageNow - 1);
				// endIndex = pageSize * currentPage;
			}
			sql += sw + " limit " + startIndex + "," + endIndex;
			
			 pst= conn.prepareStatement(sql);
			 rs= pst.executeQuery();
			while(rs.next()){
				BaoM us = new BaoM();
			    us.setId(rs.getInt(1));
			    us.setName(rs.getString(2));
			    us.setTel(rs.getString(3));
			    us.setAddress(rs.getString(4));
			    us.setBrand(rs.getString(5));
				list.add(us);
			}
			return list;
		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			return null;

		}finally{

			WokeConnection.closeMySql(conn, pst, rs);

		}

	}

	

	public int selectLendSize(){
		Connection conn = WokeConnection.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			 pst = conn.prepareStatement("select count(*) from baoming");
			 rs = pst.executeQuery();
			if(rs.next()){
				int pagecount = rs.getInt(1);
				return pagecount;
			}
			return 0;
		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			return 0;

		}finally{
			WokeConnection.closeMySql(conn, pst, rs);
		}
	}


	public static List<BaoM> getData(String sql) {
		Connection con = null;
		 PreparedStatement st = null;
		ResultSet rs = null;
		List<BaoM> list = new ArrayList<BaoM>();
		try {

			con = WokeConnection.getConnection();
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				String brand = rs.getString("brand");
				BaoM b = new BaoM(id, name, tel, address, brand);
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, rs);
		}
		return list;
	}

}
