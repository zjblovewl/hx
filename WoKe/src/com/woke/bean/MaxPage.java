package com.woke.bean;

public class MaxPage {
	/**
	 * 计算要求下的数据量需要的页数
	 */
	public static int getMaxPage(int count, int pageSize) {
		int c = 0;
		if (count % pageSize ==0) {
			c = count / pageSize;
		} else {
			c = count / pageSize + 1;
		}
		return c;
	}
}
