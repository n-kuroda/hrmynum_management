function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["partnerRegistForm"].action = "partnerRegistBack";
		form.submit();
	}
};

$(document).ready(function() {
	var stafName = $('#staffName').val();
	$('.btn-clear').click(function() {
		var confirmBox = confirm($(this).val() + "の入力値を全て削除します。\nよろしいですか？");
		if (confirmBox == true) {
			$(this).parents("table").find('input, select').val("");
			$(this).parents("table").find('input[type="checkbox"]').prop("checked",false);
			$(this).parents("table").find('.lastname').val(stafName);
		}
	});
});

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireNetwork = document.getElementById('checkPartnerNetworkOffLine');
		requireNetwork.style.display = 'block';
		var errorOther = document.getElementById('lstDependentsInfo.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}
		return true;
	} else {
		return false;
	}
}