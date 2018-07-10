<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>编辑角色管理</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
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
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增角色管理</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group ">	
				<div class="controls">
					<span class="control-label">角色名称</span>
					<input id="role_name" type="text" placeholder="输入角色名" class="input-text" >	
					<div class="lable-symbol-div"></div>		
				</div>							
			</div>
			<div class="area-text-parent">
					<span class="control-label">描述</span>
					 <textarea id="description" rows="3" cols="20"  class="area-text"></textarea>
					 <div class="lable-symbol-div"></div>		
			</div>			
		</div>
	</div>
			<div class="modal-footer">			
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
	<script>
		$(function() {
			var checkSameName = '<%=basePath%>role/checkSameName.do?rnd=' + Math.random();
			var saveOrUpdateRoleURL =  '<%=basePath%>role/saveOrUpdateRole.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			var role_name,description,create_time;
			if (json_data.role_name) {
				if(json_data.edit==2){
					$("#title_text").text("编辑角色信息");
				}
				
				$("#role_name").val(json_data.role_name);
				$("#description").val(json_data.description);				
			}
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//校验角色是否存在		
			$("#role_name").change(function(){				
				var role_name = $("#role_name").val();				
				if(role_name ==""){
					$("#role_name").siblings('*:last').text("角色名不能为空！");				
					return;
				}
				checkRoleExist(role_name);//校验			
			});
			
			$("#description").bind('input propertychange',function(){
				if($(this).val() != ""){
					$("#description").siblings('*:last').text("");
				}									
			});
			
			//保存提交
			$("#submit_modal").click(function() {
				role_name = $("#role_name").val();
				role_desc = $("#description").val();		
				
				//校验合法
				var ck_role_name = $("#role_name").siblings('*:last').text();			
				if(ck_role_name != ""){
					$("#role_name").siblings('*:last').text("角色名已存在！");
					return;
				}
				
				//校验空
				if(role_name == "" ){
					$("#role_name").siblings('*:last').text("角色名不能为空！");
				}else{
					$("#role_name").siblings('*:last').text("");
				}
				
				if(role_desc == "" ){
					$("#description").siblings('*:last').text("描述不能为空！");
				}else{
					$("#description").siblings('*:last').text("");
				}
				if(role_name == "" || role_desc == "" ){
					return;
				}
				
				$.ajax({
					url : saveOrUpdateRoleURL,
					type : "post",
					data : {
						roleId: json_data.id == undefined?"":json_data.id,
						role_name : role_name,
						role_desc : role_desc						
					},
					success : function(data) {
						parentOBJ.resfreshTable();
					},
					error : function() {
						console.error("error");
						parentOBJ.resfreshTable();
					}
				});
				
				parentOBJ.modalIn();//关闭
				
			});
			
			//校验角色是否存在
			function checkRoleExist(role_name){
				$.ajax({
					url : checkSameName,
					type : "post",
					dataType: 'json',
					data : {
						roleName : encodeURI(role_name,'utf-8'),
						roleId: json_data.id
					},
					success : function(data) {						
						if(data){							
							$("#role_name").siblings('*:last').text("");
						}else{							
							$("#role_name").siblings('*:last').text("角色已存在");
						}	
					},
					error : function() {
						console.error("error");
					}
				});				
			}
			
		});
	</script>
</body>
</html>
