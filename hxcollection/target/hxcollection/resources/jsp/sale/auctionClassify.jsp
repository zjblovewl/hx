<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
   <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>拍卖分类配置</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>

    <style>
	.btn-group{
		margin-bottom: 6px;
	}
	.table{
		width: 100%;
	}
	.table tr th{
		font-size: 14px;
		height: 100%;
		background:#e0d4bf;
		border-right: 1px solid #ddd;
		line-height: 2 !important;
	}
	.table tr td,.table tr th{
		text-align: left;
	}
	.table tr td{	    
    	line-height: 30px !important;
	}
	.close_menu{
		background:url("../../images/allbgs.png") no-repeat -48px 0px;
		width: 16px;
		height:16px;
		display: inline-block;
		vertical-align: middle;
		margin-right: 6px;
		margin-bottom: 3px;
		cursor:pointer;
	}
	.open_menu{
		background:url("../../images/allbgs.png") no-repeat -32px 0px;
		width: 16px;
		height:16px;
		display: inline-block;
		vertical-align: middle;
		margin-right: 6px;
		margin-bottom: 3px;
		cursor:pointer;
	}
	.first_name{
		font-size: 15px;
	}
	.second_name{
		padding-left: 28px;
		font-size: 14px;
	} 
	
	.bigClazz{
	
	}
	.smallClazz{
		display: none;
	}
	
    </style>
</head>
<body>
<div>
    <div id="toolbar-btn" class="btn-group pull-left">
          <button id="btn_add" type="button" class="btn btn-primary btn-space">
          <span class="fa fa-plus-square" aria-hidden="true" class="btn-icon-space">新增</span>             
          </button>          
    </div>
    <table id="table" class="table">
			
	</table>
</div>
<script>
	var modalObj = window.parent;
	var queryUrl = '<%=basePath%>goodsClassification/getSaleClassifiyRecords.do?rnd=' + Math.random();
	var deleteURL = '<%=basePath%>goodsClassification/deleteClassifyByIds.do?rnd=' + Math.random();
	$.ajax({
		url:queryUrl,
		type:"post",
		dataType:'json',
		success:function(data){
			var rows = data.resultList;			
			var tableCode = "<tbody>"+'<tr><th style="width:30%;">名称</th><th style="width:30%;">创建时间</th><th style="width:10%;">排序</th><th>描述</th><th style="width:14%;">操作</th></tr>';
			console.log(rows)
			for(var i=0;i<rows.length;i++){
				var innerTd = "";
				var isLabel = "";
				var trClass = "";
				var isOpen = false;			
				var opertionIcon = "<div><i class='fa fa-eye' title='查看' onclick='checkMenu.call(this)' style='margin-right:8px;font-size: 16px;'></i>"+
		    	   "<i class='fa fa-edit' title='编辑' onclick='editMenu.call(this)'  style='margin-right:8px;font-size: 16px;'></i>"+
		    		"<i class='fa fa-trash' title='删除' onclick='deleteMenu.call(this)'  style='margin-right:8px;font-size: 16px;'></i>";
		    		
				if(rows[i].parentCode == 0){
					trClass = "bigClazz";
					isLabel = '<span class="close_menu label_btn" onclick="foldORexpand.call(this)"></span>';
					innerTd =  '<div class="first_name">'+isLabel+'<a onclick="checkMenu.call(this)">'+rows[i].className+'</a></div>';					
					opertionIcon +=  "<i class='fa fa-plus' title='添加下级菜单' onclick='addMenu.call(this)' style='font-size: 16px;'></i></div>";
				}else{
					trClass = "smallClazz";
					innerTd =  '<div class="second_name"><a onclick="checkMenu.call(this)">'+rows[i].className+'</a></div>';					
				}
				
				tableCode += '<tr class="'+trClass+'" isOpen="'+isOpen+'" classCode="'+rows[i].classCode+'"   parentCode="'+rows[i].parentCode+'"  classId="'+rows[i].classId+'" imageIds="'+rows[i].imageIds+'"  imageUrls="'+Trim(rows[i].imageUrls,"g")+'">'+	
					'<td>'+innerTd+'</td>'+
					'<td>'+rows[i].createTime+'</td>'+
					'<td>'+rows[i].sort+'</td>'+
					'<td>'+rows[i].describe+'</td>'+
					'<td>'+opertionIcon+'</td>'+		
					'</tr>'
			}
			tableCode += '</tbody>';
			$("#table").html(tableCode);
		},
		error:function(){
			console.error("error");
		}
			
	});
	
	
	/**
	 *公共方法
	 */
	 //初始化表格		 
	function foldORexpand(){
		if($(this).hasClass("close_menu")){
			$(this).removeClass("close_menu");
			$(this).addClass("open_menu");
			$(this).parent().parent().parent().attr("isOpen","true");
			
			var currentClazz = $(this).parent().parent().parent().attr("class");
			$(this).parent().parent().parent().nextUntil('tr[class="'+currentClazz+'"]').each(function(){				
				$(this).slideDown();
			})
		}else{
			$(this).removeClass("open_menu");
			$(this).addClass("close_menu");
			$(this).parent().parent().parent().attr("isOpen","false");
			var currentClazz = $(this).parent().parent().parent().attr("class");			
			$(this).parent().parent().parent().nextUntil('tr[class="'+currentClazz+'"]').each(function(){							
				$(this).hide();
			});
		}	
	}
	
	var iframeUrl = "sale/auctionClassifyEdit.jsp";
	
	//查看
	function checkMenu(){				
		var name = $(this).parent().parent().parent().children('td').eq(0).text();
		var code = $(this).parent().parent().parent().attr("classCode");		
		var imageUrls = $(this).parent().parent().parent().attr("imageUrls");
		var createTime = $(this).parent().parent().parent().children('td').eq(1).text();
		var sort = $(this).parent().parent().parent().children('td').eq(2).text();
		var decribe = $(this).parent().parent().parent().children('td').eq(3).text();
		var parentCode = $(this).parent().parent().parent().attr("parentCode");
		
		modalObj.modalOut(iframeUrl,{
			name: name,
			classCode: code,
			parentCode: parentCode,
			sort :sort,
			edit: 1,	
			imageUrls: imageUrls,			
			decribe: decribe,
			createTime: createTime
			});
		return;
	}
	//编辑
	function editMenu(){
		var name = $(this).parent().parent().parent().children('td').eq(0).text();
		var code = $(this).parent().parent().parent().attr("classCode");
		var parentCode = $(this).parent().parent().parent().attr("parentCode");
		var classId = $(this).parent().parent().parent().attr("classId");
		var imageIds = $(this).parent().parent().parent().attr("imageIds");		
		var imageUrls = $(this).parent().parent().parent().attr("imageUrls");
		var createTime = $(this).parent().parent().parent().children('td').eq(1).text();
		var sort = $(this).parent().parent().parent().children('td').eq(2).text();
		var decribe = $(this).parent().parent().parent().children('td').eq(3).text();
		
		modalObj.modalOut(iframeUrl,{
			name: name,
			classCode: code,
			parentCode: parentCode,
			sort :sort,
			edit: 2,	
			imageIds: imageIds,
			decribe: decribe,
			createTime: createTime,
			classId: classId,
			imageUrls: imageUrls
			});
		return;
	}
	//删除
	function deleteMenu(){	
		var $this = $(this);
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			var classId = $this.parent().parent().parent().attr("classId");
			var imageIds = $this.parent().parent().parent().attr("imageIds");
			var params = {};
			params.classId = classId;			
			params.imageIds = imageIds;
			params.classType = '2';
			$.ajax({
				url: deleteURL,
				type: "post",
				data:{
					params :JSON.stringify(params)
				},
				success:function(data){
					modalObj.resfreshWindow();
				},
				error:function(){
					console.error("删除错误");
				}
			});		
		},function(){
			return false;
		});
		
	}
	//添加下级菜单
	function addMenu(){		
		var parentCode = $(this).parent().parent().parent().attr("classCode");
		var classId = $(this).parent().parent().parent().attr("classId");
		
		modalObj.modalOut(iframeUrl,{			
			edit: "lower",	
			parentCode: parentCode,
			classId: classId
			});
		return;
	}
	//新建菜单
	$("#btn_add").click(function(){
		newMenu();
		return;
	});
	function newMenu(){		
		modalObj.setWindowDialog(720,330);
		modalObj.modalOut(iframeUrl,{			
			edit: "new",
			parentCode: '2'
			});
		return;
	}
	//去掉空格
     function Trim(str,is_global){
            var result;
            result = str.replace(/(^\s+)|(\s+$)/g,"");
            if(is_global.toLowerCase()=="g"){
                result = result.replace(/\s/g,"");
             }
            return result;
		}

</script>
</body>
</html>