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
<title>定时任务</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timePermissionUtils.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.controls input {
	width: 70%;
}

.controls .control-label {
	width: 65px !important;
}

.modal-container {
	padding-top: 18px !important;
	height: 80% !important;
}

.select-style {
	width: 60% !important;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增用户</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>	
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">任务名</span> <input id="name" type="text"
						placeholder="输入任务名" class="input-text"> <span
						class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">任务组</span> <input id="t_group"
						type="text" placeholder="输入任务组" class="input-text"> <span
						class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">定时规则</span> <input id="expression"
						type="text" placeholder="输入定时规则" class="input-text"> <span
						class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">启用状态</span> <select id="status"
						class="form-control select-style">
						<option value="0">否</option>
						<option value="1">是</option>
					</select> <span class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<span class="control-label">任务类</span> <input id="classname"
						type="text" placeholder="输入任务类" class="input-text"> <span
						class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">描述</span> <input id="description"
						type="text" placeholder="输入描述信息" class="input-text"> <span
						class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<span class="control-label">开始时间</span> <input id="start_time"
						type="text" placeholder="选择开始时间" class="input-text"
						data-type="input-time"> <span class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">结束时间</span> <input id="end_time"
						type="text" placeholder="选择结束时间" class="input-text"
						data-type="input-time"> <span class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group button-group">
				<button id="submit_modal" class="btn btn-success marginRight10">保存</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
		</div>
	</div>
	<script>
	
	
	var checkTaskNameUrl = '<%=basePath%>timeTask/checkSameTaskName.do?rnd=' + Math.random();
	$(function(){
		var modalObj = window.parent;
		//获取url
		var src_data = modalObj.document.getElementById("modal-iframe").getAttribute("src-data");	
		var json_data = JSON.parse(src_data);
		console.log(json_data)
		if(json_data.name){
			if(json_data.edit == 1){
				$("#title_text").text("查看定时任务");
				$("#submit_modal").hide();
				$('input').each(function(){			
					$(this).attr("disabled","disabled")					
				});
				$('select').each(function(){			
					$(this).attr("disabled","disabled")					
				});
				$(".lable-symbol").each(function(){
					$(this).hide();
				});
			}else{
				$("#title_text").text("编辑定时任务");
			}
		
			$("#name").val(json_data.name);
			$("#t_group").val(json_data.t_group);
			$("#status").val(json_data.status);
			$("#expression").val(json_data.expression);
			$("#classname").val(json_data.classname);
			$("#description").val(json_data.description);
			$("#start_time").val(json_data.start_time);
			$("#end_time").val(json_data.end_time);
			$("#del_flag").val(json_data.del_flag);
		}
	
	$("#close_modal").click(function(){
		modalObj.modalIn();	
	});
	
	$("#close_dialog").click(function(){
		modalObj.modalIn();	
	});
	

	$("#submit_modal").click(function(){
			var name = $("#name").val();
			var t_group = $("#t_group").val();
			var expression = $("#expression").val();
			var status = $("#status").val();
			var classname = $("#classname").val();
			var description = $("#description").val();
			var start_time = $("#start_time").val();
			var end_time = $("#end_time").val();
			var del_flag = $("#del_flag").val();
			
			if(name=="" ){
				$("#name").siblings('*:last').text('任务名不能为空！');
				return;
			}else if(t_group ==""){
				$("#t_group").siblings('*:last').text('任务组不能为空！');
				return;
			}if(expression==""){
				$("#expression").siblings('*:last').text('定时规则不能为空！');
				return;
			}if(classname==""){
				$("#classname").siblings('*:last').text('任务类不能为空！');
				return;
			}if(description==""){
				$("#description").siblings('*:last').text('描述不能为空！');
				return;
			}if(start_time==""){
				$("#start_time").siblings('*:last').text('开始时间不能为空！');
				return;
			}if(end_time==""){
				$("#end_time").siblings('*:last').text('结束时间不能为空！');
				return;
			}
			
			 var jsonObj = {};
			 jsonObj.id = json_data.id;
	         jsonObj.name = name;
	         jsonObj.tGroup = t_group;
	         jsonObj.expression = expression
	         jsonObj.status = status;
	         jsonObj.classname = classname;
	         jsonObj.description = description;
	         jsonObj.startTime = start_time;
	         jsonObj.endTime = end_time;
	         jsonObj.delFlag = del_flag;
	         
			$.ajax({
				url : "<%=basePath%>timeTask/addOrUpdateTimeTask.do?rnd=' + Math.random()",
				type : "post",
				data:{data:JSON.stringify(jsonObj)},
				success:function(data){
					if(data == null){
						modalObj.$.showSuccessTimeout("添加失败");
					}else{
						modalObj.$.showSuccessTimeout("添加成功");
						modalObj.resfreshTable();
					}
				},
				error:function(){
					console.error("error");
				}
			});
			modalObj.modalIn();//关闭
			return false;
		});
	
		$('input').bind('change',function(){
			var text = $(this).val();
			if(text != ""){
				$(this).siblings('*:last').text('');
			}
		});
		//校验用户昵称{isExist:true}
		$("#name").change(function(){				
				var id = json_data.id;
				var name = $(this).val();	
				//校验空
				if(name == ""){
					modalObj.$.showSuccessTimeout("昵称不能为空");
					$("#name").next().text("X");
					$("#name").next().addClass("lable-symbol");
					$("#name").next().removeClass("lable-symbol-green");
					return;
				}
				//校验格式
				var RegName = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
				if (name.search(RegName) == -1) {
					modalObj.$.showSuccessTimeout("定时任务名称不能含有特殊字符且长度在6-18之间，请重新输入！");
					this.focus();
					return;
				}			
				$.ajax({
					url:checkTaskNameUrl,
					type:"post",
					data:{
						id:id,
						name: name
					},
				
					success:function(data){
						if(data=="false"){
							$("#name").next().text("X");
							$("#name").next().addClass("lable-symbol");
							$("#name").next().removeClass("lable-symbol-green");
							$("#name").val("");
							return;
						}else{
							$("#name").next().text("√");
							$("#name").next().addClass("lable-symbol-green");
							$("#name").next().removeClass("lable-symbol");
							return;
						}
					},
					error:function(){
						console.error("连接失败");
					}				
				});
		});
	});
</script>
</body>
</html>
