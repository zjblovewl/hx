<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <title>用户操作历史</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableUtil.js"></script>

    <style>
		.customsearch-input{
			width: 40%;
		}
		
    </style>
</head>
<body>
<div>
	<div class="customsearch allLine" id="query-form">
     	<div class="customsearch-input">
     		<span class="label-input-oneline">操作类型：</span>
	     	<input name="operationType" placeholder='操作类型' type="text" class="form-control">
     	</div>
     	<div class="customsearch-input">
     		<span class="label-input-oneline">操作内容：</span>
     	<input name="operationContent" placeholder='操作内容' type="text"class="form-control">
     	</div>          

        <div class="allLine marginRight20">
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
    var queryUrl = '<%=basePath%>userOperationLog/queryUserOperationLogList.do?rnd=' + Math.random();
    var deleteByIdUrl = '<%=basePath%>generalUser/deleteUserById.do?rnd=' + Math.random();

    var columnsArray = [{
        field: 'checkbox',
        checkbox: true,
        width: 40,
        visible: true  
    },  {
        field: 'id',
        title: '操作人ID',
        width: 150,
        visible: false  
    }, {
        field: 'user_id',
        title: '操作人角色',
        width: 150
    }, {
        field: 'nick_name',
        title: '操作人',
        width: 150
    }, {
        field: 'operation_type',
        title: '操作类型',
        width: 150,
        formatter:  function(value) {
            if (value == 1) {
                return "删除帖子";
            }
            return "锁定用户";
        }
    }, {
        field: 'operation_content',
        title: '操作内容'
    }, {
        field: 'operation_time',
        title: '操作时间',
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
