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
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>
<style>
.modal-container {
	padding-top: 0 !important;	
	height: 88% !important;
}
.select-style {
    width: 60% !important;
 }
#city{
	cursor:pointer;
}
	.controls-ul{
		position: absolute;
	    /* overflow-y: scroll !important; */
	    /* overflow: hidden;	 */
	    margin-left: 63px;
	    display: none; 	
	    width: 61%;	   
	    border: 1px solid #ddd;
	    padding-left: 5px;
	    z-index: 888;
	    background: #fff;
	}
	.left-date{
		float: left;
	    width: 55%;
	}
	.left-date li{
		margin: 5px 0;
	}
	.left-date li:hover{
		background:#ddd;
	}
	.right-time{
		display: inline-block;
	    width: 42%;
	    padding-left: 10px;
	    border-left: 1px solid #ddd;
	}
	.right-time li{
		margin: 5px 0;
	}
	.right-time li:hover{
		background:#ddd;
	}
	.selected_li{
		background: #ddd;
	}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增用户</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>

	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">昵称</span> 
					<input id="nick_name" type="text" placeholder="输入昵称"
						class="input-text">
					<span class="lable-symbol">*</span>
				</div>
				<div class="controls">
					<span class="control-label">登录密码</span> 
					<input id="password1" type="password" placeholder="输入登录密码"
						class="input-text"/>
						<span class="lable-symbol">*</span>
				</div>
				
			</div>
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">城市</span>
					<input id="city" type="text" placeholder="输入城市" class="input-text">
					<span class="lable-symbol">*</span>
					 <div class="controls-ul">				
						<ul id="left-date" class="left-date">
							<li>1111</li>
							<li>1111</li>
						</ul>
						<ul id="right-time" class="right-time">
							<li>1111</li>
							<li>1111</li>
						</ul>
					</div>
				</div>
				<div class="controls">
					<span class="control-label">手机号</span>
					<input id="phone" type="text" placeholder="请输入手机号"
						class="input-text"/>
						<span class="lable-symbol">*</span>
				</div>
			</div>
			<div class="area-text-parent">
					<span class="control-label">签名</span>
					 <textarea id="autograph" placeholder="我的签名..." rows="3" cols="20"  class="area-text"></textarea>
					 <span class="lable-symbol">*</span>			
			</div>
			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">钱包余额</span><input id="wallet_balance"
						type="password" placeholder="钱包余额" class="input-text "
						readonly="readonly" style="margin-left: 4px;">
						<span class="lable-symbol-white">*</span> 
				</div>
				<div class="controls">
					<span class="control-label">支付密码</span>
					<input id="wallet_pwd" type="password" placeholder="钱包支付密码"
						class="input-text ">
						<span class="lable-symbol">*</span>
				</div>
			</div>
			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">注册时间</span> <input id="register_time"
						type="text" placeholder="注册时间" class="input-text  "
						readonly="readonly">
						<span class="lable-symbol-white">*</span>
				</div>
				<div class="controls">
					<span class="control-label">收货地址</span> <input id="receive_address"
						type="text" placeholder="默认收货地址" class="input-text ">
						<span class="lable-symbol-white">*</span>
				</div>
			</div>

			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">是否认证</span>
					<select id="is_authentication" class="form-control select-style">
						<option value = "0">未认证</option>
						<option value = "1">已认证</option>
					</select>				
				</div>
				<div class="controls">
					<span class="control-label">性别</span>
					<select id="sex" class="form-control select-style">
						<option>男</option>
						<option>女</option>
					</select>
					
				</div>
			</div>

			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">是否推荐</span>
						<input id="is_recommend" type="text" placeholder="藏品推荐" class="input-text">
					<span class="lable-symbol-white">*</span>
				</div>
				<div class="controls">
					<span class="control-label">信誉度</span>
					<input id="credit" type="text" placeholder="信誉度" class="input-text">
					<span class="lable-symbol">*</span>
				</div>
			</div>
<!-- 
			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">PC端图片</span>
					<input id="pc_image_ids" type="text" placeholder="图片"
						class="input-text">
						<span class="lable-symbol">*</span>
				</div>
				<div class="controls">
					<span class="control-label">MC端图片</span>
					<input id="mobile_image_ids" type="text" placeholder="图片"
						class="input-text">
						<span class="lable-symbol">*</span>
				</div>
			</div>
				
			<div class="control-group displaynone">
				<div class="controls">
					<span class="control-label">创建时间</span><span class="lable-symbol-white">*</span> <input id="create_time"
						type="text" placeholder="创建时间" class="input-text "
						readonly="readonly">
				</div>
				<div class="controls">
					<span class="control-label">更新时间</span><span class="lable-symbol-white">*</span> <input id="update_time"
						type="text" placeholder="更新时间" readonly="readonly"
						class="input-text ">
				</div>
				<div class="controls">
					<span class="control-label">推荐排序</span> <input id="sort"
						type="text" placeholder="推荐排序" class="input-text "
						readonly="readonly">
						<span class="lable-symbol-white">*</span>
				</div>
			</div>
>
       		
--> 
			<div class="control-group displaynone">
				<div class="controls islock">
					<span class="control-label">解锁时间</span> <input id="clear_time"
						type="text" placeholder="解锁时间"  readonly="readonly"
						class="input-text">
						<span class="lable-symbol-white">*</span>
				</div>
			</div>
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">锁定状态</span>
					<select id="is_lock" class="form-control select-style">
						<option value="1">激活</option>
						<option value="0">锁定</option>
					</select>				
				</div>
				<div class="controls islock">
					<span class="control-label">锁定时间</span> <input id="lock_time"
						type="text" placeholder="锁定时间"  readonly="readonly"
						class="input-text">
						<span class="lable-symbol-white">*</span>
				</div>
			</div>
			
			<div class="control-group ">		
				<div class="controls  islock">
					<span class="control-label">被锁天数</span> <input id="lock_days"
						type="text" placeholder="被锁天数"
						class="input-text">
						<span class="lable-symbol-white">*</span>
				</div>
				<div class="controls  islock">
					<span class="control-label">锁定原因</span> <input id="lock_reason"
						type="text" placeholder="锁定原因" 
						class="input-text">
						<span class="lable-symbol-white">*</span>
				</div>
			</div>

			<div class="control-group button-group">
				<button id="submit_modal" class="btn btn-success marginRight10">保存</button>
				<button id="close_modal" class="btn btn-danger">关闭</button>
			</div>
			
		</div>
	</div>
	<script>
	
		var checkNickNameUrl = '<%=basePath%>generalUser/checkSameNickName.do?rnd=' + Math.random();
		var checkPhoneUrl = '<%=basePath%>generalUser/checkSamePhone.do?rnd=' + Math.random();
		
		$(function() {
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			
			if (json_data.nick_name) {
				if(json_data.is_lock == "1"){//激活
					$(".islock").hide();
				}else{//锁定
					$(".islock").show();
					$("#is_lock").val("锁定")
				}	
				
				if(json_data.edit == 2){
					$("#title_text").text("编辑用户信息");					
				}else if(json_data.edit == 1){
					$("#title_text").text("查看用户详情");
					$("#submit_modal").hide();
					$("#password1").parent().hide();
					$('input').each(function(){
						$(this).attr("disabled","disabled");
						$(this).next().addClass("lable-symbol-white");
						$(this).next().removeClass("lable-symbol");
					});
					$('select').each(function(){
						$(this).attr("disabled","disabled");
						$(this).next().addClass("lable-symbol-white");
						$(this).next().removeClass("lable-symbol");
					});
					$('textarea').each(function(){
						$(this).attr("disabled","disabled");
						$(this).next().addClass("lable-symbol-white");
						$(this).next().removeClass("lable-symbol");
					});
				}
				
				$(".displaynone").each(function(){
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
				if(json_data.is_recommend == '1'){
					$("#is_recommend").val('已推荐');
				}else{
					$("#is_recommend").val('未推荐');
				}
				//$("#is_recommend").val(json_data.is_recommend);
				$("#sort").val(json_data.sort);
				$("#receive_address").val(json_data.receive_address);
				$("#is_lock").val(json_data.is_lock);//是否锁定（0/1：解锁/锁定 根据锁定时间+锁定天数 当前时间判断得出锁定状态
				$("#clear_time").val(json_data.clear_time);//解锁时间
			}

			if(json_data.edit == "callbackCity"){
				$("#city").val(json_data.city);
				$("#city").attr('cityCode',json_data.cityCode);
			}
			
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//校验用户昵称{isExist:true}
			$("#nick_name").change(function(){				
					var id = json_data.id;
					var nickName = $(this).val();	
					//校验空
					if(nickName == ""){
						parentOBJ.$.showSuccessTimeout("昵称不能为空");
						$("#nick_name").next().text("X");
						$("#nick_name").next().addClass("lable-symbol");
						$("#nick_name").next().removeClass("lable-symbol-green");
						return;
					}
					//校验格式
					var RegName = /^[\u4E00-\u9FA5a-zA-Z0-9]{6,18}$/;
					if (nickName.search(RegName) == -1) {
						parentOBJ.$.showSuccessTimeout("用户昵称不能含有特殊字符且长度在6-18之间，请重新输入！");
						this.focus();
						return;
					}
									
					$.ajax({
						url:checkNickNameUrl,
						type:"post",
						data:{
							id:id,
							nickName: nickName
						},
						success:function(data){
							if(data=="false"){
								$("#nick_name").next().text("X");
								$("#nick_name").next().addClass("lable-symbol");
								$("#nick_name").next().removeClass("lable-symbol-green");
								$("#nick_name").val("");
								return;
							}else{
								$("#nick_name").next().text("√");
								$("#nick_name").next().addClass("lable-symbol-green");
								$("#nick_name").next().removeClass("lable-symbol");
								return;
							}
						},
						error:function(){
							console.error("连接失败");
						}				
					});
			});
			
			//校验手机号 {isExist:true}
			$("#phone").change(function(){				
					var id = json_data.id;
					var phone = $(this).val();
					if(phone == ""){
						$("#phone").next().text("X");
						$("#phone").next().addClass("lable-symbol");
						$("#phone").next().removeClass("lable-symbol-green");
						return;
					}
					
					var RegCellPhone = /^1[3|4|5|8][0-9]\d{4,8}$/;					
					if (phone.search(RegCellPhone) == -1) {
						parentOBJ.$.showSuccessTimeout("手机号码有误，请重新输入！");
						this.focus();
						return;
					}
					
					$.ajax({
						url:checkPhoneUrl,
						type:"post",
						data:{
							id:id,
							phone: phone
						},
						success:function(data){
							if(data=="false"){
								$("#phone").next().text("X");
								$("#phone").next().addClass("lable-symbol");
								$("#phone").next().removeClass("lable-symbol-green");
								$("#phone").val("");
								return;
							}else{
								$("#phone").next().text("√");
								$("#phone").next().addClass("lable-symbol-green");
								$("#phone").next().removeClass("lable-symbol");
								return;
							}
						},
						error:function(){
							console.error("连接失败");
						}				
				});
				return;
			});
			
			
			$("#submit_modal").click(
					function() {
						nick_name = $("#nick_name").val();
						phone = $("#phone").val();
						password1 = $("#password1").val();
						autograph = $("#autograph").val();
						del_flag = $("#del_flag").val();
						is_flag_nick_name = $("#is_flag_nick_name").val();
						
						console.log(nick_name + phone + password1 + city + autograph);
						//是否为空
						if (nick_name == "" || phone == "" || password1 == "" || autograph == "") {
							parentOBJ.$.showSuccessTimeout("*为必填内容");
							return false;
						}
						 var jsonObj = {};
						 jsonObj.id = json_data.id;
				         jsonObj.nickName = nick_name;
				         jsonObj.phone = phone;
				         jsonObj.city = $("#city").attr('cityCode');
				       
				         jsonObj.password1 = hex_md5(password1).toUpperCase();
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
							data : {data:JSON.stringify(jsonObj)},
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
			$("#is_lock").change(function(){
				var text  = $(this).val();
				if(text == "1"){
					$(".islock").hide();
				}else{
					$(".islock").show();
				}
			});
			
			// 校验签名的合法性
			$("#autograph").change(function(){			
				$(this).checkTextArea(parentOBJ);
			});
			$("#phone").bind('input perportychange',function(){
				 $(this).checkPhone();
			});
			$("#password1").change(function(){			
				$(this).checkPwd(parentOBJ);
			});		
		
			$("#city").bind('click',function(){
				
			});
				
		});
			
	</script>
</body>
</html>
