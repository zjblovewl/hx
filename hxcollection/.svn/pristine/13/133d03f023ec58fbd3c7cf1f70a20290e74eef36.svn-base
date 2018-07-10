<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>日志查询</title>
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
			width: 35%;
		}	
    </style>
</head>
<body>
<div>
    <div class="customsearch allLine" id="query-form">
     	<div class="customsearch-input">
	     	<span class="label-input-oneline">操作内容：</span>
	     	<input name="operation" placeholder='操作内容' type="text"class="form-control">
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
    <table id="table"></table>
</div>
<script>
	var queryUrl = '<%=basePath%>operationLog/queryOperationLogList.do?rnd=' + Math.random();
	var columnsArray = [{
	    checkbox: false,
	    visible: false    //是否显示复选框
	}, {
	    field: 'user_name',
	    title: '用户昵称',
	    width:150
	}, {
	    field: 'ip_address',
	    title: '操作者IP地址',
	    width:150
	}, {
	    field: 'operation',
	    title: '操作内容',
	    width:150
	}, {
	    field: 'create_time',
	    title: '操作时间',
	    width:150
	}, {
	    field: 'error_msg',
	    title: '异常信息',
	    width:150
	}];
	$("#table").InitMainTable(queryUrl, columnsArray);
	

</script>
</body>
</html>