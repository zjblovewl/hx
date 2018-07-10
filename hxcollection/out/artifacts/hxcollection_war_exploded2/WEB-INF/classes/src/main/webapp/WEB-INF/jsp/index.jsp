<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="<%=request.getContextPath()%>/resources/js/common/md5.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/common/jquery-1.9.1.js"></script>
</head>
<body>
	<form id="form" action="<%=request.getContextPath()%>/loginController/login.do">  
		<input type="text" name="name" id="name" />
		<input type="password" name="password" id="password">
		<input type="button" id="button" value="登录">
	</form>
	
<script type="text/javascript"> 
$(function(){ 
	$("#button").click(function(){ 
		 var name=$.trim($("#name").val());
		 var password=$.trim($("#password").val());
		 if(name=="" || password==""){
			 layer.alert("用户名或密码错误!");
			 return ;
		 }
		 if(name!="" && password!=""){
			 $("#password").val(hex_md5(password));
			 $("#form").submit();
		 }else{
			 return;
		 }
	}); 
})
</script>
</body>
</html>