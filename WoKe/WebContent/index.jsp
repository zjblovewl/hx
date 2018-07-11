<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.bie.action.Counter"%>
<%@ page import="com.woke.bean.*"%>
<%@ page import="com.woke.dao.*"%>
<%@ page import="java.util.*"%>
<!doctype html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=320,maximum-scale=1.3,user-scalable=no">

<meta name="keywords" content="书法展,沈门七">
<meta name="description"
	content="开幕式现场由中国国家博物馆、中国国家画院联合主办的沈门七子书法展于2017年12月9日下午国博西大厅隆重开幕。沈门七子，分别是：王厚祥、刘京闻、龙开胜、周剑初、李明、张志庆、方建光。早年，中国国家画院曾开   ">
<script type="text/javascript" src="js/wx/jweixin-1.2.0.js"></script>
<script type="text/javascript">


<%
			String idd = request.getParameter("id");

			String iddd = (String) session.getAttribute("id");
			String ok = request.getParameter("ok");
			boolean a = false;
			if (ok != null) {
				a = true;
			}
			int id = 10;//默认

			//判断是否有id
			if (idd != null) {
				id = Integer.parseInt(idd);
			} else {
				if (iddd != null) {
					id = Integer.parseInt(iddd);
				}
			}
			//取出该id下的页面的对象的属性
			GuangG gg = BaoMBean.getGuangGByid(id);
			int count2 = gg.getCount() + 1;
			BaoMBean.addCount(gg.getCount(), id);%>




var countdown = function ()
{
    var reg = /^\d{2}$/;
    
    var ending = new Date ('<%=gg.getYearTime()%>/<%=gg.getMonthTime()%>/<%=gg.getDayTime()%>');
    var now = new Date;
    if (now >= ending)
    {
        clearTimeout(this.timeout);
        div.innerHTML = "活动已结束";
        document.getElementById("ce").style.display="none"; 
        return;
    }
    var disc = ending - now;
    var day = parseInt (disc / 1000 / 60 / 60 / 24,10);
    var hour = parseInt (disc / 1000 / 60 / 60 % 24,10);
    hour = !reg.test(hour) ? "0" + hour : hour;
    var minute = parseInt (disc / 1000 / 60 % 60,10);
    minute = !reg.test(minute) ? "0" + minute : minute;
    var second = parseInt (disc / 1000 % 60,10);
    second = !reg.test(second) ? "0" + second : second;
    div.style.cssText += 'text-align:center; font-size:24px';
    div.innerHTML = "距离活动开始剩余<br / >" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
    this.timeout = setTimeout (countdown, 1000);
}
onload = countdown;

</script>
<title><%=gg.getTitle()%></title>
<style>
body, input, button {
	font: normal 14px "Microsoft Yahei";
	margin: 0;
	padding: 0
}

.odform-tit {
	font-weight: normal;
	font-size: 25px;
	color: #595757;
	line-height: 40px;
	text-align: center;
	border-bottom: 1px solid #c9cacb;
	margin: 0;
	padding: 5% 0
}

.odform-tit img {
	height: 40px;
	vertical-align: middle;
	margin-right: 15px
}

.odform {
	padding: 5%
}

.input-group {
	margin-bottom: 5%;
	position: relative
}

.input-group label {
	padding: 2% 0;
	position: absolute;
	color: #595757
}

.input-group input {
	margin-left: 5em;
	padding: 3% 5%;
	box-sizing: border-box;
	background: #efeff0;
	border: 0;
	border-radius: 5px;
	color: #595757;
	width: 75%
}

.odform button {
	background: #8ec31f;
	color: #fff;
	text-align: center;
	border: 0;
	border-radius: 10px;
	padding: 3%;
	width: 100%;
	font-size: 16px
}

.odform .cal {
	background-image: url(images/daetixian-cal.png);
	background-repeat: no-repeat;
	background-position: 95% center;
	background-size: auto 50%
}

.odform .xl {
	background-image: url(images/daetixian-xl.png);
	background-repeat: no-repeat;
	background-position: 95% center;
	background-size: auto 20%
}

* {
	margin: 0;
	padding: 0;
}

.demo {
	width: 100%;
	height: 100%;
}

.demo img {
	width: 100%;
	height: 62.5%;
}

#div {
	color: #A22900;
	font-size: 5px;
}
</style>
</head>
<body>
	<%
		Counter CountFileHandler = new Counter();
		long count = 10;
		if (application.getAttribute("count") == null) {
			count = CountFileHandler.readFromFile(request.getRealPath("/") + "count.txt");
			application.setAttribute("count", new Long(count));
		}
		count = (Long) application.getAttribute("count");
		if (session.isNew()) {
			count++;
			application.setAttribute("count", count);
			//更新文件目录
			CountFileHandler.writeFile(request.getRealPath("/") + "count.txt", count);
		}
	%>


	<div class="header" id="name" style="text-align: center"></div>
	<div class="demo">
		<img src="img/<%=gg.getImg()%>" id="ti"></img>
	</div>
	<div id="div"></div>
	<h1 class="odform-tit">
		<img src="images/daetixian-tit.png" alt="即刻报名">即刻报名
	</h1>
	<div class="odform">

		<form action="addBao" method="post">
			<div class="input-group">
				<label for="khname">客户姓名</label> <input type="hidden" name="pageid"
					value="<%=id%>"> <input type="text" id="khname" name="name"
					placeholder="请输入您的姓名">
			</div>
			<div class="input-group">
				<label for="khname">手机号码</label> <input type="text" id="khname"
					name="tel" placeholder="请输入您的手机号码" required
					pattern="^1[345678][0-9]{9}$"
					oninvalid="setCustomValidity('请输入有效手机号码');"
					oninput="setCustomValidity('');">
			</div>
			<div class="input-group">
				<label for="khname">所在区域</label> <input type="text" id="khname"
					name="address" placeholder="请输入您的地址">
			</div>
			<div class="input-group">
				<label for="khname">意向车型</label> <input type="text" class="cal"
					id="khname" name="brand" placeholder="请选择品牌">
			</div>
			<button onclick="ce()" id="ce">马上预约</button>
		</form>
	</div>
	<div style='color:red; height:105px;line-height:105px;overflow:hidden;border:0px solid #FF0099;text-align:center'>${msg}</div>
	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
	</div>

</body>

<div style="background-color: #DDDDDD; margin-bottom: 0px">
	<p align="center" style="padding-top: 10px">本公司在法律允许范围内拥有该活动最终解释权</p>
	<div style="text-align: center;">
		<a href="#top"><img src="images/top.png"
			style="height: 50px; width: 50px"></img></a>
	</div>
</div>

<script type="text/javascript">
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '${appId}', // 必填，公众号的唯一标识
		    timestamp: '${timestamp}', // 必填，生成签名的时间戳
		    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
		    signature: '${signature}',// 必填，签名，见附录1
		    jsApiList: [
		                "onMenuShareTimeline",
		                "onMenuShareAppMessage"
			] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		
		//调用jsticket分享
		wx.ready(function(){
			//获取“分享给朋友”按钮点击状态及自定义分享内容接口（即将废弃）
			wx.onMenuShareAppMessage({
				title:'<%=gg.getTitle()%>', // 分享标题
				desc: '<%=gg.getCentent()%>', // 分享描述
				link: '${url}/WoKe/WxSharePage?id=${id}' , // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: '${url}/WoKe/logo/<%=gg.getLogo()%>', // 分享图标
				type: 'link', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () {
					// 用户点击了分享后执行的回调函数
					alert('ok');
				}
			});
			
			//分享朋友圈
			wx.onMenuShareTimeline({
			    title: '<%=gg.getTitle()%>', // 分享标题
			    link: '${url}/WoKe/WxSharePage?id=${id}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			    imgUrl: '${url}/WoKe/logo/<%=gg.getLogo()%>', // 分享图标
			    success: function () {
			    	// 用户点击了分享后执行的回调函数
			    	alert('ok');
				}
			});
		});
</script>


</body>
</html>