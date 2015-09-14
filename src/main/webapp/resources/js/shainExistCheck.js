function checkDataValid() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		if (!checkInput()) {
			var form = document.forms["shainExistCheckForm"];
			form.action = "nextToStaffExistCheck";
			form.submit();
		} else {
			return true;
		}
	}
}

function checkDataValidWhenSearch() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		if (checkInput()) {
			return true;
		}
	}
}

function checkNetworkOffLine() {
	if (!navigator.onLine) {
		clearMessage();
		var shainInfo = document.getElementById('messageInfoShainExistCheck');
		shainInfo.style.display = 'none';
		var requireSigning = document
				.getElementById('checkShainNetworkOffLine');
		requireSigning.style.display = 'block';
		var errorOther = document.getElementById('shainInfoModel.errors');
		if (errorOther != null) {
			errorOther.style.display = 'none';
		}
		return true;
	} else {
		return false;
	}
}

function clearMessage() {
	var requireId = document.getElementById('checkrequireId');
	requireId.style.display = 'none';
	var checkLengthId = document.getElementById('checkLengthId');
	checkLengthId.style.display = 'none';
	var checkByteId = document.getElementById('checkByteId');
	checkByteId.style.display = 'none';
	var networkOffLine = document.getElementById('checkShainNetworkOffLine');
	networkOffLine.style.display = 'none';
};

function checkInput() {

	var shainNo = document.getElementById('shainNo').value;
	var requireId = document.getElementById('checkrequireId');
	var checkLengthId = document.getElementById('checkLengthId');
	var checkByteId = document.getElementById('checkByteId');
	var messageInfo = document.getElementById('shainInfoModel.errors');
	var shainInfo = document.getElementById('messageInfoShainExistCheck');
	clearMessage();

	var isCheck = false;
	var regex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g;
	// check required field
	if (shainNo.length == 0) {
		requireId.style.display = 'block';
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		shainInfo.style.display = 'none';
		document.getElementById('shainNo').className = 'error';
		return true;
	} else {
		document.getElementById('shainNo').className = '';
		requireId.style.display = 'none';
	}

	// check max length
	if (shainNo.length != 6) {
		checkLengthId.style.display = 'block';
		if (regex.test(shainNo)) {
			checkByteId.style.display = 'block';
		}
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		shainInfo.style.display = 'none';
		document.getElementById('shainNo').className = 'error';
		return true;
	} else {
		document.getElementById('shainNo').className = '';
		checkLengthId.style.display = 'none';
	}

	// check format
	if (regex.test(shainNo)) {
		checkByteId.style.display = 'block';
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		shainInfo.style.display = 'none';
		document.getElementById('shainNo').className = 'error';
		return true;
	} else {
		document.getElementById('shainNo').className = '';
		checkByteId.style.display = 'none';
	}

	if (shainNo.length == 6) {
		if (/^[a-zA-Z0-9- ]*$/.test(shainNo) == false) {
			checkByteId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			shainInfo.style.display = 'none';
			document.getElementById('shainNo').className = 'error';
			return true;
		}
		if (isNaN(shainNo)) {
			checkLengthId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			shainInfo.style.display = 'none';
			document.getElementById('shainNo').className = 'error';
			return true;
		}
	}

	document.getElementById('shainNo').className = '';
	return isCheck;
}