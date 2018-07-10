<%@page import="cn.com.hxfz.util.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>新增交易订单</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">

<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.form.js"></script>
<script src="../../js/laydate/laydate.js"></script>
<script src="../../js/timeUtils.js"></script>
<script src="../../js/common/md5.js"></script>
<script src="../../js/jquery.ztree.all.js"></script>
<script src="../../js/commonCheckUtils.js"></script>

<style>
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
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		
		<div class="form-div-edit">
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">订单号</span>
					 <input id="code" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>											
			</div>
			<div class="control-group">				
				<div class="controls">
					<span class="control-label">收货人</span>
					 <input id="code" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">联系方式</span>
					 <input id="goods_name" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>								
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="control-label">物流公司</span>
		     		<select id="isRecomend" name="bigClazz" class="form-control select-style">	
		     			<option value="">--请选择--</option>		     			
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  	
		     	<div class="controls">
					<span class="control-label">当前状态</span>
					<select id="isRecomend" name="bigClazz" class="form-control select-style">	
		     			<option value="">--请选择--</option>		     			
		     		</select>
					 <div class="lable-symbol-div"></div>
				</div>
			</div>		
			<div class="area-text-parent">
					<span class="control-label">收货地址</span>
					 <textarea id="goods_description" rows="3" cols="20"  class="area-text"></textarea>
					 <div class="lable-symbol-div"></div>		
			</div>
		</div>
	</div>
			<div class="modal-footer">			
				<button id="submit_modal" class="btn btn-success marginRight10">确认</button>
				<button id="close_modal" class="btn btn-danger">取消</button>
			</div>
	<script>
		$(function() {	
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
					
				params.goodsId= ''; //藏品id
				params.bigClassCode=  $("#big_class_code").val(); //大类
				params.smallClassVode= $("#small_class_code").val();  //小类			
				params.userId= $("#user_id").attr('user_id');  //用户id
				params.nickName = $("#user_id").val();  //用户昵称，空
				params.goodsType ='1';  //藏品类型
				params.goodsName= $("#goods_name").val(); //藏品名称
				params.goodsDescription= $("#goods_description").val();  //藏品描述
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
				//图片
				var pics_array = [];
				$('#parent-input-files').children('div[name="add"]').each(function(){
					pics_array.push($(this).attr('imageIds'));
				});		
				params.imageIds= pics_array.join(',');  //藏品图片id
				params.imageUrls = '';   //藏品图片urls
				console.log(params)
				
				var isRecommendName = $("#isRecomend option:selected").attr("name");
				//判断空
				if(params.nickName==""){
					$("#user_id").siblings('*:last').text('用户名不能为空！');
				}else{
					$("#user_id").siblings('*:last').text('');
				}
				if(params.goodsName==""){
					$("#goods_name").siblings('*:last').text('藏品名称不能为空！');
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
					$("#goods_description").siblings('*:last').text('藏品描述不能为空！');
				}else{
					$("#goods_description").siblings('*:last').text('');
				}
				if(params.imageIds==""){
					$("#imgForm").next().text('请上传图片');
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
					class_type:2,
					isAll:false
				},
				success: function(data){
					var rows = data.resultList;
					var bigCode ="";
					var smallCode = "";
					dataArray = rows;
					for(var i=0;i<rows.length;i++){
						if(rows[i].parent_code == 0){
							if(rows[i].class_code == json_data.bigClassCode){
								//选中项
								bigCode += '<option value="'+rows[i].class_code+'" selected="selected">'+rows[i].class_name+'</option>';
							}else{
								bigCode += '<option value="'+rows[i].class_code+'">'+rows[i].class_name+'</option>';
							}
						}			
					}
					$("#big_class_code").append(bigCode);
					var parentClass = $('#big_class_code option:selected').attr("value");
					if(parentClass != ""){
						if(json_data.edit !=1){
							$("#small_class_code").attr("disabled",false);							
						}
						for(var i=0;i<rows.length;i++){
							if(rows[i].parent_code == parentClass){
								if(rows[i].class_code == json_data.smallClassVode){
									smallCode += '<option value="'+rows[i].class_code+'" selected="selected">'+rows[i].class_name+'</option>';
								}else{
									smallCode += '<option value="'+rows[i].class_code+'">'+rows[i].class_name+'</option>';
								}
							}
						}
					}else{
						$("#small_class_code").html('<opiton value=""></option>');
						$("#small_class_code").attr("disabled","disabled");
					}
					$("#small_class_code").append(smallCode);
				},
				error: function(){
					console.error("请求失败");
				}
			});
			
			//大类小类联动事件	
			 $("#big_class_code").change(function() {				
				var currentClazz = $('#big_class_code option:selected').attr("value");
				var smallCode = '';
				
				if(currentClazz == ""){
					$("#small_class_code").html('<opiton value=""></option>');
					$("#small_class_code").attr("disabled","disabled");
					return;
				}else{
					$("#small_class_code").attr("disabled",false);
				}
				for(var i=0;i<dataArray.length;i++){
					if(dataArray[i].class_code == 'all'){	
						smallCode += '<option value="">'+dataArray[i].class_name+'</option>';
					}
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
			 $('input').bind('input propertychange',function(){
				 if($(this).val() != ""){
					 $(this).siblings('*:last').text('');
				 }
			 });
			 $('textarea').bind('input propertychange',function(){
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
						console.log("wwwwwwwwwwwww:"+rows)
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
