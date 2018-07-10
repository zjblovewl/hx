$.fn.extend({
    InitMainTable:function (queryUrl,columsArray) {
        $table = $(this).bootstrapTable({
            url: queryUrl,
            method: 'POST',
            striped: true,
            toolbar: '#toolbar-btn',
            dataField: "resultList",
            sidePagination: "server",
            pageNumber: 1,
            pagination: true,
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            queryParamsType: 'limit',
            queryParams: queryParams,
            search: false,
            clickToSelect: false,
            toolbarAlign: 'left',
            height: 'auto',
            uniqueId: "id",
            columns: columsArray,
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                console.log("数据加载失败！");
            },
            onClickRow : function(row, tr,flied){           	  
            	if(event.target.className.indexOf("fa-edit") != -1){//编辑	
            		var jspurl = getJSPurl(); 
            		$.extend(true,row,{edit:2});
 					modalObj.modalOut(jspurl,row);
 				}else if(event.target.className.indexOf("fa-trash-o") != -1){//删除 特殊处理交易 拍卖						
 					deleRow(row);
 				}else if(event.target.className.indexOf("fa-trash") != -1){//删除
 					var del = row.id;
 					deleID(del);
 				}else if(event.target.className.indexOf("fa-window-restore") != -1){//角色管理权限配置
 					var role_id = row.id;
 					var role_url = getRoleControlList();
 					modalObj.modalOutRole(role_url,role_id);
 				}else if(event.target.className.indexOf("fa-eye") != -1){//查看
 					var jspEyeUrl = getJSPurl(); 
 					$.extend(true,row,{edit:1});
 					modalObj.modalOut(jspEyeUrl,row);
 				}else if(event.target.className.indexOf("fa-cog") != -1){//管理键值对
 					var jspDataUrl = getJSPKeyValueUrl(); 
 					modalObj.setWindowDialog(720,450);
 					modalObj.modalOut(jspDataUrl,{pid:row.id});
 				}else if(event.target.className.indexOf("fa-unlock") != -1){//解除锁定
 					unlockORlockUser(row.id,"unclok");
 				}else if(event.target.className.indexOf("fa-lock") != -1){//解除锁定
 					unlockORlockUser(row.id,"clok");
 				}else if(event.target.className.indexOf("fa-comments") != -1){//查看评论
 					var jspCommentsUrl = getJSPCommentsUrl(); 
 					modalObj.setWindowDialog(720,600);
 					modalObj.modalOut(jspCommentsUrl,row);
 				}
    		 },
    		 onCheckAll:function(rows){
    			 
    	     },
    	     onCheck:function(row){
    	    	 console.log(row);      
    	     },
    	     onUncheck:function(row){
    	    	 console.log(row);      

    	     },
    	     onDblClickRow: function (row, $element) {
                 //do nothing
             }
            
        });
    }
});

/**
 *公共方法
 */
//格式化数据
function operateFormatter(value, row, index) {
   
}


//请求服务数据时所传参数
function queryParams(params) {
    var param = {};
    $('#query-form').find('[name]').each(function () {   	
        var value = $(this).val();
         if(value != null){
        	 param[$(this).attr('name')] = encodeURI(value,'utf-8');
         }else{
        	 param[$(this).attr('name')] = '';
         }
        
    });
    
    param['pageSize'] = params.limit;
    param['pageIndex'] = params.offset;  
    
    return param;
}
$(function(){
	//点击事件
	$("#btn_search").click(function(){
		customSearch();
	});
	$("#btn_reset").click(function(){
		resetSearch();
	});
		
});
//查询条件
function customSearch(text) {
    $table.bootstrapTable('refresh');
}
//重置查询条件
function resetSearch() {
    $('#query-form').find('[name]').each(function () {
        $(this).val('');
    });
}
//添加编辑删除按钮
function editAnddelte(){
    return "<i class='fa fa-eye' title='查看' style='margin-right:8px;font-size: 16px;'></i>"+
    	   "<i class='fa fa-edit' title='编辑'  style='margin-right:8px;font-size: 16px;'></i>"+
    		"<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>"
}

function limitDeleteEdit(){
	 return "<i class='fa fa-window-restore' title='权限配置' style='margin-right:8px;font-size: 16px;'></i>"+
	   "<i class='fa fa-edit' title='编辑'  style='margin-right:8px;font-size: 16px;'></i>"+
		"<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>"
}
function onlyDelete(){
	return "<i class='fa fa-trash' title='删除'  style='font-size: 16px;'></i>"
}


//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}