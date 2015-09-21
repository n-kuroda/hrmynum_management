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
			var staffNo = {"staffNo" : staffNoValue};
			var staffInfo = document.getElementById("messageInfoStaffExistCheck");
			
			$.ajax({
		        url: "staffExistCheck",
		        type: 'POST',
		        data: JSON.stringify(staffNo),
		        cache:false,
		        beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        },
		        success:function(staffInfoResponseDto){
		        	
		        	if (staffInfoResponseDto !== undefined) { // JSON returned OK
		        		clearMessage();
		        		var staffInfoDto = staffInfoResponseDto.staffInfoDto;
		        		var staffResponse = "<table>";
		        		staffResponse += "<tr>";
		        		staffResponse += "<td class='leftLabel'>スタッフNo</td>";
		        		staffResponse += "<td class='rightLabel'>" + staffNoValue + "</td>";
		        		staffResponse += "</tr>";
		        		staffResponse += "<tr>";
		        		staffResponse += "<td class='leftLabel'>お名前</td>";
		        		staffResponse += "<td class='rightLabel'>" +
		        			staffInfoDto.nameSei + " " + staffInfoDto.nameMei +
		        			"(" +staffInfoDto.nameKanaSei + " " + staffInfoDto.nameKanaMei +")</td>";
		        		staffResponse += "</tr>";
		        		staffResponse += "</table>";
		        		staffResponse += "<div class='mt20 ml20'>よろしければ「次へ」ボタンを押してください。</div>";
		        		staffInfo.innerHTML = staffResponse;
		        		staffInfo.style.display = 'block';
		        	} else {
		        		clearMessage();
		        		$('#checkStaffExist').show();
		        		document.getElementById('staffNo').className = 'error';
		        		staffInfo.style.display = 'none';
		        	}
		        },
		        error:function(jqXhr, textStatus, errorThrown){
		        	clearMessage();
	        		$('#checkStaffExist').show();
	        		document.getElementById('staffNo').className = 'error';
	        		staffInfo.style.display = 'none';
		        }
		    });
			return true;
		}
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
	var messageInfo = document.getElementById('staffInfoModel.errors');
	if (messageInfo != null) {
		messageInfo.style.display = 'none';
	}
	$('#checkStaffExist').hide();
	$('#serverError').hide();
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