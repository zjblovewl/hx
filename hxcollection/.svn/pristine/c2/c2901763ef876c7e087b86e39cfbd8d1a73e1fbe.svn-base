<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
.bootstrap-table {
	top: 88px !important;
}
.customsearch-input{
	width: 32%;
}	
</style>
</head>
<body>
	<div>
		<div class="customsearch" id="query-form">
			<div class="allLine">
				<div class="customsearch-input">
					<span class="label-input-oneline">买家：</span> 
					<input id="buyerName" name="buyerName" placeholder='买家昵称' type="text" class="form-control">
				</div>
				<div class="customsearch-input">
					<span class="label-input-oneline">卖家：</span> 
					<input id="sellerName" name="sellerName" placeholder='卖家昵称' type="text" class="form-control">
				</div>
				<div class="customsearch-input">
					<span class="label-input-oneline">订单号：</span> 
					<input id="orderCode" name="orderCode" placeholder='订单号' type="text" class="form-control">
				</div>
				<div class="customsearch-input">
		     		<span class="label-select-oneline">订单类型：</span>
	     			<select id="orderType" name="orderType" class="form-control select-style">	
	     				<option value="">全部</option>     			
	     				<option value="1">普通订单</option>
	     				<option value="2">拍卖订单</option>
	     			</select>
	     		</div>
			</div>
			<div class="allLine">
				<div class="customsearch-input">
		     		<span class="label-select-oneline">订单状态：</span>
	     			<select id="orderStatus" name="orderStatus" class="form-control select-style">	
	     				<option value="">全部</option>     			
	     				<option value="1">待付款</option>
	     				<option value="2">待发货</option>
	     				<option value="3">待收货</option>
	     				<option value="4">待评论</option>
	     				<option value="5">已完成</option>
	     				<option value="6">待退款</option>
	     				<option value="7">已退款</option>
	     				<option value="8">取消订单</option>
	     			</select>
	     		</div>
				<div class="customsearch-input">
					<span class="label-input-oneline">订单时间：</span> 
					<input id="startTime" name="startTime" data-type="input-time"
						placeholder='开始时间' class="form-control">
				</div>
				<div class="customsearch-input">
					<span class="label-input-oneline">至：</span> 
					<input id="endTime" name="endTime" data-type="input-time" placeholder='结束时间'
						class="form-control">
				</div>
				<div class="customsearch-input allLine_btn">
					<button id="btn_search" type="button"
						class="btn btn-primary btn-space">
						<span class="fa fa-search" aria-hidden="true"
							class="btn-icon-space">查询</span>
					</button>
					<button id="btn_reset" type="button"
						class="btn btn-default btn-space">
						<span class="fa fa-eraser" aria-hidden="true"
							class="btn-icon-space">重置</span>
					</button>
				</div>
			</div>

		</div>
		<div id="toolbar-btn" class="btn-group pull-left">
			 <button id="Export" type="button" class="btn btn-info btn-space">
                <span class="fa fa-file-excel-o" aria-hidden="true" class="btn-icon-space">订单导出</span>
            </button>
		</div>
		<table id="table">

		</table>
	</div>
<script>
		var queryOrderURL = '<%=basePath%>order/getOrderRecords.do?rnd=' + Math.random(); 
		var orderExportURL = '<%=basePath%>order/exportOrderRecord.do?rnd=' + Math.random();

		
			var columnsArray = [{
				checkbox : true,
				visible : false //是否显示复选框
			}, {
		        field: 'id',
		        title: '订单id',
		        width: 80,
		        visible:false
		    }, {
				field : 'order_code',
				title : '交易单号',
				width : 100
			}, {
				field : 'seller_name',
				title : '卖家',
				width : 100
			}, {
				field : 'buyer_name',
				title : '买家',
				width : 100
			}, {
				field : 'price',
				title : '藏品价格',
				width : 80
			}, {
				field : 'amount',
				title : '藏品数量',
				width : 80
			}, {
				field : 'postage',
				title : '邮费',
				width : 100
			},{
				field : 'pay_money',
				title : '实付价格',
				width : 100
			}, {
				field : 'receive_address',
				title : '收货地址',
				width : 120
			}, {
				field : 'order_type',
				title : '订单类型',
				width : 100,
		        formatter: function(value) {
		        	if (value == 1) {
		                return "普通订单";
		            }else if(value ==2){
		            	return "拍卖订单";
		            }
		        }
			}, {
				field : 'order_status',
				title : '订单状态',
				width : 100,
		        formatter: function(value) {
		        	if (value == 1) {
		                return "待付款";
		            }else if(value ==2){
		            	return "待发货";
		            }else if(value ==3){
		            	return "待收货";
		            }else if(value ==4){
		            	return "待评论";
		            }else if(value ==5){
		            	return "已完成";
		            }else if(value ==6){
		            	return "待退款";
		            }else if(value ==7){
		            	return "已退款";
		            }else if(value ==8){
		            	return "取消订单";
		            }	
		        }
			}, {
				field : 'order_generation_time',
				title : '订单生成时间',
				width : 120
			}, {
				field : 'consignee',
				title : '收货人',
				width : 120,
				visible : false
			}, {
				field : 'contact_information',
				title : '联系方式',
				width : 120,
				visible : false
			}, {
				field : 'logistics_company',
				title : '物流公司',
				width : 120,
				visible : false
			}, {
				field : 'logistics_code',
				title : '物流单号',
				width : 120,
				visible : false
			}, {
				field : 'refund_reason',
				title : '退款原因',
				width : 120,
				visible : false
			}, {
				field : 'pay_method',
				title : '支付方式',
				width : 120,
				visible : false
			}, {
				field : 'order_finish_time',
				title : '订单完成时间',
				width : 120,
				visible : false
			}, {
				field : 'apply_refund_time',
				title : '申请退款时间',
				width : 120,
				visible : false
			}, {
				field : 'confirm_refund_time',
				title : '确认退款时间',
				width : 120,
				visible : false
			}, {
				field : 'cancel_time',
				title : '订单取消时间',
				width : 120,
				visible : false
			}, {
				field : 'cancel_reason',
				title : '订单取消原因',
				width : 120,
				visible : false
			}, {
				field : 'remark',
				title : '买家备注',
				width : 120,
				visible : false
			}, {
				field : 'goods_name',
				title : '藏品名称',
				width : 120,
				visible : false
			}, {
				field : 'goods_description',
				title : '藏品描述',
				width : 120,
				visible : false
			}, {
				field : 'describe',
				title : '大类',
				width : 120,
				visible : false
			}, {
				field : 'class_name',
				title : '小类',
				width : 120,
				visible : false
			}, {
		        field: 'image_urls',
		        title: '图片url',
		        width: 150,
		        visible: false
		    }, {
		        field: 'parent_code',
		        title: '大类code',
		        width: 150,
		        visible: false
		    }, {
		        field: 'class_code',
		        title: '小类code',
		        width: 150,
		        visible: false
		    }, {
				field : 'handle',
				title : '操作',
				width : 100,
		        formatter :function(){
		        	return "<i class='fa fa-eye' title='查看' style='margin-right:8px;font-size: 16px;'></i>"+
				     	   "<i class='fa fa-edit' title='编辑'  style='margin-right:8px;font-size: 16px;'></i>"
		        }
			}];
			
			$("#table").InitMainTable(queryOrderURL, columnsArray);
			
			var modalObj = window.parent;
			$("#build").click(function() {
				//调取父页面模态框	
				modalObj.setWindowDialog(720, 440);
				modalObj.modalOut("transaction/dealOrderEdit.jsp",{});
			});
			

			//编辑订单按钮	
			function getJSPurl(){
				modalObj.setWindowDialog(720,600);
				return "transaction/dealOrderDetailEdit.jsp";
			}
			
			
			//查看订单详情按钮		
			function getJSPurl() {
				modalObj.setWindowDialog(720, 800);
				return "transaction/dealTieCheckEdit.jsp";
			}
	
		// 订单记录导出至excel 
		$("#Export").bind('click',function(){
			var buyerName = $("#buyerName").val();	
			var sellerName = $("#sellerName").val();
			var orderCode = $("#orderCode").val();
			var orderType = $("#orderType").val();
			var orderStatus = $("#orderStatus").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			console.log("sos"+buyerName+buyerName)
			document.getElementById("exportFrame").src = orderExportURL+'&buyerName='+buyerName+'&sellerName='+sellerName+'&orderCode='+orderCode+'&orderType='+orderType+'&orderStatus='+orderStatus+'&startTime='+startTime+'&endTime='+endTime;
		}); 
	</script>
	<iframe id="exportFrame" name="exportFrame" width="0" height="0" /></iframe>
</body>
</html>