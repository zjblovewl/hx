 $(function () {
        var array_tabs = [{
            title:"用户操作历史",
            url:"../jsp/user/userOperationLog.jsp"
        }];
        /*开发展开用户管理*/
        $("#menu_dropdown").on('click', ".menu-parent", function (ele) {
            $("#first-span").text(" >"+$(this).text());
            $("#second-span").text("");
            $("#third-span").text("");
            $(this).next().slideToggle();
            var clickIndex = $(".menu-parent").index(this);
            $(".menu-parent-ul").each(function (index, ele) {
                if (index != clickIndex) {
                    $(this).slideUp();
                }
            });
        });
        $("#menu_dropdown").on('click', ".menu-child-a", function () {
            var url = $(this).attr("data-href");
            $(".menu_dropdown_li_current").each(function (index,ele) {
                $(this).parent().children("li").removeClass("menu_dropdown_li_current");
            });
            $(this).parent().addClass("menu_dropdown_li_current");
            if(url != "undefined" && url != undefined && url != "#"){
                var txt = $(this).text();
                var isExist = false;
                for(var i=0;i<array_tabs.length;i++) {
                    if(array_tabs[i].title == txt){
                        isExist = true;
                    }
                }
                if(!isExist){
                    var tab_json = {
                        title:txt,
                        url:url
                    }
                    array_tabs.push(tab_json);
                    createTab(array_tabs);//创建tab
                }else{
                    //tabs 与左侧联动
                    for(var i=0;i<array_tabs.length;i++) {
                        if(array_tabs[i].title == txt){
                            $("#Hui_tabs_ul").children().eq(i).addClass("color_theme");
                            $("#Hui_tabs_ul").children().eq(i).siblings().removeClass("color_theme");
                        }
                    }
                }
                $("#second-span").text(" >"+txt);
                $("#iframe_box").find("iframe").attr("src", url);
                //判断服务端返回的状态码
                getServerStatus(url);

            }else if(url == "#"){
                $("#iframe_box").find("iframe").attr("src", "../jsp/404.jsp");
            }
            $(this).next().slideToggle();
        });

        $("#Hui_tabs_ul").on("click",'li a',function () {
           $(this).parent().addClass("color_theme");
           $(this).parent().siblings().removeClass("color_theme");
           //tab 与 左侧联动
           var currentTxt = $(this).text();
            $(".menu_dropdown_li_current").each(function (index,ele) {
                $(this).parent().children("li").removeClass("menu_dropdown_li_current");
            });
            $(".menu-child-a").each(function () {
               if($(this).text() == currentTxt) {
                   $(this).parent().addClass("menu_dropdown_li_current");
               }
            });

           var url = $(this).parent().attr("data-href");
           $("#iframe").attr("src", url);
           //tab 滚动动画
           scroll2center($(this));
        });

        $("#Hui_tabs_ul").on("click",'.deleTab',function () {
            var deleTxt = $(this).parent().text();
            var deleUrl = $(this).parent().attr("data-href");
            array_tabs.remove({title:deleTxt,url:deleUrl});
            //判断删除的条目
            var clazz = $(this).parent().attr("class");
            $(this).parent().remove();
            if(clazz == "color_theme"){
                $("#Hui_tabs_ul").children(':last-child').addClass("color_theme");

//            createTab(array_tabs);//创建tab
            //tabs 与左侧联动
            var currentUrl = $("#Hui_tabs_ul").find('li[class="color_theme"]').attr("data-href");
            var currentTxt = $("#Hui_tabs_ul").find('li[class="color_theme"]').text();

            $(".menu_dropdown_li_current").each(function (index,ele) {
                $(this).parent().children("li").removeClass("menu_dropdown_li_current");
            });
            $(".menu-child-a").each(function () {
                if($(this).text() == currentTxt) {
                    $(this).parent().addClass("menu_dropdown_li_current");
                }
            });
            $("#iframe_box").find("iframe").attr("src",currentUrl)
            document.frames('iframe').location.reload();
            }
        });

        Array.prototype.remove = function(val) {
            for(var i=0;i<this.length;i++){
                if(this[i].title == val.title){
                    this.splice(i, 1);
                    return;
                }
            }
        };

        function createTab(array_tabs) {
            var total_tabs ="";
            for(var i=0;i<array_tabs.length;i++){
                if(i == array_tabs.length-1 && i!=0){
                    total_tabs +='<li data-href="'+array_tabs[i].url+'" class="color_theme"><a>'+array_tabs[i].title+'</a><i class="fa fa-close deleTab" aria-hidden="true"></i></li>';
                }else if(i==0){
                    if(array_tabs.length-1 == 0){
                        total_tabs +='<li data-href="'+array_tabs[i].url+'" class="color_theme"><a>'+array_tabs[i].title+'</a></li>';
                    }else{
                        total_tabs +='<li data-href="'+array_tabs[i].url+'"><a>'+array_tabs[i].title+'</a></li>';
                    }
                }else{
                    total_tabs +='<li data-href="'+array_tabs[i].url+'"><a>'+array_tabs[i].title+'</a><i class="fa fa-close deleTab" aria-hidden="true"></i></li>';
                }
            }
            $("#Hui_tabs_ul").html(total_tabs);
            isMove("left");
        }
        //控制左右滚动
        function scroll2center($this) {
            var currentUlWidth = $(".Hui_tabs_ul").width();
            var currentDivWidth = $(".Hui-tabs").width()-161;

            var clickIndex = 0;
            $("#Hui_tabs_ul").find('li').each(function (index,ele) {
                if($this.text() == $(this).text()){
                    clickIndex = index+1;
                }
            });
            if(currentUlWidth > currentDivWidth){
                if(clickIndex > $("#Hui_tabs_ul").find('li').length/2){
                    var offLef = currentUlWidth - currentDivWidth;
                    $("#Hui_tabs_ul").animate({marginLeft:(-offLef)+'px'},500,'easeInCubic');
                }else{
                    $("#Hui_tabs_ul").animate({marginLeft:'0px'},500,'easeInCubic');
                }
            }
        }
        $("#left-scroll").click(function () {
            isMove("left");
        });
        $("#right-scroll").click(function () {
            isMove('right');
        });
        function isMove(type) {
            var currentUlWidth = $(".Hui_tabs_ul").width();
            var currentDivWidth = $(".Hui-tabs").width()-161;
            var offLef = currentUlWidth - currentDivWidth;
            if(currentUlWidth > currentDivWidth){
                var marLeft = parseInt($("#Hui_tabs_ul").css('marginLeft'));
                if(marLeft<10 && type=='left'){
                    //左移动
                    $("#Hui_tabs_ul").animate({marginLeft:(-offLef)+'px'},400,'easeInCubic');
                }else if(marLeft <= -10 && type=='right'){
                    //右移动
                    $("#Hui_tabs_ul").animate({marginLeft:'0px'},400,'easeInCubic');
                }
            }
        }
             
        //判断服务器返回的状态码
        function getServerStatus(url){
        	console.log(url)
        	$.ajax({
        	    type: 'get', 
        	    url : url,
        	    complete: function( xhr,data ){
        	        var wpoInfo = {
        	        	status:xhr.status,       	           
        	            contentLength : xhr.getResponseHeader('Content-Length'),       	       
        	        };
        	      
        	    }
        	});
        }
        //确认对话框
        $.showConfirm = function(str, funcok,funCancel) {
            BootstrapDialog.confirm({            	
                title : '操作提示',
                message : str,
                closable : true, 
                draggable : true, 
                btnCancelLabel : '取消', 
                btnOKLabel : '确定', 
                btnOKClass : 'btn-warning',
                size : BootstrapDialog.SIZE_SMALL,               
                callback : function(result) {             
                    if (result) {
                        funcok.call();
                    }else {
						funCancel.call();
					}
                }
            });
        };
        //对话框
        $.showSuccessTimeout = function(str, func) {
            BootstrapDialog.show({
                title : "提示",
                message : str,
                size : BootstrapDialog.SIZE_SMALL,
                buttons : [ {
                    label : '确定',
                    action : function(dialogItself) {
                        dialogItself.close();
                    }
                } ],
                // 指定时间内可自动关闭
                onshown : function(dialogRef) {
                    setTimeout(function() {
                        dialogRef.close();
                    }, 3000);
                },
                onhide : func
            });
        };
 });
