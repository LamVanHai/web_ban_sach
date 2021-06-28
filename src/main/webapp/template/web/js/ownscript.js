$(document).ready(function() {
	$('.tab-page').click(function(event) {
		/* Act on the event */
		var tab_click = $(this).attr('data-tab');
		var tab_control = $(this).parent().parent().attr('data-tabcontrol');
		$('#' + tab_control + ' .tab-page').removeClass('active');
		$(this).addClass('active');
		$('#' + tab_control + ' .page-content').removeClass('active');
		$('#' + tab_control + ' #' + tab_click + '').addClass('active');
	});

	$('.category-head').click(function(event) {
		/* Act on the event */
		$('.category').slideToggle();
	});

	$('.cart-head').click(function(event) {
		/* Act on the event */
		$('.cart-product').slideToggle();
	});
});


