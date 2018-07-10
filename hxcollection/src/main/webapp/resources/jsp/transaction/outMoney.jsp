<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>提现管理</title>
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
		     	<span class="label-select-oneline">提现限制：</span>
	     		<select id="isLock" name="isLock" class="form-control select-style">	
	     			<option value="">全部</option>     			
	     			<option value="0">锁定</option>
	     			<option value="1">激活</option>
	     		</select>
	     	</div>
	     	<div class="customsearch-input">
		     	<span class="label-select-oneline">审核状态：</span>
	     		<select id="auditState" name="auditState" class="form-control select-style">	
	     			<option value="">全部</option>     			
	     			<option value="1">未通过</option>
	     			<option value="2">已通过</option>
	     		</select>
	     	</div>
	     	          
    	</div>
    	<div class="allLine">
    		<div class="customsearch-input">
	   			<span class="label-input-oneline">申请时间：</span>
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
                <span class="fa fa-file-excel-o" aria-hidden="true" class="btn-icon-space">提现记录导出</span>
            </button>
       </div>
    <table id="table">
        
    </table>
</div>
<script>
	// 查询所有提现记录数据 
    var queryWRURL =  '<%=basePath%>withdrawalsRecord/getWRData.do?rnd=' + Math.random();
	// 根据搜索条件查询提现记录并导出至excel
    var withdrawalsRecordExportURL = '<%=basePath%>withdrawalsRecord/exportWRData.do?rnd=' + Math.random();
    
    
    var columnsArray = [{
        checkbox: true,
        visible: false    //是否显示复选框
    }, {
        field: 'id',
        title: '用户ID',
        width: 100,
        visible: false
    }, {
        field: 'nick_name',
        title: '用户昵称',
        width: 100
    }, {
        field: 'phone',
        title: '联系电话',
        width: 100
    }, {
        field: 'is_lock',
        title: '提现限制',
        width: 100,
        formatter:  function(value) {
            if (value == 1) {
                return "激活";
            }else if(value ==0){
            	return "锁定";
            }
        }
    }, {
        field: 'withdrawals_amount',
        title: '提现金额',
        width: 100
    }, {
        field: 'bank_name',
        title: '开户银行',
        width: 100
    }, {
        field: 'branch_name',
        title: '支行名称',
        width: 100
    }, {
        field: 'real_name',
        title: '姓名',
        width: 100
    }, {
        field: 'bank_no',
        title: '账号',
        width: 100      
    }, {
        field: 'audit_state',
        title: '审核状态',
        width: 100,
        formatter:  function(value) {
            if (value == 1) {
                return "未通过";
            }else if(value == 2){
            	return "已通过";
            }
        }      
    }, {
        field: 'wallet_balance',
        title: '钱包余额',
        width: 100,        
    }, {
        field: 'create_time',
        title: '申请时间',
        width: 140    
    }, {
        field: 'operation',
        title: '操作',
        width: 100 ,
        formatter :function(){       	
        	  return  "<i class='iconfont icon-shenhe' title='提现审核' style='margin-right:8px;font-size: 18px;'></i>"
        }      
    }];
    $("#table").InitMainTable(queryWRURL, columnsArray);
	
    var modalObj = window.parent;
    //提现审核
    function complaint(row){
    	modalObj.setWindowDialog(720,360);
		modalObj.modalOut("transaction/wrAuditEdit.jsp",{edit:"audit",id:row.id,audit_state:row.audit_state});		
    }
       
    
	//提现记录导出至excel 
	$("#Export").bind('click',function(){
		var nickName = $("#nickName").val();	
		var auditState = $("#auditState").val();
		var isLock = $("#isLock").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		document.getElementById("exportFrame").src = withdrawalsRecordExportURL+'&nickName='+nickName+'&auditState='+auditState+'&isLock='+isLock+'&startTime='+startTime+'&endTime='+endTime;
	}); 

</script>
<iframe id="exportFrame" name="exportFrame" width="0" height="0" /></iframe>
</body>
</html>