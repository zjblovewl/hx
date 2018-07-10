/*****************************************************************
                  jQuery Ajax封装通用类         
*****************************************************************/

/**
  * ajax异步请求封装
  * 
  * url 发送请求的地址
  * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
  * async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
  *       注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
  * type 请求方式("POST" 或 "GET")， 默认为 "GET"
  * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
  * successfn 成功回调函数
  * errorfn 失败回调函数
 */
ajaxAsync = function(url, param, successfn, errorfn) {
      $.ajax({
          type: "post",
          async: true,
          data: param,
          url: url,
          dataType: 'json',
          timeout : 100000,
          complete: function(xhr,status) {
              var sessionStatus = xhr.getResponseHeader('sessionStatus');
              if(sessionStatus == 'timeout') {
            	  layer.confirm('由于您长时间没有操作, session已过期, 请重新登录.',function(){
            		  window.location.href ="/hxdc/login.jsp";
            	  });       
              }
          },
          success: function(set, status){
              successfn(set, status);
          },
          error : function(xhr, status, error) {
			 var sessionStatus = xhr.getResponseHeader('sessionStatus');
             if(sessionStatus == 'timeout') {
            	return;
             }
			 if (errorfn != null) {
				errorfn(xhr, status, error);
			 } else {
				layer.alert("操作失败，请刷新页面或重试");
			 }
          }
      });
};


/**
 * ajax同步请求封装
 * 
 * url 发送请求的地址
 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
 * async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
 *       注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
 * type 请求方式("POST" 或 "GET")， 默认为 "GET"
 * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
 * successfn 成功回调函数
 * errorfn 失败回调函数
*/
ajaxSync = function(url, param, successfn, errorfn) {
    $.ajax({
        type: "post",
        async: false,
        data: param,
        url: url,
        dataType: 'json',
        timeout : 100000,
        complete: function(xhr,status) {
            var sessionStatus = xhr.getResponseHeader('sessionStatus');
            if(sessionStatus == 'timeout') {
          	  layer.confirm('由于您长时间没有操作, session已过期, 请重新登录.',function(){
          		  window.location.href ="/hxdc/login.jsp";
          	  });       
            }
        },
        success: function(set, status){
            successfn(set, status);
        },
        error : function(xhr, status, error) {
			 var sessionStatus = xhr.getResponseHeader('sessionStatus');
			 if(sessionStatus == 'timeout') {
	           	return;
			 }
			 if (errorfn != null) {
				errorfn(xhr, status, error);
			 } else {
				layer.alert("操作失败，请刷新页面或重试");
			 }
         }
    });
};
