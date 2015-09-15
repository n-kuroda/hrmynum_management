function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["dependentsRegistForm"].action = "dependentsRegistBack";
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
			$(this).parents("table").find('input, select').removeClass("error");
			var errorOther = document.getElementById('dependentsInfoListModel.errors');
			if(errorOther != null) {
				errorOther.style.display = 'none';
			}
		}
	});
});

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireNetwork = document.getElementById('checkDependentsNetworkOffLine');
		requireNetwork.style.display = 'block';
		var errorOther = document.getElementById('dependentsInfoListModel.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}
		return true;
	} else {
		return false;
	}
}