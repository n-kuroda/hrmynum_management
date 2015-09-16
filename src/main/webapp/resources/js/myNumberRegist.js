$(document).ready(function() {
	$("#options").hide();
	$('input[name="myNumberConfirm"]').click(function() {
		if ($(this).index() === 0) {
			$("#options").stop().slideUp();
		} else {
			$("#options").stop().slideDown();
		}
	})
	.filter(":checked").trigger("click");
	clearCheckBox();
});

function clearCheckBox(){
	var dtoErrors = document.getElementById('staffInfoModel.errors');
	if (dtoErrors != null) {
		$('input[type="checkbox"]').removeAttr('checked');
	}
}

function backScreen() {
	if (checkNetworkOffLine('checkMyNumberNetworkOffLine', 'staffInfoModel.errors')) {
		return true;
	} else {
		var form = document.forms["myNumberRegistForm"].action = "backToPurposeConsent";
		form.submit();
	}
}
