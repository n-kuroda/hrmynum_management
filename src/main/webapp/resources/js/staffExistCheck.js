function checkDataValid() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["staffExistCheckForm"].action = "nextToPurposeConsent";
		form.submit();
	}
}

function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["staffExistCheckForm"].action = "backToShainExistCheck";
		form.submit();
	}
}
function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireSigning = document.getElementById('checkStaffNetworkOffLine');
		requireSigning.style.display = 'block';
		var errorOther = document.getElementById('staffInfoModel.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}
		return true;
	} else {
		return false;
	}
}