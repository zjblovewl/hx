package com.woke.bean;

public class MaxPage {
	/**
	 * ����Ҫ���µ���������Ҫ��ҳ��
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
