$(document).ready(function() {
	var count = $('.slide-show .slide-stage .slide-item').length;
	for(var i = 0; i < count; i++) {
		$(".slide-show .slide-page").append('<li class="page-pointer fa fa-circle-o"></li>');
	}
	$('.slide-show .slide-item:first-child').addClass('active');
	$('.slide-show .page-pointer:first-child').removeClass('fa-circle-o').addClass('fa-circle');
	//function setslideactive
	function setslideactive (index){
		/* body... */
		$('.slide-show .slide-item').stop().animate({opacity: 0});
		$('.slide-show .slide-item:eq(' + index + ')').show().stop().animate({
			opacity: 1}, function() {
			$('.slide-show .slide-item').removeClass('active');
			$('.slide-show .slide-item').hide();
			$('.slide-show .slide-item:eq(' + index + ')').addClass('active').show();
		});
		$('.slide-show .page-pointer').addClass('fa-circle-o').removeClass('fa-circle');
		$('.slide-show .page-pointer:eq(' + index + ')').addClass('fa-circle');
	}

	var index = 0;

	function process(){
		if(index == count - 1)
			index = -1;
		index++;
		setslideactive(index);
	}

	function start_process(){
		timeinterval = 5000;
		play = setInterval(process,timeinterval);
	}

	start_process();

	//stop slide show when mouse hover img
	$('.slide-show .slide-item').hover(function() {
		clearInterval(play);
	}, function() {
		start_process();
	});

	//click page-poiter
	$('.slide-show .page-pointer').click(function() {
		index = $(this).index();
		setslideactive(index);
	});

	//arrow click
	$('.slide-show .arrow-right').click(function() {
		index++;
		if(index == count)
			index = 0;
		setslideactive(index);
	});

	$('.slide-show .arrow-left').click(function() {
		index--;
		if(index < 0)
			index = count - 1;
		setslideactive(index);
	});
});