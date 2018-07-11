package com.woke.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;

public class GuangG {
	private int id;
	private String logo;
	private String img;
	private String title;
	private String centent;
	private String datetime;
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public GuangG() {

	}

	@Override
	public String toString() {
		return "GuangG [id=" + id + ",logo=" + logo + ", img=" + img + ", title=" + title + ", centent=" + centent + ", datetime="
				+ datetime + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<GuangG> selectLend(int pageNow, int pageSize){

		List<GuangG> list = new ArrayList<GuangG>();
		Connection conn = WokeConnection.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			int startIndex =0;
			int endIndex = pageSize;
			String sql = "select  * from guanggao ";
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
				GuangG g = new GuangG();
				g.setId(rs.getInt("id"));
				g.setLogo(rs.getString("logo"));
				g.setImg(rs.getString("img"));
				g.setTitle(rs.getString("title"));
				g.setCentent(rs.getString("centent"));
				g.setDatetime(rs.getString("datetime"));
				g.setCount(rs.getInt("count"));
				list.add(g);
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
			 pst = conn.prepareStatement("select count(*) from guanggao");
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
	
	
	public static GuangG getGuangG(String sql) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		GuangG g=null;
		try {
			con = WokeConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				g= new GuangG();
				g.setId(rs.getInt("id"));
				g.setLogo(rs.getString("logo"));
				g.setImg(rs.getString("img"));
				g.setTitle(rs.getString("title"));
				g.setCentent(rs.getString("centent"));
				g.setDatetime(rs.getString("datetime"));
				g.setCount(rs.getInt("count"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, rs);
		}
		return g;
	}
	public static void addCount(String sql) {
		Connection con = null;
		Statement st = null;
		int rs = 1;
		GuangG g=null;
	
			con = WokeConnection.getConnection();
			try {
				st = con.createStatement();
				rs = st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public static List<GuangG> getData(String sql) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<GuangG> list = new ArrayList<GuangG>();
		try {
			con = WokeConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				GuangG g = new GuangG();
				g.setId(rs.getInt("id"));
				g.setLogo(rs.getString("logo"));
				g.setImg(rs.getString("img"));
				g.setTitle(rs.getString("title"));
				g.setCentent(rs.getString("centent"));
				g.setDatetime(rs.getString("datetime"));
				g.setCount(rs.getInt("count"));
				list.add(g);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WokeConnection.closeMySql(con, st, rs);
		}
		return list;
	}
  //处理时间的
	public String getYearTime() {
		String year=this.getDatetime().substring(0, 4);
		System.out.println(year);
		return year;
	}
	
	public String getMonthTime() {
		String month=this.getDatetime().substring(5, 7);
		System.out.println(month);
		return month;
		
	}
	public String getDayTime() {
		String day=this.getDatetime().substring(8, 10);
		System.out.println(day);
		return day;
	}
}
