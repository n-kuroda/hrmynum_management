$(document).ready(function() {
	  // Handler for .ready() called.
	var requireId = document.getElementById('checkrequireId');
	requireId.style.display = 'none';
	var checkLengthId = document.getElementById('checkLengthId');
	checkLengthId.style.display = 'none';
	var checkByteId = document.getElementById('checkByteId');
	checkByteId.style.display = 'none';
});

function checkInput() {	
	
	var employeeId = document.getElementById('employeeId').value;
	var requireId = document.getElementById('checkrequireId');
	var checkLengthId = document.getElementById('checkLengthId');
	var checkByteId = document.getElementById('checkByteId');
	var isCheck = false;
	if (employeeId.length == 0) {
		requireId.style.display = 'block';
		isCheck = true;
	} else {
		requireId.style.display = 'none';
	}
	if (employeeId.length < 6) {
		checkLengthId.style.display = 'block';
		if (isNaN(employeeId)) {
			checkLengthId.style.display = 'block';
		}
		isCheck = true;
	} else {
		checkLengthId.style.display = 'none';
	}
	var regex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g; 
	if(regex.test(employeeId)) {
		checkByteId.style.display = 'block';
		isCheck = true;
	} else {
		checkByteId.style.display = 'none';
	}
	
	return isCheck;
}