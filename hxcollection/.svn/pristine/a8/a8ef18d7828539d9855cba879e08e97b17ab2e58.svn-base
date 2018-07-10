<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%><!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>后台管理</title>
     <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../H-ui.admin/H-ui.admin.css">
    <link rel="stylesheet" type="text/css" href="../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.easing.1.3.js"></script> 
    <script src="../js/bootstrap-dialog.js"></script> 
 	<script src="../js/head.js"></script> 	
 	 
    <style>

    </style>
</head>
<body>
<aside class="Hui-aside">
    <!--用户头像-->
    <div class="user_aside">
        <div  class="user_img">
        	<img id="userImg" alt="头像" class="userImg"/>
        </div>
        <div class="user_name">
            <p id="username">username</p>
            <p>在线</p>
        </div>
    </div>
    <jsp:include page="menu.jsp"></jsp:include>
</aside>
<section class="Hui-article-box">
    <div class="logo_txt">
    	<div class="logo_txt_cn">乾隆收藏品管理平台</div>
    	<div class="logo_txt_en">QianLongShouCangPinguanlipingtai</div>  	
    </div>
     <div class="Hui-tabs">
        <div id="left-scroll" class="left-scroll x-scroll">
            <i class="fa fa-angle-double-left"></i>
        </div>
        <div class="Hui_tabs_ul_parent">
            <ul class="Hui_tabs_ul" id="Hui_tabs_ul">
                <li class="color_theme"><a>用户操作历史</a></li>
            </ul>
        </div>
        <div id="right-scroll" class="right-scroll  x-scroll">
            <i class="fa fa-angle-double-right"></i>
        </div>
        <div  id="logout_btn" class="logout_btn">
        	<i class="fa fa-sign-out"></i>
        	<span>退出</span>
        </div>
    </div>
    <div id="Hui-tabNav" class="Hui-tabNav" style="display: none;">
            <ul>
                <li id="min_title_list" class="active">
                    <span title="我的桌面" data-href="#">我的桌面</span>
                    <span id="first-span"> >用户管理 > 用户操作历史</span>
                    <span id="second-span"></span>
                    <span id="third-span"></span>
                </li>
            </ul>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div id="loading" class="loading"></div>
            <iframe id="iframe" name="iframe" scrolling="auto" frameborder="0" src="user/userOperationLog.jsp"></iframe>
        </div>
      
    </div>
</section>
<!-- 模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <iframe name="modal-iframe" id="modal-iframe" scrolling="no" frameborder="0" src=""></iframe>
        </div>
    </div>
</div>
<!-- 角色管理 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <iframe id="modal-iframe2" scrolling="no" frameborder="0" src=""></iframe>
        </div>
    </div>
</div>
<!-- 菜单管理 -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <iframe id="modal-iframe3" scrolling="no" frameborder="0" src=""></iframe>
        </div>
    </div>
</div>


<script type="text/javascript">
	function modalOut(url,data){
		var str_data = JSON.stringify(data);	
		$("#modal-iframe").attr('src-data',str_data);
		$("#modal-iframe").attr('src',url);
		$("#myModal").modal({			
			show:true,
			keyboard:true
		});
	}
	function modalIn(){		
		$("#myModal").modal('toggle');
	}
	//权限管理
	function modalOutRole(url,data){
		var str_data = JSON.stringify(data);	
		$("#modal-iframe2").attr('src-data',str_data);
		$("#modal-iframe2").attr('src',url);
		$("#myModal2").modal({			
			show:true,
			keyboard:true
		});
	}
	function modalInRole(){		
		$("#myModal2").modal('toggle');
	}
	//菜单管理
	function modalOutMenu(url,data){
		var str_data = JSON.stringify(data);	
		$("#modal-iframe3").attr('src-data',str_data);
		$("#modal-iframe3").attr('src',url);
		$("#myModal3").modal({			
			show:true,
			keyboard:true
		});
	}
	function modalInMenu(){		
		$("#myModal3").modal('toggle');
	}

	//退出登陆
	$("#logout_btn").click(function(){
		logout();  
		return false;
	});
	var logoutURL = '<%=basePath%>user/logout.do';
	function logout(){
	   $.ajax({
		    url : logoutURL,
	   	    type: 'post', 
	   	    success:function(data){
	   	    	window.location.reload();
	   	    },
	   	    error:function(){
	   	    	console.error("退出错误");
	   	    }
		});
	}

	//刷新
	function resfreshWindow(){
		document.getElementById('iframe').contentWindow.location.reload(true);
	}

	function resfreshTable(){
		document.getElementById('iframe').contentWindow.$("#table").bootstrapTable('refresh');		
	}
	//控制窗口大小
	function setWindowDialog(width,height){
		$("#myModal .modal-content").css({
			width: width+'px',
			height: height+'px'
		});
	}
</script>

</body>
</html>