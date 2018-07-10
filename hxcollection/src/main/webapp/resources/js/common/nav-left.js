//left 手风琴菜单
$(function(){

	$(document).on('click','h3.title',function(){
		
		if ($(this).next('.item').css('display') == 'none') {
			$(this).find('i').css({
				'transform': 'rotate(-180deg)',
				'transition': 'all .5s'
			});
		}else{
			$(this).find('i').css({
				'transform': 'rotate(0deg)',
				'transition': 'all .5s'
			});
		}
		$(this).next('.item').stop().slideToggle('fast');

	});
	
	$('.left').on('click','li.drop a',function() {
		if ($(this).next('.item2').css('display') == 'none') {
			$(this).find('i').css({
				'transform': 'rotate(-180deg)',
				'transition': 'all .5s'
			});
		}else{
			$(this).find('i').css({
				'transform': 'rotate(0deg)',
				'transition': 'all .5s'
			});
		}
		$(this).next('.item2').stop().slideToggle('fast');
	});
	
	// 收起二级菜单
	//$('.left .item li:not(.drop)').click(function() {
	$('.left').on('click','.item li:not(.drop)',function() {	
		$('.left .item li').removeClass('current');
		
		$(this).addClass('current');
		//$('.item').hide();
		$(this).parent('.item').siblings('.item').slideUp().prev().find('i').css({
			'transform': 'rotate(0deg)',
			'transition': 'all .5s'
		});
		
		
	});
	

	// alert($(document.body).outerHeight(true));
	/*
	var contRight = $('.right').outerHeight(true);
	var contWindow = $(window).height();
	if (contRight>contWindow) {
		$('#source').height(contRight+40);
	}else{
		$('#source').height(contWindow-20);
	}*/
	
	//设定左侧菜单栏高度
	$('#page').css('min-height',$(window).height()-70);
	
});
