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

	var employeeId = document.getElementById('staffNo').value;
	var requireId = document.getElementById('checkrequireId');
	var checkLengthId = document.getElementById('checkLengthId');
	var checkByteId = document.getElementById('checkByteId');
	var messageInfo = document.getElementById('messageInfo');

	var isCheck = false;
	// check required field
	if (employeeId.length == 0) {
		requireId.style.display = 'block';
		isCheck = true;
	} else {
		requireId.style.display = 'none';
	}

	// check max length
	if (employeeId.length != 9) {
		checkLengthId.style.display = 'block';
		if (isNaN(employeeId)) {
			checkLengthId.style.display = 'block';
		}
		isCheck = true;
	} else {
		checkLengthId.style.display = 'none';
	}

	// check format
	var regex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g;
	if(regex.test(employeeId)) {
		checkByteId.style.display = 'block';
		isCheck = true;
	} else {
		checkByteId.style.display = 'none';
	}

	 if (employeeId.length == 6) {
		  if(/^[a-zA-Z0-9- ]*$/.test(employeeId) == false) {
		   checkByteId.style.display = 'block';
		   isCheck = true;
		  }
		  if (isNaN(employeeId)) {
		   checkByteId.style.display = 'block';
		   isCheck = true;
		  }
		 }

	if (isCheck) {
		messageInfo.style.display = 'none';
	}

	 var errors = document.getElementById('shainExistCheckDto.errors');
	 if (errors != null) {
	  errors.innerHTML ='';
	 }

	return isCheck;
}