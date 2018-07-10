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
<title>菜单管理</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.modal-container{
    overflow-y: scroll;
	padding: 40px 20px 0 20px !important;
    height: 79%;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">菜单管理</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div id="role_list" class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">上级菜单</span>
					 <input id="parent_name" type="text" class="input-text" style="cursor:pointer;" >
					 <div class="lable-symbol-div"></div>					 
				</div>
				<div class="controls">
					<span class="control-label">名称</span>
					 <input id="name" type="text"  class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">链接</span>
					 <input id="url" type="text"  class="input-text">
					 <span class="lable-symbol-white">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">图标</span>
					 <input id="lable_icon" type="text" class="input-text">
					 <span class="lable-symbol-white">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">排序</span>
					 <input id="sort" type="text"  class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>			
			</div>
			<div class="control-group button-group">			
				<button id="submit_modal" class="btn btn-success marginRight10">保存</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
		</div>

	</div>
	<script>
		$(function() {		
			var saveMenuURL = '<%=basePath%>menu/saveOrUpdateMenu.do?rnd=' + Math.random();
			var checkMenuNameURL = '<%=basePath%>menu/checkSameMenuName.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)
			
			if(!json_data.edit){
				$('input[type="text"]').attr("disabled","disabled");
				$(".lable-symbol").each(function(){
					$(this).hide();
				});
				$("#submit_modal").hide();
			}
			//初始化
			$("#name").val(json_data.name);
			$("#parent_name").val(json_data.parent_name);
			$("#parent_name").attr("pid",json_data.res_pid);
			$("#url").val(json_data.url);
			$("#sort").val(json_data.sort);
			$("#lable_icon").val(json_data.icon);
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			
			//校验菜单名称
			$("#name").change(function(){
				var name = $("#name").val();
				if(name ==""){
					$("#name").siblings('*:last').text("菜单名不能为空！");
					return;
				}
				var params = {};		
				params.res_pid =  $("#parent_name").attr("pid");
				params.resId = json_data.menuId == undefined?"":json_data.menuId;
				params.res_name = encodeURI($("#name").val(),'utf-8');
				ajaxMenuName(params);//校验用户	
			});
			
			//保存提交
			$("#submit_modal").click(function() {
				var res_name = $("#name").val();
				var resId = "";
				if(json_data.menuId){
					resId = json_data.menuId;
				}
				var res_pid = $("#parent_name").attr("pid");
				var res_url = $("#url").val();
				var res_order = $("#sort").val();
				var res_icon  = $("#lable_icon").val();
				if(res_name == ""){
					$("#name").siblings('*:last').text("菜单名不能为空！");
				}else{
					$("#name").siblings('*:last').text("");
				}if(res_order ==""){
					$("#sort").siblings('*:last').text("排序不能为空！");
				}else{
					$("#sort").siblings('*:last').text("");
				}
				
				if(res_order =="" || res_name == ""){
					return;
				}
				
				var ckResName = $("#name").siblings('*:last').text();
				if(ckResName != ""){
					$("#name").siblings('*:last').text("名称校验失败！");
					return;
				}

				$.ajax({
					url:saveMenuURL,
					type:"post",
					data:{
						resId: resId,
						res_name:res_name,
						res_pid:res_pid,
						res_url:res_url,
						res_order:res_order,
						res_icon:res_icon
					},
					success:function(data){
						console.log("success");
						parentOBJ.resfreshWindow();
					},
					error:function(){
						console.error("提交失败");
						parentOBJ.resfreshWindow();
					}
				});
								
				parentOBJ.modalIn();//关闭
				
			});
			//校验数据库
			function ajaxMenuName(params){
				$.ajax({
					url: checkMenuNameURL,
					type:"post",
					dataType: 'json',
					data:{
						paramsStr : JSON.stringify(params) //string类型			
					},
					success:function(data){							
						if(data){													
							$("#name").siblings('*:last').text('');							
						}else{														
							$("#name").siblings('*:last').text("菜单名称已存在");							
						}							
					},
					error:function(){
						console.error("校验失败");							
					}
				});
			}
			$("#parent_name").click(function(){
				var roleUrl = "sys/menuControlListEdit.jsp";
				parentOBJ.modalOutMenu(roleUrl,"");
				return false;
			});
			//校验
			$("#sort").on('input perportychange',function(){
				$(this).checkNumber();
			});
			$("#sort").on('change',function(){
				var sort = $("#sort").val();
				if(sort == ""){													
					$("#sort").siblings('*:last').text('排序不能为空');							
				}else{														
					$("#sort").siblings('*:last').text("");							
				}	
			});
		});
	</script>
</body>
</html>
