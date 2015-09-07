function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["partnerRegistForm"].action = "partnerRegistBack";
		form.submit();
	}
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

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireSigning = document.getElementById('checkPartnerNetworkOffLine');
		requireSigning.style.display = 'block';
		var errorOther = document.getElementById('lstDependentsInfo.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}
		return true;
	} else {
		return false;
	}
}