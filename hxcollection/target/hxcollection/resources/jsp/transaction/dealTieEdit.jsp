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
<title>新增交易帖</title>
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
	<!-- 新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">新增交易帖</div>
		<div id="close_dialog" class="pull-right close_table">X</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">用户</span>
					 <input id="user_id" type="text"  class="input-text" user_id="" style="cursor:pointer;">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">商品名称</span>
					 <input id="goods_name" type="text"  class="input-text">
					 <span class="lable-symbol">*</span>
					 <div class="lable-symbol-div"></div>
				</div>								
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">省级城市</span>
		     		<select id="province_code" name="province_code" class="form-control select-style">
		     			<option value="">--请选择--</option>		
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
		     		<select id="big_class_code" name="bigClazz" class="form-control select-style">	
		     			<option value="">--请选择--</option>		     			
		     		</select>
		     		<span class="lable-symbol">*</span>	
		     		<div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="marginTop10 controls">
		     		<span class="label-select-oneline">所属小类</span>
		     		<select id="small_class_code" name="smallClazz" class="form-control select-style">	
		     		</select>
		     		<span class="lable-symbol">*</span>	
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
		     			<option value="">--请选择--</option>		     			
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
					<span class="control-label">商品描述</span>
					 <textarea id="goods_description" rows="3" cols="20"  class="area-text"></textarea>
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
				    	$("#imgForm").next().text('*');
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
			$("#pcImage").attr("imageIds",'');
		});
	</script>
	<script>
		$(function() {	
			var username = '<%=name%>';
			var queryBigClazzUrl = '<%=basePath%>goodsClassification/getGoodsClass.do?rnd=' + Math.random();
			var saveGoodsURL = '<%=basePath%>goods/saveOrUpdateTransGoods.do?rnd=' + Math.random();
			var queryUrl = '<%=basePath%>generalUser/queryGeneralUserList.do?rnd=' + Math.random();
			var yesOnURL = '<%=basePath%>dictionary/getDicListByDicKey.do?rnd=' + Math.random();
			var queryAreaInfoUrl = '<%=basePath%>goods/queryAreaInfo.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			console.log(json_data)
			
			if(json_data.edit=="callback"){
				$("#user_id").val(json_data.callTxt.split(',')[0]);	
				$("#user_id").attr('user_id',json_data.callTxt.split(',')[1]);
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
					key: 'yes_or_no' ,
					isAll:false
				},
				type:'post',
				dataType: 'json',
				success:function(data){
					var rows = data.resultList;
					console.log(data);
					var recomendCode = '<option name="" value="">--请选择--</option>';
					for(var i=0;i<rows.length;i++){
						recomendCode += '<option name="'+rows[i].dic_name+'" value="'+rows[i].dic_value+'">'+rows[i].dic_name+'</option>';
					}
					$("#isRecomend").html(recomendCode);
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
					$("#recomend_sort").attr("disabled","disabled");
					$("#recomend_sort").siblings('*:last').text('');
				}
			});
			//保存提交
			$("#submit_modal").click(function() {
				var params = {};
							
				params.goodsId= ''; //商品id
				params.bigClassCode=  $("#big_class_code").val(); //大类
				params.smallClassVode= $("#small_class_code").val();  //小类			
				params.userId= $("#user_id").attr('user_id');  //用户id
				params.nickName = $("#user_id").val();  //用户昵称，空
				params.goodsType ='1';  //商品类型
				params.goodsName= $("#goods_name").val(); //商品名称
				params.goodsDescription= $("#goods_description").val();  //商品描述
				params.inventory= $("#inventory").val();  //库存
				params.price= $("#price").val(); //交易价格
				params.postage= $("#postage").val();  //邮费
				params.status=  ''; //状态，空
				params.firstStepTime = '';  //初次发布时间，空
				params.lastUpdateTime = '';  //最新更新时间，空
				params.browseNum= '';  //浏览次数，空
				params.publishCustomer= username;   //发布用户
				params.publishTime= $("#publish_time").val();  //发布时间
				params.startPrice= ''; //起拍价格，空
				params.markupPrice=  ''; //每手加价，空
				params.currentPrice= '';  //当前价格，空
				params.endTime= '';  //结拍时间，空
				params.provinceCode= $("#province_code").val();  //城市code
				params.cityCode= $("#city_code").val();  //城市code
				params.isRecommend= $("#isRecomend").val();  //是否推荐，空			
				params.sort= $("#recomend_sort").val(); //推荐排序，空
				params.imageIds= $("#pcImage").attr("imageIds");  //商品图片id
				params.imageUrls = '';   //商品图片urls
				console.log(params)
				
				var isRecommendName = $("#isRecomend option:selected").attr("name");
				//判断空
				if(params.nickName==""){
					$("#user_id").siblings('*:last').text('用户名不能为空！');
				}else{
					$("#user_id").siblings('*:last').text('');
				}
				if(params.goodsName==""){
					$("#goods_name").siblings('*:last').text('商品名称不能为空！');
				}else{
					$("#goods_name").siblings('*:last').text('');
				}
				if(params.inventory==""){
					$("#inventory").siblings('*:last').text('库存不能为空！');
				}else{
					$("#inventory").siblings('*:last').text('');
				}
				if(params.bigClassCode==""){
					$("#big_class_code").siblings('*:last').text('请选择大类');
				}else{
					$("#big_class_code").siblings('*:last').text('');
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
				if(params.sort=="" && isRecommendName=="是"){
					$("#recomend_sort").siblings('*:last').text('推荐排序不能为空！');
				}else{
					$("#recomend_sort").siblings('*:last').text('');
				}
				if(params.goodsDescription==""){
					$("#goods_description").siblings('*:last').text('商品描述不能为空！');
				}else{
					$("#goods_description").siblings('*:last').text('');
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
					||params.price==""||params.postage==""||params.isRecommend ==""||(params.sort=="" && isRecommendName=="是")
					||params.goodsDescription==""||params.imageIds==""||params.provinceCode==""||params.cityCode==""){
					return;
				}
				
				
				$.ajax({
					url: saveGoodsURL,
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
					var bigCode ="";
					var smallCode = "";
					dataArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].parent_code == 0){	
							bigCode += '<option value="'+rows[i].class_code+'">'+rows[i].class_name+'</option>';
						}			
					}					
					$("#big_class_code").append(bigCode);
					$("#small_class_code").attr("disabled","disabled");
				},
				error: function(){
					console.error("请求失败");
				}
			});
			//大类小类联动事件	
			 $("#big_class_code").change(function() {
				var smallCode = '';
				var currentClazz = $('#big_class_code option:selected').attr("value");
				if(currentClazz == ""){	
					// 大类未选中时，小类不可编辑
					$("#small_class_code").html('<opiton value=""></option>');
					$("#small_class_code").attr("disabled","disabled");					
				}else{
					$("#small_class_code").attr("disabled",false);
				}
				for(var i=0;i<dataArray.length;i++){
					if(dataArray[i].parent_code == currentClazz){
						smallCode += '<option value="'+dataArray[i].class_code+'">'+dataArray[i].class_name+'</option>';
					}
				}
				$("#small_class_code").html(smallCode);		
			 });
			
			 //用户列表			
			$("#user_id").bind('click',function(){
				parentOBJ.setWindowDialog(720,600);
				var userlistUrl = '<%=basePath%>generalUser/allCommonUserInfo.do?rnd=' + Math.random();				
				parentOBJ.modalOut("component/mutiCheckboxComponent.jsp",{name:"用户选择",edit:"userId",url:"transaction/dealTieEdit.jsp",width:720,height:600,queryUrl:userlistUrl});		
			});
			 
			 $(".input_number_ck").bind('input propertychange',function(){
				 $(this).checkNumber();
			 });
			 //校验input
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
					areaArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].parent == 1){	
							provinceCode += '<option value="'+rows[i].id+'">'+rows[i].province+'</option>';
						}			
					}					
					$("#province_code").append(provinceCode);
					$("#city_code").attr("disabled","disabled");
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