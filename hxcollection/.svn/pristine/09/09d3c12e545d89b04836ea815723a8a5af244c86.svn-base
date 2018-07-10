<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理数据字典</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
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
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增数据字典</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">字典名称</span>
					 <input id="dic_name" type="text" placeholder="输入字典名称" class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">字典值</span>
					 <input id="dic_value" type="text" placeholder="输入字典值" class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
			</div>	
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">排序</span>
					 <input id="sort" type="text" placeholder="排序" class="input-text">
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
			var saveOrUpdateUrl = '<%=basePath%>dictionary/saveOrUpdateKeyAndVal.do?rnd=' + Math.random();
			var checkExistURL = '<%=basePath%>dictionary/checkDicCode.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			
			if(json_data.edit == "new"){
				$("#title_text").text("新增键值对");				
			}else{
				$("#title_text").text("编辑键值对");
			}
			$("#dic_name").val(json_data.dic_name);
			$("#dic_value").val(json_data.dic_value);								
			$("#sort").val(json_data.sort);	
					
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.setWindowDialog(720,450);
				var closeJson = {};
				closeJson.pid = json_data.pid;
				parentOBJ.modalOut("sys/dataKeyValueEdit.jsp",closeJson);
			});
			$("#close_dialog").click(function() {
				parentOBJ.setWindowDialog(720,450);
				var closeJson = {};
				closeJson.pid = json_data.pid;
				parentOBJ.modalOut("sys/dataKeyValueEdit.jsp",closeJson);
			});
			
			//保存提交
			$("#submit_modal").click(function() {
				var dic_name = $("#dic_name").val();
				var dic_value = $("#dic_value").val();					
				var dicId = json_data.id == undefined?"":json_data.id;
				var sort = $("#sort").val();				
				
				//校验合法
				var ckName = $("#dic_name").siblings('*:last').text();
				var ckValue = $("#dic_value").siblings('*:last').text();
				
				if(ckName !=""){
					$("#dic_name").siblings('*:last').text("校验不通过");
					return;
				}else if(ckValue != ""){
					$("#dic_value").siblings('*:last').text("校验不通过");
					return;
				}
				//校验空
				if(dic_value == ""){
					$("#dic_value").siblings('*:last').text("字典值不能为空");
				}else{
					$("#dic_value").siblings('*:last').text("");
				}
				if(dic_name == ""){
					$("#dic_name").siblings('*:last').text("描述不能为空");
				}else{
					$("#dic_name").siblings('*:last').text("");
				}
				if(sort == ""){
					$("#sort").siblings('*:last').text("排序不能为空");
				}else{
					$("#sort").siblings('*:last').text("");
				}
				if(dic_value == "" || dic_name == "" || sort == ""){
					return;
				}
				
				$.ajax({
					url : saveOrUpdateUrl,
					type : "post",
					data : {
						dic_name : dic_name,
						dic_value : dic_value,						
						dicId : dicId,
						sort : parseInt(sort),
						p_id : json_data.pid
					},
					success : function(data) {

					},
					error : function() {
						console.error("error");
					}
				});
						
				parentOBJ.setWindowDialog(720,450);
				var closeJson = {};
				closeJson.pid = json_data.pid;
				parentOBJ.modalOut("sys/dataKeyValueEdit.jsp",closeJson);
			});
			
			//校验
			$('#sort').bind('input propertychange', function() {			
			    $(this).checkNumber();
			});
			
			//校验字典值是否存在	
			$('#dic_value').bind('change', function() {			
				var params = {};		
				params.p_id = json_data.pid;
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
			
			$("#sort").bind('change',function(){
				var sort = $("#sort").val();
				if(sort == ""){
					$("#sort").siblings('*:last').text("排序不能为空");
					return;
				}else{
					$("#sort").siblings('*:last').text("");
				}
			});
		});
	</script>
</body>
</html>
