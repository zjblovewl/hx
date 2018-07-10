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
<title>菜单列表</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
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
    height: 79%;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">菜单列表</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div id="role_list" class="form-div-edit">
			<div data-options="iconCls:'icon-remove'" title="功能菜单">
                <ul id="ztree" class="ztree"></ul>
           </div>
			<div class="control-group button-group">			
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
		</div>

	</div>
	<script>
		$(function() {
			var getDataListUrl ='<%=basePath%>role/getMenuTreeRecords.do?rnd=' + Math.random();		
			var parentOBJ = window.parent;	
			
			//获取树菜单数据
			$.ajax({
				url:getDataListUrl,
				type:"POST",
				data:{					
				},
				success:function(data){
					createTreeList(data);
				}
			});
			//创建树 
			function createTreeList(data){				
				var setting = {						
						data: {
							simpleData: {
								enable: true,
								idKey: "menuId",
								pIdKey: "parentId",
								rootId: ""
							}
						},
						callback: {  
							
				        }
						 		
		           };
				$.fn.zTree.init($("#ztree"), setting, data);
			}
						
			
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalInMenu();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalInMenu();
			});
			//保存提交
			$("#submit_modal").click(function() {				
				var treeObj = $.fn.zTree.getZTreeObj("ztree");
				var selectedNodes = treeObj.getSelectedNodes();	
				console.log(selectedNodes[0])
				parent.$("#modal-iframe")[0].contentWindow.$("#parent_name").val(selectedNodes[0].menuName);
				parent.$("#modal-iframe")[0].contentWindow.$("#parent_name").attr("pid",selectedNodes[0].menuId);
				parentOBJ.modalInMenu();//关闭
				
			});
		
		});
	</script>
</body>
</html>
