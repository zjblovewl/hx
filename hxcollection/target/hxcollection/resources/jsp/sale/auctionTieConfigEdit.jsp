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
<title>拍卖帖场次配置</title>
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
	    height: 73%;
	}
	
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">拍卖场次配置</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="area-text-parent">
					<span class="control-label">拍卖场次</span>
					 <textarea id="number" rows="2" cols="10"  class="area-text" readonly="readonly"></textarea>
					 <span class="lable-symbol">*</span>	
					 <div class="lable-symbol-div"></div>						
			</div>
			<div class="control-group">
				<div class=" controls">
					<span class="control-label">拍卖天数</span>
					 <input id="days" type="text"  class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
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
			var saveAuctionSessionUrl = '<%=basePath%>goods/saveAuctionSession.do?rnd=' + Math.random();		
			var getAuctionSessionUrl = '<%=basePath%>goods/getAuctionSession.do?rnd=' + Math.random();	
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)
			
			if(json_data.edit && json_data.edit == "callback"){
				$("#number").val(json_data.callTxt);
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
				var params = {};
				params.days = $("#days").val();
				params.auction_sessions = json_data.callTxt;
				//判断空
				if(params.auction_sessions==""){
					$("#number").siblings('*:last').text('拍卖场次不能为空！');
				}else{
					$("#number").siblings('*:last').text('');			
				}
				if(params.days==""){
					$("#days").siblings('*:last').text('结拍天数不能为空！');
				}else{
					$("#days").siblings('*:last').text('');			
				}
				if(params.days=="" || params.auction_sessions==""){
					return;
				}
				$.ajax({
					url:saveAuctionSessionUrl,
					type:"post",
					data:{
						paramsStr:JSON.stringify(params) 
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
			//选择场次
			$("#number").bind('click',function(){				
				parentOBJ.setWindowDialog(720,400);
				var sessionUrl = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random()+'&key=auction_session&isAll=false';				
				parentOBJ.modalOut("component/mutiCheckboxComponent.jsp",{name:"拍卖帖场次选择",edit:"session",url:"sale/auctionTieConfigEdit.jsp",width:720,height:300,queryUrl:sessionUrl});		
			});
			$('#days').bind('input propertychange', function() {			
			    $(this).val($(this).val().replace(/[^\d]/g,''));
			});
			// 
			$.ajax({
				url: getAuctionSessionUrl,
				type: "post",
				data:{
				},
				success: function(data){
					var result = data.data;
					console.log(result);
					var days = result.days;
					var auction_session = result.auction_session;
					$('#days').val(days);
					$('#number').append(auction_session);
				},
				error: function(){
					console.error("请求失败");
				}
			});
			
			
		});
	</script>
</body>
</html>
