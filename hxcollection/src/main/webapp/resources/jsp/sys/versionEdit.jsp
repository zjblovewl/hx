<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>版本管理编辑/新增</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.controls input{	
    width: 70%;
}
.controls .control-label{
    width: 65px !important;
}

</style>
</head>
<body>
	<!-- 修改/新增 -->
	<p id="modal-title" class="modal-title">新增用户</p>
	<div class="modal-container">
		<form class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">用户名</span>
					 <input id="user_name" type="text" placeholder="输入字典名称" class="input-text">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">登录名</span>
					 <input id="login_name" type="text" placeholder="输入字典值" class="input-text">					 
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">邮箱</span>
					 <input id="email" type="text" placeholder="输入描述内容" class="input-text">

				</div>
			</div>
		</form>

	</div>
			<div class="modal-footer">				
				<button id="submit_modal" class="btn btn-success">确定</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
	<script>
	$(function() {
		var parentOBJ = window.parent;
		//获取url
		var src_data = parentOBJ.document.getElementById("modal-iframe")
				.getAttribute("src-data");
		var json_data = JSON.parse(src_data);
		console.log(json_data);
		var user_name,login_name,email,create_time;
		if (json_data.user_name) {
			$("#modal-title").val("编辑版本");
			$("#user_name").val(json_data.user_name);
			$("#login_name").val(json_data.login_name);
			$("#email").val(json_data.email);
		}
		//关闭
		$("#close_modal").click(function() {
			parentOBJ.modalIn();
		});
		//保存提交
		$("#submit_modal").click(function() {
			user_name = $("#user_name").val();
			login_name = $("#login_name").val();
			email = $("#email").val();
			createtime = getNowFormatDate();
			
			if(user_name == "" || login_name=="" || email == ""){
				alert("*为必填内容");
				return;
			}
			
			$.ajax({
				url : "",
				type : "post",
				data : {
					user_name : user_name,
					login_name : login_name,
					email : email,
					create_time :create_time
				},
				success : function(data) {

				},
				error : function() {
					console.error("error");
				}
			});
			
			parentOBJ.modalIn();//关闭
			
		});
		
		//获取当前时间，格式YYYY-MM-DD
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var year = date.getFullYear();
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = year + seperator1 + month + seperator1 + strDate;
		    return currentdate;
		}
	});
	</script>
</body>
</html>
