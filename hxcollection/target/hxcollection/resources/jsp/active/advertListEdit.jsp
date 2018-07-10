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
<title>新增广告帖</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" type="text/css" href="../../css/common_menu.css">
<link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
	.modal-container{
	    overflow-y: scroll;
		padding: 0 20px 0 20px !important;
	    height: 82%;
	}
	.label-select-oneline {
	    width: 18% !important;
	    text-align: left !important;
	}
	.select-style {
    	width: 60% !important;
    }
</style>
</head>
<body>
	<!-- 新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增广告位</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">广告标题</span>
					 <input id="ad_title" type="text"  class="input-text" >
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>	
				</div>
				<div class="controls">
					<span class="control-label">关联类型</span>					
					 <select id="service_type" class="form-control select-style">
						<option value="">--请选择--</option>
					
					</select>
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>	
				</div>									
			</div>
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">关联ID</span>
					 <select id="service_id" class="form-control select-style" disabled="disabled">
						<option value="">--请选择--</option>
						
					</select>
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>	
				</div>
				<div class="controls">
					<span class="control-label">链接</span>
					 <input id="url" type="text" class="input-text" disabled="disabled">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>	
				</div>
			</div>	
			<div class="area-text-parent">
					<span class="control-label">广告描述</span>
					 <textarea id="ad_content" rows="3" cols="20"  class="area-text"></textarea>
					 <span class="lable-symbol">*</span>	
					 <div class="lable-symbol-div"></div>			
			</div>				
				<div class="marginTop20">				
					<span class="control-label">上传图片</span>
					<form id="imgForm" class="inputBox" action="" method="post" enctype="multipart/form-data">
						<input type="hidden" id="pageUrl">
						<div class="parent-input-files" id="parent-input-files">						
							<div class="cover-input-files">
								<input type="file" id="addimg" value="" name="files"/>
							</div>
							
						</div>												
					</form>	
					<span class="lable-symbol">*</span>
					<div class="lable-symbol-div"></div>							 				
			</div>
			<div class="control-group button-group">			
				<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
		</div>

	<div id="pcImage"  imageIds="" style="width: 0;height: 0;"></div>
	</div>
	<script type="text/javascript">
		// 上传图片URL
		var uploadImageUrl = '<%=uploadImageUrl%>';
		var delImageUrl = '<%=basePath%>advertisement/deleteImageByIds.do?rnd=' + Math.random();
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
				    	var images = $("#pcImage").attr("imageIds");
				    	$("#pcImage").attr("imageIds",images+","+imageIds);
				    	$("#imgForm").next().text('');
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
			        	$("#parent-input-files").find('div[class="cover-input-file"]').remove();
			        	var imgCode = '<div class="cover-input-files">'+
							'<img alt="图片" src="'+e.target.result+'"/>'+
							 '<i class="fa fa-times dele-input-file" aria-hidden="true"></i>'+ 
							'</div>';						       	
			        	$("#parent-input-files").children(':last-child').before(imgCode);
			        	
			        	//========自动上传图片==========		
			    		$("#imgForm").ajaxSubmit(optionsFileAct);			    		
			         };    
			    } 
			});
		$("#parent-input-files").on('click','.dele-input-file',function(){
			var ids = $("#pcImage").attr("imageIds");
			if(ids != "" && ids != undefined){
				$.post(delImageUrl,{imageIds:ids});
			}			
			$(this).parent().remove();
			$("#pcImage").attr("imageIds",'');
		});
	</script>
	<script>
		$(function() {				
			var saveAdvertURL = '<%=basePath%>advertisement/saveOrUpdateAdver.do?rnd=' + Math.random();
			var queryTypeUrl = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random();
			var getImageUrl = '<%=getImageUrl%>'.trim();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)
			
			//初始化
			$("#ad_title").val(json_data.homepageName);		
			$("#ad_content").val(json_data.decribe);
			$("#url").val(json_data.htmlUrl);
			
			var imagesIds = json_data.imageIds;
			var imagesUrls = json_data.imageUrls;
			var imageCode = "";
			var imageCodeSee = "";
			try{
				for(var i=0;i<imagesUrls.split(',').length;i++){
					imageCode += '<div class="cover-input-files">'+
						'<img alt="图片" src="'+getImageUrl+imagesUrls.split(',')[i]+'"/>'+
						 '<i class="fa fa-times dele-input-file" aria-hidden="true"></i>'+ 
						'</div>';
					imageCodeSee += '<div class="cover-input-files">'+
						'<img alt="图片" src="'+getImageUrl+imagesUrls.split(',')[i]+'"/>'+						
						'</div>';
				}
			}catch (e) {
				// TODO: handle exception
			}
			
			if(json_data.edit=="new"){
				//新增
				$("#title_text").text("新增广告");
				
			}else if(json_data.edit == 1){
				//查看
				$("#title_text").text("查看广告");
				$("#submit_modal").hide();
				
				$('input').each(function(){
					$(this).attr("disabled","disabled");				
				});
				$('select').each(function(){
					$(this).attr("disabled","disabled");					
				});
				$('textarea').each(function(){
					$(this).attr("disabled","disabled");					
				});
				$(".lable-symbol").each(function(){
        			$(this).hide();
        		});
				$("#parent-input-files").html(imageCodeSee);
			}else if(json_data.edit == 2){
				//编辑
				$("#title_text").text("编辑广告");
				$("#parent-input-files").prepend(imageCode);
				$("#pcImage").attr('imageIds',imagesIds);
				
				if($("#service_type option:selected").text() == "外网"){
					$("#service_id").attr('disabled','disabled');
					$("#url").attr('disabled',false);
				}else{
					$("#url").attr('disabled','disabled');
					$("#service_id").attr('disabled',false);
				}
			}
			
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//保存提交
			$("#submit_modal").click(function() {
				var params = {};
				
				params.id= json_data.id == undefined?'':json_data.id;
				if(json_data.edit == 'new'){
					params.id = '';
				}
				params.homepageName= $("#ad_title").val();
				params.htmlUrl=  $("#url").val();
				params.sort=   '';
				params.serviceType=  $("#service_type").val();
				params.serviceId= $("#service_id option:selected").text();
				params.createTime= '';//给空就行
				params.updateTime= '';//给空就行
				params.delFlag= '';//给空就行
				params.imageUrls= '';//给空就行
				params.imageIds=  imageIds ;
				
				console.log(params);
				if(params.homepageName == ""){
					$("#ad_title").siblings('*:last').text("分类名称不能为空！");				
					return;
				}else if(params.serviceType == ""){
					$("#service_type").siblings('*:last').text("请选择关联类型！");	
					return;
				}else if($("#service_type option:selected").text() == "外网" && params.htmlUrl == ""){
					$("#url").siblings('*:last').text("链接不能为空！");	
					return;
				}else if($("#service_type option:selected").text() != "外网" && params.serviceId == ""){
					$("#service_id").siblings('*:last').text("请选择关联id！");
					return;
				}else if(params.imageIds == ''){
					$("#imgForm").next().text('请选择图片！');
					return;
				}
								
				$.ajax({
					url: saveAdvertURL,
					type:"post",
					data:{
						 paramsStr:JSON.stringify(params) 
					},
					success:function(data){
						console.log("success");
						parentOBJ.resfreshWindow();
					},
					error:function(){
						console.error("提交失败");
						parentOBJ.resfreshWindow();
					}
				});
								
				parentOBJ.modalIn();//关闭
				
			});
			//关联类型
			$.ajax({
				url: queryTypeUrl,
				type: "post",
				data:{
					key: 'advertisement_type',
					isAll:false
				},
				success: function(data){			
					var rows = data.resultList;
					var typeCode = "";
					var typeId ="";
					for(var i=0;i<rows.length;i++){
						if(rows[i].dic_value == json_data.serviceType){
							typeCode += '<option value="'+rows[i].dic_value+'" selected="selected">'+rows[i].dic_name+'</option>';
						}else{
							typeCode += '<option value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
						}
						if(rows[i].dic_value == json_data.serviceId){
							typeId += '<option value="'+rows[i].dic_value+'" selected="selected">'+rows[i].id+'</option>';
						}else{
							typeId += '<option value="'+rows[i].dic_value+'">'+rows[i].id+'</option>';
						}				
					}
					$("#service_type").append(typeCode);
					$("#service_id").append(typeId);
				},
				error: function(){
					console.error("请求失败");
				}
			});
			//校验
			$('input').bind('input propertychange',function(){
				if($(this).val() != ""){
					$(this).siblings('*:last').text('');
				}
			});
			
			
			$("#service_type").bind('change',function(){
				if($("#service_type option:selected").text() == "外网"){
					$("#service_id").attr('disabled','disabled');
					$("#url").attr('disabled',false);
				}else{
					$("#url").attr('disabled','disabled');
					$("#service_id").attr('disabled',false);
				}
			});
			 
		});
	</script>
</body>
</html>
