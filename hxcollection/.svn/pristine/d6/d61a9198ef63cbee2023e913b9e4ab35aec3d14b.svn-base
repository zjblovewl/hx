<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>拍卖订单查询</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table-new.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/laydate/laydate.js"></script>
    <script src="../../js/timeUtils.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableNewUtil.js"></script>
    <style>
		.bootstrap-table{
			top:88px !important;
		}
		
		.customsearch-input{
			width: 29%;
		}	
    </style>
</head>
<body>
<div>
     <div class="customsearch" id="query-form">
   		<div class="allLine">
    		<div class="customsearch-input">
     		<span class="label-input-oneline">关键字：</span>
	     	<input name="keyWord" placeholder='昵称' type="text" class="form-control">
	     	</div>	     	     	
	     	<div class="customsearch-input">
	     		<span class="label-select-oneline">筛选类型：</span>
	     		<select id="bigClazz" name="big_class_code" class="form-control select-style">	
	     			<option value="">全部</option> 
	     			<option value="">按买家</option> 
	     			<option value="">按卖家</option> 
	     			<option value="">按订单号</option>     				     			
	     		</select>
	     	</div>   
	     	 <div class="customsearch-input">
	     		<span class="label-select-oneline">订单状态：</span>
	     		<select id="smallClazz" name="small_class_code" class="form-control select-style">	
	     			<option value="">全部</option> 
	     			<option value="">待支付</option> 
	     			<option value="">代发货</option> 
	     			<option value="">待收货</option>  
	     			<option value="">待评价</option> 
	     			<option value="">交易完成</option> 
	     			<option value="">交易取消</option>    
	     			<option value="">已申请退货</option> 
	     			<option value="">已确认退货</option> 
	     			<option value="">已评价</option>    
	     			<option value="">已拒绝申请</option>       				     			
	     		</select>
	     	</div>   
    	</div>
    	<div class="allLine">   		
    		<div class="customsearch-input">
	   			<span class="label-input-oneline">时间：</span>
		     	<input id="startTime" name="first_step_start_time" data-type="input-time" placeholder='开始时间'  class="form-control">
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">至：</span>
		     	<input id="endTime" name="first_step_end_time" data-type="input-time" placeholder='结束时间' class="form-control">
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
       </div>
        <div id="toolbar-btn" class="btn-group pull-left">
            <button id="build" type="button" class="btn btn-primary btn-space marginRight10">
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
    var queryUrl = '/product';
    var columnsArray = [{
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
        title: '成交价格'
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

    var modalObj = window.parent;
	$("#build").click(function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,400);
		modalObj.modalOut("sale/auctionOrderDetailEdit.jsp",[{}]);		
	});
	
	//编辑按钮	
	function getJSPurl(){
		modalObj.setWindowDialog(720,400);
		return "sale/auctionOrderEdit.jsp";
	}
	function deleID(id){		
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(delUrl,{userIds:id});
			$("#table").bootstrapTable('refresh');			
		},function(){
			return false;
		});
		
	}
	$("#btnDel").click(function(){
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;L
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
				url:delUrl,
				type:"post",
				data:{
					userIds:delID.join(",")
				},
				success:function(data){
					$("#table").bootstrapTable('refresh');
					console.log(data.resultMsg);
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
