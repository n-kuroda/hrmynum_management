function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["registConfirmForm"].action = "staffRegistConfirmBack";
		form.submit();
	}
};

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireNetwork = document.getElementById('checkRegistNetworkOffLine');
		requireNetwork.style.display = 'block';
		return true;
	} else {
		return false;
	}
}