package com.bie.action;

import com.woke.dao.MySql;

public class MaxPage {
	public static int getTotalPages(int pageSize){
		int r = 0;
		int counts = MySql.getNums("select count(*) from emp");
		if(counts % pageSize == 0){
			r = counts / pageSize;
		}else{
			r = counts / pageSize + 1;
		}
		return r;
	}
}
