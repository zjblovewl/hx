/**
 * 
 * ren
 * base js操作
 * 
 */

function ajaxUtil(url, param, callBack){
	$.ajax({
		type: 'post',
		url: url,
		data: param,
		dataType: 'json',
		/*complete: function(xhr,status) {
            var sessionStatus = xhr.getResponseHeader('sessionStatus');
            if(sessionStatus == 'timeout') {
          	  layer.confirm('由于您长时间没有操作, session已过期, 请重新登录.',function(){
          		  window.location.href ="/hxdc/login.jsp";
          	  });       
            }
        },*/
		success: callBack,
		error : function(xhr, status, error) {
			 var sessionStatus = xhr.getResponseHeader('sessionStatus');
			 if(sessionStatus == 'timeout') {
	           	return;
			 }
             var errorfn = xhr.getResponseHeader('errorfn');
			 if (errorfn != null) {
				errorfn(xhr, status, error);
			 } else {
				layer.alert("操作失败，请刷新页面或重试");
			 }
        }
	});
}


//显示loading
function showLoading(){
	var imgSrc = basePUrl + "/resources/img/loading.gif";
	//弹出代码
	$("body").append('<div class="loading" id="loadingGif" style="display:block"><img src="'+imgSrc+'"></div>');
	$("body").append('<div class="loadingMoveDiv" id="loadingGifDiv" style="top: 0;"></div>');	
	$("#loadingGifDiv").height($(document).height());
}

//隐藏loading
function hideLoading(){
	//隐藏代码
	$("#loadingGif").remove();
	$("#loadingGifDiv").remove();
}


//时间转字符串
function dateToString(now){  
    var year = now.getFullYear();  
    var month =(now.getMonth() + 1).toString();  
    var day = (now.getDate()).toString();  
    var hour = (now.getHours()).toString();  
    var minute = (now.getMinutes()).toString();  
    if (month.length == 1) {  
        month = "0" + month;  
    }  
    if (day.length == 1) {  
        day = "0" + day;  
    }  
    if (hour.length == 1) {  
        hour = "0" + hour;  
    }  
    if (minute.length == 1) {  
        minute = "0" + minute;  
    }  
     var dateTime = year + "-" + month + "-" + day +" "+ hour +":"+minute;  
     return dateTime;  
  }  


function getMonthNumber(d_start, d_end) {
	var y_start = d_start.getYear();
	var y_end = d_end.getYear();
	var m_start = d_start.getMonth();
	var whatDay = parseInt(Math.abs(d_start - d_end) / 1000 / 60 / 60 / 24);
	if(y_start != y_end && whatDay >= 31){
		return true;
	}
	
	if(y_start == y_end){
		if(isLeapYear(y_start)){
			if(m_start == 1){
				if(whatDay >= 29){
					return true;
				}
			}else{
				if(is30DayMonth(m_start+1)){
					if(whatDay >= 30){
						return true;
					}
				}else if(is31DayMonth(m_start+1)){
					if(whatDay >= 31){
						return true;
					}
				}
			}
		}else{
			if(m_start == 1){
				if(whatDay >= 28){
					return true;
				}
			}else{
				if(is30DayMonth(m_start+1)){
					if(whatDay >= 30){
						return true;
					}
				}else if(is31DayMonth(m_start+1)){
					if(whatDay >= 31){
						return true;
					}
				}
			}
		}
	}
	return false;
	
}

function isLeapYear(year){
    var flag = false;
    year+=1900;
    if((year%400==0)||(year%4==0&&year%100!=0)){
        flag = true;
    }
    return flag;
}

function is30DayMonth(month){
    var tmp = [4,6,9,11];
    month = month-0;
    var flag = false;
    for(var i=0; i<tmp.length; i++){
        if(month==tmp[i]){
            flag = true;
            break;
        }
    }
    return flag;
}

function is31DayMonth(month){
    var tmp = [1,3,5,7,8,10,12];
    var flag = false;
    month = month-0;
    for(var i=0; i<tmp.length; i++){
        if(month==tmp[i]){
            flag = true;
            break;
        }
    }
    return flag;
}

function isFebMonth(month){
    month = month-0;
    var flag = false;
    if(month==2){
        flag = true;
    }
    return flag;
}

function isLastDay(year, month, date){
    var flag = false;
    if(date==30&&is30DayMonth(month)){
        flag = true;
    }else if(date==31&&is31DayMonth(month)){
        flag = true;
    }else if(isFebMonth(month)){
        if(isLeapYear(year)&&date==29){
            flag = true;
        }else if(!isLeapYear(year)&&date==28){
            flag = true;
        }
    }
    return flag;
}
//时间戳转换时间
function timestampToTime(times) {
    var date = new Date(times);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    YY = date.getFullYear() + '-';
    MM = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    DD = (date.getDate()+1 < 10 ? '0'+(date.getDate()+1) : date.getDate()+1) + ' ';
    hh = (date.getHours()+1 < 10 ? '0'+(date.getHours()+1) : date.getHours()+1) + ':';
    mm = (date.getMinutes()+1 < 10 ? '0'+(date.getMinutes()+1) : date.getMinutes()+1) + ':';
    ss = (date.getSeconds()+1 < 10 ? '0'+(date.getSeconds()+1) : date.getSeconds()+1);
    return YY+MM+DD+hh+mm+ss;
}
/*
 * 校验表单内容
 */
function validateForm(obj){
	var column = new columnArray();
	for(var i in column){
		var item = column[i];
		if(item[0] != null && item[0] != ''){
			if(item[1] != null && item[1] != ''){
				if(obj[item[0]] == undefined || obj[item[0]] == null || obj[item[0]] == ''){
					layer.alert(item[1]);
					return false;
				}
			}
			if(item[2] != null && item[2] != ''){
				if(parseInt(item[2]) < obj[item[0]].length){
					layer.alert(item[3]);
					return false;
				}
			}
		}
	}
	return true;
}