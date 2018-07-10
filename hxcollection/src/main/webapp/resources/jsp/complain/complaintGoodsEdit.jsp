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
<title>藏品投诉处理</title>
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.radio_group {
	display: inline-block;
}

.radio_group .status_div {
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
		<div class="pull-left" id="title_text">投诉处理</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="area-text-parent">
				<span class="control-label">处理备注</span>
				<textarea id="remarks" placeholder="备注..." rows="3" cols="20"
					class="area-text"></textarea>
				<div class="lable-symbol-div"></div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">处理状态</span>
					<div class="radio_group">
						<div class="status_div">
							<input type="radio" name="dealStatus" value="1"/> <span>已处理</span>
						</div>
						<div class="status_div">
							<input type="radio" name="dealStatus" value="0"/> <span>无效投诉</span>
						</div>
					</div>
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
			var saveContentURL = '<%=basePath%>goodsComplaint/addGoodsComInfo.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);

			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});

			$("#submit_modal").bind('click', function() {
				var remarks = $("#remarks").val();
				var dealStatus = $("input[type='radio']:checked").val();	
				console.log(remarks+dealStatus)
				var jsonObj = {};
				jsonObj.remarks = remarks;
				jsonObj.dealStatus = dealStatus;
				jsonObj.id = json_data.id;
				console.log(jsonObj.id+"77777777777777")
				$.ajax({
					url : saveContentURL,
					type : "post",
					data :  {
						paramsStr:JSON.stringify(jsonObj)
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
