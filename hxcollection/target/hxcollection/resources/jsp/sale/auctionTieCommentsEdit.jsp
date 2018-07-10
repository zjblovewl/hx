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
<title>交易帖评论</title>
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
	.modal-container{
	    overflow-y: scroll;
		padding: 10px 20px 0 20px !important;
	    height: 86%;
	}

       .content_parent{
    	display: inline-block;
    	width: 98%;
    }
    .content_parent li{
    	display: flex;
    	align-items: center;
    	margin-bottom: 8px;
    }
    .content_block{
    	display: inline-block;
    }
    .content_pic{
    	width: 50px;
	    height: 50px;
	    border: 1px solid #ddd;
	    border-radius: 2px;
	    vertical-align: middle;
	    margin-left: 10px;
    }
    .content_name{
    	margin-right: 30px;
    }
    .content_advice{
    	flex-grow: 2;
    }
    .content_close{
    	color: #ff0000;
    	padding: 0 5px;
    	cursor: pointer;
    }
    .content_close:hover{
    	background: #ddd;
    }
    .content_reply{
    	cursor: pointer;
    }
    .content_reply:hover{
    	background: #ddd;
    }
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">交易帖评论</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>

	<div class="modal-container">
		<div class="form-div-edit">	
			<div class="control-group">		
				<ul class="content_parent">
					<li>
						<div class="content_pic content_block"></div>
						<div class="content_name content_block">钱币收藏阁</div>
						<div class="content_advice content_block"> 好东西,希望多上点好藏品!</div>						
						<div class="content_close" onclick="delComment().call(this)">X</div>
					</li>	
					<li>
						<div class="content_pic content_block"></div>
						<div class="content_name content_block">钱币收藏阁</div>
						<div class="content_advice content_block"> 好东西,希望多上点好藏品!</div>												
						<div class="content_close" onclick="delComment().call(this)">X</div>
					</li>					
				</ul>			
			</div>
			<div class="control-group button-group">			
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
		</div>

	</div>
	<script>
		$(function() {	
			var querygoodsUrl = '<%=basePath%>goods/getGoodsComments.do?rnd=' + Math.random();	
			var delGoodsReplyUrl = '<%=basePath%>goods/deleteGoodsCommentByIds.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)
			
			//初始化加载
			var params = {};
			params.id = json_data.goodsId;
			$.ajax({
				url: querygoodsUrl,
				type: 'post',
				dataType: 'json',
				data:{
					paramsStr: JSON.stringify(params) 
				},
				success: function(data){
					console.log(data)
				},
				error: function(){
					console.error("加载错误");
				}
				
			});
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});	
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});	
			//删除评论
			function delComment(){
				var commentIds = $(this).attr("id");
				$.ajax({
					url: delGoodsReplyUrl,
					type: 'post',
					data:{
						ids: commentIds
					},
					dataType: 'json',
					success: function(data){
						
					},
					error:function(){
						console.error("删除失败");
					}
				});
			}
			
		});
	</script>
</body>
</html>
