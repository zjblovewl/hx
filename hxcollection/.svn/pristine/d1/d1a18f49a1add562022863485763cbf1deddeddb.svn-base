<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易佣金记录</title>
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
			width: 23%;
		}
		
    </style>
</head>
<body>
<div>
    <div class="customsearch" id="query-form">
    	<div class="allLine">
    		<div class="customsearch-input">
		     	<span class="label-input-oneline">用户昵称：</span>
		     	<input id="nickName" name="nickName" placeholder='用户昵称' type="text"class="form-control">
	     	</div>   
    		<div class="customsearch-input">
	    		<span class="label-input-oneline">创建时间：</span>
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
            	<button id="btnSearch" type="button" class="btn btn-default btn-space marginRight10">
                	<span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">导出搜索结果</span>             
            	</button>
            	 <button id="btnConfig" type="button" class="btn btn-default btn-space marginRight10">
                	<span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">佣金配置</span>             
            	</button>
      </div>    
    <table id="table">
        
    </table>
</div>
<script>
    var queryCommissionURL = '<%=basePath%>commissionRecord/getCommissionData.do?rnd=' + Math.random();
    var commissionExportURL = '<%=basePath%>commissionRecord/exportCommissionData.do?rnd=' + Math.random();
    
    var modalObj = window.parent;
    
    var columnsArray = [{
        checkbox: true,
        visible: false    //是否显示复选框
    }, {
        field: 'id',
        title: '佣金ID',
        width: 150,
        visible: false
    }, {
        field: 'withdrawals_id',
        title: '提现ID',
        width: 150,
        visible: false
    }, {
        field: 'nick_name',
        title: '用户昵称',
        width: 150
    }, {
        field: 'withdrawals_amount',
        title: '提现金额',
        width: 150
    }, {
        field: 'bank_no',
        title: '卡号',
        width: 150
    }, {
        field: 'bank_name',
        title: '开户银行',
        width: 150
    }, {
        field: 'branch_name',
        title: '支行名称',
        width: 150
    }, {
        field: 'commission_rate',
        title: '佣金比例',
        width: 150
    }, {
        field: 'commission_amount',
        title: '佣金金额',
        width: 150
    }, {
        field: 'create_time',
        title: '创建时间',
        width: 150
    }];
    $("#table").InitMainTable(queryCommissionURL, columnsArray);

   
    //佣金配置导出至EXCEL
    $("#btnSearch").bind('click',function(){
    	var nickName = $("#nickName").val();	
    	var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		document.getElementById("exportFrame").src = commissionExportURL+'&nickName='+nickName+'&startTime='+startTime+'&endTime='+endTime;;
    });

	//佣金配置
	$("#btnConfig").bind('click',function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,265);
		modalObj.modalOut("transaction/commisConfigureEdit.jsp",[{edit:"config"}]);
	});

</script>
<iframe id="exportFrame" name="exportFrame" width="0" height="0" /></iframe>
</body>
</html>