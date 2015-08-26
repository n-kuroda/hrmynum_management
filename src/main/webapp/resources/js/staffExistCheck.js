function checkDataValid() {
	var form = document.forms["staffExistCheckForm"].action = "staffnext";
	form.submit();
}

function backScreen() {
	var form = document.forms["staffExistCheckForm"].action = "staffback";
	form.submit();
}