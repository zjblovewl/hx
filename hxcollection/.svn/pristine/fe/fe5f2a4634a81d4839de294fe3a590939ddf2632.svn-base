<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
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

    <style>
		.bootstrap-table{
			top:88px !important;
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
	    	<div class="customsearch-input ">
	    		<span class="label-input-oneline">手机号：</span>
		     	<input name="phone" id="phone" placeholder='手机号' data-type='input-phone' type="text" class="form-control">
	     	</div>
	     	<div class="customsearch-input ">
	     		<span class="label-input-oneline">昵称：</span>
		     	<input name="nickName" id="nickName" placeholder='昵称' type="text" class="form-control">
	     	</div>
	     	<div class="customsearch-input ">
	     		<span class="label-select-oneline">实名认证：</span>
	     		<select name="isAuthentication" class="form-control select-style" id="isAuthentication">	
	     			<option value="">全部</option>     			
	     			<option value="1">已认证</option>
	     			<option value="0">未认证</option>
	     		</select>
	     	</div>   
	     	<div class="customsearch-input " style="display: none;">
	     		<span class="label-select-oneline">锁定状态：</span>
	     		<select name=""  class="form-control select-style">
	     			<option>全部</option>
	     			<option>已激活</option>
	     			<option>未激活</option>
	     		</select>
	     	</div>             	
    	</div>
     	<div class="allLine">
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">注册时间：</span>
		     	<input id="startTime" name="startTime" data-type="input-time" placeholder='开始时间'  class="form-control">
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">至：</span>
		     	<input id="endTime" name="endTime" data-type="input-time" placeholder='结束时间'  class="form-control">
	     	</div>
	     	 <div class="customsearch-input allLine_btn" >
		            <button id="btn_search" type="button" class="btn btn-primary btn-space marginRight10">
		                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
		            </button>
		            <button id="btn_reset"  type="button" class="btn btn-default btn-space">
		                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
		            </button>	     	 	
        	</div> 
     	</div>		   
       </div>
      <div id="toolbar-btn" class="btn-group">
            <button id="build" type="button" class="btn btn-primary marginRight5">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">新增</span>             
            </button>
            <button id="btnDel" type="button" class="btn btn-danger btn-space marginRight5">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">批量删除</span>
            </button>
            <button id="Export" type="button" class="btn btn-info btn-space">
                <span class="fa fa-file-excel-o" aria-hidden="true" class="btn-icon-space">用户导出</span>
            </button>
       </div>
    <table id="table">
       
    </table>
</div>

<script>

    var queryUrl = '<%=basePath%>generalUser/queryGeneralUserList.do?rnd=' + Math.random();
    var deleteByIdUrl = '<%=basePath%>generalUser/deleteUserById.do?rnd=' + Math.random();
    var deleteBatchUrl = '<%=basePath%>generalUser/delGeneralUserBatch.do?rnd=' + Math.random();
	var userExportUrl = '<%=basePath%>generalUser/exportUserInfoData.do?rnd=' + Math.random();
	
    var columnsArray = [{
        field: 'checkbox',
        title: '选择',
        checkbox: true,
        width: 40
    }, {
        field: 'id',
        title: '用户ID',
        width: 150,
        visible: false  
    }, {
        field: 'nick_name',
        title: '昵称',
        width: 150
    }, {
        field: 'phone',
        title: '手机号',
        width: 150
    }, {
        field: 'autograph',
        title: '签名',
        width: 80,
        visible: false  
    }, {
        field: 'city',
        title: '城市',
        width: 80
    }, {
        field: 'is_authentication',
        title: '实名认证',
        width: 150,
        formatter:  function(value) {
        	
            if (value == 0) {
                return "未认证";
            }
            return "已认证";
        }
    }, {
        field: 'credit',
        title: '信誉度',
        width: 150,
        visible: true  
    }, {
        field: 'wallet_balance',
        title: '钱包余额',
        width: 150,
        visible: true  
    }, {
        field: 'is_lock',
        title: '锁定状态',
        width: 150,
        formatter:  function(value) {
            if (value == 0) {
                return "锁定";
            }
            return "激活";
        },
        visible: true  
    }, {
        field: 'register_time',
        title: '注册时间',
        width: 150,
        visible: true  
    }, {
        field: 'sex',
        title: '性别',
        width: 150,
        visible: false  
    }, {
        field: 'receive_address',
        title: '收货地址',
        width: 150,
        visible: false  
    }, {
        field: 'lock_days',
        title: '锁定天数',
        width: 150,
        visible: false  
    }, {
        field: 'lock_time',
        title: '锁定时间',
        width: 150,
        visible: false  
    }, {
        field: 'lock_reason',
        title: '锁定原因',
        width: 150,
        visible: false  

    }, {
        field: 'clear_time',
        title: '解锁时间',
        width: 150,
        visible: false  
    }, {
        field: 'update_time',
        title: '更新时间',
        width: 150,
        visible: false  
    }, {
        field: 'is_recommend',
        title: '是否推荐',
        width: 150,
        formatter:  function(value) {
            if (value == 0) {
                return "未推荐";
            }
            return "已推荐";
        },
        visible: false  
    }, {
        field: 'sort',
        title: '推荐排序',
        width: 150,
        visible: false  
    }, {
        field: '--',
        title: '操作',
        width: 150,
        formatter :function(){
        	return "<i class='iconfont icon-shenhe' title='用户审核' style='margin-right:8px;font-size: 18px;'></i>"+
        			"<i class='fa fa-eye' title='查看' style='margin-right:8px;font-size: 16px;'></i>"+
		     	   "<i class='fa fa-edit' title='编辑'  style='margin-right:8px;font-size: 16px;'></i>"+
		   			"<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>"
        }
    }];

    $("#table").InitMainTable(queryUrl, columnsArray);
    
    var modalObj = window.parent;
	$("#build").click(function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,360);
		modalObj.modalOut("user/userListNewEdit.jsp",[{edit:"new"}]);		
	});
	 //用户审核
    function complaint(row){
    	modalObj.setWindowDialog(720,360);
		modalObj.modalOut("user/userListAuditEdit.jsp",{edit:"audit",id:row.id,is_authentication:row.is_authentication});		
    }
	//编辑按钮	
	function getJSPurl(){
		modalObj.setWindowDialog(720,680);
		return "user/userListEdit.jsp";
	}
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
	//用户导出
	$("#Export").bind('click',function(){
		var nickName = $("#nickName").val();	
		var phone = $("#phone").val();
		var isAuthentication = $("#isAuthentication").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endtime").val();
		console.log("sos"+startTime+endTime)
		document.getElementById("exportFrame").src = userExportUrl+'&nickName='+nickName+'&phone='+phone+'&isAuthentication='+isAuthentication+'&startTime='+startTime+'&endTime='+endTime;
	}); 
	
</script>
<iframe id="exportFrame" name="exportFrame" width="0" height="0" /></iframe>
</body>
</html>