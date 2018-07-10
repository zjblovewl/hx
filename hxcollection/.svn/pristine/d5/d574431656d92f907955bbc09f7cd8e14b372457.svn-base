<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String baseUrl = request.getContextPath();
	String path = request.getContextPath();
	String justin = (String) request.getSession().getAttribute("name");
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title> 
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-dialog.js"></script> 
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/common/md5.js"></script>
    <style>
       
    </style>
    <script language="JavaScript"> 
	    if (window != top) 
	    top.location.href = location.href; 
    </script>
</head>
<body>

<div class="main">
	<div class="login">
		<div class="login_area">
        <div class="title title1">乾隆</div>
        <div class="title title2">QianLong</div>
        <div class="title title3">收藏品后台管理系统</div>
        <div class="title title4">ShouCangpinhoutaiguanlixitong</div>
        <div class="subtitle">
            <span class="line"></span>
            <span class="line_txt">欢迎登录</span>
            <span class="line"></span>
        </div>
        <div class="input_area">
            <label><i class="fa fa-user-o" aria-hidden="true"></i></label>
			<input type="text"  name="username" id="username"  class="inputTxt username"/>
			<div class="warn_text"></div>
            <label class="psd"><i class="fa fa-lock" aria-hidden="true"></i></label>
			<input type="password" class="inputTxt password" name="password" id="password" />
			<div class="warn_text"></div>
            <div class="button" id="login"></div>
            <div class="cb_txt">               
                <p></p>               
            </div>
            <img class="foot-img" src="images/login-img-foot.png"/>
        </div>
    </div>
	</div>
</div>
<script>

    $("#username").focus();
 	function login(){		
 		var loginname = $.trim($('#username').val());
 		if(!loginname || loginname == ""){
 			$(".warn_text").eq(0).text("请输入用户名");
 			return;
 		}
 		
 		var password = $.trim($('#password').val());
 		if(!password || password == ""){
 			$(".warn_text").eq(1).text("请输入密码");
 			return;
 		}		
 		$.ajax({
 			type:"post",    			
 			url : '../user/login.do',		
 			data:{
 				name : loginname,
 				password : hex_md5(password).toUpperCase()
 			},
 			success:function(result){   
 				//1：登录成功！ 2:用户名不存在 3:密码输入错误 4:用户没有登录权限  
 				if(result==1){					
 					window.location.href='jsp/index.jsp';
 				}else if(result==2){
 					$(".warn_text").eq(0).text("登录失败,用户名不存在！");
 				}else if(result==3){
 					$(".warn_text").eq(1).text("登录失败,密码输入错误！");
 				}else if(result==4){
 					$.showSuccessTimeout("登录失败,该用户没有登录权限,请联系管理员授权！");
 				}else{
 					$.showSuccessTimeout("登录失败！");
 				}
 			},
 			error:function(){
 				$.showSuccessTimeout("登录失败");
 			}
 		});
 	}
 	
 	 //对话框
    $.showSuccessTimeout = function(str, func) {
        BootstrapDialog.show({
            title : "提示",
            message : str,
            size : BootstrapDialog.SIZE_SMALL,
            buttons : [ {
                label : '确定',
                action : function(dialogItself) {
                    dialogItself.close();
                }
            } ],
            // 指定时间内可自动关闭
            onshown : function(dialogRef) {
                setTimeout(function() {
                    dialogRef.close();
                }, 1500);
            },
            onhide : func
        });
    };
 	$(function(){ 		
 		$("#login").bind("click",function(){
 			login();
 	 	});
 		
 		//是否为空
 		$('input').bind('change',function(){
 			if($(this).val() != ""){
 				$(this).next().text('');
 			}
 		});
 	    $(document).keypress(function (e) {
 	      if (e.keyCode == 13) {
 	    	  login();
 	      }
 	   });
 	  
 	});    	
 	
 </script>
</body>
</html>