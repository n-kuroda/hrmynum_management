function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["perposeConsentForm"].action = "backToStaffExistCheck";
		form.submit();
	}
}

function skipToSignning() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["perposeConsentForm"].action = "skipToSignningScreen";
		form.submit();
	}
}

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireSigning = document.getElementById('checkPurposeNetworkOffLine');
		requireSigning.style.display = 'block';
		return true;
	} else {
		return false;
	}
}