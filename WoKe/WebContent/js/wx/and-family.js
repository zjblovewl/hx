//zmt-nav
$('.zmt-nav').on('click', 'a', function () {
    var $this = $(this),
        $ul = $this.parents('ul'),
        sum = $ul.find('li').length,
        index = $this.parent('li').index();

    $this.addClass('selected').parents('li').siblings('li').children('a').removeClass('selected');
    $ul.siblings('.icon-arrow-top').animate({
        "left": 24.5+100/sum*index + "%"
    });
    $ul.parents('.zmt-nav').siblings('.zmt-container').find('.zmt-list').eq(index).fadeIn().siblings().hide();
});

$('.zmt-list').on('click', 'li', function () {
    var $this = $(this),
        url = $this.data('url');

    if(url) window.location.href = url;
});

$('.msg-list-wrap').on('click', '.msg-item-bd', function () {
    var $this = $(this),
        url = $this.data('url');

    if(url) window.location.href = url;

}).on('click', '.msg-list-load-more', function () {
    var $this = $(this),
        $msgList = $this.siblings('.msg-list');

    $msgList.append('<div>i am example.</div>');
});

$(".home-slide").jswitch({
    autoplay : true ,  // true, false 自动切换
    interval : 5000, //切换的时间间隔
    //    	prev : ".slide-prev", // 可无 上一帧
    //    	next : ".slide-next",// 可无 下一帧
    enableTouch : true,
    //triggerClass : "slide-trigger", // trigger的类名未实现
    //trigger : true, //true(有trigger), false(无trigger), classname(用页面已有的dom来控制切换)
    duration : 500, //动画延续的时间
    effect : 'slideLeft' // fade, slideLeft, -slideUp未实现-
 });

$('.slide-content').on('click', 'li', function (ev) {
    var $this = $(this);

    location.href = $this.data('url');
});
$('.zmt-weather').on('click', 'img',function () {
    var $this = $(this),
        $weather = $this.parents('.zmt-weather'),
        index = $weather.siblings('.home-slide').find('.current').index();

    $weather.siblings('.home-slide').find('li').eq(index).trigger('click');
}).on('click', 'h3',function () {
    var $this = $(this),
        $weather = $this.parents('.zmt-weather'),
        index = $weather.siblings('.home-slide').find('.current').index();

    $weather.siblings('.home-slide').find('li').eq(index).trigger('click');
}).on('click', 'p',function () {
    location.href = $(this).data('href');
});