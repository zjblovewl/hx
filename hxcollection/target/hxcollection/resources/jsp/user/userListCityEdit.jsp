<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>城市选择</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" type="text/css" href="../../css/common_menu.css">
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.modal-container {
	overflow-y: scroll;
	padding: 10px 20px 0 20px !important;
	height: 73%;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<p id="modal-title" class="modal-title">选择城市</p>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">选择省</span> <select id="province_check"
						class="form-control select-style">
						<option value="">--请选择--</option>
					</select>
				</div>
				<div class="controls">
					<span class="control-label">选择市</span> <select id="city_check"
						class="form-control select-style">
						<option value="">--请选择--</option>

					</select>
				</div>
			</div>
			<div class="control-group button-group">
				<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
		</div>

	</div>
	<script>
		$(function() {	
			var queryCityUrl = '<%=basePath%>generalUser/queryAreaInfo.do?rnd='+ Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)

			//关闭
			$("#close_modal").click(function() {
				parentOBJ.setWindowDialog(720,700);				
				parentOBJ.modalOut("user/userListEdit.jsp",json_data);
			});
			$("#submit_modal").click(function() {
				parentOBJ.setWindowDialog(720,700);	
				var city = $("#city_check option:selected").text();
				var cityCode = $("#city_check").val();
				$.extend(true,json_data,{edit:"callbackCity",city:city,cityCode:cityCode});
				parentOBJ.modalOut("user/userListEdit.jsp",json_data);
			});
			//保存提交
			var cityArray = [];
			$.ajax({
				url : queryCityUrl,
				type : "post",
				data : {				
				},
				success : function(data) {
					console.log(data);
					var rows = data.data;
					var provinceCode = "";
					cityArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].province_code != "000000"){
							provinceCode += '<option value="'+rows[i].province_code+'">'+rows[i].province_name+'</option>';
						}					
					}
					$("#province_check").append(provinceCode);
				},
				error : function() {
					console.error("提交失败");					
				}
			});
			
			$("#province_check").change(function(){
				if($(this).val() == ""){
					$("#city_check").html('<option value="">--请选择--</option>');
				}
				
				for(var i=0;i<cityArray.length;i++){
					if($(this).val() == cityArray[i].province_code){
						var cityCode = '<option value="">--请选择--</option>';
						for(var j=0;j<cityArray[i].city_info.length;j++){
							cityCode += '<option value="'+cityArray[i].city_info[j].city_code+'">'+cityArray[i].city_info[j].city_name+'</option>';
						}						
					}
				}
				$("#city_check").html(cityCode);
			});
		});
	</script>
</body>
</html>
