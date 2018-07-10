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
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="../../fonts/iconfont.css"/>

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
    .content_parent{
    	display: inline-block;
    	width: 98%;
    }
    .content_parent li{
    	display: flex;
    	align-items: center;
	    height: 90px;
	    border-bottom: 1px solid #ddd;
    }
    .content_pic{
    	width: 50px;
	    height: 50px;
	    border-radius: 25px;
	    border: 1px solid #ddd;
    }
    .content_txt{
    	margin-left: 16px;
	    height: 80%;
	    display: flex;
	    flex-direction: column;
	    justify-content: space-around;
	    flex: 1;
    }
    .content_time{
    	 height: 80%;
	    text-align: right;
	    display: flex;
	    flex-direction: column;
	    justify-content: space-around;
    }
    .content_close{
    	cursor:pointer;
    }
    
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">拍卖帖评论</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>

	<div class="modal-container">
		<div class="form-div-edit">	
			<div class="control-group">		
				<ul class="content_parent">
					<li>
						<div class="content_pic"></div>
						<div class="content_txt">
							<div class="content_name">钱币收藏阁</div>
							<div class="content_advice"> 好东西,希望多上点好藏品!</div>			
						</div>
						<div class="content_time">
							<div class="sub_time">2018-05-15 20:24:19</div>
							<div class="content_close" onclick="delComment().call(this)">
								<i class="iconfont icon-shachu-xue"></i>
							</div>
						</div>			
					</li>	
					<li>
						<div class="content_pic content_block"></div>
						<div class="content_txt">
							<div class="content_name content_block">钱币收藏阁</div>
							<div class="content_advice content_block"> 好东西,希望多上点好藏品!</div>			
						</div>
						<div class="content_time">
							<div class="sub_time">2018-05-15 20:24:19</div>
							<div class="content_close" onclick="delComment().call(this)">
								<i class="iconfont icon-shachu-xue"></i>
							</div>
						</div>		
						
					</li>					
				</ul>			
			</div>
		</div>
	</div>
			<div class="modal-footer">			
				<button id="close_modal" class="btn btn-danger">取消</button>
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
