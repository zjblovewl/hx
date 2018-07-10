<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统消息管理</title>
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
    <script src="../../js/timeUtils.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableUtil.js"></script>
	<script src="../../js/common/common.js"></script>
    <style>
		.bootstrap-table{
			top:46px !important;
		}
		.customsearch-input{
			width: 45%;
		}
    </style>
</head>
<body>
<div>
   <div class="customsearch allLine" id="query-form">     	        
		<div class="customsearch-input">
			<span class="label-input-oneline">消息标题：</span>
	     	<input name="mesTitle" placeholder='输入消息标题' type="text"class="form-control">
     	</div>
     	<div class="customsearch-input marginRight20">
	     		<span class="label-select-oneline">推送平台：</span>
	     		<select id="plantform" name="platform" class="form-control select-style">	
	     		</select>
	     	</div> 
        <div class="allLine marginRight10">
            <button id="btn_search" type="button" class="btn btn-primary btn-space marginRight10">
                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
            </button>
            <button id="btn_reset"  type="button" class="btn btn-default btn-space">
                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
            </button>
        </div>
       </div>
        <div id="toolbar-btn" class="btn-group pull-left">
            <button id="build" type="button" class="btn btn-primary btn-space marginRight5">
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
    var queryUrl = '<%=basePath%>sysMessage/getMessageRecords.do?rnd=' + Math.random();
    var delMessageUrl = '<%=basePath%>sysMessage/deleteMessageByIds.do?rnd=' + Math.random();
    var plantformMessageUrl = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random();
   
    var columnsArray = [{
        checkbox: true,
        visible: true    //是否显示复选框
    }, {
        field: 'id',
        title: 'id',
        width: 50,
        visible:false
    }, {
        field: 'message_title',
        title: '消息标题',
        width: 150
    }, {
        field: 'message_content',
        title: '消息内容',
        width: 150,
        visible:false
    }, {
        field: 'platform',
        title: '推送平台',
        width: 150,
        formatter:  function(value) {
           return getNameByCode(value,plantformArray); // 第一个参数是code，第二个参数是字典数组
        }
    }, {
        field: 'operation',
        title: '操作',
        width: 150,
        formatter:editAnddelte()
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    //初始化
    var plantformArray = new Array();;
    $.ajax({
    	url: plantformMessageUrl,
    	type: "POST",
    	data:{
    		key: "plantform",
    		isAll : true
    	},
    	dataType: 'json',
    	success: function(data){
    		var rows = data.resultList;
    		var plantformCode = "";
    		for(var i=0;i<rows.length;i++){
    			plantformCode += '<option value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>'
    		}
    		$("#plantform").append(plantformCode);
    		for(var i=0;i<rows.length;i++){
				plantformArray[i] = new Array();
				plantformArray[i][0]=rows[i].dic_value;    //这里将变量初始化，我这边统一初始化为空，后面在用所需的值覆盖里面的值
				plantformArray[i][1]=rows[i].dic_name; 
			}
    	},
    	error: function(){
    		console.error("初始化数据类型");
    	}
    });
    var modalObj = window.parent;
	$("#build").click(function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,300);
		modalObj.modalOut("sys/messageEdit.jsp",{edit:"new"});		
	});
	
	//编辑按钮	
	function getJSPurl(){
		modalObj.setWindowDialog(720,500);
		return "sys/messageEdit.jsp?js=555";
	}
	function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目", function(){
			$.post(delMessageUrl,{mesIds:id});
			$("#table").bootstrapTable('refresh');			
		},function(){
			return false;
		});
		
	}
	$("#btnDel").bind('click',function(){
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
				url: delMessageUrl,
				type:"post",
				data:{
					mesIds:delID.join(",")
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