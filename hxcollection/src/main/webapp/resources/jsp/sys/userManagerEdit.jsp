<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String uploadImageUrl = Configuration.getInstance().getValue("upload_image_url");
	String getImageUrl = Configuration.getInstance().getValue("get_image_url");
%>
<!DOCTYPE html>
<html lang="en">	
<head>
<meta charset="UTF-8">
<title>用户管理编辑</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css"/>
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<link rel="stylesheet" href="../../css/jspEditcommon.css" />

<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
.controls .input-text{	
    width: 67%;
}

.controls-ul{
	overflow-y: scroll !important;
	overflow-x: hidden;
    height: 110px;
	display: none;
    margin-left: 64px;
    width: 66%;
    border: 1px solid #ddd;
    padding-left: 5px;
}
.controls ul li{
	margin-bottom:5px;
}
.controls .checkbox-role{
	vertical-align: middle;
	margin-right:6px;
}

</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增系统用户</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">用户名 </span>
					<input id="user_name" type="text" placeholder="输入用户名" class="input-text">	
					 <div class="lable-symbol-div"></div>				 
				</div>
				<div class="controls">
					<span class="control-label">密码</span>					
					 <input id="password" type="password" placeholder="输入密码" class="input-text">
					 <div class="lable-symbol-div"></div>	
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">登录名</span>
					 <input id="login_name" type="text" placeholder="输入登录名" class="input-text">
					<div class="lable-symbol-div"></div>	
				</div>
				<div class="controls">
					<span class="control-label">邮箱</span> 
					<input id="email" type="text" placeholder="输入邮箱" class="input-text">
					<div class="lable-symbol-div"></div>	
				</div>
			</div>	
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">用户角色</span> 
					<input id="roles" type="text" placeholder="选择用户角色" class="input-text role_down" style="cursor:pointer;">
					<span class="role_down" style="position: absolute;right: 56px;top: 3px;">▼</span>					
					<ul class="controls-ul">				
					
					</ul>
					<div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">图片</span>					
					<form id="imgForm" class="inputBox" action="" method="post" enctype="multipart/form-data">
						<input type="hidden" id="pageUrl">
						<div class="parent-input-file" id="parent-input-file">							
							<div id="addimg_parent" class="cover-input-file">
								<input type="file" id="addimg" value="" name="files"/>					
							</div>							
						</div>												
					</form>	
					<span class="lable-symbol"></span>						
				</div>
			</div>				
		</div>
		<div id="pcImage" style="width: 0;height: 0;"></div>
	</div>
			<div class="modal-footer">			
				<button id="submit_modal" class="btn btn-success marginRight10">确定</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
	<script type="text/javascript">
		// 上传图片URL
		var uploadImageUrl = '<%=uploadImageUrl%>';
		var delImageUrl = '<%=basePath%>goodsClassification/deleteImageByIds.do?rnd=' + Math.random();
		var parentPicOBJ = window.parent;
		//上传图片ID
		var imageIds;
		var optionsFileAct = {
				 url:uploadImageUrl,
				 type: 'post',
				 beforeSubmit: checkinputeditFileAct,//根据id获得上传图片的val值,验证上传格式
				 data:{
					 is_source:'pc',//来源pc
				 },
				 success: function (data) {	
					 imageIds = data.data[0];								  
				    if(imageIds!=null){				    	
				    	$('#parent-input-file').children('div[name="add"]').last().attr('imageIds',imageIds);
				    	$("#imgForm").next().text("");
				    }
			     },		
				 error:function(){
					 parentPicOBJ.$.showSuccessTimeout("上传图片失败,系统错误");
				 }
		};
		
		//验证图片上传格式
		function  checkinputeditFileAct(){
			var fileName = $("#addimg").val();			
			if(fileName==""){
				parentPicOBJ.$.showSuccessTimeout("请选择图片!");
				return false;
			}
			var end = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length);
			end = end.toLowerCase();
			if( !(end=='jpg'||end=='jpeg'||end=='gif'||end=='png'||end=='bmp') )
			{
				parentPicOBJ.$.showSuccessTimeout("上传图片格式错误!");
				return false;
			}
			return true;
		}
		//添加图片
		var imagesCode = [];
		$("input[type='file']").change(function(){   
			 var file = this.files[0];
			   if (window.FileReader) {    
			        var reader = new FileReader();    
			        reader.readAsDataURL(file);    
			        //监听文件读取结束后事件    
			        reader.onloadend = function (e) {
			        	imagesCode.push(e.target.result);
			        	//限制图片数量1
			        	var num = $("#parent-input-file").children().length;
						if(num >= 1){
							$("#addimg_parent").hide();				        	
						}
						
			        	var imgCode = '<div name="add" class="cover-input-file">'+
							'<img alt="图片" src="'+e.target.result+'"/>'+
							 '<i class="fa fa-times dele-input-file" aria-hidden="true"></i>'+ 
							'</div>';						       	
			        	$("#parent-input-file").children(':last-child').before(imgCode);
			        	//========自动上传图片==========		
			    		$("#imgForm").ajaxSubmit(optionsFileAct);			    		
			         };    
			    } 
			});
		$("#parent-input-file").on('click','.dele-input-file',function(){
			var ids = $(this).parent().attr('imageIds');
			$("#addimg").val('');
			if(ids != "" && ids != undefined){
				$.post(delImageUrl,{imageIds:ids});					
			}		
			$("#addimg_parent").show();
			$(this).parent().remove();	
		});
	</script>
	<script>
		$(function() {
			var checkUserNameURL = '<%=basePath%>user/checkSameUserName.do?rnd=' + Math.random();
			var checkLoginNameURL = '<%=basePath%>user/checkSameLoginName.do?rnd=' + Math.random();
			var checkEmailURL = '<%=basePath%>user/checkSameEmailName.do?rnd=' + Math.random();
			var getImageUrl = '<%=getImageUrl%>'.trim();
			//初始化角色下拉框
			$.ajax({
				url : '<%=basePath%>role/getRoleList.do?rnd=' + Math.random(),
				type : "post",
				success : function(data) {
					//console.log(data.resultList);
					var html_roles = "";
					for(var i=0;i<data.resultList.length;i++){
						html_roles += '<li>'+
							'<input class="checkbox-role" type="checkbox" ids="'+data.resultList[i].id+'" value="'+data.resultList[i].role_name+'"><span>'+data.resultList[i].role_name+'</span>'+
							'</li> ';
					}
					$(".controls-ul").html(html_roles);
				},
				error : function() {
					console.error("error");
				}
			});
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data);
			
			var role_id='';
			var imagesIds = json_data.image_ids;
			var imagesUrls = json_data.image_urls;
			var imageCode = "";
			var imageCodeSee = "";			
			var picsNum = 0;
			try{
				picsNum = imagesUrls.split(',').length;
				for(var i=0;i<picsNum;i++){
					imageCode += '<div name="add" class="cover-input-file" imageIds="'+imagesIds.split(',')[i]+'">'+
						'<img alt="图片" src="'+getImageUrl+imagesUrls.split(',')[i]+'"/>'+
						 '<i class="fa fa-times dele-input-file" aria-hidden="true"></i>'+ 
						'</div>';
					imageCodeSee += '<div name="add" class="cover-input-file">'+
						'<img alt="图片" src="'+getImageUrl+imagesUrls.split(',')[i]+'"/>'+						
						'</div>';
				}
			}catch (e) {
				// TODO: handle exception
			}
			if(json_data.edit == 1){
				$("#title_text").text("查看系统用户");
				$("#submit_modal").hide();
				$('input').each(function(){
					$(this).attr("disabled","disabled");											
					$("#pic-submit-info").hide();
				});
				$(".lable-symbol").each(function(){
					$(this).hide();
				});	
				$("#parent-input-file").html(imageCodeSee);					
			}else if(json_data.edit == 2){				
				$("#title_text").text("编辑系统用户");
				if(picsNum >=1){
					$("#parent-input-file").prepend(imageCode);
					$("#addimg_parent").hide();
				}
			}
			role_id = json_data.role_id;
			$("#roles").val(json_data.role_name);
			$("#roles").attr('rolesIds',json_data.role_id);
			$("#user_name").val(json_data.user_name);
			$("#password").val(json_data.password);
			$("#login_name").val(json_data.login_name);
			$("#email").val(json_data.email);	
				
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			
			//校验用户名是否存在
			$("#user_name").change(function(){
					var userName = $("#user_name").val();
					if(userName ==""){
						$("#user_name").siblings('*:last').text("用户名不能为空！");
						return;
					}
					ajaxUserName(userName);//校验用户					
			});
			//校验登录名是否存在
			$("#login_name").change(function(){				
					var loginName = $("#login_name").val();
					if(loginName ==""){
						$("#login_name").siblings('*:last').text("登录名不能为空！");
						return;
					}
					ajaxLoginName(loginName);//校验登录名
			});
			//校验邮箱是否存在
			$("#email").change(function(){	
				var email = $("#email").val();
				if(email ==""){
					$("#email").siblings('*:last').text("邮箱不能为空！");
					return;
				}else{
					$("#email").siblings('*:last').text("");
				}
				
				var ckEmail = $(this).checkEmail();
				if(ckEmail != "sss"){
					$("#email").siblings('*:last').text(ckEmail);
					return;
				}
				ajaxEmial(email);//校验
			});
			
			//保存提交
			$("#submit_modal").click(function() {
				var user_name = $("#user_name").val();
				var user_pwd = $("#password").val();
				var md5PWD = "";
				if(user_pwd != json_data.password){
					md5PWD = hex_md5(user_pwd).toUpperCase()
				}else{
					md5PWD = user_pwd;
				}
				var login_name = $("#login_name").val();
				var email = $("#email").val();				
				//图片
				var pics_array = [];
				$("#parent-input-file").children('div[name="add"]').each(function(){
					pics_array.push($(this).attr('imageIds'));
				});					
				var imageIds  = pics_array.join(',');//藏品图片id		
				var roleIds = $("#roles").attr('rolesIds');	
				var roleNames = $("#roles").val();
				//校验合法
				var ckUserTxt = $("#user_name").siblings('*:last').text();
				var ckLoginTxt = $("#login_name").siblings('*:last').text();
				var ckEmailTxt = $("#email").siblings('*:last').text();
				if(ckUserTxt != ''){
					$("#user_name").siblings('*:last').text("校验失败");
				}if(ckLoginTxt!=''){
					$("#login_name").siblings('*:last').text("校验失败");
				}if(ckEmailTxt!=''){
					$("#email").siblings('*:last').text("校验失败");
				}if(ckEmailTxt!='' || ckUserTxt != '' || ckLoginTxt!=''){
					return;
				}
				//校验空
				if(user_name ==""){
					$("#user_name").siblings('*:last').text("用户名不能为空！");
				}else{
					$("#user_name").siblings('*:last').text("");
				}
				if(user_pwd == ""){
					$("#password").siblings('*:last').text("密码不能为空！");
				}else{
					$("#password").siblings('*:last').text("");
				}
				if(login_name == ""){
					$("#login_name").siblings('*:last').text("登录名不能为空！");
				}else{
					$("#login_name").siblings('*:last').text("");
				}
				if(email == ""){
					$("#email").siblings('*:last').text("邮箱不能为空！");
				}else{
					$("#email").siblings('*:last').text("");
				}
				if(roleNames == ""){
					$("#roles").siblings('*:last').text("请选择用户角色！");
				}else{
					$("#roles").siblings('*:last').text("");
				}
				if(imageIds == ""||imageIds==undefined){
					$("#imgForm").next().text("请上传图片！");
				}else{
					$("#imgForm").next().text("");
				}
				
				if(user_name =="" || user_pwd == "" || login_name == "" || email == "" || roleNames == ""
						||imageIds == ""||imageIds==undefined ){
					return;
				}
							
				
				$.ajax({
					url : '<%=basePath%>user/saveOrUpdateUser.do?rnd=' + Math.random(),
					type : "post",
					data : {
						userId : json_data.id==undefined?"":json_data.id,
						userName : encodeURI(user_name),
						userPwd : md5PWD,
						loginName : encodeURI(login_name),
						email : email,
						roleIds : roleIds,
						imageIds:imageIds
					},
					success : function(data) {
						parentOBJ.resfreshTable();
					},
					error : function() {
						console.error("error");
						parentOBJ.resfreshTable();
					}
				});
				
				parentOBJ.modalIn();//关闭
				
			});
			
			
			$(".role_down").each(function(){
				$(this).click(function(){
					$(".controls-ul").slideToggle();
					if(role_id != undefined){								
						for(var i=0;i<role_id.split(",").length;i++){												
							$(".controls-ul").find('input').each(function(){					
								if($(this).attr("ids") == role_id.split(",")[i]){
									$(this).attr("checked",true);
								}
							});
						}
					}			
				});
			});
			
								
			$(".controls-ul").on('change',".checkbox-role",function(){
				var checkbox_txt="";
				roleIds = "";
				$(".checkbox-role").each(function(){
					if($(this).is(":checked")){
						checkbox_txt += $(this).val()+",";
						roleIds += $(this).attr('ids')+",";
					}
				});
				if(checkbox_txt.substring(0,checkbox_txt.length-1) != ''){
					$("#roles").siblings('*:last').text('');
				}
				$("#roles").val(checkbox_txt.substring(0,checkbox_txt.length-1));	
				$("#roles").attr('title',checkbox_txt.substring(0,checkbox_txt.length-1));	
				$("#roles").attr('rolesIds',roleIds.substring(0,roleIds.length-1));
			});
			//校验数据库
			function ajaxUserName(userName){				
				$.ajax({
					url: checkUserNameURL,
					type:"post",
					dataType: 'json',
					data:{
						userId: json_data.id ==undefined?"":json_data.id,
						userName: encodeURI(userName,'utf-8')							
					},
					success:function(data){	
						//result 0:校验通过,1:存在重复，校验不通过2:用户名不能为空3:系统异常
						if(data==0){													
							$("#user_name").siblings('*:last').text('');							
						}else{							
							var errorMsg = '';						
							if(data==1){
								errorMsg = '用户名已存在！'
							}else if(data==2){
								errorMsg = '用户名不能为空！';
							}
							$("#user_name").siblings('*:last').text(errorMsg);
						}							
					},
					error:function(){
						console.error("校验失败");							
					}
				});
			}
			function ajaxLoginName(loginName){				
				$.ajax({
					url: checkLoginNameURL,
					type:"post",
					dataType: 'json',
					data:{
						userId: json_data.id ==undefined?"":json_data.id,
						loginName: encodeURI(loginName,'utf-8')							
					},
					success:function(data){									
						if(data==0){
							$("#login_name").siblings('*:last').text('');								
						}else{
							var errorMsg = '';						
							if(data==1){
								errorMsg = '登录名已存在！'
							}else if(data==2){
								errorMsg = '登录名不能为空！';
							}
							$("#login_name").siblings('*:last').text(errorMsg);
						}							
					},
					error:function(){
						console.error("校验失败");							
					}
				});
			}
			function ajaxEmial(userEmail){
				$.ajax({
					url: checkEmailURL,
					type:"post",
					dataType: 'json',
					data:{
						userId: json_data.id ==undefined?"":json_data.id,
						userEmail: encodeURI(userEmail,'utf-8')							
					},
					success:function(data){									
						if(data == 0){	
							$("#email").siblings('*:last').text('');		
						}else{
							var errorMsg = '';					
							if(data==1){
								errorMsg = '邮件已存在！'
							}else if(data==2){
								errorMsg = '邮件不能为空！';
							}				
							$("#email").siblings('*:last').text(errorMsg);
						}							
					},
					error:function(){
						console.error("校验失败");							
					}
				});
			}
			
			$("#password").change(function(){
				if($("#password").val() != ''){
					$("#password").siblings('*:last').text('');
				}
			});
			$('input').bind('input propertychange',function(){
				if($(this).val() != ''){
					$(this).siblings('*:last').text('');
				}
			});
			
		});
	</script>
</body>
</html>
