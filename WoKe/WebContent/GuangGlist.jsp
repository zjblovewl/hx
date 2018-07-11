<%@page import="com.sun.jmx.snmp.Timestamp"%>
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
<meta name="sharecontent" data-msg-img="images/1.jpg"
	data-msg-title="你的标题" data-msg-content="你的简介" data-msg-callBack=""
	data-line-img="images/2.jpg" data-line-title="你的标题"
	data-line-callBack="" />
<title>主要内容区main</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="images/main/favicon.ico" />
<script src="jquery-2.1.1.js"></script>
 <script>
 
 
 function fabu(id) {
	
	alert("你发布的网页的url为："+'http://211b5r8311.imwork.net:12205/WoKe/index.jsp?id='+id)
}
 
 var ShareId = "";
//绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
$(function () {
	 
   $(".bds_more").mouseover(function () {

       ShareId = $(this).attr("data-id");
      
   });
});
function SetShareUrl(cmd, config) {            
    if (ShareId) {
        config.bdUrl = '${url}/WoKe/WxSharePage?id='+ShareId;    
    }
    return config;
}


 	window._bd_share_config = {
										common : {
											onBeforeClick:SetShareUrl,
											bdText : '这是内容',
											bdDesc : '这里是描述',
									
										//	bdUrl : 'http://211b5r8311.imwork.net:12205/WoKe/index?id='+${guanggao.id}+'.jsp',
											bdPic : 'images/2.jpg'
										},
										share : [ {
											"bdSize" : 16
										} ],
										slide : [ {
											bdImg : 0,
											bdPos : "right",
											bdTop : 100
										} ],
										image : [ {
											viewType : 'list',
											viewPos : 'top',
											viewColor : 'black',
											viewSize : '16',
											viewList : [ 'qzone', 'tsina',
													'huaban', 'tqq', 'renren' ]
										} ],
										selectShare : [ {
											"bdselectMiniList" : [ 'qzone',
													'tqq', 'kaixin001', 'bdxc',
													'tqf' ]
										} ]
									}
									
									with (document)
										0[(getElementsByTagName('head')[0] || body)
												.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='
												+ ~(-new Date() / 36e5)];
									
								</script>
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
							<form method="post" action="selectGuangG">
								<span>编号：</span> <input type="text" name="id" maxlength="10"
									value="<%=request.getParameter("id") != null ? request.getParameter("id") : ""%>"
									class="text-word"> <input type="submit" value="查询"
									class="text-but">
							</form>
						</td>
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
						<th align="center" valign="middle" class="borderright">logo</th>
						<th align="center" valign="middle" class="borderright">图片</th>
						<th align="center" valign="middle" class="borderright">标题</th>
						<th align="center" valign="middle" class="borderright">描述</th>
						<th align="center" valign="middle" class="borderright">活动时间</th>
						<th align="center" valign="middle" class="borderright">浏览量</th>
						<th align="center" valign="middle" class="borderright">操作</th>
						<th align="center" valign="middle" class="borderright">发布</th>
					</tr>
					
					<%
					int pageSize = 4;//4个一分
					int currentPage = 1;//开始
					request.setCharacterEncoding("UTF-8");
					String cp = request.getParameter("page");
					if (cp != null ) {
						currentPage = Integer.parseInt(cp);
					}
					String sw = " 1=1 ";
					GuangG dao = new GuangG();
					List<GuangG> list = dao.selectLend(currentPage, pageSize);
					
					int qqsum =dao.selectLendSize();
					int qq =qqsum/4;
					qq++;
					int dddd =currentPage;
					int ff =dddd++;
					int dd =dddd--;
					
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							GuangG guanggao = list.get(i);
					
							//<s:iterator id="guanggao" value="#session.guanggaoinfo">
					%>
					
				
						<tr onMouseOut="this.style.backgroundColor='#ffffff'"
							onMouseOver="this.style.backgroundColor='#edf5ff'">
							<td align="center" valign="middle"
								class="borderright borderbottom"><%=guanggao.getId() %></td>
							<td align="center" valign="middle"
								class="borderright borderbottom"><img src="logo/<%=guanggao.getLogo() %>"
								style="width: 50px; height: 50px"></img></td>
							<td align="center" valign="middle"
								class="borderright borderbottom"><img src="img/<%=guanggao.getImg() %>"
								style="width: 50px; height: 50px"></img></td>
							<td align="center" valign="middle"
								class="borderright borderbottom"><%=guanggao.getTitle()%></td>
							<td align="center" valign="middle"
								class="borderright borderbottom"><%=guanggao.getCentent()%></td>
							<td align="center" valign="middle"
								class="borderright borderbottom"><%=guanggao.getDatetime()%></td>
							<td align="center" valign="middle"
								class="borderright borderbottom"><%=guanggao.getCount()%></td>
							<td align="center" valign="middle" class="borderbottom"><a
								href="updateGuangG.jsp?id=<%=guanggao.getId() %>&logo=<%=guanggao.getLogo() %>&img=<%=guanggao.getImg() %>&title=<%=guanggao.getTitle()%>&centent=<%=guanggao.getCentent()%>&datetime=<%=guanggao.getDatetime()%>"
								target="mainFrame" onFocus="this.blur()" class="add">编辑</a><span
								class="gray">&nbsp;|&nbsp;</span><a
								href="deleGuangG?BmId=<%=guanggao.getId() %>" target="mainFrame"
								onFocus="this.blur()" class="add">删除</a></td>
							<td align="center" valign="middle"
								class="borderright borderbottom">
								<!--  <a
								href="FaBu?id=<%=guanggao.getId() %>&img=<%=guanggao.getImg() %>&title=<%=guanggao.getTitle() %>&centent=<%=guanggao.getCentent()%>&datetime=<%=guanggao.getDatetime()%>"
								target="mainFrame" onFocus="this.blur()" class="add">发布</a>-->
								<a
								
								onFocus="this.blur()" onclick="fabu(<%=guanggao.getId() %>)" class="add">发布</a>
								
								<div style="border-color: red;background-color: bule; whith:100px;height:50px;">
								<div class="bdsharebuttonbox"  data-tag="share_1" >
							
									<a class="bds_more" data-id="<%=guanggao.getId()%>" data-cmd="more">分享</a>
								</div>
								</div>
								</td>
						</tr>
						<%}} %>
				
				</table>
			</td>
		</tr>
		
		<tr>
		
			
		
			<td align="left" valign="top" class="fenye">
			<div>
			 第<%=currentPage %>/<%=qq%>页 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="GuangGlist.jsp">首页</a>  
			 <a href="GuangGlist.jsp?page=<%=qq%>">尾页</a>  &nbsp;&nbsp;&nbsp; <a href="GuangGlist.jsp?page=<%=dd%>">下一页</a>  &nbsp;&nbsp;&nbsp;
			  <a href="GuangGlist.jsp?page=<%=--currentPage%>">上一页</a> 
			
			</div>
			</td>
		</tr>
	</table>
	<h1>
		<div style="color: red; padding-left: 700px">${msg}</div>
	</h1>
	<h1>
		<div style="color: red; padding-left: 700px">${msg2}</div>
	</h1>
	<h1>
		<div style="color: red; padding-left: 700px">${msg3}</div>
	</h1>
</body>
</html>