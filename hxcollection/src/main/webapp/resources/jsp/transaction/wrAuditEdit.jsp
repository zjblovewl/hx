<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>提现审核</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../../css/common_menu.css">
<link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">


<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>



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
		<div class="pull-left" id="title_text">提现审核</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="fa fa-times"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">审核</span>
					<div class="radio_group">	
						<input type="radio" name="isPass" value="1"  id="pass"/> 
						<span>未通过</span>
						<input type="radio" name="isPass" value="2" id="nonePass"/>
						<span>已通过</span> 
					</div>
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
			
			var auditResultURL = '<%=basePath%>withdrawalsRecord/updateWRAuditInfo.do?rnd='+ Math.random();
			var parentOBJ = window.parent;
			
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			
			var audit_state = json_data.audit_state;
			if(audit_state == 1){
				$("#pass").attr('checked','checked');
			}else{
				$("#nonePass").attr('checked','checked');
				$('input[type="radio"]').each(function(){
					$(this).attr('disabled','disabled');
				});
			}
			//关闭
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
