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
<title>查看订单详情</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../H-ui.admin/H-ui.min.css" />
<link rel="stylesheet" href="../../css/jspEditcommon.css" />
<link rel="stylesheet" href="../../css/font-awesome.min.css"/>
<link rel="stylesheet" href="../../fonts/iconfont.css"/>
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
	<!-- 修改/新增 -->
	<div id="modal-title" class="modal-title">
		<div class="pull-left" id="title_text">编辑订单</div>
		<div id="close_dialog" class="pull-right close_table">
			<i class="iconfont icon-shachu-xue"></i>
		</div>
	</div>
	<div class="modal-container">
		<div class="form-div-edit">
			<div>藏品信息</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">卖家</span>
					 <input id="sellerName" type="text" class="input-text">
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
		     		<span class="label-select-oneline">所属大类</span>
		     		<select id="big_class_code" name="bigClazz" class="form-control select-style">	
		     			<option value="">--请选择--</option>		     			
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
					<span class="control-label">藏品描述</span>
					 <input id="goodsDescription" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">藏品数量</span>
					 <input id="amount" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">藏品价格</span>
					 <input id="price" type="text" class="input-text input_number_ck" >
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">邮费</span>
					 <input id="postage" type="text"  class="input-text input_number_ck">
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			
			<div class="marginTop20">				
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
			
			
			<div>订单信息</div>
			<div class="control-group">
				<div class="controls">
					<span class="control-label">交易单号</span>
					 <input id="orderCode" type="text" class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">订单状态</span>
		     		<select id="orderStatus" class="form-control select-style">	
		     			<option value="1">待付款</option>
						<option value="2">待发货</option>
						<option value="3">待收货</option>
						<option value="4">待评论</option>
						<option value="5">已完成</option>
						<option value="6">待退款</option>
						<option value="7">已退款</option>
						<option value="8">取消订单</option>
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
			</div>
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">买家</span>
					 <input id="buyerName" type="text"  class="input-text">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">买家备注</span>
					 <input id="remark" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<span class="control-label">实付价格</span>
					 <input id="payMoney" type="text" class="input-text input_number_ck">
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">支付方式</span>
		     		<select id="orderType" class="form-control select-style">	
		     			<option value="1">支付宝</option>
						<option value="2">微信</option>
						<option value="3">钱包</option>
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
			</div>
			
			<div class="control-group">	
				<div class="controls">
					<span class="control-label">收货人</span>
					 <input id="consignee" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>			
				<div class="controls">
					<span class="control-label">联系方式</span>
					 <input id="contactInformation" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>	
			</div>		
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">收货地址</span>
					<textarea id="receiveAddress" rows="2" cols="20" class="area-text" style="width:60%;"></textarea>
					<div class="lable-symbol-div"></div>
				</div>	   
			</div>
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">物流公司</span>
					 <input id="logisticsCompany" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">物流单号</span>
					 <input id="logisticsCode" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>					
			</div>
			
			<div class="control-group">
				<div class="marginTop10 controls">
		     		<span class="label-select-oneline">订单类型</span>
		     		<select id="orderType" class="form-control select-style">	
		     			<option value="1">普通订单</option>
						<option value="2">拍卖订单</option>
		     		</select>
		     		<div class="lable-symbol-div"></div>
		     	</div>  
		     	<div class="controls">
					<span class="control-label">订单生成时间</span>
					 <input id="orderGenerationTime" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
			</div>	
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">退款原因</span>
					 <input id="refundReason" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">申请退款时间</span>
					 <input id="applyRefundTime" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">订单取消时间</span>
					 <input id="cancelTime" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">订单取消原因</span>
					 <input id="cancelReason" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
			</div>
			
			
			<div class="control-group">
				<div class="controls">
					<span class="control-label">确认退款时间</span>
					 <input id="confirmRefundTime" type="text" class="input-text input_number_ck">					
					 <div class="lable-symbol-div"></div>
				</div>
				<div class="controls">
					<span class="control-label">订单完成时间</span>
					 <input id="orderFinishTime" type="text" class="input-text input_number_ck">					
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
			var isCheck = checkinputeditFileAct();
			if(!isCheck){
				return;
			} 
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
			var saveOrderURL = '<%=basePath%>order/saveOrUpdateOrder.do?rnd=' + Math.random(); 
			
			var parentOBJ = window.parent;
			var getImageUrl = '<%=getImageUrl%>'.trim();
			//获取url
			var src_data = parentOBJ.document.getElementById("modal-iframe")
					.getAttribute("src-data");
			var json_data = JSON.parse(src_data);
			
		
			$("#id").val(json_data.id);
			
			$("#sellerName").val(json_data.seller_name);//卖家 
			$("#goodsName").val(json_data.goods_name);//藏品名称 
			$("#goodsDescription").val(json_data.goods_description);//藏品描述  
			$("#orderCode").val(json_data.order_code);
			
			$("#buyerName").val(json_data.buyer_name);
			$("#amount").val(json_data.amount);
			$("#price").val(json_data.price);
			$("#postage").val(json_data.postage);
			$("#payMoney").val(json_data.pay_money);
			$("#receiveAddress").val(json_data.receive_address);
			$("#orderType").val(json_data.order_type);
			$("#orderStatus").val(json_data.order_status);
			initOrderStatus(parseInt(json_data.order_status));			
			$("#orderGenerationTime").val(json_data.order_generation_time);
			$("#consignee").val(json_data.consignee);
			$("#contactInformation").val(json_data.contact_information);
			$("#logisticsCompany").val(json_data.logistics_company);
			$("#logisticsCode").val(json_data.logistics_code);
			$("#refundReason").val(json_data.refund_reason);
			$("#payMethod").val(json_data.pay_method);
			$("#orderFinishTime").val(json_data.order_finish_time);
			$("#applyRefundTime").val(json_data.apply_refund_time);
			$("#confirmRefundTime").val(json_data.confirm_refund_time);
			$("#cancelTime").val(json_data.cancel_time);
			$("#cancelReason").val(json_data.cancel_reason);
			$("#remark").val(json_data.remark);
			
			
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
				$("#title_text").text("查看交易订单");
				$(".lable-symbol").each(function(){
					$(this).hide();
				});

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
				$("#title_text").text("编辑交易订单");
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
				
				params.id= json_data.id;  //订单ID
				params.receiveAddress= $("#receiveAddress").val();  //收货地址
				/* 
				params.orderCode= $("#orderCode").val();  //订单号
				params.sellerName= $("#sellerName").val();  //卖家
				params.buyerName= $("#buyerName").val(); //买家
				params.amount= $("#amount").val();  //数量
				params.price= $("#price").val(); //单价
				params.postage= $("#postage").val();  //邮费
				params.payMoney= $("#payMoney").val(); //总价
				params.orderType= $("#orderType").val(); //订单类型
				params.orderStatus= $("#orderStatus").val();  //订单状态
				params.orderGenerationTime= $("#orderGenerationTime").val();  //订单时间 
 */
				
				
				//判断空
			
				if(params.receiveAddress == ""){
					$("#receiveAddress").siblings('*:last').text('收货地址不能为空！');
				}else{
					$("#receiveAddress").siblings('*:last').text('');
				}
				if(params.receiveAddress == ""){
					return;
				}

				$.ajax({
					url:saveOrderURL,
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
			
			//监听订单状态干煸
			$("#orderStatus").change(function(){
				var val = parseInt($(this).val());
				initOrderStatus(val);
			});
			function initOrderStatus(val){
					hideInput();					
				if(val == 5){
					$("#orderFinishTime").parent().show();
				}else if(val == 6){
					$("#refundReason").parent().show();
					$("#applyRefundTime").parent().show();
				}else if(val == 7){
					$("#refundReason").parent().show();
					$("#applyRefundTime").parent().show();
					$("#confirmRefundTime").parent().show();
				}else if(val == 8){
					$("#cancelTime").parent().show();
					$("#cancelReason").parent().show();
				}
				
			}
			function hideInput(){
				$("#orderFinishTime").parent().hide();
				$("#refundReason").parent().hide();
				$("#applyRefundTime").parent().hide();
				$("#cancelTime").parent().hide();
				$("#cancelReason").parent().hide();
				$("#confirmRefundTime").parent().hide();
			}
			
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
		});
		</script>
		</body>
</html>