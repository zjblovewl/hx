// JavaScript Document
$(function(){
	if (window != top)
	{
		top.location.href = location.href; 
	}
	
	var input = $("#accounts, #code, #unit"),
	   formerror = $(".form-error");
	
	input.focus(function(){
		if(this.value == this.defaultValue && this.name !="unit"){
		    this.value="";
		}
		$(this).parents(".form-item").addClass("form-item-active");
	});
	
	input.blur(function(){
	    var $this = $(this);
        if(this.value == ""){
            this.value = this.defaultValue;
            $this.removeClass('input-real');
        }else{
            $this.addClass('input-real');
        }
        $(this).parents(".form-item").removeClass("form-item-active");
	});

	$(document).keydown(function(event){
			var keyCode = event.keyCode;
			if(keyCode == 13){
				if(!checkcellphone($("#accounts"), formerror)){return false;}
		        if(!checkunit($("#unit"), formerror)){return false;}
		        if(!checkcode($("#code"), formerror)){return false;}
		        if(!checkunitId($("#unitId"), formerror)){return false;}
		        $(this).attr("disabled",true); 
				  $.ajax({
				            type:"post",
				            url:ctx+"/wx/user/toLogin",
				            data:{"username": $("#accounts").val(), 
				            	"unit": $("#unitId").val(), 
				            	"captcha": $("#code").val(),
				            	"thirdPartyId":$("#thirdPartyId").val(),
				            	"wxNo":$("#wxNo").val()}
			        	}).done(function(data){
			        	$("#login").attr("disabled",false); 
				            if(data == 0){
				                formerror.html("验证码输入错误，请重新获取");
				                return false;
				            }else if(data == 1){
				                //formerror.html("登录成功");
				                window.location.href=ctx+"/wx/user/index";
				            }else {
				            	formerror.html("");
				            	return false;
				            }
			       });	
			}
	 });
	 
    $("#login").click(function(event){
        if(!checkcellphone($("#accounts"), formerror)){return false;}
        if(!checkunit($("#unit"), formerror)){return false;}
        if(!checkcode($("#code"), formerror)){return false;}
        if(!checkunitId($("#unitId"), formerror)){return false;}
        $(this).attr("disabled",true).val("登录中...");
        var $this = $(this);
        $.ajax({
            type:"post",
            url:ctx+"/wx/user/toLogin",
            data:{"username": $("#accounts").val(), 
            	"unit": $("#unitId").val(), 
            	"captcha": $("#code").val(),
            	"thirdPartyId":$("#thirdPartyId").val(),
            	"wxNo":$("#wxNo").val()}
        }).done(function(data){
        	$("#login").attr("disabled",false); 
            if(data == 0){
                formerror.html("验证码输入错误，请重新获取");
                $this.val("重试");
                return false;
            }else if(data == 1){
                //formerror.html("登录成功");
                $this.val("登录成功");
                window.location.href=ctx+"/wx/user/index";
            }else {
                $this.val("登录");
            	formerror.html("");
            	return false;
            }
        });
    });
	
    var loadUnitAsync = false, //加载登录单位进程
    	lastUnitPhone; //上次加载的单位手机号
	
	$("#accounts").blur(function(){
		if(lastPhone != this.value){
			$getCode.html("获取验证码");
		}
		//已经在加载 ...或者手机号没变
		if(loadUnitAsync || lastUnitPhone == this.value){return false;}
		$(".unit-lst").html("");
		$("#unit").val("");
		$("#unitId").val("");
	    
		if(!checkcellphone($("#accounts"), formerror)){return false;}
		loadUnitAsync = true;
		$(".unit-lst").addClass("sys-loading");
		lastUnitPhone = this.value;
        $.ajax({
            type:"get",
            url:ctx+"/wx/user/ajax/getAllUnit",
            data:{"phone": $("#accounts").val(), "state":Math.random()}
        }).done(function(data){
        	loadUnitAsync = false;
            var str = "";
            if(data.length == 0){
                formerror.html("系统没找到你的单位");
                return false;
            }else{
                formerror.html("");
            }
            $.each(data, function(i, item){
                if(i==0){
                   str +="<li id='" + item.id + "' class=\"active\">" + item.corporationname + "</li>";
                }else{
                   str +="<li id='" + item.id + "'>" + item.corporationname + "</li>";
                }
            });
            $(".unit-lst").html(str).removeClass("sys-loading");
            $(".unit-lst").show(); 
        });		
	});
	
	
	$("body").on("click", ".unit-lst li", function(e) {
//	$(".unit-lst").on("click", "li", function(e){
		e.stopPropagation();
	   $(".unit-lst").hide();
	   
	   $("#unit").val($(this).html()).blur();
	   $("#unitId").val($(this).attr("id"));
	});
	
	$("#unit").focus(function(e){
	   e.stopPropagation();	 
	   if ($(".unit-lst li").length > 0){
		   $(".unit-lst").show(); 
	   }
	   //点击页面 其他地方关闭unit-lst
	   $("body").one('click', function(){
		   $(".unit-lst").hide();
	   });
	}).click(function(e){e.stopPropagation();});

	//单位搜索功能，文本框内容发生变化则按内容显示相应的单位
	$('#unit').bind('input propertychange', function(e) {
		var uname=$("#unit").val();
		if(this.value == this.defaultValue || this.value == false){
			$(".unit-lst li").show();
			return false;
		}
		$(".unit-lst li").each(function(index){
			 var a= $(this).text().indexOf(uname);
			 if(a>=0){
				 $(this).show();					 
			 }else{
				 $(this).hide();					
			 }
		}); 		
//	   //点击页面 其他地方关闭unit-lst
//	   $("body").one('click', function(){
//		   $(".unit-lst").hide();
//	   });
	});
	
    var $getCode = $("#getCode"),
        lastTime,
        lastPhone,
        timer= null;
    
    $getCode.click(function(){
        if($getCode.hasClass("get-code-ing")){return false;}
        if($("#accounts").val() == lastPhone && $getCode.html() == "休息一下"){return false;}
        if(!checkcellphone($("#accounts"), formerror)){return false;}
        if(!checkunit($("#unit"), formerror)){return false;}
        $getCode.addClass("get-code-ing");
        lastPhone = $("#accounts").val();
        $.ajax({
            type:"get",
            url:ctx+"/front/user/ajax/sendValidCode",
            data:{"phone": $("#accounts").val(), "unit":$("#unitId").val(), "state":Math.random()},
            dataType:"json"
        }).done(function(data){
        	if (data == 1){
        		formerror.html("验证码发送成功，半小时内有效");
        	}else if (data == 2){
        		formerror.html("用户不在所选择的单位中，请重新输入");
        	}else if (data == 3){
        		formerror.html("尊敬的用户请不要频繁操作，请等待半小时");
        		clearTimeout(timer);
        		$getCode.removeClass('get-code-ing').html("休息一下");
        	}else{
        		formerror.html("");
        	}
        });
        lastTime = 60; //重置lastTime的值
        getCode();
    });
    
    function getCode(){
           lastTime--;
           if(lastTime==0){
                $getCode.removeClass('get-code-ing');
                $getCode.html("重新发送");
           }else{
                $getCode.html("重新发送(" +(lastTime) + ")");
                timer = setTimeout(getCode, 1000);                
           }        
    }
	
});



function checkcellphone(jo, joero){
    if($.trim(jo.val()) == "" || jo[0].value == jo[0].defaultValue){
        joero.html("手机号不能为空");
        return false;
    }   
    if(!/^1[3|4|5|6|7|8|9][0-9]{9}$/.test(jo.val())){
        joero.html("手机号码格式不正确");
        return false;
    }
    joero.html("");
    return true;
}

function checkcode(jo, joero){
    if($.trim(jo.val()) == "" || jo[0].value == jo[0].defaultValue){
        joero.html("验证码不能为空");
        return false;
    }   
    joero.html("");
    return true;
}


function checkunit(jo, joero){
    if($.trim(jo.val()) == "" || jo[0].value == jo[0].defaultValue){
        joero.html("登录单位不能为空");
        return false;
   }   
      joero.html("");
    return true;
}
//登录单位ID判断
function checkunitId(jo, joero){
    if($.trim(jo.val()) == ""){
        joero.html("未选择登录单位请重新选择");
        return false;
   }   
      joero.html("");
    return true;
}

