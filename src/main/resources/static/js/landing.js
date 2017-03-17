(function($) {
	$(document).ready(
			function() {
				$(".toTop-btn").hide();
				$(function() {
					$(window).scroll(
							function() {
								if ($(this).scrollTop() > 40) {
									$('.toTop-btn').removeClass(
											'animated fadeOutUp').addClass(
											'animated fadeInDown').fadeIn();

								} else {
									$('.toTop-btn').removeClass(
											'animated fadeInDown').addClass(
											'animated fadeOutUp').fadeOut();
								}
							});
				});

			});
}(jQuery));

function toTop() {
	verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
    element = $('body');
    offset = element.offset();
    offsetTop = offset.top;
    $('html, body').animate({scrollTop: offsetTop}, 500, 'linear');
}