<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册用户走势</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/laydate/laydate.js"></script>
    <script src="../../js/timeUtils.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/echarts3-0.min.js"></script>

    <style>
      .customsearch-input{
			width: 29%;
		}	
	  .line_chart{
	  	display: block;
	  	width: 100%;
	  	height: 400px;
	  }
    </style>
</head>
<body>
	 <div class="customsearch" id="query-form">
    	<div class="allLine-time">   		
    		<div class="customsearch-input">
	   			<span class="label-input-oneline">时间:</span>
		     	<input id="startTime" name="first_step_start_time" data-type="input-time" placeholder='开始时间'  class="form-control">
	     	</div>
	     	<div class="customsearch-input">
	     		<span class="label-input-oneline">至</span>
		     	<input id="endTime" name="first_step_end_time" data-type="input-time" placeholder='结束时间' class="form-control">
	     	</div>
	     	<div class="customsearch-input allLine marginRight10" style="text-align: right;justify-content:flex-end;">
	            <button id="btn_search" type="button" class="btn btn-primary btn-space marginRight10">
	                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
	            </button>
	            <button id="btn_reset"  type="button" class="btn btn-default btn-space marginRight10">
	                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
	            </button>
	             <button id="build" type="button" class="btn btn-default btn-space marginRight10">
                <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">导出报表</span>             
            </button> 
	        </div>
    	</div> 
  		<div id="line_chart" class="line_chart">
  		</div>
    </div>
<script>
    var option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
        }]
    };
  var myEchart = echarts.init(document.getElementById("line_chart"));
  myEchart.setOption(option);
</script>
</body>
</html>