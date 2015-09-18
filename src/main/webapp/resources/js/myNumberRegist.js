$(document).ready(function() {
	var hasStop = true;
	var prevIndex = null;
	$('input[name="myNumberConfirm"]').click(function() {
		if ($(this).index() === 0) {
			$("#options").stop().slideUp();
			prevIndex = $(this).index();
		} else {
			var dtoErrors = document.getElementById('staffInfoModel.errors');
			if (dtoErrors != null && hasStop && prevIndex == null) {
				$("#options").show();
				hasStop = false;
			} else {
				$("#options").stop().slideDown();
			}
		}
	})
	.filter(":checked").trigger("click");
});

function backScreen() {
	if (checkNetworkOffLine('checkMyNumberNetworkOffLine', 'staffInfoModel.errors')) {
		return true;
	} else {
		var form = document.forms["myNumberRegistForm"].action = "backToPurposeConsent";
		form.submit();
	}
}
