function checkDataValid() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["shainExistCheckForm"].action = "nextToStaffExistCheck";
		form.submit();
	}
}

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireSigning = document.getElementById('checkShainNetworkOffLine');
		requireSigning.style.display = 'block';
		var errorOther = document.getElementById('shainInfoDto.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}
		return true;
	} else {
		return false;
	}
}