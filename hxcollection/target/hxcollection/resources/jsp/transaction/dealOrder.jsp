<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易订单查询</title>
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
			top:88px !important;
		}
		.customsearch-input{
			width: 40%;
		}
    </style>
</head>
<body>
<div>
    <div class="customsearch" id="query-form">
    	<div class="allLine">
    		<div class="customsearch-input">
	     		<span class="label-input-oneline">类别：</span>
		     	<input name="type" placeholder='类别' type="text" class="form-control">
	     	</div>
	     	<div class="customsearch-input">
		     	<span class="label-input-oneline">关键字：</span>
		     	<input name="content" placeholder='关键字' type="text"class="form-control">
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">交易状态：</span>
		     	<input name="status" placeholder='交易状态' type="text" class="form-control">
	     	</div>
	     	          
    	</div>
    	<div class="allLine">
    		<div class="customsearch-input">
	   			<span class="label-input-oneline">提现时间：</span>
		     	<input id="startTime" name="startTime" data-type="input-time" placeholder='开始时间'  class="form-control">
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">至：</span>
		     	<input id="endTime" name="endTime" data-type="input-time" placeholder='结束时间' class="form-control">
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
      
       </div>
        <div id="toolbar-btn" class="btn-group pull-left">
            <button id="btn_add" type="button" class="btn btn-primary btn-space marginRight10">
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
        field: 'code',
        title: '交易单号',
        width: 150
    }, {
        field: 'sell',
        title: '卖家',
        width: 150
    }, {
        field: 'buy',
        title: '买家',
        width: 150
    }, {
        field: 'price',
        title: '交易价格'
    }, {
        field: 'postage',
        title: '邮费',
        width: 150
    }, {
        field: 'total',
        title: '总价',
        width: 150
    }, {
        field: 'status',
        title: '状态',
        width: 150
    }, {
        field: 'address',
        title: '收货信息',
        width: 150
    }, {
        field: 'time',
        title: '更新时间',
        width: 150
    }, {
        field: 'handle',
        title: '操作',
        width: 150
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);


</script>
</body>
</html>