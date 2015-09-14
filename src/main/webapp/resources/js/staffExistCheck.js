function checkDataValid() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		if (!checkInput()) {
			var form = document.forms["staffExistCheckForm"];
			form.action = "nextToPurposeConsent";
			form.submit();
		} else {
			return true;
		}
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

function clearMessage() {
	var requireId = document.getElementById('checkrequireId');
	requireId.style.display = 'none';
	var checkLengthId = document.getElementById('checkLengthId');
	checkLengthId.style.display = 'none';
	var checkByteId = document.getElementById('checkByteId');
	checkByteId.style.display = 'none';
	var networkOffLine = document.getElementById('checkStaffNetworkOffLine');
	networkOffLine.style.display = 'none';
};

function checkInput() {

	var staffNo = document.getElementById('staffNo').value;
	var requireId = document.getElementById('checkrequireId');
	var checkLengthId = document.getElementById('checkLengthId');
	var checkByteId = document.getElementById('checkByteId');
	var messageInfo = document.getElementById('shainInfoModel.errors');
	var staffInfo = document.getElementById('messageInfoStaffExistCheck');
	clearMessage();

	var isCheck = false;
	var regex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g;
	// check required field
	if (staffNo.length == 0) {
		requireId.style.display = 'block';
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		staffInfo.style.display = 'none';
		document.getElementById('staffNo').className = 'error';
		return true;
	} else {
		document.getElementById('staffNo').className = '';
		requireId.style.display = 'none';
	}

	// check max length
	if (staffNo.length != 9) {
		checkLengthId.style.display = 'block';
		if (regex.test(staffNo)) {
			checkByteId.style.display = 'block';
		}
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		staffInfo.style.display = 'none';
		document.getElementById('staffNo').className = 'error';
		return true;
	} else {
		document.getElementById('staffNo').className = '';
		checkLengthId.style.display = 'none';
	}

	// check format
	if (regex.test(staffNo)) {
		checkByteId.style.display = 'block';
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		staffInfo.style.display = 'none';
		document.getElementById('staffNo').className = 'error';
		return true;
	} else {
		document.getElementById('staffNo').className = '';
		checkByteId.style.display = 'none';
	}

	if (staffNo.length == 9) {
		if (/^[a-zA-Z0-9- ]*$/.test(staffNo) == false) {
			checkByteId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			staffInfo.style.display = 'none';
			document.getElementById('staffNo').className = 'error';
			return true;
		}
		if (isNaN(staffNo)) {
			checkLengthId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			staffInfo.style.display = 'none';
			document.getElementById('staffNo').className = 'error';
			return true;
		}
	}

	document.getElementById('staffNo').className = '';
	return isCheck;
}