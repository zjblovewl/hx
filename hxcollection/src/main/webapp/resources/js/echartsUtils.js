$.fn.extend({
    initLineChart:function (data_leg,data_xAxis,data_series,data_xy) {
        var lineChartDom = echarts.init($(this)[0],'westeros');
        //位置
        var grid_arr = [];
        grid_arr.push({
        	bottom:100
        });
        //图例
        var leg_arr = [];
        leg_arr.push({
            data: data_leg,
            left:'center',
            top:'bottom',
            textStyle:{
            	fontSize:16
            }
        });
        //x轴
        var xAxis_arr = [];
        xAxis_arr.push({
            type: 'category',
            name: data_xy[0],
            splitLine:{
              show:false
            },
            data: data_xAxis
        });
        //y轴
        var yAxis_arr = [];
        yAxis_arr.push({
            type: 'value',
            name: data_xy[1]
        });
        //series
        var ser_arr = [];
        for(var i=0;i<data_leg.length;i++){
            ser_arr.push({
                name:data_leg[i],
                data: data_series[i],
                type: 'line',
                smooth: false
            });
        }
        //缩略滑块
        var dataZoom_arr = [];
        dataZoom_arr.push({        	
            type: 'inside',
            start: 0,
            end: 80
        });     
        dataZoom_arr.push({
        	show:true,
            start: 0,
            end: 80,       
            handleSize: '80%',
            borderColor: '#e3e3e3',
            bottom: 40,
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        });
  
        var option = {};
        $.extend(true,option,{
        	tooltip: {},
        	grid: grid_arr,
            legend: leg_arr,
            xAxis: xAxis_arr,
            yAxis: yAxis_arr,
            series: ser_arr
        });
      
        if(data_xAxis.length >10){
        	$.extend(true,option,{
                dataZoom: dataZoom_arr
            });
        }   
        lineChartDom.clear();
        lineChartDom.setOption(option);
        setTimeout(function (){
            window.onresize = function () {
                lineChartDom.resize();
            }
        },200);
    }
});
//时间插件
$(function () {
    $("input[data-type='input-time']").each(function () {
        laydate.render({
            elem:'#'+this.id,
            type:'datetime',
            theme: '#81A1D1',
            max:getNowFormatDate(0),
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
    function getNowFormatDate(code) {
    	var currentdate = new Date();
    	var times = currentdate.getTime();
    	var date = new Date(times - code*24*3600*1000);
    	   	
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
    
    //初始化当前时间
    $("#startTime").val(getNowFormatDate(7));
    $("#endTime").val(getNowFormatDate(0));

   var parentPicOBJ = window.parent;
   function checkTime(start,end) {
       var startMill,endMill;
       start = start.replace(new RegExp("-","gm"),"/");
       end = end.replace(new RegExp("-","gm"),"/");
       endMill = (new Date(end)).getTime(); //得到毫秒数
       startMill = (new Date(start)).getTime();
       var st_en = parseFloat(endMill) - parseFloat(startMill);
       if(!(endMill > startMill)){
    	   parentPicOBJ.$.showSuccessTimeout("结束时间不能大于开始时间");
           setTimeout(function () {
               $("#endTime").val("");
           },50);
       }else if(st_en/1000 > 2592000){
    	   parentPicOBJ.$.showSuccessTimeout("开始时间与结束时间，间隔不能大于30天");
    	   setTimeout(function () {
               $("#endTime").val("");
           },50);
       }
   }
   
});
(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['exports', 'echarts'], factory);
    } else if (typeof exports === 'object' && typeof exports.nodeName !== 'string') {
        // CommonJS
        factory(exports, require('echarts'));
    } else {
        // Browser globals
        factory({}, root.echarts);
    }
}(this, function (exports, echarts) {
    var log = function (msg) {
        if (typeof console !== 'undefined') {
            console && console.error && console.error(msg);
        }
    };
    if (!echarts) {
        log('ECharts is not Loaded');
        return;
    }
    echarts.registerTheme('westeros', {
        "color": [
            "#c12e34",
            "#0000FF",           
            "#2b821d",
            "#005eaa",
            "#d4a4eb",
            "#2ed30e",
            "#3c78ff"
        ],
        "backgroundColor": "rgba(0,0,0,0)",
        "textStyle": {},
        "title": {
            "textStyle": {
                "color": "#333333"
            },
            "subtextStyle": {
                "color": "#333333"
            }
        },
        "line": {
            "itemStyle": {
                "normal": {
                    "borderWidth": "2"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": "2"
                }
            },
            "symbolSize": "6",
            "symbol": "circle",
            "smooth": true
        },
        "radar": {
            "itemStyle": {
                "normal": {
                    "borderWidth": "2"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": "2"
                }
            },
            "symbolSize": "6",
            "symbol": "circle",
            "smooth": true
        },
        "bar": {
            "itemStyle": {
                "normal": {
                    "barBorderWidth": 0,
                    "barBorderColor": "#ccc"
                },
                "emphasis": {
                    "barBorderWidth": 0,
                    "barBorderColor": "#ccc"
                }
            }
        },
        "pie": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "scatter": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "boxplot": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "parallel": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "sankey": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "funnel": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "gauge": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "candlestick": {
            "itemStyle": {
                "normal": {
                    "color": "#edafda",
                    "color0": "transparent",
                    "borderColor": "#d680bc",
                    "borderColor0": "#8fd3e8",
                    "borderWidth": "2"
                }
            }
        },
        "graph": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": 1,
                    "color": "#aaa"
                }
            },
            "symbolSize": "6",
            "symbol": "circle",
            "smooth": true,
            "color": [
                "#ff265f",
                "#7021f3",
                "#e4ec00",
                "#ffb12a",
                "#2eb8ff",
                "#f321ab",
                "#2ed30e",
                "#3c78ff"
            ],
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#eee"
                    }
                }
            }
        },
        "categoryAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#666"                  
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#666"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#666",
                    "fontSize": 14
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#e9f0ff"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.05)",
                        "rgba(200,200,200,0.02)"
                    ]
                }
            }
        },
        "valueAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#666"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#666"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#666",
                    "fontSize": 14
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#e9f0ff"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.05)",
                        "rgba(200,200,200,0.02)"
                    ]
                }
            }
        },
        "logAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#b8c2d8"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#333"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#b8c2d8"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#e9f0ff"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.05)",
                        "rgba(200,200,200,0.02)"
                    ]
                }
            }
        },
        "timeAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#b8c2d8"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#333"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#b8c2d8"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#e9f0ff"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.05)",
                        "rgba(200,200,200,0.02)"
                    ]
                }
            }
        },
        "toolbox": {
            "iconStyle": {
                "normal": {
                    "borderColor": "#999999"
                },
                "emphasis": {
                    "borderColor": "#666666"
                }
            }
        },
        "legend": {
            "textStyle": {
                "color": "#333333"
            }
        },
        "tooltip": {
            "axisPointer": {
                "lineStyle": {
                    "color": "#cccccc",
                    "width": 1
                },
                "crossStyle": {
                    "color": "#cccccc",
                    "width": 1
                }
            }
        },
        "timeline": {
            "lineStyle": {
                "color": "#8fd3e8",
                "width": 1
            },
            "itemStyle": {
                "normal": {
                    "color": "#8fd3e8",
                    "borderWidth": 1
                },
                "emphasis": {
                    "color": "#8fd3e8"
                }
            },
            "controlStyle": {
                "normal": {
                    "color": "#8fd3e8",
                    "borderColor": "#8fd3e8",
                    "borderWidth": 0.5
                },
                "emphasis": {
                    "color": "#8fd3e8",
                    "borderColor": "#8fd3e8",
                    "borderWidth": 0.5
                }
            },
            "checkpointStyle": {
                "color": "#8fd3e8",
                "borderColor": "rgba(138,124,168,0.37)"
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#8fd3e8"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#8fd3e8"
                    }
                }
            }
        },
        "visualMap": {
            "color": [
                "#516b91"
            ]
        },
        "dataZoom": {
            "backgroundColor": "rgba(0,0,0,0)",
            "dataBackgroundColor": "rgba(255,255,255,0.3)",
            "fillerColor": "rgba(167,183,204,0.4)",
            "handleColor": "#a7b7cc",
            "handleSize": "100%",
            "textStyle": {
                "color": "#333333"
            }
        },
        "markPoint": {
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#eee"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#eee"
                    }
                }
            }
        }
    });
}));