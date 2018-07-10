<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户意见与反馈</title>
    <link rel="stylesheet" type="text/css" href="../../H-ui.admin/H-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="../../css/common_menu.css">

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/keywordSelect.js"></script>
    <script src="../../js/bootstrapTableUtil.js"></script>

    <style>
    	.customsearch-input{
			width: 28%;
		}
	
    </style>
</head>
<body>
<div>
    <div class="customsearch allLine" id="query-form">
     	<div class="customsearch-input">
     		<span class="label-input-oneline">处理状态：</span>
	     	<select name="processingState" class="form-control select-style" id="processingState">	
	     			<option value="">全部</option>     			
	     			<option value="1">未查看</option>
	     			<option value="2">已查看</option>
	     		</select>
     	</div>
     	<div class="customsearch-input">
     		<span class="label-input-oneline">反馈内容：</span>
     		<input name="feedbackContent" placeholder='反馈内容' type="text"class="form-control">
     	</div>     

        <div class="customsearch-input allLine_btn">
            <button id="btn_search" type="button" class="btn btn-primary btn-space marginRight10">
                <span class="fa fa-search" aria-hidden="true" class="btn-icon-space">查询</span>
            </button>
            <button id="btn_reset"  type="button" class="btn btn-default btn-space">
                <span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space">重置</span>
            </button>
        </div>
       </div>
      
    <table id="table">
        
    </table>
</div>
<script>
    var queryUrl = '<%=basePath%>userOpinionFeedback/queryUserOFList.do?rnd=' + Math.random();
    var delUrl = '<%=basePath%>userOpinionFeedback/deleteOFById.do?rnd=' + Math.random();
   
    var columnsArray = [{
    	checkbox: true,
        visible: true,
        width:50,
        visible: false  
    }, {
        field: 'id',
        title: 'ID',
        width: 150,
        visible: false  
    }, {
        field: 'nick_name',
        title: '昵称',
        width: 100
    }, {
        field: 'feedback_content',
        title: '用户反馈内容',
        width: 500

    }, {
        field: 'submit_time',
        title: '提交时间',
        width: 120
    }, {
        field: 'customerservice_feedback',
        title: '客服反馈',
        width: 200,
        visible: false
    }, {
        field: 'processing_state',
        title: '查看状态',
        width: 120,
        formatter:  function(value) {
            if (value == 1) {
                return "未查看";
            }else if(value ==2){
            	return "已查看";
            }
        }
    }, {
        field: 'handle',
        title: '操作',
        width: 120,
        formatter:function(){
        	return  "<i class='fa fa-eye' title='查看' style='margin-right:8px;font-size: 16px;'></i>"+ 			
  					"<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>";
	}
    }];
    $("#table").InitMainTable(queryUrl, columnsArray);

    var modalObj = window.parent;
	
  	//查看	
	function getJSPurl(){
		modalObj.setWindowDialog(720,400);
		return "user/userOpinionFeedBackEdit.jsp";
	}
  
	function deleID(id){
		modalObj.$.showConfirm("确认删除当前条目?", function(){
			$.post(delUrl,{ids:id});
			$("#table").bootstrapTable('refresh');			
		},function(){
			return false;
		});
		
	}

</script>
</body>
</html>