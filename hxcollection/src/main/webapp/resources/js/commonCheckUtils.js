$(function(){
	$('input').bind('focus',function(){
		this.select();	
	});
	$('input').each(function(){
		$(this).attr('autocomplete','off');
	});
	//限制textarea 输入字数
	$('textarea').bind('input propertychange',function(){
		var num = $(this).val().length;
		if(num>200){
			 $(this).val($(this).val().substring(0, 200)); 
			 $(this).siblings('*:last').text('输入字数限制在200字以内');
		} 
	});
	//我的钱
	 $("input[data-type='input-money']").each(function () {
		 var phoneIcon = '<span class="fa fa-jpy phone_icon" aria-hidden="true"></span>';
	     $(this).after(phoneIcon); 
	 });
	//下拉三角标志
	 $("input[data-type='input-triangle']").each(function () {
		 $(this).css({
			cursor: 'pointer' 
		 });
		 var phoneIcon = '<span class="fa fa-sort phone_icon" aria-hidden="true"></span>';
	     $(this).after(phoneIcon); 
	 });
	//用户选择
	 $("input[data-type='input-userlist']").each(function () {
		 var phoneIcon = '<span class="fa fa-address-book-o phone_icon" aria-hidden="true"></span>';
	     $(this).after(phoneIcon); 
	 });
	 //城市
	 $("input[data-type='input-city']").each(function () {
		 var phoneIcon = '<span class="fa fa-map-marker phone_icon" aria-hidden="true"></span>';
	     $(this).after(phoneIcon); 
	 });
	//手机图标
	 $("input[data-type='input-phone']").each(function () {
		 var phoneIcon = '<span class="fa fa-phone phone_icon" aria-hidden="true"></span>';
	     $(this).after(phoneIcon); 
	 });	
	 
	 $("input[data-type='input-phone']").bind('input propertychange',function(){
		 $(this).val($(this).val().replace(/[^\d]/g,''));
	 });
});

$.fn.extend({
	checkNumber:function(){//校验手机
		$(this).val($(this).val().replace(/[^\d]/g,''));
	},
	checkPhone:function(){//校验手机
		$(this).val($(this).val().replace(/[^\d]/g,''));
	},
	checkTextArea:function(parentPicOBJ){//校验文本域
		var RegAutograph = /^(?![\\p{P}\\p{S}])[\u4e00-\u9fa5]+$/;
		var autograph = $(this).val();
		var falg = autograph.search(RegAutograph);
		if (falg == -1) {
			return "签名含有特殊字符";
			this.focus();
		}
	},
	checkPwd:function(parentPicOBJ){//校验密码
		var RegPwd = /^[A-Za-z0-9]{6,18}$/;
		var password1 = $(this).val();
		var falg = password1.search(RegPwd);
		if (falg == -1) {
			 return "密码必须为字母、数字组合且长度在6-18之间";
			this.focus();
		}
	},
	checkEmail:function(email){//校验邮箱
		 var myreg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		 var email =  $(this).val();
		 var falg = email.search(myreg);
			if (falg == -1) {
				return "邮箱格式不正确";
				this.focus();
			}else{
				return 'sss';
			}
	}
});