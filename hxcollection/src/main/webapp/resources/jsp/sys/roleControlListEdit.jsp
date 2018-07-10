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
<title>菜单权限列表</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>

</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">菜单权限列表</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<form id="role_list" class="form-div-edit">
			<div data-options="iconCls:'icon-remove'" title="功能菜单">
                <div id="ztree" class="ztree"></div>
           </div>
		</form>
	</div>
			<div class="modal-footer">			
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
	<script>
		$(function() {
			var getDataListUrl ='<%=basePath%>role/getMenuTreeRecords.do?rnd=' + Math.random();
			var getRolePermissionUrl = '<%=basePath%>role/getRolePermission.do?rnd=' + Math.random();
			var saveRoleUrl = '<%=basePath%>role/saveRolePermission.do?rnd=' + Math.random();
			var parentOBJ = window.parent;			
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe2")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			//获取树菜单数据
			$.ajax({
				url:getDataListUrl,
				type:"POST",
				data:{					
				},
				success:function(data){
					//console.log(data)
					createTreeList(data);
				}
			});
			//创建树 
			function createTreeList(data){				
				var setting = {
						check: {
							enable: true,
							autoCheckTrigger: true/* ,
							chkStyle: "checkbox",
							chkboxType : { "Y" : "s", "N" : "s" } */
						},
						data: {
							simpleData: {
								enable: true,
								idKey: "menuId",
								pIdKey: "parentId",
								rootPId: 0
							}
						},
						callback: {  
							
				        }
						 		
		           };
				$.fn.zTree.init($("#ztree"), setting, data);
				getRolePermissionList();				
			}
			
			function getRolePermissionList(){
				//获取角色权限
				$.ajax({
					url: getRolePermissionUrl,
					type:"POST",
					data:{
					    roleId: json_data
					},
					success:function(data){					
						var rows = data.result;
						initRolePermission(rows);
					}
				});
			}
			//选中权限菜单			
			function initRolePermission(rows){
				var ztreeObj = $.fn.zTree.getZTreeObj("ztree")
				for(var i=0;i<rows.length;i++){
					console.log(rows)
					var node = ztreeObj.getNodeByParam("menuId",rows[i].menu_id,null);
					ztreeObj.checkNode(node,true,true);
				}								
			}
			
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalInRole();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalInRole();
			});
			//保存提交
			$("#submit_modal").click(function() {	
				var treeObj = $.fn.zTree.getZTreeObj("ztree")
				var selectedNodes = treeObj.getCheckedNodes();					
				var menuIdArray = [];
				for(var i=0;i<selectedNodes.length;i++){
					menuIdArray.push(selectedNodes[i].menuId);
				}
				
				$.ajax({
					url : saveRoleUrl,
					type : "POST",
					data : {
						roleIdWillSet: json_data,
						checkedList: menuIdArray.join(",")
					},
					success:function(data) {
						console.log("success");
					},
					error:function() {
						console.error("error");
					}
				});
				
				parentOBJ.modalInRole();//关闭
				
			});
		
		});
	</script>
</body>
</html>
