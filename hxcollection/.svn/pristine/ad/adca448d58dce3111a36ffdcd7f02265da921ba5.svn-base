<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公共弹窗</title>
    <link rel="stylesheet" href="../../fonts/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">
    <link rel="stylesheet" type="text/css" href="../../css/jspEditcommon.css" />

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/commonCheckUtils.js"></script>
  
    <style>
		.bootstrap-table{
			top:35px !important;
			overflow-y: scroll;		
		    bottom: 42px;
		}
    </style>
</head>
<body>
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text"></div>	
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>	
    <table id="table_data" class="table_data"></table>
	<div class="control-group button-group">			
				<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
	</div>
<script>
	var parentOBJ = window.parent;
	//获取url
	var src_data = parentOBJ.document.getElementById("modal-iframe")
			.getAttribute("src-data");
	var json_data = JSON.parse(src_data);
	//初始化
	$("#title_text").text(json_data.name);	
	var arrayColumns = [];
	console.log(src_data);
   
	var table_config = {           
            method: 'post',
            striped: true,         
            dataField: "resultList",
            sidePagination: "server",
            pageNumber: 1,         
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            queryParamsType: 'limit',
            queryParams: queryParams,         
            search: false,
            clickToSelect: true,
            height: 'auto',
            uniqueId: "id",  
            columns: arrayColumns,
            onLoadError: function () {
                console.log("数据加载失败！");
            }          
        };
    
	if(json_data.edit == "session"){
		arrayColumns.push({
	           field: 'checkbox',
	           checkbox: true,
	           visible: true,
	           width: 50
	        });
		arrayColumns.push({
            field: 'dic_name',
            title: '名称',
            width: 50
        });
		arrayColumns.push({
            field: 'dic_value',
            title: '值',
            width: 50
        });
		arrayColumns.push({
            field: 'sort',
            title: '排序',
            width: 50
        });
		
	
		$.extend(true,table_config,{url: json_data.queryUrl,pagination: false});
		$("#table_data").bootstrapTable(table_config);
	}else if(json_data.edit == "userId"){
		arrayColumns.push({
	           field: 'radio',
	           radio: true,
	           visible: true,
	           width: 50
	        });
		arrayColumns.push({
            field: 'nick_name',
            title: '名称',
            width: 50
        });
		arrayColumns.push({
            field: 'phone',
            title: '值',
            width: 50
        });
		arrayColumns.push({
            field: 'id',
            title: 'userId',
            width: 50,
            visible: false
        });
	
		$.extend(true,table_config,{url: json_data.queryUrl,pagination: true,dataField: "rows",});
		$("#table_data").bootstrapTable(table_config);
	}
    var modalObj = window.parent;	
	//关闭弹窗
	$("#close_modal").bind("click",function(){
		modalObj.setWindowDialog(json_data.width,json_data.height);
		modalObj.modalOut(json_data.url,[{edit:"callback"}]);
	});
	$("#close_dialog").bind("click",function(){
		modalObj.setWindowDialog(json_data.width,json_data.height);
		modalObj.modalOut(json_data.url,[{edit:"callback"}]);
	});
	$("#submit_modal").bind("click",function(){
		modalObj.setWindowDialog(json_data.width,json_data.height);
		//获取选中的行
		var getSelectRows = $("#table_data").bootstrapTable('getSelections', function (row) {
	        return row;
		});
		var callText = [];
		for(var i=0;i<getSelectRows.length;i++){
			if(json_data.edit == "userId"){
				callText.push(getSelectRows[i].nick_name+','+getSelectRows[i].id);
			}else if(json_data.edit == "session"){
				callText.push(getSelectRows[i].dic_value);
			}			
		}
				
		modalObj.setWindowDialog(json_data.width,json_data.height);
		modalObj.modalOut(json_data.url,{edit:"callback",callTxt:callText.join(',')});
	});
	
	//请求服务数据时所传参数
	function queryParams(params) {
	    var param = {};
	    param['pageSize'] = params.limit;
	    param['pageIndex'] = params.offset;  
	   
	    return param;
	}
</script>
</body>
</html>