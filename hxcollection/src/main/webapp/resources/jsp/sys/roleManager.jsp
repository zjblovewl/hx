<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <title>角色管理</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/laydate/laydate.js"></script>
    <script src="../../js/timeUtils.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableUtil.js"></script>

    <style>
		.bootstrap-table{
			top:46px !important;
		}
		.customsearch-input{
			width: 35%;
		}
    </style>
</head>
<body>
<div>
   <div class="customsearch allLine" id="query-form">
   		
     	<div class="customsearch-input">
     		<span class="label-input-oneline">角色名称：</span>
     		<input name=roleName placeholder='角色名称' type="text"class="form-control">
     	</div>          

        <div class="customsearch-input allLine_btn">
            <button id="btn_search" type="button" class="btn btn-primary btn-space marginRight10">
                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
            </button>
            <button id="btn_reset"  type="button" class="btn btn-default btn-space">
                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
            </button>
        </div>
       </div>
        <div id="toolbar-btn" class="btn-group pull-left">
            <button id="build" type="button" class="btn btn-primary btn-space marginRight5">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">新增</span>             
            </button>
            <button id="btnDel" type="button" class="btn btn-danger btn-space">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">删除</span>
            </button>
        </div>
    <table id="table">
       
    </table>
</div>
<script>
    var queryUrl = '<%=basePath%>role/getRoleRecords.do?rnd=' + Math.random();
    var deleteRoleUrl = '<%=basePath%>role/deleteRoleByIds.do?rnd=' + Math.random();
    var columnsArray = [{
    	checkbox: true,
        visible: true,
        width:50
    }, {
        field: 'id',
        title: 'id',
        width: 50,
        visible:false
    }, {
        field: 'role_name',
        title: '角色名称',
        width: 150
    }, {
        field: 'description',
        title: '描述',
        width: 150
    }, {
        field: 'create_time',
        title: '创建时间',
        width: 150
    }, {
        field: 'operation',
        title: '操作',
        width: 100,
        formatter :limitDeleteEdit()
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    var modalObj = window.parent;
	$("#build").click(function(){
		//调取父页面模态框		
		modalObj.modalOut("sys/roleManagerEdit.jsp",[{}]);		
	});
	//编辑按钮	
	function getJSPurl(){
		return "sys/roleManagerEdit.jsp";
	}
	function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(deleteRoleUrl,{roleId:id});
			$("#table").bootstrapTable('refresh');		
		},function(){
			return false;
		});
		
	}
	//权限配置按钮
	function getRoleControlList(){
		return "sys/roleControlListEdit.jsp";
	}
	$("#btnDel").click(function(){
		var delID = [];
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;
    	});
		if(getSelectRows.length ==0){
			alert("未勾选要删除的条目");
		}
		for(var i=0;i<getSelectRows.length;i++){
			delID.push(getSelectRows[i].id);
		}
		$.ajax({
			url:deleteRoleUrl,
			type:"post",
			data:{
				roleId: delID.join(",")
			},
			success:function(data){
				console.log("success");
				$("#table").bootstrapTable('refresh');	
			},
			error:function(){
				console.error("error");
			}
		});
	});
</script>
</body>
</html>