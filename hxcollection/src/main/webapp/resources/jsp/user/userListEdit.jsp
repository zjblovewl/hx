<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css"/>
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery-ui.min.js"></script>
<script src="../../js/commonCheckUtils.js"></script>
<style>
.select-style {
	width: 60% !important;
}

#city {
	cursor: pointer;
}

.controls-ul {
	position: absolute;
	margin-left: 63px;
	display: none;
	width: 61%;
	border: 1px solid #ddd;
	padding-left: 5px;
	z-index: 888;
	background: #fff;
    display: none;
    height: 163px;
}

.left-date {
	float: left;
	width: 55%;
	height:100%;
	list-style: none;
	overflow-y: scroll;
	padding:0 0 0 5px;
}

.left-date li {
	margin: 5px 0;
}

.left-date li:hover {
	background: #ddd;
}

.right-time {
	display: inline-block;
	width: 42%;
	height:100%;
	padding: 0 0 0 5px;
	border-left: 1px solid #ddd;
	list-style: none;
	overflow-y: scroll;
}

.right-time li {
	margin: 5px 0;
	text-decoration: none;
	list-style: none;
}

.right-time li:hover {
	background: #ddd;
}

.selected_li {
	background: #ddd;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">编辑用户</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>

	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">昵称</span> <input id="nick_name"
						type="text" placeholder="输入昵称" class="input-text">
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">登录密码</span> <input id="password1"
						type="password" placeholder="输入登录密码" class="input-text" /> 
					<div class="lable-symbol-div"></div>
				</div>

			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">城市</span> 
					<input id="city" type="text"
						placeholder="输入城市" class="input-text" data-type="input-city">
					<div id="controls-ul" class="controls-ul">
						<ul id="left-date" class="left-date">
							
						</ul>
						<ul id="right-time" class="right-time">
							
						</ul>
					</div>			
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">手机号</span> <input id="phone"
						type="text" placeholder="请输入手机号" data-type='input-phone'
						class="input-text" />
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="area-text-parent">
				<span class="control-label">签名</span>
				<textarea id="autograph" placeholder="我的签名..." rows="3" cols="20"
					class="area-text"></textarea>
				<div class="lable-symbol-div"></div>
			</div>
			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">钱包余额</span>
					<input id="wallet_balance" type="text" placeholder="钱包余额" class="input-text" data-type="input-money" readonly="readonly" > 				
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">支付密码</span> <input id="wallet_pwd"
						type="password" placeholder="钱包支付密码" class="input-text ">
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">注册时间</span> <input id="register_time"
						type="text" placeholder="注册时间" class="input-text" data-type="input-time"
						readonly="readonly">
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">收货地址</span> <input id="receive_address"
						type="text" placeholder="默认收货地址" class="input-text "> 
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">认证状态</span>
					<input id="is_authentication" type="text" 
						class="input-text "
						readonly="readonly">			
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">性别</span> <select id="sex"
						class="form-control select-style">
						<option>男</option>
						<option>女</option>
					</select>
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">是否推荐</span> 
					<select id="is_recommend" class="form-control select-style">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">信誉度</span> 
					<input id="credit" type="text" placeholder="信誉度" class="input-text">
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group displaynone">
				<div class="controls islock">
					<span class="control-label">解锁时间</span> <input id="clear_time"
						type="text" placeholder="解锁时间" readonly="readonly"
						class="input-text"> 
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">锁定状态</span> <select id="is_lock"
						class="form-control select-style">
						<option value="1">激活</option>
						<option value="0">锁定</option>
					</select>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls islock">
					<span class="control-label">锁定时间</span> <input id="lock_time"
						type="text" placeholder="锁定时间" readonly="readonly"
						class="input-text">
					<div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group ">
				<div class="controls  islock">
					<span class="control-label">被锁天数</span> <input id="lock_days"
						type="text" placeholder="被锁天数" class="input-text"> 
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls  islock">
					<span class="control-label">锁定原因</span> <input id="lock_reason"
						type="text" placeholder="锁定原因" class="input-text"> 
					<div class="lable-symbol-div"></div>
				</div>
			</div>
		</div>
	</div>
			<div class="modal-footer">
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
	<script>
		$(function() {//可拖动
			 $("#modal-title").draggable(); 
			var checkNickNameUrl = '<%=basePath%>generalUser/checkSameNickName.do?rnd=' + Math.random();
			var checkPhoneUrl = '<%=basePath%>generalUser/checkSamePhone.do?rnd='+ Math.random();
			var queryCityUrl = '<%=basePath%>generalUser/queryAreaInfo.do?rnd='+ Math.random();
			
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			//console.log(json_data,json_data.is_authentication);
			if(json_data.is_authentication == 1){
				json_data.is_authentication = "已认证";
			}else{
				json_data.is_authentication = "未认证";
			}

			if (json_data.nick_name) {
				if (json_data.is_lock == "1") {//激活
					$(".islock").hide();
				} else {//锁定
					$(".islock").show();
					$("#is_lock").val("锁定")
				}

				if (json_data.edit == 2) {
					$("#title_text").text("编辑用户信息");
				} else if (json_data.edit == 1) {
					$("#title_text").text("查看用户详情");
					$("#submit_modal").hide();
					$('input').each(function() {
						$(this).attr("disabled", "disabled");
					});
					$('select').each(function() {
						$(this).attr("disabled", "disabled");
					});
					$('textarea').each(function() {
						$(this).attr("disabled", "disabled");
					});
					$(".lable-symbol").each(function() {
						$(this).hide();
					});
				}

				$(".displaynone").each(function() {
					$(this).removeClass("displaynone");
				});

				$("#nick_name").val(json_data.nick_name);
				$("#phone").val(json_data.phone);
				$("#password1").val(json_data.password);
				$("#city").val(json_data.city);
				$("#is_authentication").val(json_data.is_authentication);
				$("#sex").val(json_data.sex);
				$("#autograph").val(json_data.autograph);
				$("#credit").val(json_data.credit);
				$("#wallet_balance").val(json_data.wallet_balance);
				$("#wallet_pwd").val(json_data.wallet_pwd);
				$("#register_time").val(json_data.register_time);
				$("#lock_time").val(json_data.lock_time);
				$("#lock_days").val(json_data.lock_days);
				$("#lock_reason").val(json_data.lock_reason);
				if (json_data.is_recommend == '1') {
					$("#is_recommend").find('option[value="1"]').attr(
							'selected', 'selected');
				} else {
					$("#is_recommend").find('option[value="0"]').attr(
							'selected', 'selected');
				}
				//$("#is_recommend").val(json_data.is_recommend);
				$("#sort").val(json_data.sort);
				$("#receive_address").val(json_data.receive_address);
				$("#is_lock").val(json_data.is_lock);//是否锁定（0/1：解锁/锁定 根据锁定时间+锁定天数 当前时间判断得出锁定状态
				$("#clear_time").val(json_data.clear_time);//解锁时间
			}

			if (json_data.edit == "callbackCity") {
				$("#city").val(json_data.city);
				$("#city").attr('cityCode', json_data.cityCode);
			}

			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//校验用户昵称{isExist:true}
			$("#nick_name").change(function() {
				var id = json_data.id;
				var nickName = $(this).val();
				//校验空
				if (nickName == "") {
					$("#nick_name").siblings('*:last').text("昵称不能为空！");
					return;
				}
				//校验格式
				//var RegName = /^[\u4E00-\u9FA5a-zA-Z0-9]{6,18}$/;
				//if (nickName.search(RegName) == -1) {
				//	$("#nick_name").siblings('*:last').text("用户昵称不能含有特殊字符且长度在6-18之间，请重新输入！");
				//	this.focus();
				//	return;
				//}

				$.ajax({
					url : checkNickNameUrl,
					type : "post",
					data : {
						id : id,
						nickName : nickName
					},
					success : function(data) {
						if (data == "false") {
							$("#nick_name").siblings("*:last").text('用户名已存在！');
							return;
						} else {
							$("#nick_name").siblings('*:last').text("");
							return;
						}
					},
					error : function() {
						console.error("连接失败");
					}
				});
			});

			//校验手机号 {isExist:true}
			$("#phone").change(function() {
				var id = json_data.id;
				var phone = $(this).val();
				if (phone == "") {
					$("#phone").siblings('*:last').text("手机号不能为空！");
					return;
				}
				var RegCellPhone = /^1[3|4|5|8][0-9]\d{4,8}$/;
				if (phone.search(RegCellPhone) == -1) {
					$("#phone").siblings('*:last').text("手机号码有误！");
					this.focus();
					return;
				}

				$.ajax({
					url : checkPhoneUrl,
					type : "post",
					data : {
						id : id,
						phone : phone
					},
					success : function(data) {
						if (data == "false") {
							$("#phone").siblings('*:last').text("手机号已存在！");
							return;
						} else {
							$("#phone").siblings('*:last').text("");
							return;
						}
					},
					error : function() {
						console.error("连接失败");
					}
				});
				return;
			});

			$("#submit_modal").click(function() {
				var nick_name = $("#nick_name").val();
				var phone = $("#phone").val();
				var password1 = $("#password1").val();
				var autograph = $("#autograph").val();
				var del_flag = $("#del_flag").val();
				var is_flag_nick_name = $("#is_flag_nick_name").val();
				var wallet_pwd = $("#wallet_pwd").val();

				//是否通过
				var ck_nickname = $("#nick_name").siblings('*:last').text();
				var ck_phone = $("#phone").siblings('*:last').text();
				if (ck_nickname != "") {
					$("#nick_name").siblings('*:last').text("昵称校验失败，请重新输入！");
					return;
				} else if (ck_phone != "") {
					$("#phone").siblings('*:last').text("手机号校验失败，请重新输入！");
					return;
				}
				//是否为空
				if (nick_name == '') {
					$("#nick_name").siblings('*:last').text('昵称不能为空！');					
				} else{
					$("#nick_name").siblings('*:last').text('');		
				} 
				if (phone == '') {
					$("#phone").siblings('*:last').text('手机号不能为空！');				
				} else{
					$("#phone").siblings('*:last').text('');
				}
				if (password1 == '') {
					$("#password1").siblings('*:last').text('密码不能为空！');					
				} else{
					$("#password1").siblings('*:last').text('');	
				}
				if (autograph == '') {
					$("#autograph").siblings('*:last').text('签名不能为空！');					
				}else{
					$("#autograph").siblings('*:last').text('');		
				}
				if(nick_name == ''||phone == ''||password1 == ''||autograph == ''){
					return;
				}
				var jsonObj = {};
				jsonObj.id = json_data.id;
				jsonObj.nickName = nick_name;
				jsonObj.phone = phone;
				jsonObj.city = $("#city").attr('cityCode');
				//用户密码
				if(json_data.password != password1){
					jsonObj.password1 = hex_md5(password1).toUpperCase();
				}else{
					jsonObj.password1 = password1;
				}	
				//支付密码
				if(json_data.wallet_pwd != wallet_pwd){
					jsonObj.wallet_pwd = hex_md5(wallet_pwd).toUpperCase();
				}else{
					jsonObj.wallet_pwd = wallet_pwd;
				}	
				jsonObj.autograph = autograph;
				jsonObj.delFlag = del_flag;
				jsonObj.isFlagNickName = is_flag_nick_name;
				jsonObj.isLock = $("#is_lock").val();
				//	 jsonObj.lock_time = $("#lock_time").val();
				jsonObj.lockDays = $("#lock_days").val();
				jsonObj.lockReason = $("#lock_reason").val();
				jsonObj.clearTime = $("#clear_time").val();
				jsonObj.receiveAddress = $("#receive_address").val();
				jsonObj.isRecommend = $("#is_recommend").val();
				jsonObj.isAuthentication = $("#is_authentication").val();

				$.ajax({
					url : "<%=basePath%>generalUser/addOrUpdateGeneralUser.do?rnd=' + Math.random()",
					type : "post",
					data : {
						data : JSON.stringify(jsonObj)
					},
					success : function(data) {
						console.log(data)
						if (data == null) {
							parentOBJ.$.showSuccessTimeout("添加失败");

						} else {
							parentOBJ.$.showSuccessTimeout("添加成功");
							parentOBJ.resfreshTable();
						}
					},
					error : function() {
						console.error("error");
					}
				});
				parentOBJ.modalIn();//关闭
				return false;
			});

			//是否锁定
			$("#is_lock").change(function() {
				var text = $(this).val();
				if (text == "1") {
					$(".islock").hide();
				} else {
					$(".islock").show();
				}
			});

			// 校验签名的合法性
			$("#autograph").change(function() {
				var parentOBJ = window.parent;
				$(this).checkTextArea(parentOBJ);
			});
			$("#phone").bind('input propertychange', function() {
				$(this).checkPhone();
			});
			$("#password1").change(function() {
				var parentOBJ = window.parent;
				$(this).checkPwd(parentOBJ);
			});
			$("#autograph").bind('input propertychange', function() {
				if ($(this).val() != "") {
					$("#autograph").siblings('*:last').text('');
				}
			});

			$("#city").bind('click', function() {
				$(".controls-ul").slideToggle();
			});
			//加载城市选项
			var cityArray = [];
			$.ajax({
				url : queryCityUrl,
				type : "post",
				data : {				
				},
				success : function(data) {
					console.log(data);
					var rows = data.data;
					var provinceCode = "";
					cityArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].province_code != "000000"){
							provinceCode += '<li value="'+rows[i].province_code+'">'+rows[i].province_name+'</li>';
						}					
					}
					$("#left-date").html(provinceCode);
					$("#left-date").css({width:'100%'});
					$("#right-time").hide();
				},
				error : function() {
					console.error("提交失败");					
				}
			});
			
			var city_chose = '';
			var province_chose = '';
			var city_chose_val = '';
			var province_chose_val = "";
			$("#left-date").on('click','li',function(){	
				$(this).addClass('selected_li');
				$(this).siblings().removeClass('selected_li');
				$("#left-date").css({width:'55%'});
				$("#right-time").show();
				province_chose = $(this).text();
				province_chose_val = $(this).attr('value');
				$("#city").val(province_chose);
				$("#city").attr('code',province_chose_val);
				for(var i=0;i<cityArray.length;i++){
					if($(this).attr('value') == cityArray[i].province_code){
						var cityCode = '';
						for(var j=0;j<cityArray[i].city_info.length;j++){
							cityCode += '<li value="'+cityArray[i].city_info[j].city_code+'">'+cityArray[i].city_info[j].city_name+'</li>';
						}						
					}
				}
				$("#right-time").html(cityCode);
			});
			$("#right-time").on('click','li',function(){
				$(this).addClass('selected_li');
				$(this).siblings().removeClass('selected_li');
				city_chose = $(this).text();
				city_chose_val = $(this).attr('value');
				$("#city").val(province_chose+','+city_chose);
				$("#city").attr('code',province_chose_val+','+city_chose_val);
				$(".controls-ul").slideToggle();
			});
		});
	</script>
</body>
</html>
