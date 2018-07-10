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
<title>编辑拍卖帖</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="../../fonts/iconfont.css"/>

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
    .content_parent{
    	display: inline-block;
    	position: relative;
    }
    .content_block{
    	display: inline-block;
    }
    .content_pic{
    	width: 50px;
	    height: 50px;
	    border: 1px solid #ddd;
	    border-radius: 2px;
	    vertical-align: middle;
	    margin-left: 5px;
    }
    .content_name{
    	margin-right: 30px;
    }
    .content_close{
    	margin-left: 50px;
    	padding: 5px;
    }
    .content_close:hover{
    	background: #ddd;
    }
    
    .controls-ul{
		position: absolute;
	    /* overflow-y: scroll !important; */
	    overflow: hidden;	
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
		<div class="pull-left" id="title_text">查看拍卖帖</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div class="control-group">
				<div class="controls">
					<span class="control-label">用户</span>
					 <input id="userID" type="text" userID=""  class="input-text" data-type="input-userlist" style="cursor:pointer;">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">藏品名称</span>
					 <input id="goodsName" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">省级城市</span>
		     		<select id="province_code" name="province_code" class="form-control select-style">	
		     			<option  value="">--请选择--</option>     
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="marginTop10 controls">
		     		<span class="label-select-oneline">市级城市</span>
		     		<select id="city_code" name="city_code" class="form-control select-style">	
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
			</div>
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">所属大类</span>
		     		<select id="big_class_code" name="bigClazz" class="form-control select-style">	
		     			<option  value="">--请选择--</option>     				     			
		     		</select>
		     		 <div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="marginTop10 controls">
		     		<span class="label-select-oneline">所属小类</span>
		     		<select id="small_class_code" name="smallClazz" class="form-control select-style">	
		     		</select>
		     		 <div class="lable-symbol-div"></div>
		     	</div>  
			</div>
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">起拍价格</span>
					 <input id="startPrice" type="text" class="input-text" data-type="input-money">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">每手加价</span>
					 <input id="markupPrice" type="text"  class="input-text" data-type="input-money">
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">当前价格</span>
					 <input id="currentPrice" type="text" class="input-text" data-type="input-money">
					 <div class="lable-symbol-div"></div>
				</div>	
				<div class="controls">
					<span class="control-label">结拍时间</span>
					 <input id="endTime"type="text" class="input-text" data-type="input-triangle">
					 <div class="controls-ul">				
						<ul id="left-date" class="left-date">
							
						</ul>
						<ul id="right-time" class="right-time">
							
						</ul>
					</div>
					 <div class="lable-symbol-div"></div>
				</div>			
			</div>
		<!-- 	<div class="control-group">				
				<div class="controls">
					<span class="control-label">库存</span>
					 <input id="inventory" type="text" class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
			</div> -->
			<div class="area-text-parent">
					<span class="control-label">帖子内容</span>
					 <textarea id="goodsDescription" rows="3" cols="20"  class="area-text"></textarea>
					 <div class="lable-symbol-div"></div>		
			</div>
			<div class="control-group-oneline marginTop20">				
					<span class="control-label">藏品图片</span>		
					<form id="imgForm" class="inputBox" action="" method="post" enctype="multipart/form-data">
						<input type="hidden" id="pageUrl">
						<div class="parent-input-files" id="parent-input-files">						
							<div id="addimg_parent" class="cover-input-files">
								<input type="file" id="addimg" value="" name="files"/>
							</div>
							
						</div>														
					</form>	
					<span class="lable-symbol"></span>
					<div class="lable-symbol-div"></div>			 				
			</div>				
		</div>
	<div id="pcImage"  imageIds="" style="width: 0;height: 0;"></div>
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
				    	$('#parent-input-files').children('div[name="add"]').last().attr('imageIds',imageIds);	
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
			        	//限制图片数量6
			        	var num = $("#parent-input-files").children().length;
						if(num >= 6){
							$("#addimg_parent").hide();				        	
						}
			        
			        	var imgCode = '<div name="add" class="cover-input-files">'+
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
			var username = '<%=name%>';
			var queryBigClazzUrl = '<%=basePath%>goodsClassification/getGoodsClass.do?rnd=' + Math.random();
			var saveSaleGoodsURL = '<%=basePath%>goods/saveOrUpdateSaleGoods.do?rnd=' + Math.random();
			var queryAuctionTimeUrl = '<%=basePath%>goods/getAuctionSession.do?rnd=' + Math.random();
			var queryUrl = '<%=basePath%>generalUser/queryGeneralUserList.do?rnd=' + Math.random();
			var queryAreaInfoUrl = '<%=basePath%>goods/queryAreaInfo.do?rnd=' + Math.random();
			var parentOBJ = window.parent;
			var getImageUrl = '<%=getImageUrl%>'.trim();
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			
			if(json_data.edit=="callback"){
				$("#userID").val(json_data.callTxt.split(',')[0]);		
				$("#userID").attr("userId",json_data.callTxt.split(',')[1]);
			}

			$("#userID").val(json_data.nickName);
			$("#userID").attr("userId",json_data.userId);
			
			$("#goodsDescription").val(json_data.goodsDescription);
			$("#goodsName").val(json_data.goodsName);
		//	$("#inventory").val(json_data.inventory);
			$("#startPrice").val(json_data.startPrice);
			$("#markupPrice").val(json_data.markupPrice);
			$("#currentPrice").val(json_data.currentPrice);
			$("#startTime").val(json_data.publishTime);
			$("#endTime").val(json_data.endTime);
			
			var imagesIds = json_data.imageIds;
			var imagesUrls = json_data.imageUrls;
			var imageCode = "";
			var imageCodeSee = "";
			var picsNum = 0;
			try{
				picsNum = imagesUrls.split(',').length;
				for(var i=0;i<picsNum;i++){
					imageCode += '<div name="add" class="cover-input-files" imageIds="'+imagesIds.split(',')[i]+'">'+
						'<img alt="图片" src="'+getImageUrl+imagesUrls.split(',')[i]+'"/>'+
						 '<i class="fa fa-times dele-input-file" aria-hidden="true"></i>'+ 
						'</div>';
					imageCodeSee += '<div name="add" class="cover-input-files">'+
						'<img alt="图片" src="'+getImageUrl+imagesUrls.split(',')[i]+'"/>'+						
						'</div>';
				}
			}catch (e) {
				// TODO: handle exception
			}
			if(json_data.edit == 1){
				$("#title_text").text("查看拍卖帖");
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
			}else if(json_data.edit == 2){
				$("#title_text").text("编辑拍卖帖");
				if(picsNum >=6){
					$("#parent-input-files").prepend(imageCode);
					$("#addimg_parent").hide();			
				}else if(picsNum>0 && picsNum<6){
					$("#parent-input-files").prepend(imageCode);
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
				
				params.goodsId= json_data.goodsId; //藏品id
				params.bigClassCode=  $("#big_class_code").val(); //大类
				params.smallClassVode= $("#small_class_code").val();  //小类
				
				params.userId= $("#userID").attr("userId");  //用户id
				params.nickName = $("#userID").val();  //用户昵称，空
				
				params.goodsType ='2';  //藏品类型
				params.goodsName= $("#goodsName").val(); //藏品名称
				params.goodsDescription= $("#goodsDescription").val();  //藏品描述
			//	params.inventory= $("#inventory").val();  //库存
				params.price= ''; //交易价格
				params.postage= '';  //邮费
				params.status=  ''; //状态，空
				params.firstStepTime = '';  //初次发布时间，空
				params.lastUpdateTime = '';  //最新更新时间，空
				params.browseNum= '';  //浏览次数，空
				params.publishCustomer= username;   //发布用户
				params.publishTime= $("#startTime").val();  //发布时间
				params.startPrice= $("#startPrice").val(); //起拍价格，空
				params.markupPrice=  $("#markupPrice").val(); //每手加价，空
				params.currentPrice= $("#currentPrice").val();  //当前价格，空
				/* var str = $("#endTime").val();
				str = str.replace(/-/g,"/");
				var date = new Date(str );alert(date); */
				params.endTime= $("#endTime").val();  //结拍时间
				/* var str ='2012-08-12 23:12';
				str = str.replace(/-/g,"/");
				var date = new Date(str );alert(date); */
				params.cityCode= '';  //城市code，空
				params.isRecommend= '';  //是否推荐，空
				params.sort= '';  //推荐排序，空
				//图片
				var pics_array = [];
				$("#parent-input-files").children('div[name="add"]').each(function(){
					pics_array.push($(this).attr('imageIds'));
				});
				params.imageIds = pics_array.join(',');  //藏品图片id
				
				params.imageUrls = '';   //藏品图片urls
				params.provinceCode= $("#province_code").val();  //城市code
				params.cityCode= $("#city_code").val();  //城市code
				
				//判断空
				if(params.nickName==""){
					$("#userID").siblings('*:last').text('用户名不能为空！');
				}else{
					$("#userID").siblings('*:last').text('');			
				}
				if(params.goodsName==""){
					$("#goodsName").siblings('*:last').text('藏品名称不能为空！');
				}else{
					$("#goodsName").siblings('*:last').text('');			
				}
		/* 		if(params.inventory==""){
					$("#inventory").siblings('*:last').text('库存不能为空！');
				}else{
					$("#inventory").siblings('*:last').text('');			
				} */
				if(params.bigClassCode==""){
					$("#big_class_code").siblings('*:last').text('请选择大类');
				}else{
					$("#bigClassCode").siblings('*:last').text('');			
				}
				if(params.smallClassVode==""){
					$("#small_class_code").siblings('*:last').text('请选择小类');
				}else{
					$("#smallClassVode").siblings('*:last').text('');			
				}
				if(params.startPrice==""){
					$("#startPrice").siblings('*:last').text('起拍价格不能为空！');				
				}else{
					$("#startPrice").siblings('*:last').text('');			
				}
				if(params.markupPrice==""){
					$("#markupPrice").siblings('*:last').text('每手加价不能为空！');				
				}else{
					$("#markupPrice").siblings('*:last').text('');			
				}
				if(params.currentPrice==""){
					$("#currentPrice").siblings('*:last').text('当前价格不能为空！');				
				}else{
					$("#currentPrice").siblings('*:last').text('');			
				}
				if(params.endTime==""){
					$("#endTime").siblings('*:last').text('结拍时间不能为空！');				
				}else{
					$("#endTime").siblings('*:last').text('');			
				}
				if(params.goodsDescription==""){
					$("#goodsDescription").siblings('*:last').text('藏品描述不能为空！');
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
				if(params.nickName=="" || params.goodsName==""||params.bigClassCode==""||params.smallClassVode==""||params.startPrice==""||params.markupPrice==""
					||params.currentPrice==""||params.endTime==""||params.goodsDescription==""||params.imageIds==""||params.provinceCode==""||params.cityCode==""){
					return;
				}
				
				$.ajax({
					url:saveSaleGoodsURL,
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
				//初始化拍卖时间
				$.ajax({
					url: queryAuctionTimeUrl,
					type: 'post',
					dataType: 'json',
					success:function(data){
						var rows = data.data;
						var days = rows.days;
						var times = rows.auction_session;
						var daysCode = [];
						var timesCode = [];
						for(var i=0;i<parseInt(days);i++){
							daysCode.push('<li>'+getNowFormatDays(i)+'</li>');
						}
						for(var j=0;j<times.split(',').length;j++){
							timesCode.push('<li>'+times.split(',')[j]+'</li>');
						}
						$("#left-date").html(daysCode.join(''));
						$("#right-time").html(timesCode.join(''));
					},
					error:function(){
						console.error("初始化发布时间为空！");
					}
				});
				//选择结拍时间
				$("#endTime").bind("click",function(){
					$(".controls-ul").slideToggle();
					$("#right-time").hide();						
				});
				
				$("#left-date").on('click','li',function(){
					$(this).attr('class','selected_li');
					$(this).siblings().removeClass('selected_li');
					$("#right-time").slideDown();
					var daysTxt = $(this).text();
					$("#endTime").val(daysTxt);
				});
				$("#right-time").on('click','li',function(){
					$(this).attr('class','selected_li');
					$(this).siblings().removeClass('selected_li');
					$(".controls-ul").slideUp();
					var timesTxt = $(this).text();

					$("#endTime").val($("#endTime").val()+ " " +timesTxt);
				});
				 //用户列表			
				$("#userID").bind('click',function(){
					parentOBJ.setWindowDialog(720,600);
					var userlistUrl = '<%=basePath%>generalUser/allCommonUserInfo.do?rnd=' + Math.random();				
					parentOBJ.modalOut("component/mutiCheckboxComponent.jsp",{name:"用户选择",edit:"userId",url:"sale/auctionTieEdit.jsp",width:720,height:600,queryUrl:userlistUrl});		
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
				 //获取当前日期
				  function getNowFormatDays(code) {
					  var date = new Date();
					  var seperator1 = "-";
					  
					  var month = date.getMonth() + 1;
					  var strDate = date.getDate()+code;
					  if (month >= 1 && month <= 9) {
					      month = "0" + month;
					  }
					  if (strDate >= 0 && strDate <= 9) {
					      strDate = "0" + strDate;
					  }
					  var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
					  return currentdate;
				  }	 
			//城市
			var areaArray = [];
			$.ajax({
				url: queryAreaInfoUrl,
				type: "post",
				data:{
				},
				success: function(data){
					var rows = data.resultList;
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
