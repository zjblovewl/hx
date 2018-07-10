<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理数据字典键值对</title>
    <link rel="stylesheet"  href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet"  href="../../css/bootstrap.min.css">
    <link rel="stylesheet"  href="../../css/bootstrap-table.css">
    <link rel="stylesheet"  href="../../css/font-awesome.min.css">
    <link rel="stylesheet"  href="../../fonts/iconfont.css"/>
    <link rel="stylesheet"  href="../../css/jspEditcommon.css" />

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/commonCheckUtils.js"></script>
  
    <style>
		.bootstrap-table{
			top:83px !important;
			overflow-y: scroll;		
		    bottom: 10px;
		}
    </style>
</head>
<body>
	<div id="modal-title" class="modal-title">
		<div class="pull-left">管理键值对</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>	
	      		     	
       <div id="toolbar-btn" class="btn-group pull-left marginTop10">
            <button id="new_key" type="button" class="btn btn-primary btn-space marginRight5">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">添加键值对</span>             
            </button>
        </div>
    <table id="table_data" class="table_data"></table>

<script>
	var parentOBJ = window.parent;
	//获取url
	var src_data = parentOBJ.document.getElementById("modal-iframe")
			.getAttribute("src-data");
	var json_data = JSON.parse(src_data);
	var pid = json_data.pid;
	console.log(src_data);
    var queryUrl = '<%=basePath%>dictionary/getKeyAndValRecords.do?rnd=' + Math.random()+'&pId='+pid;
    var delKeyValueUrl = '<%=basePath%>dictionary/deleteDictionaryByIds.do?rnd=' + Math.random();
    $table = $("#table_data").bootstrapTable({
            url: queryUrl,
            method: 'GET',
            striped: true,         
            dataField: "resultList",
            sidePagination: "server",
            pageNumber: 1,
            pagination: false,
            pageSize: 10,         
            queryParamsType: 'limit',           
            search: false,
            clickToSelect: false,
            height: 'auto',
            uniqueId: "id",
            columns: [{
                field: 'id',
                title: 'id',
                width: 50,
                visible: false
            }, {
                field: 'dic_name',
                title: '字典名称',
                width: 150
            }, {
                field: 'dic_value',
                title: '字典值',
                width: 150
            }, {
                field: 'pid',
                title: '父类ID',
                width: 150,
                visible: false
            }, {
                field: 'sort',
                title: '排序',
                width: 150
            }, {
                field: 'operation',
                title: '操作',
                width: 150,
                formatter :function(){
                	return  "<i class='fa fa-pencil' title='编辑'  style='margin-right:8px;font-size: 16px;'></i>"+
           					 "<i class='fa fa-times' title='删除'  style='margin-right:8px; font-size: 16px;'></i>"
                }
            }],      
            onLoadError: function () {
                console.log("数据加载失败！");
            },
            onClickRow : function(row, tr,flied){           	
            	if(event.target.className.indexOf("fa-pencil") != -1){//编辑	
            		$.extend(true,row,{edit:"edit"});
            		getJSPKeyValueUrl(row);
 				}else if(event.target.className.indexOf("fa-times") != -1){//删除
 					var del = row.id;
 					deleKeyValueID(del);
 				}
    		 }           
        });
  
    var modalObj = window.parent;	
	//删除
	function deleKeyValueID(id){
		$.post(delKeyValueUrl,{dicIds:id});
		modalObj.setWindowDialog(720,450);
		modalObj.modalOut("sys/dataKeyValueEdit.jsp",{pid:pid});
	}
	//关闭弹窗
	$("#close_dialog").bind("click",function(){
		modalObj.modalIn();
	});
	//编辑按钮	
	function getJSPKeyValueUrl(row){
		modalObj.setWindowDialog(720,300);
		modalObj.modalOut("sys/dataKeyValueEditEdit.jsp",row);
	}
	//添加键值对
	$("#new_key").bind('click',function(){
		var newJson = {};
		newJson.edit = "new";
		newJson.pid = pid;
		newJson.id = "";
		modalObj.setWindowDialog(720,300);
		modalObj.modalOut("sys/dataKeyValueEditEdit.jsp",newJson);
	});
</script>
</body>
</html>