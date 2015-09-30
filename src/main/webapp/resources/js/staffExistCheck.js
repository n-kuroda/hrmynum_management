function checkDataValid() {
	clearMessage();

	if (checkNetworkOffLine('checkStaffNetworkOffLine', 'staffInfoModel.errors')) {
		return true;
	} else {
		var form = document.forms["staffExistCheckForm"];
		form.action = "nextToPurposeConsent";
		form.submit();
	}
}

function backScreen() {
	clearMessage();

	if (checkNetworkOffLine('checkStaffNetworkOffLine', 'staffInfoModel.errors')) {
		return true;
	} else {
		var form = document.forms["staffExistCheckForm"].action = "backToShainExistCheck";
		form.submit();
	}
}

function checkDataValidWhenSearch() {
	clearMessage();
	if (checkNetworkOffLine('checkStaffNetworkOffLine', 'staffInfoModel.errors')) {
		return true;
	} else {
		if (checkInput()) {
			$.ajax({
		        url: "removeSessionStaff", // remove session
		        type: 'POST',
		        cache:false,
		        beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        }
		    });
			return true;
		} else {

			var staffNoValue = $('#staffNo').val();
			var tokenValue = $('#token').val();
			var staffInfoMsg = document.getElementById("messageInfoStaffExistCheck");
			 $.ajax({
				 type: "GET",
		            url: "http://10.170.122.93/tact-hr/api/staff/" + staffNoValue,
		            dataType: "jsonp",
		            success: function(data) {

		            	// API returned status code 204: No Content
		            	if (data == undefined) {
		            		// display error 204 on GUI
		            		$('#checkStaffExist').show();
			        		document.getElementById('staffNo').className = 'error';
			        		staffInfoMsg.style.display = 'none';
		            		return;
		            	}

		            	// API returned status code 200: OK
		            	if (data.statusCode == 200) {

		            		// call StaffExistCheckController to set data to model/ session and display data to GUI
		            		var staffInfoDtoResponse = data.staffInfoDto; // get staffInfoDto from response
		            		var staffInfoDto =
		            			{
		            				"staffNo"     : staffInfoDtoResponse.staffNo,
		            				"nameSei"     : staffInfoDtoResponse.nameSei,
		            				"nameMei"     : staffInfoDtoResponse.nameMei,
		            				"nameKanaSei" : staffInfoDtoResponse.nameKanaSei,
		            				"nameKanaMei" : staffInfoDtoResponse.nameKanaMei,
		            				"token"       : tokenValue
		            			};

		            		$.ajax({
		        		        url: "staffExistCheck",
		        		        type: 'POST',
		        		        data: JSON.stringify(staffInfoDto),
		    			        cache:false,
		    			        beforeSend: function(xhr) {
		    			            xhr.setRequestHeader("Accept", "application/json");
		    			            xhr.setRequestHeader("Content-Type", "application/json");
		    			        },
		    			        success: function(response) {
		    			        	 $("#wrapper").html( response );
			   		            	 var staffInfoDiv = document.getElementById('staffInfo');
			   		            	 staffInfoDiv.style.display = 'block';

			   		            	 var staffNo = document.getElementById('staffNo');
			   		            	 staffNo.value = staffNoValue;
		    			        }
		        		    });
		            	}
		            },
		            error: function(dataError) {
		            	// display error on the GUI
		            	if (dataError.statusCode == 204) {
		            		$('#checkStaffExist').show();
			        		document.getElementById('staffNo').className = 'error';
			        		staffInfoMsg.style.display = 'none';
		            		return;
		            	} else {
		            		// display other error on GUI
		            		$('#serverError').show();
			        		document.getElementById('staffNo').className = 'error';
			        		staffInfoMsg.style.display = 'none';
			        		return;
		            	}
		            }
		        });

			 return true;
		}
	}
	return false;
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
	var messageInfo = document.getElementById('staffInfoModel.errors');
	if (messageInfo != null) {
		messageInfo.style.display = 'none';
	}
};

function checkInput() {

	var staffNo = document.getElementById('staffNo').value;
	var requireId = document.getElementById('checkrequireId');
	var checkLengthId = document.getElementById('checkLengthId');
	var checkByteId = document.getElementById('checkByteId');
	var messageInfo = document.getElementById('staffModel.errors');
	var staffInfo = document.getElementById('messageInfoStaffExistCheck');
	clearMessage();

	if (staffNo.length == 0) {
		requireId.style.display = 'block';
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		staffInfo.style.display = 'none';
		document.getElementById('staffNo').className = 'error';
		return true;
	} else {
		var flagCheck = 0;
		var regex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g;
		if (regex.test(staffNo)) {
			checkByteId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			staffInfo.style.display = 'none';
			document.getElementById('staffNo').className = 'error';
			flagCheck++ ;
		}
		if (isNaN(staffNo) || staffNo.length != 9) {
			checkLengthId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			staffInfo.style.display = 'none';
			document.getElementById('staffNo').className = 'error';
			flagCheck++;
		}
		if (flagCheck > 0) {
			return true;
		} else {
			document.getElementById('staffNo').className = '';
			return false;
		}
	}
}