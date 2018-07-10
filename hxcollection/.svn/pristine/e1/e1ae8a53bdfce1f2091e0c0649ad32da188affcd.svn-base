<%@page import="cn.com.hxfz.model.User"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String baseUrl = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
	String name = user.getUserName();
	String imageUrls = user.getImageUrls().trim();
	JSONArray userPermission = (JSONArray) request.getSession().getAttribute("userPermission");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String getImageUrl = Configuration.getInstance().getValue("get_image_url").trim();
%>
<script type="text/javascript">
//获取用户名
var username = '<%=name%>';
var imageUrls = '<%=imageUrls%>';
var getImageUrl = '<%=getImageUrl%>';

$("#userImg").attr('src',getImageUrl+imageUrls);
$("#username").text(username);

var userPermission = '<%=userPermission%>';
	$(function() {
		var data = JSON.parse(userPermission);
		var asideJson = data;
		var asideStr = "";
		for (var i = 0; i < asideJson.length; i++) {
			asideStr += '<dl id="' + i + '">'
					+ "<dt class='menu-parent'><i class='fa "+asideJson[i].menuIcon+" font-left'></i>"
					+ asideJson[i].menuName
					+ "<i class='fa fa-caret-down font-right' aria-hidden='true'></i></dt>"
					+ '<dd class="menu-parent-ul">'
					+ initAsideMenu(asideJson[i].childMenus) + "</dd></dl>";
		}

		function initAsideMenu(params) {
			var asideStr_ul = "<ul>";
			for (var i = 0; i < params.length; i++) {
				asideStr_ul += '<li><a class="menu-child-a" data-href="'
						+ params[i].url + '" href="javascript:void(0)">'
						+ params[i].menuName;
				if (params[i].childMenus.length != 0) {
					asideStr_ul += '<i class="fa fa-caret-down font-right" aria-hidden="true"></i></a>';
					var result = initAsideMenu(params[i].childMenus);
					asideStr_ul += result + '</li>';
				} else {
					asideStr_ul += '</a></li>';
				}
			}
			return asideStr_ul + "</ul>";
		}
		$("#menu_dropdown").append(asideStr);
		//$(".menu-parent-ul").eq(0).find('ul').children(':first-child').addClass('menu_dropdown_li_current');

	});
</script>
<div id="menu_dropdown" class="menu_dropdown">
	<!-- <dl id="menu-user">
		<dt class="menu-parent">
			<i class="fa fa-user-circle-o font-left"></i>用户管理<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li class="menu_dropdown_li_current"><a class="menu-child-a"
					data-href="user/userHistory.jsp" href="javascript:void(0)">用户操作历史</a>
					<ul class="menu-child-ul">
                         <li><a data-href="##" data-title="用户列表" href="javascript:void(0)">用户列表</a></li>
                     </ul></li>
				<li><a class="menu-child-a" data-href="user/userLogin.jsp"
					href="javascript:void(0)">用户登录日志</a></li>
				<li><a class="menu-child-a" data-href="user/userList.jsp"
					href="javascript:void(0)">用户列表</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">用户信息详情</a></li>
				<li><a class="menu-child-a" data-href="user/userPromise.jsp"
					href="javascript:void(0)">用户意见与反馈</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-tousu">
		<dt class="menu-parent">
			<i class="fa fa-pencil-square-o font-left" aria-hidden="true"></i>投诉管理<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a"
					data-href="complain/complaintGoods.jsp" href="javascript:void(0)">藏品投诉查询</a></li>
				<li><a class="menu-child-a"
					data-href="complain/complaintGoods.jsp" href="javascript:void(0)">藏品投诉查询</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-jiaoyi">
		<dt class="menu-parent">
			<i class="fa fa-rmb font-left" aria-hidden="true"></i>交易管理<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">编辑交易帖</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">查看藏品详情</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">交易单编辑</a></li>
				<li><a class="menu-child-a"
					data-href="transaction/dealOrder.jsp" href="javascript:void(0)">交易订单查询</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">交易订单详情</a></li>
				<li><a class="menu-child-a" data-href="transaction/dealTie.jsp"
					href="javascript:void(0)">交易帖查询</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">新增交易帖</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-paimai">
		<dt class="menu-parent">
			<i class="fa fa-gavel font-left" aria-hidden="true"></i>拍卖管理<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">编辑拍卖贴</a>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">拍卖订单编辑</a>
				<li><a class="menu-child-a" data-href="sale/auctionOrder.jsp"
					href="javascript:void(0)">拍卖订单查询</a>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">拍卖订单详情</a>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">拍卖藏品详情</a>
				<li><a class="menu-child-a" data-href="sale/autionTie.jsp"
					href="javascript:void(0)">拍卖贴查询</a>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">新增拍卖贴</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-chat">
		<dt class="menu-parent">
			<i class="fa fa-snapchat font-left" aria-hidden="true"></i>即时聊天<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">发消息</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">屏蔽词管理</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-pinglun">
		<dt class="menu-parent">
			<i class="fa fa-area-chart font-left" aria-hidden="true"></i>报表管理<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">订单走势</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">评论增长走势</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">帖子增长走势</a></li>
				<li><a class="menu-child-a" data-href="#"
					href="javascript:void(0)">注册用户走势</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-config">
		<dt class="menu-parent">
			<i class="fa fa-cog font-left" aria-hidden="true"></i>系统配置<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a" data-href="sys/userManager.jsp"
					href="javascript:void(0)">用户管理</a></li>
				<li><a class="menu-child-a" data-href="sys/roleManager.jsp"
					href="javascript:void(0)">角色管理</a></li>
				<li><a class="menu-child-a" data-href="sys/menuManager.jsp"
					href="javascript:void(0)">菜单管理</a></li>
				<li><a class="menu-child-a" data-href="sys/dataDirectory.jsp"
					href="javascript:void(0)">数据字典</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-system">
		<dt class="menu-parent">
			<i class="fa fa-thermometer-empty font-left" aria-hidden="true"></i>系统监控<i
				class="fa fa-caret-down font-right" aria-hidden="true"></i>
		</dt>
		<dd class="menu-parent-ul">
			<ul>
				<li><a class="menu-child-a"
					data-href="monitor/operationLog.jsp" href="javascript:void(0)">日志查询</a></li>
				<li><a class="menu-child-a" data-href="monitor/timeTask.jsp"
					href="javascript:void(0)">定时任务</a></li>
			</ul>
		</dd>
	</dl> -->
</div>