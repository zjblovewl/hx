<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>后台管理</title>
<link rel="stylesheet" href="../fonts/iconfont.css">
<link rel="stylesheet" href="../H-ui.admin/H-ui.admin.css">
<link rel="stylesheet" href="../H-ui.admin/H-ui.min.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/home.css">

<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-dialog.js"></script>
<script src="../js/echarts3-0.min.js"></script>
<script src="../js/home.js"></script>

<style>

</style>
</head>
<body>
<div>
	<div class="home-banner">
		<ul>
			<li>
				<div class="icon-li icon-li1"><i class="iconfont icon-tongyongleiyonghunan"></i></div>
				<div class="icon-div">
					<div>123456</div>
					<div>用户数量</div>
				</div>
			</li>
			<li>
				<div class="icon-li icon-li2"><i class="iconfont icon-icon-test"></i></div>
				<div class="icon-div">
					<div>888888</div>
					<div>藏品数量</div>
				</div>
			</li>
			<li>
				<div class="icon-li icon-li3"><i class="iconfont icon-paimaichui"></i></div>
				<div class="icon-div">
					<div>888888</div>
					<div>拍卖数量</div>
				</div>
			</li>
			<li>
				<div class="icon-li icon-li4"><i class="iconfont icon-gouwucheman"></i></div>
				<div class="icon-div">
					<div>888888</div>
					<div>订单数量</div>
				</div>
			</li>
		</ul>
	</div>
	<div class="chart_group">
		<div class="line_left_chart">
			<div class="line">
				<div class="line_child" id="line_chart1"></div>
			</div>
			<div class="line">
				<div class="line_child" id="line_chart2"></div>
			</div>
		</div>
		<div class="pi_right_chart">
			<div class="pie_chart" id="pie_chart"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
		var line_chart1 = echarts.init(document.getElementById('line_chart1'));
		var line_chart2 = echarts.init(document.getElementById('line_chart2'));
		var pie_chart = echarts.init(document.getElementById('pie_chart'));
		
		var option = {
				title : {
			        text: '七天内用户增长走势',			       
			        x:'center'
			    },
			    xAxis: {
			        type: 'category',
			        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        data: [820, 932, 901, 934, 1290, 1330, 1320],
			        type: 'line'
			    }]
			};
		line_chart1.setOption(option);
		line_chart2.setOption(option);
		var optionPie = {
			    title : {
			        text: '某站点用户访问来源',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
			    },
			    series : [
			        {
			            name: '访问来源',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			                {value:335, name:'直接访问'},
			                {value:310, name:'邮件营销'},
			                {value:234, name:'联盟广告'},
			                {value:135, name:'视频广告'},
			                {value:1548, name:'搜索引擎'}
			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		pie_chart.setOption(optionPie);
</script>

</body>
</html>