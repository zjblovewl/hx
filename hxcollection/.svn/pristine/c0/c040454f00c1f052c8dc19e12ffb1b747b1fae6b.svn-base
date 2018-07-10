<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>广告位列表</title>
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
	<script src="../../js/common/common.js"></script>
    <style>
		.customsearch-input{
			width: 23%;
		}
		.bootstrap-table{
			top:46px !important;
		}
    </style>
</head>
<body>
<div>
   <div class="customsearch allLine" id="query-form">     	          
		<div class="customsearch-input">
     		<span class="label-input-oneline">广告标题：</span>
	     	<input name="homepage_name" placeholder='广告标题' type="text" class="form-control">
     	</div>
     	<div class="customsearch-input">
     		<span class="label-input-oneline">发布状态：</span>
	     	<select name="service_status" class="form-control select-style" id="service_status">	
	     	</select>
     	</div>
     	<div class="customsearch-input">
     		<span class="label-input-oneline">关联类型：</span>   		
	     	<select name="service_type" class="form-control select-style" id="service_type">	
	     	</select>
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
       <div id="toolbar-btn" class="btn-group">            
            <button id="newBtn" type="button" class="btn btn-primary marginRight5">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">新增</span>             
            </button>
            <button id="btnDel" type="button" class="btn btn-danger btn-space marginRight5">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">删除</span>
            </button>
            <button id="fbBtn" type="button" class="btn btn-default btn-space marginRight5">
                <span class="fa fa-share" aria-hidden="true" class="btn-icon-space">发布</span>             
            </button>  
             <button id="cxBtn" type="button" class="btn btn-default btn-space">
                <span class="fa fa-reply" aria-hidden="true" class="btn-icon-space">撤销</span>             
            </button>                
        </div>
    <table id="table">
       
    </table>
</div>
<script>
    var queryUrl = '<%=basePath%>advertisement/getAdvertisementRecords.do?rnd=' + Math.random();
    var deleteByIdUrl = '<%=basePath%>advertisement/deleteAdvertisementByIds.do?rnd=' + Math.random();
    var updateStatusUrl = '<%=basePath%>advertisement/updateStatus.do?rnd=' + Math.random();
    var queryDicValueUrl = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random();
    
    var columnsArray = [{
    	field: 'checkbox',
        checkbox: true,
        width: 40,
        visible: true  
    }, {
        field: 'id',
        title: 'ID',
        width: 150,
        visible: false  
    }, {
        field: 'homepageName',
        title: '广告标题',
        width: 150
    }, {
        field: 'serviceType',
        title: '关联类型',
        width: 100,
        formatter:  function(value) {
            return getNameByCode(value,typeArray); // 第一个参数是code，第二个参数是字典数组
         },
    }, {
        field: 'status',
        title: '发布状态',
        width: 100,
        formatter:  function(value) {
            return getNameByCode(value,statusArray); // 第一个参数是code，第二个参数是字典数组
         },
    }, {
        field: 'sort',
        title: '排序',
        width: 100
    }, {
        field: 'createTime',
        title: '创建时间',
        width: 100
    }, {
        field: 'operation',
        title: '操作',
        width: 150,
        formatter:function(){
        	return  "<i class='fa fa-eye' title='查看' style='margin-right:8px;font-size: 16px;'></i>"+  
        			"<i class='fa fa-edit' title='编辑'  style='margin-right:8px;font-size: 16px;'></i>"+
     	  			"<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>";
        }
    }];

    var modalObj = window.parent;
    //新增
    $("#newBtn").bind('click',function(){
    	//调取父页面模态框	
		modalObj.setWindowDialog(720,390);
		modalObj.modalOut("active/advertListEdit.jsp",{edit:"new"});		
    });
    //编辑
    function getJSPurl(){
    	modalObj.setWindowDialog(720,390);
		return "active/advertListEdit.jsp";
	}
   //删除
	function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(deleteByIdUrl,{advIds:id});
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
				url: deleteByIdUrl,
				type:"post",
				data:{
					advIds:delID.join(",")
				},
				success:function(data){				
					$("#table").bootstrapTable('refresh');						
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
	
	//关联类型
	var typeArray = [];
	$.ajax({
		url: queryDicValueUrl,
		type: "post",
		data:{
			key: 'advertisement_type',
			isAll:true
		},
		success: function(data){			
			var rows = data.resultList;
			var typeCode = "";
			for(var i=0;i<rows.length;i++){
				typeCode += '<option value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
			}
			$("#service_type").append(typeCode);
			for(var i=0;i<rows.length;i++){
				typeArray[i] = new Array();
				typeArray[i][0]=rows[i].dic_value;
				typeArray[i][1]=rows[i].dic_name; 
			}
		},
		error: function(){
			console.error("请求失败");
		}
	});
	//广告状态
	var statusArray = [];
	$.ajax({
		url: queryDicValueUrl,
		type: "post",
		data:{
			key:'advertisement_status',
			isAll:true
		},
		success: function(data){
			var rows = data.resultList;
			var status = "";
			for(var i=0;i<rows.length;i++){
				if(rows[i].dic_name == '已发布'){
					status += '<option value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
				}else{
					status += '<option value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
				}				
			}
			$("#service_status").append(status);
			for(var i=0;i<rows.length;i++){
				statusArray[i] = new Array();
				statusArray[i][0]=rows[i].dic_value;
				statusArray[i][1]=rows[i].dic_name; 
			}
			//初始化table表格
			 $("#table").InitMainTable(queryUrl, columnsArray);
		},
		error: function(){
			console.error("请求失败");
		}
	});
	//发布
    $("#fbBtn").bind('click',function(){
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;
    	});	
		if(getSelectRows.length ==0){
			modalObj.$.showSuccessTimeout("请选择要发布的广告");
			return;	
		}
		modalObj.$.showConfirm("确认发布?", function(){
			var putID = [];				
			for(var i=0;i<getSelectRows.length;i++){
				putID.push(getSelectRows[i].id);
			}
			$.ajax({
				url:updateStatusUrl,
				type:"post",
				data:{
					advIds:putID.join(","),
					status:statusArray[2][0]
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
	
  	//撤销发布
    $("#cxBtn").bind('click',function(){
		var getSelectRows = $("#table").bootstrapTable('getSelections', function (row) {
            return row;
    	});	
		if(getSelectRows.length ==0){
			modalObj.$.showSuccessTimeout("请选择撤销的广告");
			return;	
		}
		modalObj.$.showConfirm("确认撤销?", function(){
			var putID = [];				
			for(var i=0;i<getSelectRows.length;i++){
				putID.push(getSelectRows[i].id);
			}
			$.ajax({
				url:updateStatusUrl,
				type:"post",
				data:{
					advIds:putID.join(","),
					status:statusArray[1][0]
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