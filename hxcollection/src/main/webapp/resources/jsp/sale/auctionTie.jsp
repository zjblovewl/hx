<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
   <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>拍卖贴查询</title>
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
     		<span class="label-input-oneline">昵称：</span>
	     	<input name="nick_name" placeholder='昵称' type="text" class="form-control">
	     	</div>	  
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">关键字：</span>
		     	<input name="goods_name" placeholder='藏品名称' type="text" class="form-control">
	     	</div>	   	     	
	     	<div class="customsearch-input">
	     		<span class="label-select-oneline">大类：</span>
	     		<select id="big_class_code" name="big_class_code" class="form-control select-style">	
	     		</select>
	     	</div>   
	     	 <div class="customsearch-input">
	     		<span class="label-select-oneline">小类：</span>
	     		<select id="small_class_code" name="small_class_code" class="form-control select-style">	
	     		</select>
	     	</div> 
	     
    	</div>
    	<div class="allLine">
			<div class="customsearch-input">	     	
	     		<span class="label-select-oneline">拍卖状态：</span>
	     		<select id="status" name="status" class="form-control select-style">	
	     		</select>
	     	</div> 	
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
            <button id="btnDel" type="button" class="btn btn-danger btn-space marginRight10">
                <span class="fa fa-trash-o" aria-hidden="true" class="btn-icon-space">删除</span>
            </button>
            <button id="btnConfig" type="button" class="btn btn-default btn-space">
                <span class="fa fa-cog" aria-hidden="true" class="btn-icon-space">场次配置</span>             
            </button>
        </div>
    <table id="table">
       
    </table>
</div>
<script>
    var queryUrl = '<%=basePath%>goods/getSaleGoodsRecords.do?rnd=' + Math.random();
    var queryBigClazzUrl = '<%=basePath%>goodsClassification/getGoodsClass.do?rnd=' + Math.random();
    var delUrl = '<%=basePath%>goods/deleteGoodsByIds.do?rnd=' + Math.random();
    var queryDicValueUrl = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random();
    var columnsArray = [{
        checkbox: true,
        visible: true    //是否显示复选框
    }, {
        field: 'goodsId',
        title: '藏品id',
        width: 150,
        visible:false
    }, {
        field: 'goodsName',
        title: '藏品名称',
        width: 150
    }, {
        field: 'nickName',
        title: '用户昵称',
        width: 150
    }, {
        field: 'goodsDescription',
        title: '内容',
        width: 150
    }, {
        field: 'startPrice',
        title: '起拍价',
        width: 150
    }, {
        field: 'markupPrice',
        title: '每手加价'
    }, {
        field: 'postage',
        title: '邮费',
        width: 150,
        visible: false
    }, {
        field: 'currentPrice',
        title: '当前价格',
        width: 150
    }, {
        field: 'publishCustomer',
        title: '发布人',
        width: 150
    }, {
        field: 'status',
        title: '状态',
        width: 150,
        visible: true,
        formatter:  function(value) {
           return getNameByCode(value,goodsStatusArray); // 第一个参数是code，第二个参数是字典数组
        },
    }, {
        field: 'imageIds',
        title: '图片ids',
        width: 150,
        visible: false
    }, {
        field: 'imageUrls',
        title: '图片url',
        width: 150,
        visible: false
    }, {
        field: 'publishTime',
        title: '发布时间',
        width: 150,
        visible: false
    }, {
        field: 'firstStepTime',
        title: '起拍时间',
        width: 150,
        visible: false
    }, {
        field: 'bigClassCode',
        title: '大类code',
        width: 150,
        visible: false
    }, {
        field: 'browseNum',
        title: '浏览次数',
        width: 150,
        visible: false
    }, {
        field: 'smallClassVode',
        title: '小类code',
        width: 150,
        visible: false
    }, {
        field: 'inventory',
        title: '库存',
        width: 150,
        visible: false
    }, {
        field: 'handle',
        title: '操作',
        width: 150,
        formatter: function(){
        	return "<i class='fa fa-eye' title='查看' style='margin-right:4px;font-size: 16px;'></i>"+
		     	   "<i class='fa fa-edit' title='编辑'  style='margin-right:4px;font-size: 16px;'></i>"+
		   		   "<i class='fa fa-trash-o' title='删除'  style='margin-right:4px;font-size: 16px;'></i>"+
		   		   "<i class='fa fa-comments' title='查看评论'  style='font-size: 16px;'></i>";
        }
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    var modalObj = window.parent;
	$("#build").click(function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,600);
		modalObj.modalOut("sale/auctionTieEdit.jsp",[{}]);		
	});
	//查看评论
	function getJSPCommentsUrl(){
		return "sale/auctionTieCommentsEdit.jsp";
	}
	//编辑按钮	
	function getJSPurl(){
		modalObj.setWindowDialog(720,563);
		return "sale/auctionTieCheckEdit.jsp";
	}
	function deleRow(row){		
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(delUrl,{goodsIds:row.goodsId});
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
				delID.push(getSelectRows[i].goodsId);
			}
			$.ajax({
				url:delUrl,
				type:"post",
				data:{
					goodsIds:delID.join(",")
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
	//场次配置
	$("#btnConfig").bind('click',function(){
		//调取父页面模态框	
		modalObj.setWindowDialog(720,300);
		modalObj.modalOut("sale/auctionTieConfigEdit.jsp",[{edit:"config"}]);		
	});
	//大类
	var dataArray = [];
	$.ajax({
		url: queryBigClazzUrl,
		type: "post",
		data:{
			class_type:2,
			isAll:true
		},
		success: function(data){
			var rows = data.resultList;
			var bigCode ="";
			var smallCode = "";
			dataArray = rows;
			for(var i=0;i<rows.length;i++){
				if(rows[i].class_code == 'all'){	
					bigCode += '<option value="">'+rows[i].class_name+'</option>';
				}	
				if(rows[i].parent_code == 0){	
					bigCode += '<option value="'+rows[i].class_code+'">'+rows[i].class_name+'</option>';
				}			
			}
			$("#big_class_code").append(bigCode);
			$("#small_class_code").attr("disabled","disabled");
		},
		error: function(){
			console.error("请求失败");
		}
	});
	//大类小类联动事件	
	 $("#big_class_code").change(function() {	
		 var smallCode = '';
		var currentClazz = $('#big_class_code option:selected').attr("value");
		if(currentClazz == ""){
			// 大类未选中时，小类不可编辑
			$("#small_class_code").html('<opiton value=""></option>');
			$("#small_class_code").attr("disabled","disabled");
			return;
		}else{
			$("#small_class_code").attr("disabled",false);
		}
		for(var i=0;i<dataArray.length;i++){
			if(dataArray[i].class_code == 'all'){	
				smallCode += '<option value="">'+dataArray[i].class_name+'</option>';
			}
			if(dataArray[i].parent_code == currentClazz){
				smallCode += '<option value="'+dataArray[i].class_code+'">'+dataArray[i].class_name+'</option>';
			}
		}
		$("#small_class_code").html(smallCode);		
	 });
		//藏品状态
		var goodsStatusArray = new Array();
		$.ajax({
			url: queryDicValueUrl,
			type: "post",
			data:{
				key:'sale_status',
				isAll:true
			},
			success: function(data){
				var rows = data.resultList;
				var status = "";
				for(var i=0;i<rows.length;i++){
					status += '<option value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
				}
				$("#status").append(status);
				
				for(var i=0;i<rows.length;i++){
					goodsStatusArray[i] = new Array();
					goodsStatusArray[i][0]=rows[i].dic_value;    //这里将变量初始化，我这边统一初始化为空，后面在用所需的值覆盖里面的值
					goodsStatusArray[i][1]=rows[i].dic_name; 
				}
			},
			error: function(){
				console.error("请求失败");
			}
		});
		
</script>
</body>
</html>