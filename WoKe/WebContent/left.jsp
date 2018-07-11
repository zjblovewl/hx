<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧导航menu</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/sdmenu.js"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
</script>
<style type=text/css>
html{ SCROLLBAR-FACE-COLOR: #538ec6; SCROLLBAR-HIGHLIGHT-COLOR: #dce5f0; SCROLLBAR-SHADOW-COLOR: #2c6daa; SCROLLBAR-3DLIGHT-COLOR: #dce5f0; SCROLLBAR-ARROW-COLOR: #2c6daa;  SCROLLBAR-TRACK-COLOR: #dce5f0;  SCROLLBAR-DARKSHADOW-COLOR: #dce5f0; overflow-x:hidden;}
body{overflow-x:hidden; background:url(images/main/leftbg.jpg) left top repeat-y #f2f0f5; width:194px;}
</style>
</head>
<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
<div id="left-top">
	<div><img src="images/main/role.jpg" width="44" height="44" /></div>
    <span>用户：${user.username}<br>角色：${user.role}</span>
</div>
    <div style="float: left" id="my_menu" class="sdmenu">
      <div class="collapsed">
        <span>首页</span>
        <a href="main.jsp" target="mainFrame" onFocus="this.blur()">后台首页</a>
        <a href="lendaction" target="mainFrame" onFocus="this.blur()">客户列表</a>
      </div>
      <div>
        <span>管理员设置</span>
        <a href="getUserList" target="mainFrame" onFocus="this.blur()">用户列表</a>
      </div>
      <div>
        <span>广告设置</span>
        <a href="addGuangG.jsp" target="mainFrame" onFocus="this.blur()">添加广告</a>
        <a href="GuangGlist.jsp" target="mainFrame" onFocus="this.blur()">广告列表</a>
      </div>
    </div>
</body>
</html>