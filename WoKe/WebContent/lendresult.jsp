<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.bie.action.*"%>
   <%@ page import="com.woke.dao.*"%>
      <%@ page import="com.woke.bean.*"%>
      <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>沃客营销</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="images/main/favicon.ico" />
<style>
body {
	overflow-x: hidden;
	background: #f2f0f5;
	padding: 15px 0px 10px 5px;
}

#searchmain {
	font-size: 12px;
}

#search {
	font-size: 12px;
	background: #548fc9;
	margin: 10px 10px 0 0;
	display: inline;
	width: 100%;
	color: #FFF;
	float: left
}

#search form span {
	height: 40px;
	line-height: 40px;
	padding: 0 0px 0 10px;
	float: left;
}

#search form input.text-word {
	height: 24px;
	line-height: 24px;
	width: 180px;
	margin: 8px 0 6px 0;
	padding: 0 0px 0 10px;
	float: left;
	border: 1px solid #FFF;
}

#search form input.text-but {
	height: 24px;
	line-height: 24px;
	width: 55px;
	background: url(images/main/list_input.jpg) no-repeat left top;
	border: none;
	cursor: pointer;
	font-family: "Microsoft YaHei", "Tahoma", "Arial", '宋体';
	color: #666;
	float: left;
	margin: 8px 0 0 6px;
	display: inline;
}

#search a.add {
	background: url(images/main/add.jpg) no-repeat -3px 7px #548fc9;
	padding: 0 10px 0 26px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	font-weight: bold;
	color: #FFF;
	float: right
}

#search a:hover.add {
	text-decoration: underline;
	color: #d2e9ff;
}

#main-tab {
	border: 1px solid #eaeaea;
	background: #FFF;
	font-size: 12px;
}

#main-tab th {
	font-size: 12px;
	background: url(images/main/list_bg.jpg) repeat-x;
	height: 32px;
	line-height: 32px;
}

#main-tab td {
	font-size: 12px;
	line-height: 40px;
}

#main-tab td a {
	font-size: 12px;
	color: #548fc9;
}

#main-tab td a:hover {
	color: #565656;
	text-decoration: underline;
}

.bordertop {
	border-top: 1px solid #ebebeb
}

.borderright {
	border-right: 1px solid #ebebeb
}

.borderbottom {
	border-bottom: 1px solid #ebebeb
}

.borderleft {
	border-left: 1px solid #ebebeb
}

.gray {
	color: #dbdbdb;
}

td.fenye {
	padding: 10px 0 0 0;
	text-align: right;
}

.bggray {
	background: #f9f9f9
}
</style>
</head>
<body>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td width="99%" align="left" valign="top">您的位置：用户管理</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="selectBaoM">
								<span>客户名：</span> <input type="text" name="name" maxlength="20"
									value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
									class="text-word"> <input type="submit" value="查询"
									class="text-but">
							</form>
						</td>
						<td width="10%" align="center" valign="middle"
							style="text-align: right; width: 150px;"><a
							href="addBaoM.jsp" target="mainFrame" onFocus="this.blur()"
							class="add">新增客户</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tab">
					<tr>
						<th align="center" valign="middle" class="borderright">编号</th>
						<th align="center" valign="middle" class="borderright">姓名</th>
						<th align="center" valign="middle" class="borderright">电话</th>
						<th align="center" valign="middle" class="borderright">地址</th>
						<th align="center" valign="middle" class="borderright">意向车型</th>
						<th align="center" valign="middle">操作</th>
					</tr>
					<s:iterator  value="#session.userinfo" id="baoming">
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="center" valign="middle"
								class="borderright borderbottom">${baoming.id}</td>
							<td align="center" valign="middle"
								class="borderright borderbottom">${baoming.name}</td>
							<td align="center" valign="middle"
								class="borderright borderbottom">${baoming.tel}</td>
							<td align="center" valign="middle"
								class="borderright borderbottom">${baoming.address}</td>
							<td align="center" valign="middle"
								class="borderright borderbottom">${baoming.brand}</td>
							<td align="center" valign="middle" class="borderbottom"><a
								href="updateBM.jsp?id=${baoming.id}&name=${baoming.name}&tel=${baoming.tel}&address=${baoming.address}&brand=${baoming.brand}"
								target="mainFrame" onFocus="this.blur()" class="add">编辑</a><span
								class="gray">&nbsp;|&nbsp;</span><a
								href="deleBaoM?BmId=${baoming.id}" target="mainFrame"
								onFocus="this.blur()" class="add">删除</a></td>
						</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
		
		<tr>
    		<td align="right">
    			<s:set name="page" value="#session.pageinfo"/>
    			<a href="lendaction?pageNow=1">首页</a>
    			<s:if test="#page.hasPre">
    				<a href="lendaction?pageNow=<s:property value="#page.pageNow-1"/>">上一页</a>
    			</s:if>
    			<s:else>
    				<a href="lendaction?pageNow=1">上一页</a>
    			</s:else>

    			<s:if test="#page.hasNext">
    				<a href="lendaction?pageNow=<s:property value="#page.pageNow+1"/>">下一页</a>
    			</s:if>
    			<s:else>

    				<a href="lendaction?pageNow=<s:property value="#page.totalPage"/>">下一页</a>
    			</s:else>

    			<a href="lendaction?pageNow=<s:property value="#page.totalPage"/>">尾页</a>

    		</td>

    	</tr>
		<tr>
		<%
		BaoM dao = new BaoM();
		int number=dao.selectLendSize();
		int qq=number/10;
		qq++;
		%>
			<td align="left" valign="top" class="fenye">&nbsp;&nbsp;
			第${page.pageNow}/<%=qq %> 页&nbsp;共 <%=number %> 条记录
			</td>
		</tr>
	</table>
</body>
</html>