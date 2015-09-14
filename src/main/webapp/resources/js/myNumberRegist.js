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
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["myNumberRegistForm"].action = "backToPurposeConsent";
		form.submit();
	}
}

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireNetwork = document.getElementById('checkMyNumberNetworkOffLine');
		requireNetwork.style.display = 'block';
		var errorOther = document.getElementById('staffInfoModel.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}

		return true;
	} else {
		return false;
	}
}