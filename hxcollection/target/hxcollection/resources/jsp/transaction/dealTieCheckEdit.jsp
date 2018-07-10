<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cn.com.hxfz.model.User"%>
<%@page import="net.sf.json.JSONArray"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String uploadImageUrl = Configuration.getInstance().getValue("upload_image_url");
	String getImageUrl = Configuration.getInstance().getValue("get_image_url");
	User user = (User) request.getSession().getAttribute("user");
	String name = user.getUserName();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>编辑交易帖</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" type="text/css" href="../../css/common_menu.css">
<link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
	.modal-container{
	    overflow-y: scroll;
		padding: 0 20px 0 20px !important;
	    height: 86%;
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
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">编辑交易帖</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">用户</span>
					 <input id="userID" type="text" userId=""  class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">商品名称</span>
					 <input id="goodsName" type="text"  class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">省级城市</span>
		     		<select id="province_code" name="province_code" class="form-control select-style">	
		     			<option  value="">--请选择--</option>     
		     		</select>
		     		<span class="lable-symbol">*</span>	
		     		<div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="marginTop10 controls">
		     		<span class="label-select-oneline">市级城市</span>
		     		<select id="city_code" name="city_code" class="form-control select-style">	
		     		</select>
		     		<span class="lable-symbol">*</span>	
		     		<div class="lable-symbol-div"></div>
		     	</div>  
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">所属大类</span>
		     		<select id="bigClazz" name="bigClazz" class="form-control select-style">	
		     			<option  value="">--请选择--</option>     				     			
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="marginTop10 controls">
		     		<span class="label-select-oneline">所属小类</span>
		     		<select id="smallClazz" name="smallClazz" class="form-control select-style">	
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">交易价格</span>
					 <input id="price" type="text" class="input-text input_number_ck" >
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">邮费</span>
					 <input id="postage" type="text"  class="input-text input_number_ck">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">是否推荐</span>
		     		<select id="isRecomend" name="bigClazz" class="form-control select-style">	
		     		</select>
		     		<span class="lable-symbol">*</span>	
		     		<div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="controls">
					<span class="control-label">推荐排序</span>
					 <input id="recomend_sort" type="text"  class="input-text input_number_ck" disabled="disabled">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>	
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">库存</span>
					 <input id="inventory" type="text" class="input-text input_number_ck">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>		
			<div class="area-text-parent">
					<span class="control-label">帖子内容</span>
					 <textarea id="goodsDescription" rows="3" cols="20"  class="area-text"></textarea>
					 <span class="lable-symbol">*</span>	
					 <div class="lable-symbol-div"></div>		
			</div>
			
			<div class="control-group-oneline marginTop20">				
					<span class="control-label">商品图片</span>		
					<form id="imgForm" class="inputBox" action="" method="post" enctype="multipart/form-data">
						<input type="hidden" id="pageUrl">
						<div class="parent-input-files" id="parent-input-files">						
							<div class="cover-input-files">
								<input type="file" id="addimg" value="" name="files"/>
							</div>
							
						</div>	
						<span class="lable-symbol">*</span>
						<div class="lable-symbol-div"></div>											
					</form>	
								 				
			</div>
			
			<div class="control-group button-group">			
				<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
		</div>
		<div id="pcImage" imageIds="" style="width: 0;height: 0;"></div>
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
				    	var images = $("#pcImage").attr("imageIds");
				    	$("#pcImage").attr("imageIds",images+","+imageIds);
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
			        	$("#parent-input-files").prepend(imgCode);
			        	
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
		});
	</script>
	<script>
		$(function() {	
			var username = '<%=name%>';
			var queryBigClazzUrl = '<%=basePath%>goodsClassification/getTransClassifiyRecords.do?rnd=' + Math.random();
			var saveGoodsURL = '<%=basePath%>goods/saveOrUpdateTransGoods.do?rnd=' + Math.random();
			var yesOnURL = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random();
			var queryAreaInfoUrl = '<%=basePath%>goods/queryAreaInfo.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			var getImageUrl = '<%=getImageUrl%>'.trim();
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)
			
			if(json_data.edit=="callback"){
				$("#user_id").val(json_data.callTxt.split(',')[0]);		
				$("#userID").attr("userId",json_data.callTxt.split(',')[1]);
			}
			
			$("#userID").val(json_data.nickName);
			$("#userID").attr("userId",json_data.userId);
			$("#goodsDescription").val(json_data.goodsDescription);
			$("#goodsName").val(json_data.goodsName);
			$("#inventory").val(json_data.inventory);
			$("#price").val(json_data.price);
			$("#postage").val(json_data.postage);
			$("#publishTime").val(json_data.publishTime);
			var isRecomend = json_data.isRecommend;
			var imagesIds = json_data.imageIds;
			var imagesUrls = json_data.imageUrls;
			var imageCode = "";
			var imageCodeSee = "";
			var isRecomendName= "";
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
							
			if(json_data.edit == 1){
				$("#title_text").text("查看交易帖");
				$(".lable-symbol").each(function(){
					$(this).hide();
				});
				$("#parent-input-files").html(imageCodeSee);
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
				if(isRecomend == 1){
					$("#isRecomend").html('<option>是</option>');
					$("#recomend_sort").val(json_data.sort);
				}else{
					$("#isRecomend").html('<option>否</option>');
					$("#recomend_sort").parent().hide();
				}
			}else if(json_data.edit == 2){
				$("#title_text").text("编辑交易帖");
				$("#parent-input-files").prepend(imageCode);
				$("#pcImage").attr('imageIds',imagesIds);
			}
			//关闭
			$("#close_modal").click(function() {
				parentOBJ.modalIn();
			});
			$("#close_dialog").click(function() {
				parentOBJ.modalIn();
			});
			//初始化是否推荐
			$.ajax({
				url:yesOnURL,
				data:{
					key: 'yes_or_no',
					isAll: false
				},
				type:'post',
				dataType: 'json',
				success:function(data){
					var rows = data.resultList;
					var recomendCode = '';
					for(var i=0;i<rows.length;i++){
						if(json_data.isRecommend == rows[i].dic_value){
							recomendCode += '<option name="'+rows[i].dic_name+'" value="'+rows[i].dic_value+'" selected="selected">'+rows[i].dic_name+'</option>';
						}else{
							recomendCode += '<option name="'+rows[i].dic_name+'" value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
						}
					}
					$("#isRecomend").html(recomendCode);
					var isRecommendTxt = $("#isRecomend option:selected").attr('name');
					if(isRecommendTxt == "是"){
						$("#recomend_sort").val(json_data.sort);
						$("#recomend_sort").attr("disabled",false);
					}else{
						$("#recomend_sort").val("");
						$("#recomend_sort").attr("disabled","disabled");
						$("#recomend_sort").siblings('*:last').text('');
					}
				},
				error:function(){
					console.error("初始化是否推荐失败!"); 
				}
			});
			$("#isRecomend").change(function(){		
				var isRecommendTxt = $("#isRecomend option:selected").attr('name');
				if(isRecommendTxt == "是"){
					$("#recomend_sort").attr("disabled",false);
				}else{
					$("#recomend_sort").val("");
					$("#recomend_sort").attr("disabled","disabled");
					$("#recomend_sort").siblings('*:last').text('');
				}
			});
			//保存提交
			$("#submit_modal").click(function() {
				var params = {};
				
				params.goodsId= json_data.goodsId; //商品id
				params.bigClassCode=  $("#bigClazz").val(); //大类
				params.smallClassVode= $("#smallClazz").val();  //小类
				params.userId= $("#userID").attr("userId");  //用户id
				params.nickName = $("#userID").val();  //用户昵称，空
				params.goodsType ='1';  //商品类型
				params.goodsName= $("#goodsName").val(); //商品名称
				params.goodsDescription= $("#goodsDescription").val();  //商品描述
				params.inventory= $("#inventory").val();  //库存
				params.price= $("#price").val(); //交易价格
				params.postage= $("#postage").val();  //邮费
				params.status=  ''; //状态，空
				params.firstStepTime = '';  //初次发布时间，空
				params.lastUpdateTime = '';  //最新更新时间，空
				params.browseNum= '';  //浏览次数，空
				params.publishCustomer= username;   //发布用户
				params.publishTime= $("#publishTime").val();  //发布时间
				params.startPrice= ''; //起拍价格，空
				params.markupPrice=  ''; //每手加价，空
				params.currentPrice= '';  //当前价格，空
				params.endTime= '';  //结拍时间，空
				params.isRecommend= $("#isRecomend").val();  //是否推荐，空
				params.sort= $("#recomend_sort").val();  //推荐排序，空
				params.imageIds= $("#pcImage").attr("imageIds");  //商品图片id
				params.imageUrls = '';   //商品图片urls
				params.provinceCode= $("#province_code").val();  //城市code
				params.cityCode= $("#city_code").val();  //城市code
				console.log(params)
				
				//判断空
				if(params.nickName==""){
					$("#userID").siblings('*:last').text('用户名不能为空！');
				}else{
					$("#userID").siblings('*:last').text('');
				} 
				if(params.goodsName==""){
					$("#goodsName").siblings('*:last').text('商品名称不能为空！');
				}else{
					$("#goodsName").siblings('*:last').text('');
				}
				if(params.inventory==""){
					$("#inventory").siblings('*:last').text('库存不能为空！');
				}else{
					$("#inventory").siblings('*:last').text('');
				}
				if(params.bigClassCode==""){
					$("#bigClazz").siblings('*:last').text('请选择大类');
				}else{
					$("#bigClazz").siblings('*:last').text('');
				}
				if(params.price==""){
					$("#price").siblings('*:last').text('交易价格不能为空！');				
				}else{
					$("#price").siblings('*:last').text('');
				}
				if(params.postage==""){
					$("#postage").siblings('*:last').text('邮费不能为空！');
				}else{
					$("#postage").siblings('*:last').text('');
				}
				if(params.isRecommend ==""){
					$("#isRecomend").siblings('*:last').text('请选择是否推荐');
				}else{
					$("#isRecomend").siblings('*:last').text('');
				}
				if(params.sort=="" && params.isRecommend=="1"){
					$("#recomend_sort").siblings('*:last').text('推荐排序不能为空！');
				}else{
					$("#recomend_sort").siblings('*:last').text('');
				}
				if(params.goodsDescription==""){
					$("#goodsDescription").siblings('*:last').text('商品描述不能为空！');
				}else{
					$("#goodsDescription").siblings('*:last').text('');
				}
				if(params.imageIds==""){
					$("#imgForm").next().text('请上传图片');
				}else{
					$("#imgForm").next().text('');
				}
				if(params.provinceCode==""){
					$("#province_code").siblings('*:last').text('省级城市不能为空！');				
				}else{
					$("#province_code").siblings('*:last').text('');
				}
				if(params.cityCode==""){
					$("#city_code").siblings('*:last').text('市级城市不能为空！');
				}else{
					$("#city_code").siblings('*:last').text('');
				}
				if(params.nickName==""||params.goodsName==""||params.inventory==""||params.bigClassCode==""
					||params.price==""||params.postage==""||params.isRecommend ==""
					||params.goodsDescription==""||params.imageIds==""||params.provinceCode==""||params.cityCode==""){
					return;
				}
				if(params.sort=="" && params.isRecommend=="1"){
					return;
				}
				
				$.ajax({
					url:saveGoodsURL,
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
			//大类
			var dataArray = [];
			$.ajax({
				url: queryBigClazzUrl,
				type: "post",
				data:{
					class_type:1,
					isAll:false
				},
				success: function(data){
					var rows = data.resultList;
					var bigCode ="";//大类
					var smallCode = "";//小类
					dataArray = rows;	
					for(var i=0;i<rows.length;i++){
						if(rows[i].parentCode == "0"){	
							if(rows[i].classCode == json_data.bigClassCode){
								//选中项
								bigCode += '<option value="'+rows[i].classCode+'" selected="selected">'+rows[i].className+'</option>';
							}else{
								bigCode += '<option value="'+rows[i].classCode+'">'+rows[i].className+'</option>';
							}
						}			
					}	
					$("#bigClazz").append(bigCode);
					var parentClass = $('#bigClazz option:selected').attr("value");
					if(parentClass != ""){
						$("#smallClazz").attr("disabled",false);
						for(var i=0;i<rows.length;i++){
							if(rows[i].parentCode == parentClass){
								if(rows[i].classCode == json_data.smallClassVode){
									smallCode += '<option value="'+rows[i].classCode+'" selected="selected">'+rows[i].className+'</option>';
								}else{
									smallCode += '<option value="'+rows[i].classCode+'">'+rows[i].className+'</option>';
								}
							}
						}
					}else{
						$("#smallClazz").html('<opiton value=""></option>');
						$("#smallClazz").attr("disabled","disabled");
					}
					$("#smallClazz").append(smallCode);
					
				},
				error: function(){
					console.error("请求失败");
				}
			});
			//大类小类联动事件	
			 $("#bigClazz").change(function() {			
				var smallCode = "";
				var currentClazz = $('#bigClazz option:selected').attr("value");
				if(currentClazz == ""){
					// 大类未选中时，小类不可编辑
					$("#small_class_code").html('<opiton value=""></option>');
					$("#smallClazz").attr("disabled","disabled");					
				}else{
					$("#smallClazz").attr("disabled",false);
				}
				for(var i=0;i<dataArray.length;i++){
					if(dataArray[i].parentCode == currentClazz){
						if(dataArray[i].classCode == json_data.smallClassVode){
							//选中项
							smallCode += '<option value="'+dataArray[i].classCode+'" selected="selected">'+dataArray[i].className+'</option>';
						}else{
							smallCode += '<option value="'+dataArray[i].classCode+'">'+dataArray[i].className+'</option>';
						}
					}
				}
				$("#smallClazz").html(smallCode);		
			 });
			
			 //校验input			  
			 $(".input_number_ck").bind('input propertychange',function(){
				 $(this).checkNumber();
			 });
			 $('input').bind('change',function(){
				 if($(this).val() != ""){
					 $(this).siblings('*:last').text('');
				 }
			 });
			 $('textarea').bind('change',function(){
				 if($(this).val() != ""){
					 $(this).siblings('*:last').text('');
				 }
			 });
			 $('select').bind('change',function(){
				 if($(this).val() != ""){
					 $(this).siblings('*:last').text('');
				 }
			 });
			 
			//城市
			var areaArray = [];
			$.ajax({
				url: queryAreaInfoUrl,
				type: "post",
				data:{
				},
				success: function(data){
					var rows = data.resultList;
					console.log(rows)
					var provinceCode ="";
					var cityCode ="";
					areaArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].parent == 1){
							if(rows[i].code == json_data.cityParentCode){
								//选中项
								provinceCode += '<option value="'+rows[i].id+'" selected="selected">'+rows[i].province+'</option>';
							}else{
								provinceCode += '<option value="'+rows[i].id+'">'+rows[i].province+'</option>';
							}
						}			
					}					
					$("#province_code").append(provinceCode);
					var currentPro = $('#province_code option:selected').attr("value");
					if(currentPro != ""){
						$("#city_code").attr("disabled",false);
						for(var i=0;i<rows.length;i++){
							if(rows[i].parent == currentPro){
								if(rows[i].code == json_data.cityCode){
									//选中项
									cityCode += '<option value="'+rows[i].id+'" selected="selected">'+rows[i].city+'</option>';
								}else{
									cityCode += '<option value="'+rows[i].id+'">'+rows[i].city+'</option>';
								}
							}
						}
					}else{
						$("#city_code").html('<opiton value=""></option>');
						$("#city_code").attr("disabled","disabled");
					}
					$("#city_code").append(cityCode);
				},
				error: function(){
					console.error("请求失败");
				}
			});
			//省级市级城市联动事件	
			 $("#province_code").change(function() {
				var cityCode = '';
				var currentPro = $('#province_code option:selected').attr("value");
				if(currentPro == ""){	
					// 省级城市未选中时，市级城市不可编辑
					$("#city_code").html('<opiton value=""></option>');
					$("#city_code").attr("disabled","disabled");					
				}else{
					$("#city_code").attr("disabled",false);
				}
				for(var i=0;i<areaArray.length;i++){
					if(areaArray[i].parent == currentPro){
						cityCode += '<option value="'+areaArray[i].code+'">'+areaArray[i].city+'</option>';
					}
				}
				$("#city_code").html(cityCode);		
			 });
				
		});
	</script>
</body>
</html>
