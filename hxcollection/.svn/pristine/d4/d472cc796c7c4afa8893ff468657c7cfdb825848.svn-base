<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String uploadImageUrl = Configuration.getInstance().getValue("upload_image_url");
	String getImageUrl = Configuration.getInstance().getValue("get_image_url");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>佣金配置编辑</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<link rel="stylesheet" href="../../css/jspEditcommon.css" />

<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>

</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">佣金配置</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div id="role_list" class="form-div-edit">
			<div class="control-group"> 
				<div class="controls">
					<span class="control-label">佣金比例</span> 
					<input id="commissionRate" name ="commissionRate" type="text" placeholder="输入佣金配置比例" class="input-text">%
					<div class="lable-symbol-div"></div>
				</div>				
			 </div>				
		</div>										
	</div>
			
	<div class="modal-footer">			
		<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
		<button id="close_modal" class="btn btn-danger">取消</button>
	</div>
	<script>
	
		$(function() {	
			var queryCRURL = '<%=basePath%>commissionRecord/getCRInfo.do?rnd=' + Math.random();
			var saveCRConfigURL = '<%=basePath%>commissionRecord/saveCommissionRate.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
					
			//保存提交
			$("#submit_modal").click(function() {	
				var id = "12";
				var commissionRate = $("#commissionRate").val();
				var jsonObj={};
				jsonObj.id = id;
				jsonObj.commissionRate = commissionRate;
				
				$.ajax({
					url: saveCRConfigURL,
					type:"post",
					data:{
						data : JSON.stringify(jsonObj)
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
					
		});
	</script>
</body>
</html>
