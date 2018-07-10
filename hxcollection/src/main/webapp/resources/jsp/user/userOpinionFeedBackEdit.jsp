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
<title>查看用户意见反馈</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<style>
.controls input {
	width: 70%;
}

.controls .control-label {
	width: 65px !important;
}

.only_read {
	background: #ddd;
}

.displaynone {
	display: none;
}
.select-style{
	width: 197px !important;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">查看用户意见及反馈</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
		
			<div class="control-group">
				<div class="controls">
					<span class="control-label">昵称</span> 
					<input id="nick_name" type="text" placeholder="输入昵称"
						class="input-text">					
				</div>
			</div>
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">提交时间</span>
					<input id="submit_time" type="text" placeholder="提交时间" class="input-text"/>						
				</div>
			</div>
			
			<div class="area-text-parent">
					<span class="control-label">用户反馈内容</span>
					<textarea id="feedback_content" rows="3" cols="20" class="area-text"></textarea>						
			</div>
			
		</div>
	</div>
			<div class="modal-footer">
				<button id="submit_modal" class="btn btn-success marginRight10">保存</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
	<script>
	
		var updateUserFCUrl = '<%=basePath%>userOpinionFeedback/addOrUpdateUserFC.do?rnd=' + Math.random();
		 
		$(function() {
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			if (json_data.nick_name) {
				if(json_data.edit == 1){
					$("#title_text").text("查看用户详情");
					$("#submit_modal").hide();
					$('input').each(function(){
						$(this).attr("readonly","readonly");
						$(this).css({							
							background:'#fff'
						});					
					});
					$('textarea').each(function(){
						$(this).attr("readonly","readonly");
						$(this).css({							
							background:'#fff'
						});						
					});
				}			
				console.log("用户反馈数据集:"+json_data)
				$("#nick_name").val(json_data.nick_name);
				$("#submit_time").val(json_data.submit_time);							
				$("#feedback_content").val(json_data.feedback_content);				
				$("#processing_state").val(json_data.processing_state);		
			}

			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			
			//查看接口 TODO =======================		
			var nick_name = $("#nick_name").val();
			var feedback_content = $("#feedback_content").val();
			var submit_time = $("#submit_time").val();
			var processing_state = $("#processing_state").val();
			var jsonObj = {};
			jsonObj.id = json_data.id;
			jsonObj.feedbackContent = json_data.feedback_content;
			jsonObj.submitTime = json_data.submit_time;
			jsonObj.processingState = json_data.processing_state;
					
	  		$.ajax({
	  			url:updateUserFCUrl,
	  			type:'post',
	  			data:{
	  				ids:JSON.stringify(jsonObj)
	  			},
	  			dataType:"json",
	  			success:function(data){
	  				parentOBJ.resfreshTable();
	  			},
	  			error:function(){
	  				console.error("error");
	  			}
	  		});
	  		
		});				
		
	</script>
</body>
</html>
