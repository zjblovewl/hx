<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>评论增长走势</title>
<link rel="stylesheet" type="text/css"
	href="../../H-ui.admin/H-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="../../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../../css/bootstrap-table.css">
<link rel="stylesheet" type="text/css"
	href="../../css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/keywordSelect.js"></script>
<script src="../../js/echarts3-0.min.js"></script>
<script src="../../js/echartsUtils.js"></script>
<style>
.customsearch-input {
	width: 29%;
}

.line_chart {
	display: block;
	width: 100%;
	min-height: 500px;
		height: 100%;
}
</style>
</head>
<body>
	<div class="customsearch" id="query-form">
		<div class="allLine-time">
			<div class="customsearch-input">
				<span class="label-input-oneline">时间：</span> 
				<input id="startTime" name="startTime" data-type="input-time" placeholder='开始时间' class="form-control">
			</div>
			<div class="customsearch-input">
				<span class="label-input-oneline">至：</span> 
				<input id="endTime" name="endTime" data-type="input-time" placeholder='结束时间' class="form-control">
			</div>
			<div class="customsearch-input allLine_btn">
				<button id="btn_search" type="button"
					class="btn btn-primary btn-space marginRight10">
					<span class="fa fa-search" aria-hidden="true"
						class="btn-icon-space">查询</span>
				</button>
				<button id="btn_reset" type="button"
					class="btn btn-default btn-space marginRight10">
					<span class="fa fa-eraser" aria-hidden="true"
						class="btn-icon-space">重置</span>
				</button>
				<button id="build" type="button"
					class="btn btn-default btn-space marginRight10">
					<span class="fa fa-plus-square" aria-hidden="true"
						class="btn-icon-space">导出报表</span>
				</button>
			</div>
		</div>
		<div id="line_chart" class="line_chart"></div>
	</div>
	<script>
	$(function(){
    	//查询订单趋势URL
    	var queryCommentThrend = '<%=basePath%>chartTrend/queryCommentThrend.do?rnd=' + Math.random(); 
    	//导出订单数据URL
    	var commentDataExport = '<%=basePath%>chartTrend/queryCommentData.do?rnd=' + Math.random(); 
    	var startTime = $("#startTime").val();
    	var endTime = $("#endTime").val();
    	 var param = {startTime:startTime,endTime:endTime};
    	/* 加载数据 */
    	initData(param);	    	
    	function initData(param){
    		 $.ajax({  
    	       	  url : queryCommentThrend,
    	   		  type : "post",
    	   		  data:param,
    	          success: function (data) {  
    	        	var rowsComment1 = data.rows;
    	        	var rowsComment = eval("("+rowsComment1+")");
    	           	//交易订单趋势 
    	           	var data_leg = ['评论总数','拍卖帖评论总数','交易帖评论总数']; //图例
    	        	var data_xy = ['日期','个数'];//x y轴名称
    	           	var data_xAxis = []; //x轴
    	           	var data_series = [];  //数据集 二维数组	
    	          // 	for(var j=0;j<data_leg.length;j++){
    	           		var arr = [];
    	           		var arr1 = [];
    	           		var arr2 = [];
    	           	   for(var i=0;i<rowsComment.length;i++){
	     	            	data_xAxis.push(rowsComment[i].commenttime);	
	     	            	arr.push(rowsComment[i].transactioncount);
	     	            	arr1.push(rowsComment[i].salecount);
	     	            	arr2.push(rowsComment[i].commentcount);
     	           		}
    	           		data_series.push(arr);
    	           		data_series.push(arr1);
    	           		data_series.push(arr2);
    	           	//}
    	           	
    	            $("#line_chart").initLineChart(data_leg,data_xAxis,data_series,data_xy);  
    	            
    	          },
    	          error:function(){
    	        	  console.error("error");
    	          }
    	     });   	
    	}
    	//查询数据
    	$("#btn_search").bind('click',function(){   		
		    $('#query-form').find('[name]').each(function () {   	
		        var value = $(this).val();
		        if(value != null){
		       	 	param[$(this).attr('name')] = encodeURI(value,'utf-8');
		        }else{
		       	 	param[$(this).attr('name')] = '';
		        }
		    });   
		    initData(param);
		});
    	
    	//导出报表
    	$("#build").bind('click',function(){
    		var startTime = $("#startTime").val();
    		var endTime = $("#endTime").val();
    		console.log(startTime+endTime+"ssssssssssssss")
    		document.getElementById("exportFrame").src = commentDataExport +'&startTime='+startTime+'&endTime='+endTime;
    	});
    });

	//重置
	$("#btn_reset").bind('click',function(){
		$('#startTime').val('');
		$('#endTime').val('');
	});   	
</script>
<iframe id="exportFrame" name="exportFrame" width="0" height="0" /></iframe>
</body>
</html>