package com.bie.action;

import java.util.List;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.woke.dao.BaoM;
import com.woke.dao.Pager;

public class LendAction extends ActionSupport{

		private int pageNow=1;           //初始页为第一页

		private int pageSize=10;			 //每页数据为4条，可调节
		public String execute() throws Exception{
			BaoM dao = new BaoM();
			List<BaoM> list = dao.selectLend(pageNow, pageSize);
			Pager page = new Pager(pageNow, dao.selectLendSize());
			Map session = ActionContext.getContext().getSession();
			session.put("userinfo", list);
			session.put("pageinfo", page);
			return SUCCESS;

		}
		public int getPageNow() {

			return pageNow;

		}
		public void setPageNow(int pageNow) {

			this.pageNow = pageNow;

		}
		public int getPageSize() {

			return pageSize;

		}
		public void setPageSize(int pageSize) {

			this.pageSize = pageSize;

		}

		

}
