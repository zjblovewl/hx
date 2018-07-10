<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String getImageUrl = Configuration.getInstance().getValue("get_image_url");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>用户审核</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>

.radio_group {
	display: inline-block;
}

input[type="radio"] {
	outline: none;
	width: 20px;
	height: 20px;
	vertical-align: bottom;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">用户审核</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">真实姓名</span> 
						<input id="real_name" type="text" class="input-text input_number_ck" placeholder="姓名">
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">身份证</span> 
						<input id="id_number" type="text" class="input-text input_number_ck" placeholder="身份证号码">
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<!-- image_url -->
					<span class="control-label">正面照</span> 
					<div class="copy-cover-input-file">
						<img id="pic_up_img" alt="图片" src=""/>
					</div>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
				<!-- image_url -->
					<span class="control-label">反面照</span> 
					<div class="copy-cover-input-file">
						<img id="pic_down_img" alt="图片" src=""/>
					</div>
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">审核</span>
					<div class="radio_group">	
						<input type="radio" name="isPass" value="1"  id="pass"/> 
						<span>通过</span>
						<input type="radio" name="isPass" value="0" id="nonePass"/>
						<span>不通过</span> 
					</div>
					<div class="lable-symbol-div"></div>
				</div>
			</div>

		</div>
	</div>
			<div class="modal-footer">
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
	<script>			
		$(function() {
			var auditResultURL = '<%=basePath%>generalUser/updateAuditInfo.do?rnd='+ Math.random();
			var userAuditUrl = '<%=basePath%>generalUser/queryUserAuthenTicationInfo.do?rnd=' + Math.random();		
			var parentOBJ = window.parent;
			var getImageUrl = '<%=getImageUrl%>'.trim();
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			
			var is_authentication = json_data.is_authentication;
			if(is_authentication == 0){
				$("#nonePass").attr('checked','checked');
			}else{
				$("#pass").attr('checked','checked');
				$('input[type="radio"]').each(function(){
					$(this).attr('disabled','disabled');
				});
			}
			
			$.ajax({
				url : userAuditUrl,
				type : "post",
				data : {
					id : json_data.id
				},
				success : function(data) {
					console.log(data)
					//初始化
					if(data.rows.length == 0){
						return;
					}
					var rows = data.rows;
					$("#real_name").val(rows[0].real_name);
					$("#id_number").val(rows[0].id_number);
					$("#pic_up_img").attr('src',getImageUrl+rows[0].image_url);
					if(data.rows.length == 1){
						return;
					}
					$("#pic_down_img").attr('src',getImageUrl+rows[1].image_url);					
				},
				error : function() {
					console.error("error");
				}
			});
					
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
		
			$("#submit_modal").bind('click', function() {
				var isPass = $("input[type='radio']:checked").val();
				var jsonObj = {};
				jsonObj.id = json_data.id;
				jsonObj.isPass = isPass;
				$.ajax({
					url : auditResultURL,
					type : "post",
					data : {
						paramsStr : JSON.stringify(jsonObj)
					},
					success : function(data) {
						console.log(data)

						parentOBJ.resfreshTable();
					},
					error : function() {
						console.error("error");
					}
				});
				parentOBJ.modalIn();//关闭
				return false;
			});

		});
	</script>
</body>
</html>
