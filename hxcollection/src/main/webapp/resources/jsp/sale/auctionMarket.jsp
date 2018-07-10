<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
   <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>拍卖场次配置</title>
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

    </style>
</head>
<body>
<div>
     <div class="customsearch pull-right" id="query-form">
   		<div class="customsearch-input">
	     	<input id="startTime" name="startTime" data-type="input-time" placeholder='开始时间'  class="form-control">
     	</div>
     	<div class="customsearch-input">
	     	<input id="endTime" name="endTime" data-type="input-time" placeholder='结束时间' class="form-control">
     	</div>
     	<div class="customsearch-input">
	     	<input name="type" placeholder='类别' type="text" class="form-control">
     	</div>
     	<div class="customsearch-input">
	     	<input name="status" placeholder='拍卖状态' type="text" class="form-control">
     	</div>
     	<div class="customsearch-input">
     	<input name="content" placeholder='关键字' type="text"class="form-control">
     	</div>          

        <div class="customsearch-input allLine_btn">
            <button id="btn_search" type="button" class="btn btn-primary btn-space">
                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
            </button>
            <button id="btn_reset"  type="button" class="btn btn-default btn-space">
                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
            </button>
        </div>
       </div>
        <div id="toolbar-btn" class="btn-group pull-left">
            <button id="btn_add" type="button" class="btn btn-primary btn-space">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">新增</span>             
            </button>
            <button id="btn_delete" type="button" class="btn btn-danger btn-space">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">删除</span>
            </button>
        </div>
    <table id="table">
       
    </table>
</div>
<script>
    var queryUrl = '/product';
    var columnsArray = [{
        checkbox: true,
        visible: true    //是否显示复选框
    }, {
        field: 'type',
        title: '分类',
        width: 150
    }, {
        field: 'nickname',
        title: '用户昵称',
        width: 150
    }, {
        field: 'content',
        title: '内容',
        width: 150
    }, {
        field: 'start',
        title: '起拍价',
        width: 150
    }, {
        field: 'per',
        title: '每手加价'
    }, {
        field: 'postage',
        title: '邮费',
        width: 150
    }, {
        field: 'now',
        title: '当前价格',
        width: 150
    }, {
        field: 'person',
        title: '领先者',
        width: 150
    }, {
        field: 'status',
        title: '状态',
        width: 150
    }, {
        field: 'publish',
        title: '发布时间',
        width: 150
    }, {
        field: 'time',
        title: '起拍时间',
        width: 150
    }, {
        field: 'handle',
        title: '操作',
        width: 150
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    $(".fixed-table-footer").css({display: 'block'});
    $(".fixed-table-pagination").css({display: 'block'});
    $(".pagination").css({display: 'block'});

</script>
</body>
</html>