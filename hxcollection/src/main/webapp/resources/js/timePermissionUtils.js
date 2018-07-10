//时间插件
$(function () {
    $("input[data-type='input-time']").each(function () {
        laydate.render({
            elem:'#'+this.id,
            type:'datetime',
            theme: '#81A1D1',          
            done: function(value, date, endDate){
                if(this.elem.selector == "#endTime"){
                    var start = $("#startTime").val();
                    if(start != "" && start != undefined){
                        checkTime(start,value);
                    }
                }else{
                    var end = $("#endTime").val();
                    if(end != "" && end != undefined){
                        checkTime(value,end);
                    }
                }
            }
        }) ;
        
        var timeIcon = '<span class="fa fa-calendar time_icon" aria-hidden="true"></span>';
        $(this).after(timeIcon);
    
    });
    /*当前时间*/
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +" " + date.getHours() + seperator2 + date.getMinutes()+ seperator2 + date.getSeconds();
        return currentdate;
    }
   function checkTime(start,end) {
       var startMill,endMill;
       start = start.replace(new RegExp("-","gm"),"/");
       end = end.replace(new RegExp("-","gm"),"/");
       endMill = (new Date(end)).getTime(); //得到毫秒数
       startMill = (new Date(start)).getTime();
       if(!(endMill > startMill)){
           alert("结束时间不能大于开始时间");
           setTimeout(function () {
               $("#endTime").val("");
           },300);
       }
   }
   
});
