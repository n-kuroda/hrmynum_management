function backScreen() {
	if (checkNetworkOffLine('checkPurposeNetworkOffLine', null)) {
		return true;
	} else {
		var form = document.forms["perposeConsentForm"].action = "backToStaffExistCheck";
		form.submit();
	}
}

function skipToSigning() {
	if (checkNetworkOffLine('checkPurposeNetworkOffLine', null)) {
		return true;
	} else {
		var form = document.forms["perposeConsentForm"].action = "skipToStaffRegistConfirm";
		form.submit();
	}
}
