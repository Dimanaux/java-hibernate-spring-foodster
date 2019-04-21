$(function () {
	$('.question-text').hide(300);
	$(document).on('click', '.question-heading', function (e) {
		e.preventDefault()
		$(this).parents('.spoiler').toggleClass("active").find('.question-text').slideToggle();
	});
});