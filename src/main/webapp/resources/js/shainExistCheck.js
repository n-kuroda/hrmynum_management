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

function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}

function removeParam(key, sourceURL) {
    var rtn = sourceURL.split("?")[0],
        param,
        params_arr = [],
        queryString = (sourceURL.indexOf("?") !== -1) ? sourceURL.split("?")[1] : "";
    if (queryString !== "") {
        params_arr = queryString.split("&");
        for (var i = params_arr.length - 1; i >= 0; i -= 1) {
            param = params_arr[i].split("=")[0];
            if (param === key) {
                params_arr.splice(i, 1);
            }
        }
        rtn = rtn + "?" + params_arr.join("&");
    }
    return rtn;
}
$( document ).ready(function() {
//	clearMessage();
	var errorSession = getURLParameter('errorSession');
	if (errorSession == "true") {
		document.getElementById('checkShainSession').style.display = 'block';
		var url = document.URL.substring(0, document.URL.indexOf("?errorSession"));
		window.history.pushState({}, '', url);
	}
});

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
			 var shainInfoMsg = document.getElementById("messageInfoShainExistCheck");

			 $.ajax({
		            type: "GET",
		            url: "http://10.170.122.93/tact-hr/api/shain/" + shainNoValue,
		            dataType: "jsonp",
		            success: function(data, xhr, status) {
		            	// API returned status code 204: No Content
		            	if (data == undefined) {
		            		// display error 204 on GUI
		            		$('#checkShainExist').show();
			        		document.getElementById('shainNo').className = 'error';
			        		shainInfoMsg.style.display = 'none';
		            		return;
		            	}

		            	// API returned status code 200: OK
		            	if (status.status == 200) {
		            		
		            		// call ShainExistCheckController to set data to model/ session and display data to GUI
		            		var shainInfoResponse = data.shainInfo; // get shainInfo from response and add token to json object
		            		
		            		// result 0
		            		if(shainInfoResponse.nameSei == null ||
                               shainInfoResponse.nameMei == null ||
                               shainInfoResponse.nameKanaSei == null ||
                               shainInfoResponse.nameKanaMei == null){
			                		$('#checkShainExist').show();
					        		document.getElementById('shainNo').className = 'error';
					        		shainInfoMsg.style.display = 'none';
				            		return;
		            		}

		            		var shainInfo =
		            			{
		            				"shainNo"          : shainNoValue,
		            				"shainNameSei"     : shainInfoResponse.nameSei,
		            				"shainNameMei"     : shainInfoResponse.nameMei,
		            				"shainNameSeiKana" : shainInfoResponse.nameKanaSei,
		            				"shainNameMeiKana" : shainInfoResponse.nameKanaMei,
		            				"token"            : tokenValue
		            			};

		            		$.ajax({
		        		        url: "shainExistCheck",
		        		        type: 'POST',
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
		            	}
		            },
		            error: function(data, xhr, status) {
		            	// display error on the GUI
		            	if (status.status == 204) {
		            		$('#checkShainExist').show();
			        		document.getElementById('shainNo').className = 'error';
			        		shainInfoMsg.style.display = 'none';
		            		return;
		            	} else {
		            		// display other error on GUI
		            		$('#serverError').show();
			        		document.getElementById('shainNo').className = 'error';
			        		shainInfoMsg.style.display = 'none';
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
	var networkOffLine = document.getElementById('checkShainNetworkOffLine');
	networkOffLine.style.display = 'none';
	var messageInfo = document.getElementById('shainInfoModel.errors');
	if (messageInfo != null) {
		messageInfo.style.display = 'none';
	}
	var checkShainExist = document.getElementById('checkShainExist');
	checkShainExist.style.display = 'none';
	var errorSession = document.getElementById('checkShainSession');
	errorSession.style.display = 'none';
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