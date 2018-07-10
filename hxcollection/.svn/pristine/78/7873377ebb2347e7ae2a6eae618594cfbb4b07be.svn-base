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
<title>用户新增</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.controls input {
	width: 70%;
}

.controls .control-label {
	width: 65px !important;
}

.only_read {
	background: #ddd;
}

.displaynone {
	display: none;
}

.select-style {
	width: 197px !important;
}
</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增用户</div>
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
					<span class="control-label">手机号</span> <input id="phone"
						type="text" placeholder="请输入手机号" data-type='input-phone'
						class="input-text" /> 
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">登录密码</span> <input id="password1"
						type="password" placeholder="输入登录密码" class="input-text" />
					<div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="area-text-parent">
				<span class="control-label">签名</span>
				<textarea id="autograph" placeholder="我的签名..." rows="3" cols="20"
					class="area-text"></textarea>				
				<div class="lable-symbol-div"></div>
			</div>

		</div>
	</div>
			<div class="modal-footer">
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
	<script>
	
		var checkNickNameUrl = '<%=basePath%>generalUser/checkSameNickName.do?rnd=' + Math.random();
		var checkPhoneUrl = '<%=basePath%>generalUser/checkSamePhone.do?rnd='+ Math.random();

		$(function() {
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);

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
				//var RegName = /^[\u4E00-\u9FA5a-zA-Z0-9]{1,18}$/;
				//if (nickName.search(RegName) == -1) {
				//	$("#nick_name").siblings('*:last').text("用户昵称不能含有特殊字符且长度在1-18之间！");
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
							$("#nick_name").siblings('*:last').text("校验失败！");
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

			$("#submit_modal").bind('click',function() {
						nick_name = $("#nick_name").val();
						phone = $("#phone").val();
						password1 = $("#password1").val();
						autograph = $("#autograph").val();

						//是否通过
						var ck_nickname = $("#nick_name").siblings('*:last')
								.text();
						var ck_phone = $("#phone").siblings('*:last').text();
						if (ck_nickname != "") {
							$("#nick_name").siblings('*:last').text(
									"昵称校验失败，请重新输入！");
							return;
						} else if (ck_phone != "") {
							$("#phone").siblings('*:last').text(
									"手机号校验失败，请重新输入！");
							return;
						}
						//是否为空
						if (nick_name == '') {
							$("#nick_name").siblings('*:last').text('昵称不能为空！');
						}else{
							$("#nick_name").siblings('*:last').text('');
						}
						if (phone == '') {
							$("#phone").siblings('*:last').text('手机号不能为空！');
						}else{
							$("#phone").siblings('*:last').text('');
						}
						if (password1 == '') {
							$("#password1").siblings('*:last').text('密码不能为空！');
						}else{
							$("#password1").siblings('*:last').text('');
						}
						if (autograph == '') {
							$("#autograph").siblings('*:last').text('签名不能为空！');
						}else{
							$("#autograph").siblings('*:last').text('');
						}
						if (nick_name == '' || phone == '' || password1 == ''
								|| autograph == '') {
							return;
						}
						var jsonObj = {};
						jsonObj.id = json_data.id;
						jsonObj.nickName = nick_name;
						jsonObj.phone = phone;
						jsonObj.password1 = hex_md5(password1).toUpperCase();
						jsonObj.autograph = autograph;

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
			$("input").bind('input propertychange', function() {
				if ($(this).val() != "") {
					$(this).siblings('*:last').text('');
				}
			});
			$("textarea").bind('input propertychange', function() {
				if ($(this).val() != "") {
					$(this).siblings('*:last').text('');
				}
			});
		});

	</script>
</body>
</html>
