function checkDataValid() {
	clearMessage();
	if (checkNetworkOffLine('checkShainNetworkOffLine', 'shainInfoModel.errors')) {
		var shainInfo = document.getElementById('messageInfoShainExistCheck');
		shainInfo.style.display = 'none';
		return true;
	} else {
		var form = document.forms["shainExistCheckForm"];
		form.action = "nextToStaffExistCheck";
		form.submit();
	}
}

function checkDataValidWhenSearch() {
	clearMessage();
	if (checkNetworkOffLine('checkShainNetworkOffLine', 'shainInfoModel.errors')) {
		return true;
	} else {
		if (checkInput()) {
			$.ajax({
		        url: "removeSessionShain", // remove session
		        type: 'POST',
		        cache:false,
		        beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        }
		    });
			return true;
		} else {
			
			 var shainNoValue = $('#shainNo').val();
			 var tokenValue = $('#token').val();
			 var shainInfo = {"shainNo" : shainNoValue, "token" : tokenValue};
			 
			 $.ajax({
		            type: "POST",
		            url: "shainExistCheck",
		            data: JSON.stringify(shainInfo),
			        cache:false,
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			        },
		            success: function(response) {
		            	 $("#wrapper").html( response );
		            	 var shainInfoDiv = document.getElementById('shainInfo');
		            	 shainInfoDiv.style.display = 'block';
		            	 
		            	 var shainNo = document.getElementById('shainNo');
		            	 shainNo.value = shainNoValue;
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
	var networkOffLine = document.getElementById('checkShainNetworkOffLine');
	networkOffLine.style.display = 'none';
	var messageInfo = document.getElementById('shainInfoModel.errors');
	if (messageInfo != null) {
		messageInfo.style.display = 'none';
	}
};

function checkInput() {

	var shainNo = document.getElementById('shainNo').value;
	var requireId = document.getElementById('checkrequireId');
	var checkLengthId = document.getElementById('checkLengthId');
	var checkByteId = document.getElementById('checkByteId');
	var messageInfo = document.getElementById('shainInfoModel.errors');
	var shainInfo = document.getElementById('messageInfoShainExistCheck');
	clearMessage();

	if (shainNo.length == 0) {
		requireId.style.display = 'block';
		if (messageInfo != null) {
			messageInfo.style.display = 'none';
		}
		shainInfo.style.display = 'none';
		document.getElementById('shainNo').className = 'error';
		return true;
	} else {
		var flagCheck = 0;
		var regex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g;
		if (regex.test(shainNo)) {
			checkByteId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			shainInfo.style.display = 'none';
			document.getElementById('shainNo').className = 'error';
			flagCheck++ ;
		}
		if (isNaN(shainNo) || shainNo.length != 6) {
			checkLengthId.style.display = 'block';
			if (messageInfo != null) {
				messageInfo.style.display = 'none';
			}
			shainInfo.style.display = 'none';
			document.getElementById('shainNo').className = 'error';
			flagCheck++;
		}
		if (flagCheck > 0) {
			return true;
		} else {
			document.getElementById('shainNo').className = '';
			return false;
		}
	}
}