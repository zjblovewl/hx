<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>数据字典编辑</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
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
.modal-container{
	padding-top: 2px !important;
	height: 73% !important;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增数据字典</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">字典值</span>
					 <input id="dic_value" type="text" placeholder="输入字典值" class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>	
			<div class="area-text-parent">
						<span class="control-label">描述</span>
					 <textarea id="dic_name" rows="3" cols="20"  class="area-text"></textarea>
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>			
			</div>
			<div class="control-group button-group">			
				<button id="submit_modal" class="btn btn-success marginRight10">保存提交</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
		</div>

	</div>
	<script>
		$(function() {
			var saveOrUpdateUrl = '<%=basePath%>dictionary/saveOrUpdateDictionary.do?rnd=' + Math.random();
			var checkExistURL = '<%=basePath%>dictionary/checkDicCode.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			var dic_name,dic_value,describe,pid,sort;
			if (json_data.dic_name) {
				if(json_data.edit == 1){
					$("#title_text").text("查看数据字典");
					$("#submit_modal").hide();
					$('input').each(function(){
						$(this).attr("disabled","disabled");											
					});
					$("textarea").each(function(){
						$(this).attr("disabled","disabled");	
					});
					$(".lable-symbol").each(function(){
						$(this).hide();
					});
				}else{
					$("#title_text").text("编辑数据字典");
				}
				$("#title_text").val("编辑数据字典");
				$("#dic_name").val(json_data.dic_name);
				$("#dic_value").val(json_data.dic_value);					
			}
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//保存提交
			$("#submit_modal").click(function() {
				var dic_name = $("#dic_name").val();
				var dic_value = $("#dic_value").val();	
				
				if(dic_value == ""){
					$("#dic_value").siblings('*:last').text("字典值不能为空");
				}else{
					$("#dic_value").siblings('*:last').text("");
				}if(dic_name == ""){
					$("#dic_name").siblings('*:last').text("描述不能为空");
				}else{
					$("#dic_name").siblings('*:last').text("");
				}if(dic_value == "" || dic_name == ""){
					return;
				}
				
				var ckName = $("#dic_name").siblings('*:last').text();
				var ckValue = $("#dic_value").siblings('*:last').text();				
				
				if(ckName !=""){
					$("#dic_name").siblings('*:last').text("校验不通过");
				}if(ckValue != ""){
					$("#dic_value").siblings('*:last').text("校验不通过");
				}if(ckName !="" || ckValue != ""){
					return;
				}
				
				$.ajax({
					url : saveOrUpdateUrl,
					type : "post",
					data : {
						dic_name : dic_name,
						dic_value : dic_value,
						 dicId : json_data.id == undefined?"":json_data.id,
						pId: "0"		 
					},
					success : function(data) {

					},
					error : function() {
						console.error("error");
					}
				});
				
				parentOBJ.modalIn();//关闭
				parentOBJ.resfreshTable();
				
			});
			
			//校验字典值是否存在	
			$('#dic_value').bind('change', function() {			
				var params = {};		
				params.p_id = "0";
				params.dic_id = json_data.id == undefined?"":json_data.id;
				params.dic_value = encodeURI($("#dic_value").val(),'utf-8');
				
				if(params.dic_value == ""){
					$("#dic_value").siblings('*:last').text("字典值不能为空");
					return;
				}
				
				console.log(params);
				$.ajax({
					url: checkExistURL,
					type:"post",
					dataType: 'json',
					data:{
						paramsStr : JSON.stringify(params) //string类型
					},
					success:function(data){										
						if(data){														
							$("#dic_value").siblings('*:last').text("");
						}else{
							$("#dic_value").siblings('*:last').text("字典值已存在");
						}
						
					},
					error:function(){
						console.error("校验失败");							
					}
				});
			});
			
			$("#dic_name").bind('change',function(){
				var name = $("#dic_name").val();
				if(name == ""){
					$("#dic_name").siblings('*:last').text("描述不能为空");
					return;
				}else{
					$("#dic_name").siblings('*:last').text("");
				}
			});
			
		});
	</script>
</body>
</html>
