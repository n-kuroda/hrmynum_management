$(document).ready(function() {
	$("#options").hide();
	$('input[name="myNumberConfirm"]').click(function() {
		if ($(this).index() === 0) {
			$("#options").stop().slideUp();
			$('input[type="checkbox"]').removeAttr('checked');
		} else {
			$("#options").stop().slideDown();
		}
	})
	.filter(":checked").trigger("click");
});