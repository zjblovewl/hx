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
<title>新增拍卖订单</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../css/common_menu.css">
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="../../fonts/iconfont.css"/>

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
	.label-select-oneline {
	    width: 18% !important;
	    text-align: left !important;
	}
	.select-style {
    	width: 60% !important;
    }
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">拍卖订单</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">订单号</span>
					 <input id="order_code" type="text" class="input-text" readonly="readonly">
				</div>				
			</div>			
			<div class="area-text-parent">
					<span class="control-label">收货地址</span>
					 <textarea id="address" rows="3" cols="20"  class="area-text"></textarea>		
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">收货人</span>
					 <input id="name" type="text" class="input-text" >
				</div>
				<div class="controls">
					<span class="control-label">联系方式</span>
					 <input id="phone" type="text"  class="input-text">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">物流公司选择</span>
					<select id="is_authentication" class="form-control select-style">
						<option>中通快递</option>
						<option>申通快递</option>
						<option>顺丰快递</option>
					</select>				
				</div>
				<div class="controls">
					<span class="control-label">当前状态</span>
					<select id="is_authentication" class="form-control select-style">
						<option>待支付</option>
						<option>已付款</option>
					</select>				
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
			var queryBigClazzUrl = '<%=basePath%>goodsClassification/getTransClassifiyRecords.do?rnd=' + Math.random();
			var saveMenuURL = '<%=basePath%>menu/saveOrUpdateMenu.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			
			//初始化
			/* $("#name").val(json_data.name);
			$("#parent_name").val(json_data.parent_name);
			$("#parent_name").attr("pid",json_data.res_pid);
			$("#url").val(json_data.url);
			$("#sort").val(json_data.sort);
			$("#lable_icon").val(json_data.icon); */
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//保存提交
			$("#submit_modal").click(function() {
				var res_name = $("#name").val();
				var resId = "";
				if(json_data.menuId){
					resId = json_data.menuId;
				}
				var res_pid = $("#parent_name").attr("pid");
				var res_url = $("#url").val();
				var res_order = $("#sort").val();
				var res_icon  = $("#lable_icon").val();
				console.log(res_name+ "||"+res_pid+ "||"+res_url+ "||"+res_order+ "||"+res_icon+"||"+resId);
				$.ajax({
					url:saveMenuURL,
					type:"post",
					data:{
						resId: resId,
						res_name:res_name,
						res_pid:res_pid,
						res_url:res_url,
						res_order:res_order,
						res_icon:res_icon
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
				
			var dataArray = [];
			$.ajax({
				url: queryBigClazzUrl,
				type: "post",
				data:{},
				success: function(data){
					var rows = data.resultList;
					var bigCode ="";
					var smallCode = "";
					dataArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].parentCode == 0){
							bigCode += '<option name="'+rows[i].classCode+'" value="'+rows[i].classId+'">'+rows[i].className+'</option>';
						}			
					}
					$("#bigClazz").append(bigCode);				
				},
				error: function(){
					console.error("请求失败");
				}
			});
			//大类小类联动事件
			 $("#smallClazz").attr("disabled","disabled");
			 $("#bigClazz").change(function() {
				$("#smallClazz").attr("disabled",false);
				var currentClazz = $('#bigClazz option:selected').attr("name");
				if(currentClazz == 0){
					$("#smallClazz").attr("disabled","disabled");
				}
				var smallCode = '<option value="0">全部</option>';
				for(var i=0;i<dataArray.length;i++){
					if(dataArray[i].parentCode == currentClazz){
						smallCode += '<option value="'+dataArray[i].classId+'">'+dataArray[i].className+'</option>';
					}
				}
				$("#smallClazz").html(smallCode);		
			 });
		});
	</script>
</body>
</html>