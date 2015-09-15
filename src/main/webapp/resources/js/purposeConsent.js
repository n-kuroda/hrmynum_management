function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["perposeConsentForm"].action = "backToStaffExistCheck";
		form.submit();
	}
}

function skipToSigning() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["perposeConsentForm"].action = "skipToStaffRegistConfirm";
		form.submit();
	}
}

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireNetwork = document.getElementById('checkPurposeNetworkOffLine');
		requireNetwork.style.display = 'block';
		return true;
	} else {
		return false;
	}
}