<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录日志</title>
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
		.customsearch-input{
			width: 30%;
		}
		.label-input-oneline{
			width: 50px;
		}
    </style>
</head>
<body>
<div>
   <div class="customsearch allLine" id="query-form">
     	<div class="customsearch-input">
     		<span class="label-input-oneline">用户：</span>
	     	<input name="id" placeholder='用户' type="text" class="form-control">
     	</div>
     	<div class="customsearch-input">
     		<span class="label-input-oneline">昵称：</span>
     		<input name="nickName" placeholder='昵称' type="text"class="form-control">
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
       
    <table id="table">
       
    </table>
</div>
<script>
    var queryUrl = '<%=basePath%>userLoginLog/queryUserLoginLogList.do?rnd=' + Math.random();
    var deleteByIdUrl = '<%=basePath%>generalUser/deleteUserById.do?rnd=' + Math.random();

    var columnsArray = [{
    	field: 'checkbox',
        checkbox: true,
        width: 40,
        visible: false  
    }, {
        field: 'id',
        title: 'ID',
        width: 150,
        visible: false  
    }, {
        field: 'nick_name',
        title: '昵称',
        width: 150
    }, {
        field: 'login_num',
        title: '登录次数',
        width: 150
    }, {
        field: 'version',
        title: '版本号',
        width: 150
    }, {
        field: 'equipment_information',
        title: '设备信息',
        width: 150
    }, {
        field: 'login_time',
        title: '登录时间',
        width: 150
    }, {
        field: 'operation',
        title: '操作',
        width: 150,
        visible: false,
        formatter:onlyDelete()
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    var modalObj = window.parent;
	function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(deleteByIdUrl,{userIds:id});
			$("#table").bootstrapTable('refresh');			
		},function(){
			return false;
		});
		
	}
	$("#btnDel").click(function(){
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;
    	});	
		if(getSelectRows.length ==0){
			modalObj.$.showSuccessTimeout("请选择要删除的条目");
			return;	
		}
		modalObj.$.showConfirm("确认批量删除?", function(){
			var delID = [];				
			for(var i=0;i<getSelectRows.length;i++){
				delID.push(getSelectRows[i].id);
			}
			$.ajax({
				url: deleteByIdUrl,
				type:"post",
				data:{
					userIds:delID.join(",")
				},
				success:function(data){				
					$("#table").bootstrapTable('refresh');						
				},
				error:function(){
					console.error("error");
				}
			});	
		},function(){
			return false;
		});
		return false;
	});
</script>
</body>
</html>