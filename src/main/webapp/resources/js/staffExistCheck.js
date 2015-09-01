function checkDataValid() {
	var form = document.forms["staffExistCheckForm"].action = "nextToPurposeConsent";
	form.submit();
}

function backScreen() {
	var form = document.forms["staffExistCheckForm"].action = "backToShainExistCheck";
	form.submit();
}