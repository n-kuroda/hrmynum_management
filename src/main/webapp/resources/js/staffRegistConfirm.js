function backScreen() {
	if (checkNetworkOffLine('checkRegistNetworkOffLine', null)) {
		return true;
	} else {
		var form = document.forms["registConfirmForm"].action = "staffRegistConfirmBack";
		form.submit();
	}
};
