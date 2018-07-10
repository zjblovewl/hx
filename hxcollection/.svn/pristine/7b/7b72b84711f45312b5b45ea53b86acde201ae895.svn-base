<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易流水</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../fonts/iconfont.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">
    
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>

    <script src="../../js/laydate/laydate.js"></script>
    <script src="../../js/timeUtils.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableUtil.js"></script>
	<script src="../../js/commonCheckUtils.js"></script>
	<script src="../../js/common/common.js"></script>

    <style>
		.bootstrap-table{
			top:80px !important;
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
	     		<span class="label-input-oneline">用户昵称：</span>
		     	<input id="nickName" name="nickName" placeholder='请输入昵称' type="text" class="form-control">
	     	</div>
	     	<div class="customsearch-input">
		     	<span class="label-input-oneline">订单号：</span>
		     	<input id="orderCode" name="orderCode" placeholder='请输入订单号' type="text" class="form-control">
	     	</div>
    	</div>
    	
    	<div class="allLine">
    		<div class="customsearch-input">
		     	<span class="label-select-oneline">交易类型：</span>
	     		<select id="transactionType" name="transactionType" class="form-control select-style">	
	     			<option value="">全部</option>     			
	     			<option value="1">付款</option>
	     			<option value="0">退款</option>
	     		</select>
	     	</div>
	     	<div class="customsearch-input">
		     	<span class="label-select-oneline">交易状态：</span>
	     		<select id="transactionState" name="transactionState" class="form-control select-style">	
	     			<option value="">全部</option>     			
	     			<option value="1">成功</option>
	     			<option value="2">失败</option>
	     		</select>
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">注册时间：</span>
		     	<input id="startTime" name="startTime" data-type="input-time" placeholder='开始时间'  class="form-control">
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">至：</span>
		     	<input id="endTime" name="endTime" data-type="input-time" placeholder='结束时间'  class="form-control">
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
       
       <div id="toolbar-btn" class="btn-group">
            <button id="Export" type="button" class="btn btn-info btn-space">
                <span class="fa fa-file-excel-o" aria-hidden="true" class="btn-icon-space">交易流水导出</span>
            </button>
       </div>
    <table id="table">
        
    </table>
</div>
<script>

    var queryTradeFlowUrl = '<%=basePath%>tradingFlow/getTradingFlowData.do?rnd=' + Math.random();
    var tradeFlowExportUrl = '<%=basePath%>tradingFlow/exportTradeFlowData.do?rnd=' + Math.random();
    
    var columnsArray = [{
        checkbox: true,
        visible: false    //是否显示复选框
    }, {
        field: 'id',
        title: '用户ID',
        width: 150,
        visible: false  
    }, {
        field: 'nick_name',
        title: '用户昵称',
        width: 150
    }, {
        field: 'third_flowcode',
        title: '第三方流水号',
        width: 150
    }, {
        field: 'third_precode',
        title: '第三方预付码',
        width: 150
    }, {
        field: 'channel_flowcode',
        title: '渠道流水号',
        width: 150
    }, {
        field: 'order_code',
        title: '订单号',
        width: 150
    }, {
        field: 'transaction_type',
        title: '交易类型',
        width: 150,
        visible: true,
        formatter:  function(value) {
            if (value == 1) {
                return "付款";
            }else if(value ==2){
            	return "退款";
            }
        }
    }, {
        field: 'pay_type',
        title: '支付方式',
        width: 150,
        formatter:  function(value) {
            if (value == 1) {
                return "支付宝";
            }else if(value ==2){
            	return "微信";
            }
        }
    }, {
        field: 'transaction_price',
        title: '交易金额',
        width: 150
    }, {
        field: 'transaction_state',
        title: '交易状态',
        width: 150, 
        formatter:  function(value) {
            if (value == 1) {
                return "成功";
            }else if(value ==2){
            	return "失败";
            }
        }       
    }, {
        field: 'create_time',
        title: '创建时间',
        width: 150,
        visible: true  
    }];
    
    $("#table").InitMainTable(queryTradeFlowUrl, columnsArray);
	

  	//交易流水导出
	$("#Export").bind('click',function(){
		var nickName = $("#nickName").val();	
		var orderCode = $("#orderCode").val();
		var transactionType = $("#transactionType").val();
		var transactionState = $("#transactionState").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		document.getElementById("exportFrame").src = tradeFlowExportUrl+'&nickName='+nickName+'&orderCode='+orderCode+'&transactionType='+transactionType+'&transactionState='+transactionState+'&startTime='+startTime+'&endTime='+endTime;
	}); 
</script>
<iframe id="exportFrame" name="exportFrame" width="0" height="0" /></iframe>
</body>
</html>