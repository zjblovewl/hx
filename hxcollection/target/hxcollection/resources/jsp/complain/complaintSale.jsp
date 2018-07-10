<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商户投诉</title>
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
		.bootstrap-table{
			top:46px !important;
		}
		.customsearch-input{
			width: 40%;
		}	
    </style>
</head>
<body>
<div>
    <div class="customsearch allLine" id="query-form">   		
     	<div class="customsearch-input">
	     	<span class="label-select-oneline">举报类型：</span>
	     		<select name="report_type" class="form-control select-style">	
	     			<option value="">全部</option>     			
	     			<option value="1">交易帖</option>
	     			<option value="0">拍卖帖</option>
	     		</select>
     	</div>
     	<div class="customsearch-input">
     		<span class="label-input-oneline">关键字：</span>
		    <input name="report_content" placeholder='关键字' type="text" class="form-control">
     	</div>          

       <div class="allLine_btn marginRight20" >
	            <button id="btn_search" type="button" class="btn btn-primary btn-space marginRight10">
	                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
	            </button>
	            <button id="btn_reset"  type="button" class="btn btn-default btn-space">
	                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
	            </button>
        	</div> 
       </div>
        <div id="toolbar-btn" class="btn-group pull-left">           
            <button id="btnDel" type="button" class="btn btn-danger btn-space marginRight5">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">批量删除</span>
            </button>
        </div>
    <table id="table">
        
    </table>
</div>
<script>
	var queryUrl = '<%=basePath%>sellerComplaint/querySellerComplaintList.do?rnd=' + Math.random();
    var columnsArray = [{
        checkbox: false,
        visible: false
    }, {
        field: 'id',
        title: 'id',
        width: 150,
        visible: false
    }, {
        field: 'report_seller',
        title: '举报卖家',
        width: 150
    }, {
        field: 'report_type',
        title: '举报类型',
        width: 150
    }, {
        field: 'report_content',
        title: '举报内容',
        width: 150
    }, {
        field: 'report_people',
        title: '举报人',
        width: 150
    }, {
        field: 'report_time',
        title: '举报时间',
        width: 150
    }, {
        field: 'deal_status',
        title: '处理状态',
        width: 150
    }, {
        field: 'remarks',
        title: '处理备注',
        width: 150
    }, {
        field: '--',
        title: '操作',
        width: 150,
        formatter :function(){       	
     	   "<i class='fa fa-file-text' title='投诉处理'  style='margin-right:8px;font-size: 16px;'></i>"+
     		"<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>"
        }
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    $("#table").InitMainTable(queryUrl, columnsArray);
    var modalObj = window.parent;
    //投诉处理
    function complaint(row){
    	modalObj.setWindowDialog(720,300);
		modalObj.modalOut("complain/complainGoodsEdit.jsp",[{edit:"complain"}]);		
    }
    //删除
    function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(deleteByIdUrl,{userIds:id});
			$("#table").bootstrapTable('refresh');			
		},function(){
			return false;
		});
		
	}
    //批量删除
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
				url:deleteBatchUrl,
				type:"post",
				data:{
					userIds:delID.join(",")
				},
				success:function(data){
					customSearch();
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