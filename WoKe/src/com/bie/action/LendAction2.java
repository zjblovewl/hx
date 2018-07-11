package com.bie.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woke.context.WeiXinApplicationContext;
import com.woke.dao.BaoM;
import com.woke.dao.GuangG;
import com.woke.dao.Pager;

public class LendAction2 extends ActionSupport{

		private int pageNow=1;           //初始页为第一页

		private int pageSize=4;			 //每页数据为4条，可调节
		public String execute() throws Exception{
			GuangG dao = new GuangG();
			List<GuangG> list = dao.selectLend(pageNow, pageSize);
			System.out.println(list.size());
			Pager page = new Pager(pageNow, dao.selectLendSize());
			Map session = ActionContext.getContext().getSession();
			session.put("guanggaoinfo", list);
			session.put("gpageinfo", page);
			session.put("url", WeiXinApplicationContext.DOMAIN_URL);
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
