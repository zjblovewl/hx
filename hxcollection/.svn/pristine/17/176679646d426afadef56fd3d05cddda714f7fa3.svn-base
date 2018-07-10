<%--
  Created by IntelliJ IDEA.
  User: qiangxuan
  Date: 2018/3/22
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>定时任务</title>
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
    <script src="../../js/timePermissionUtils.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableUtil.js"></script>

    <style>
		.bootstrap-table{
			top:46px !important;
		}
		.customsearch-input{
			width: 19%;
		}		
		.label-input-oneline{
			width: 65px;
		}
    </style>
</head>
<body>
<div>
    <div class="customsearch" id="query-form">
  		<div class="allLine">
  			<div class="customsearch-input">
     		<span class="label-input-oneline">任务名：</span>
     		<input name="name" placeholder='任务名' type="text" class="form-control">
	     	</div> 
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">关键字：</span>
	     		<input name="description" placeholder='关键字' type="text" class="form-control">
	     	</div> 
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">时间：</span>
	     		<input id="startTime" data-type='input-time' name="startTime" placeholder='开始时间' class="form-control">
	     	</div>          
			<div class="customsearch-input">
				<span class="label-input-oneline">至：</span>
	     		<input id="endTime" data-type='input-time' name="endTime" placeholder='结束时间' class="form-control">	     		
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
       <div id="toolbar-btn" class="btn-group">
            <button id="build" type="button" class="btn btn-primary btn-space marginRight5">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">新增</span>             
            </button>
            <button id="btnDel" type="button" class="btn btn-danger btn-space marginRight5">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">批量删除</span>
            </button>  
             <button id="btnStop" type="button" class="btn btn-default  marginRight5">
                <span  aria-hidden="true" class="btn-icon-space">暂停</span>             
            </button>
             <button id="btnStart" type="button" class="btn btn-default  marginRight5">
                <span  aria-hidden="true" class="btn-icon-space">启动</span>             
            </button>
            <button id="btnFunc" type="button" class="btn btn-default  marginRight5">
                <span  aria-hidden="true" class="btn-icon-space">立即运行一次</span>
            </button>      
        </div>
    <table id="table">
    </table>
   
</div>
<script>
	var $table;
	var queryUrl = '<%=basePath%>timeTask/queryTimeTaskList.do?rnd=' + Math.random();
	var deleteByIdUrl = '<%=basePath%>timeTask/deleteTimeTaskById.do?rnd=' + Math.random();
	var deleteBatchUrl = '<%=basePath%>timeTask/delTimeTaskBatch.do?rnd=' + Math.random();
	var pauseByIdUrl = '<%=basePath%>timeTask/pauseTimeTaskById.do?rnd=' + Math.random();
	var startByIdUrl = '<%=basePath%>timeTask/startTimeTaskById.do?rnd=' + Math.random();
	
	
	var onceByIdUrl = '<%=basePath%>timeTask/onceTimeTaskById.do?rnd=' + Math.random();
	
	var columnsArray = [{
        checkbox: true,
        visible: true    //是否显示复选框
    }, {
        field: 'id',
        title: 'ID',
        width:60,
        visible: false  
    }, {
        field: 'name',
        title: '任务名',
        width:60
    }, {
        field: 't_group',
        title: '任务组',
        width:60
    }, {
        field: 'expression',
        title: '定时规则',
        width:100
    }, {
        field: 'status',
        title: '启用状态',
        width: 80,
        formatter:  function(value) {
            if (value == 0) {
                return "否";
            }
            return "是";
        }
    }, {
        field: 'classname',
        title: '任务类',
        width:100
    }, {
        field: 'description',
        title: '描述',
        width:200
    }, {
        field: 'start_time',
        title: '开始时间',
        width:120
    }, {
        field: 'end_time',
        title: '结束时间',
        width:120
    }, {
        field: 'operation',
        title: '操作',
        width:100,
        formatter:editAnddelte()
    }];
	$("#table").InitMainTable(queryUrl, columnsArray);
	
	var modalObj = window.parent;
	$("#build").click(function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,400);
		modalObj.modalOut("monitor/timeTaskEdit.jsp",[{}]);		
	});
	
	//编辑按钮	
	function getJSPurl(row){
		if(row && row.status ==1){
			modalObj.$.showSuccessTimeout("任务正在执行，请暂停！");
    		return "";
		}
		modalObj.setWindowDialog(720,400);
		return "monitor/timeTaskEdit.jsp";
	}
	function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目", function(){
			var allTableData = $("#table").bootstrapTable('getData');
			for(var i=0;i<allTableData.length;i++){				
			    if(allTableData[i].id==id){
			    	if(allTableData[i].status == 1){
			    		modalObj.$.showSuccessTimeout("任务正在执行，请暂停！");
			    		return;
			    	}
			        break;
			    }
			}
			return;
			$.post(deleteByIdUrl,{ids:id});
			modalObj.$.showSuccessTimeout("删除成功");
			$("#table").bootstrapTable('refresh');//刷新			
		},function(){
			return false;
		});	
	}
	
	function delWarn(status){
		alert("定时任务状态："+status)
		if (status == 1){
			alert("定时任务为启动状态无法删除，请先暂停定时任务！")
		}else {
		
		}
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
			var delNames = [];
			for(var i=0;i<getSelectRows.length;i++){
				if(getSelectRows[i].status == 1){
					modalObj.$.showSuccessTimeout("有正在执行的任务，请暂停！");
		    		return;
				}				
				delID.push(getSelectRows[i].id);
				delNames.push(getSelectRows[i].name);
			}
			console.log("name++++++++++++："+delNames)
			$.ajax({
				url:deleteBatchUrl,
				type:"post",
				data:{
					ids:delID.join(","),
					names:delNames.join(",")
				},
				success:function(data){
					$("#table").bootstrapTable('refresh');//刷新
				},
				error:function(){
					console.error("error");
				}
			});
			$("#table").bootstrapTable('refresh');//刷新		
		},function(){
			return false;
		});

		return false;		
	});

	
	//启动定时任务 
	$("#btnStart").bind('click', function() {
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
			console.log(row)
            return row;
    	});
		if(getSelectRows.length == 0){
			modalObj.$.showSuccessTimeout("请选择要启动的条目");
			return;	
		}
		/* var startId = [];		
		var startName = [];
		var startClassName = [];
		var startExpression = [];
		var startStartTime = [];
		var startEndTime = []; */
		var start = [];	
		for(var i=0;i<getSelectRows.length;i++){
			var a =new Object();
			a.id =getSelectRows[i].id;
			a.name = getSelectRows[i].name;
			a.classname = getSelectRows[i].classname;
			a.expression=getSelectRows[i].expression;
			a.start_time=getSelectRows[i].start_time;
			a.end_time=getSelectRows[i].end_time;
			start.push(a);
		}
	
		$.ajax({
			url:startByIdUrl,
			type:"post",
			data:{datas:JSON.stringify(start)},
			success:function(data){
				$("#table").bootstrapTable('refresh');//刷新
			},
			error:function(){
				console.error("error");
			}
		});
	});
	
	//暂停定时任务 
	$("#btnStop").bind('click',function() {
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;
    	});
		if(getSelectRows.length == 0){
			modalObj.$.showSuccessTimeout("请选择要暂停的条目");
			return;	
		}
		var pauseID = [];
		var pauseName = [];
		for(var i=0;i<getSelectRows.length;i++){
			pauseID.push(getSelectRows[i].id);
			pauseName.push(getSelectRows[i].name);
		}
		console.log("暂停定时任务参数："+pauseID+pauseName)
		$.ajax({
			url:pauseByIdUrl,
			type:"post",
			data:{
				ids:pauseID.join(","),
				names:pauseName.join(",")
			},
			success:function(data){
				$("#table").bootstrapTable('refresh');//刷新
			},
			error:function(){
				console.error("error");
			}
		});
	});
	
	$("#btnFunc").bind('click', function() {
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;
    	});
		if(getSelectRows.length == 0){
			modalObj.$.showSuccessTimeout("请选择要立即运行的条目");
			return;	
		}
		var onceID = [];
		var onceName = [];
		var onceTime = [];
		var onceClazzName = [];
		for(var i=0;i<getSelectRows.length;i++){
			onceID.push(getSelectRows[i].id);
			onceName.push(getSelectRows[i].name);
			onceClazzName.push(getSelectRows[i].classname);
		}
		$.ajax({
			url:onceByIdUrl,
			type:"post",
			data:{
				ids:onceID.join(","),
				names:onceName.join(","),
				clazznames:onceClazzName.join(",")
			},
			success:function(data){
				$("#table").bootstrapTable('refresh');//刷新
			},
			error:function(){
				console.error("error");
			}
		});
	});
</script>
</body>
</html>