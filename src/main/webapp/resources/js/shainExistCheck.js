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
		var shainInfo = document.getElementById('messageInfoShainExistCheck');
		shainInfo.style.display = 'none';
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
			 var shainNo = {"shainNo" : shainNoValue};
			$.ajax({
		        url: "shainExistCheck",
		        type: 'POST',
		        data: JSON.stringify(shainNo),
		        cache:false,
		        beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        },
		        success:function(shainInfoResponseDto){
		        	var shainInfo = document.getElementById("messageInfoShainExistCheck");
		        	if (shainInfoResponseDto.httpStatus == 200) {
		        		clearMessage();
		        		var shainInfoDto = shainInfoResponseDto.shainInfoDto;
		        		var shainResponse = "<table>";
		        		shainResponse += "<tr>";
		        		shainResponse += "<td class='leftLabel'>社員番号 </td>";
		        		shainResponse += "<td class='rightLabel'>" + shainInfoDto.shainNo + "</td>";
						shainResponse += "</tr>";
						shainResponse += "<tr>";
		        		shainResponse += "<td class='leftLabel'>お名前</td>";
		        		shainResponse += "<td class='rightLabel'>" +
		        			shainInfoDto.shainNameSei + " " + shainInfoDto.shainNameMei +
		        			"(" +shainInfoDto.shainNameSeiKana + " " + shainInfoDto.shainNameMeiKana +")</td>";
						shainResponse += "</tr>";
						shainResponse += "</table>";
						shainResponse += "<div class='mt20 ml20'>よろしければ「次へ」ボタンを押してください。</div>";
						shainInfo.innerHTML = shainResponse;
						shainInfo.style.display = 'block';
		        	} else if(shainInfoResponseDto.httpStatus == 204) {
		        		clearMessage();
		        		$('#checkShainExist').show();
		        		document.getElementById('shainNo').className = 'error';
		        		shainInfo.style.display = 'none';
		        	} else {
		        		clearMessage();
		        		$('#serverError').show();
		        		document.getElementById('shainNo').className = 'error';
		        		shainInfo.style.display = 'none';
		        	}
		        },
		        error:function(jqXhr, textStatus, errorThrown){
		        	alert(textStatus);
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
	var networkOffLine = document.getElementById('checkShainNetworkOffLine');
	networkOffLine.style.display = 'none';
	var messageInfo = document.getElementById('shainInfoModel.errors');
	if (messageInfo != null) {
		messageInfo.style.display = 'none';
	}
	$('#checkShainExist').hide();
	$('#serverError').hide();
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