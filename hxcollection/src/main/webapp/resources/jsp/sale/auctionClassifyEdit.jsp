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
<title>拍卖分类配置新增/编辑</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>

<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>

</style>
</head>
<body>
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增拍卖树类别</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div id="role_list" class="form-div-edit">
			<div class="control-group">	
				<div class="controls">
					<span class="control-label">名称</span>					 
					 <input id="name" type="text"  class="input-text">		
					  <div class="lable-symbol-div"></div>			
				</div>
				<div class="controls">
					<span class="control-label">上级分类</span>					 
					 <input id="pre_name" type="text"  class="input-text" readonly="readonly">	
					  <div class="lable-symbol-div"></div>							
				</div>			
			</div>
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">code</span>				
					 <input id="classCode" type="text" class="input-text">	
					  <div class="lable-symbol-div"></div>				
				</div>			
				<div class="controls">
					<span class="control-label">排序</span>					
					 <input id="sort" type="text"  class="input-text">	
					  <div class="lable-symbol-div"></div>			 
				</div>				
			</div>
			<div class="control-group">	
				<div class="controls">
					<span class="control-label">描述</span>					
					<input id="content" type="text" class="input-text">		
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
					<div class="lable-symbol-div"></div>										 											
				</div>										
			</div>
			
		</div>
	<div id="pcImage" imageIds="" style="width: 0;height: 0;"></div>
	</div>
			<div class="modal-footer">			
				<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
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
			var saveDealURL = '<%=basePath%>goodsClassification/saveOrUpdateSaleClass.do?rnd=' + Math.random();
			var checkExistURL = '<%=basePath%>goodsClassification/checkSameSaleClassName.do?rnd=' + Math.random();
			var checkCodeExistURL = '<%=basePath%>goodsClassification/checkSameSaleClassCode.do?rnd=' + Math.random();
			var getImageUrl = '<%=getImageUrl%>'.trim();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			//初始化
			$("#name").val(json_data.name);
			$("#classCode").val(json_data.classCode);
			$("#pre_name").val(json_data.parentCode);
			$("#content").val(json_data.decribe);
			$("#sort").val(json_data.sort);
			
			var imagesIds = json_data.imageIds;
			var imagesUrls = json_data.imageUrls;
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
			if(json_data.edit == 'eye'){
				$("#title_text").text("查看类别");							        	
        		$("#parent-input-file").html(imageCode);
        		$("#submit_modal").hide();
        		$('input').each(function(){
        			$(this).attr("disabled","disabled");       			
        		});
        		$(".lable-symbol").each(function(){
        			$(this).hide();
        		});
			}else if(json_data.edit == 'edit'){//编辑
				$("#title_text").text("编辑类别");	
				if(picsNum >=1){
					$("#parent-input-file").prepend(imageCode);
					$("#addimg_parent").hide();
				}
        		
			}else if(json_data.edit == "lower"){//添加下级
				$("#modal-title").text("添加下级类别");			
			}else if(json_data.edit == "new"){//新增
				$("#modal-title").text("新增拍卖类别");	
				$("#pre_name").val("0");
			}			
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//校验排序
			$('#sort').bind('input propertychange', function() {			
			    $(this).val($(this).val().replace(/[^\d]/g,''));
			});
			$('input').bind('input propertychange',function(){
				if($(this).val() != ""){
					$(this).siblings('*:last').text('');
				}
			});
			//校验是否存在
			$("#name").bind('change',function(){
					var params = {};		
					params.class_name = encodeURI($("#name").val(),'utf-8');
					if(json_data.edit == "lower" || json_data.edit == "new"){
						params.class_id = "";
					}else{
						params.class_id = json_data.classId == undefined?"":json_data.classId;
					}
					if(json_data.edit == "lower"  || json_data.edit == "edit"){
						params.parent_code = json_data.parentCode+'';
					}
					if(json_data.edit == "new"){
						params.parent_code = "0";
					}
					if(params.class_name == ""){
						$("#name").siblings('*:last').text("分类名称不能为空！");				
						return;
					}
					$.ajax({
						url: checkExistURL,
						type:"post",
						dataType: 'json',
						data:{
							paramsStr : JSON.stringify(params) //string类型
						},
						success:function(data){										
							if(data){
								$("#name").siblings('*:last').text("");						
							}else{
								$("#name").siblings('*:last').text("校验失败！");
							}
							
						},
						error:function(){
							console.error("校验失败");							
						}
					});
					return;
			});
			//校验code是否存在	
			$('#classCode').bind('change', function() {			
				var params = {};		
				params.class_code = encodeURI($("#classCode").val(),'utf-8');
				if(params.class_code == ""){
					$("#classCode").siblings('*:last').text("code不能为空！");
					return;
				}
				if(json_data.edit == "lower" || json_data.edit == "new"){
					params.class_id = "";
				}else{
					params.class_id = json_data.classId == undefined?"":json_data.classId;
				}
				if(json_data.edit == "lower"  || json_data.edit == "edit"){
					params.parent_code = json_data.parentCode+'';
				}
				if(json_data.edit == "new"){
					params.parent_code = "0";
				}
				
				$.ajax({
					url: checkCodeExistURL,
					type:"post",
					dataType: 'json',
					data:{
						paramsStr : JSON.stringify(params) //string类型
					},
					success:function(data){										
						if(data){							
							$("#classCode").siblings('*:last').text("");
							return;
						}else{
							$("#classCode").siblings('*:last').text("校验失败！");
						}
						
					},
					error:function(){
						console.error("校验失败");							
					}
				});
			});
			//保存提交
			$("#submit_modal").click(function() {					
					var subData = {};
					subData["class_id"] = json_data.classId == undefined?"":json_data.classId;
					if(json_data.edit == "lower" || json_data.edit == "new"){
						subData["class_id"] = "";
					}
					if(json_data.edit == "lower"  || json_data.edit == "edit"){
						subData["parent_code"] = json_data.parentCode;
					}
					if(json_data.edit == "new"){
						subData["parent_code"] = "0";
					}
					subData["class_code"] = $("#classCode").val();
					subData["class_name"] = $("#name").val();
					
					subData["sort"] = $("#sort").val(); 
					subData["describe"] =  $("#content").val();
					//图片
					var pics_array = [];
					$("#parent-input-file").children('div[name="add"]').each(function(){
						pics_array.push($(this).attr('imageIds'));
					});					
					subData["image_ids"] = pics_array.join(',');//藏品图片id										
					
					if(subData.class_name == "" ){
						$("#name").siblings('*:last').text("分类名称不能为空！");
					}else{
						$("#name").siblings('*:last').text("");
					} 
					if(subData.class_code == "" ){
						$("#classCode").siblings('*:last').text("code不能为空！");
					}else{
						$("#classCode").siblings('*:last').text("");
					} 
					if(subData.sort == ""){
						$("#sort").siblings('*:last').text("排序不能为空！");
					}else{
						$("#sort").siblings('*:last').text("");
					} 
					if(subData.describe==""){
						$("#content").siblings('*:last').text("描述不能为空！");
					}else{
						$("#content").siblings('*:last').text("");
					} 
					if(subData.image_ids==""){
						$("#imgForm").next().text('请上传图片！');
					}else{
						$("#imgForm").next().text("");
					} 
					
					if(subData.class_name==""||subData.class_code==""||subData.sort == ""||subData.describe==""||subData.image_ids==""){
						return;
					}
				
					$.ajax({
						url: saveDealURL,
						type:"post",
						data:subData,
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
					
		});
	</script>
</body>
</html>
