function backScreen() {
	var form = document.forms["partnerRegistForm"].action = "backToMyNumberRegist";
	form.submit();
};

$(document).ready(function() {
	$('.btn-clear').click(function() {
		var confirmBox = confirm($(this).val() + "の入力値を全て削除します。\nよろしいですか？");
		if (confirmBox == true) {
			$(this).parents(".box").find('input').val("");
			$(this).parents(".box").find('select').val("");
		}
	});
});