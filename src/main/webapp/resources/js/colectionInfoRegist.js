function checkRequiedCheckBox() {
	clearMessage();
	if (checkNetworkOffLine('checkColectionNetworkOffLine', 'checkRequiedCheckBox')) {
		return true;
	} else {
		var reasonForChoosing = document.getElementById('reasonForChoosing');
		var miteikyoRiyu1 = document.getElementById('miteikyoRiyu1');
		var miteikyoRiyu2 = document.getElementById('miteikyoRiyu2');
		var miteikyoRiyu3 = document.getElementById('miteikyoRiyu3');
		var miteikyoRiyu4 = document.getElementById('miteikyoRiyu4');
		var miteikyoRiyu5 = document.getElementById('miteikyoRiyu5');
		if(document.getElementById('reasonForChoosing') != null){
			var miteikyoRiyu6 = document.getElementById('miteikyoRiyu6').value;
		}
		
		if (reasonForChoosing != null) {
			
			var inputError = false;

			// no select check
			if (!miteikyoRiyu1.checked && !miteikyoRiyu2.checked && !miteikyoRiyu3.checked && !miteikyoRiyu4.checked && !miteikyoRiyu5.checked) {
				var checkCheckBox = document.getElementById('checkRequiedCheckBox');
				checkCheckBox.style.display = 'block';
				var errorOther = document.getElementById('checkColectionNetworkOffLine');
				if(errorOther != null) {
					errorOther.style.display = 'none';
				}
				inputError = true;
			}
			
			if(400 < miteikyoRiyu6.length){
				var checkOtherResonlength = document.getElementById('checkOtherResonlength');
				checkOtherResonlength.style.display = 'block';
				var errorOther = document.getElementById('checkColectionNetworkOffLine');
				if(errorOther != null) {
					errorOther.style.display = 'none';
				}
				inputError = true;			
			}
			
			// correlation check
			if(miteikyoRiyu5.checked && miteikyoRiyu6.length == 0){
				var checkOtherReson = document.getElementById('checkOtherReson');
				checkOtherReson.style.display = 'block';
				var errorOther = document.getElementById('checkColectionNetworkOffLine');
				if(errorOther != null) {
					errorOther.style.display = 'none';
				}
				document.getElementById('miteikyoRiyu6').className = 'error';
				inputError = true;				
			}
			if(!miteikyoRiyu5.checked && miteikyoRiyu6.length != 0){
				var checkOtherReson = document.getElementById('checkOtherCheck');
				checkOtherReson.style.display = 'block';
				var errorOther = document.getElementById('checkColectionNetworkOffLine');
				if(errorOther != null) {
					errorOther.style.display = 'none';
				}
				inputError = true;
			}
			
			if(inputError){
				return true;
			}
			
			// regist
			callTACTApi(getData(reasonForChoosing, miteikyoRiyu1, miteikyoRiyu2, miteikyoRiyu3, miteikyoRiyu4, miteikyoRiyu5, miteikyoRiyu6));
			return false;

		} else {

			callTACTApi(getData(reasonForChoosing, miteikyoRiyu1, miteikyoRiyu2, miteikyoRiyu3, miteikyoRiyu4, miteikyoRiyu5, miteikyoRiyu6));
		}
	}
}

function clearMessage() {
	document.getElementById('checkRegistMyNumber').style.display = 'none';
	document.getElementById('checkColectionNetworkOffLine').style.display = 'none';
	document.getElementById('checkRequiedCheckBox').style.display = 'none';
	document.getElementById('checkOtherReson').style.display = 'none';
	document.getElementById('checkOtherCheck').style.display = 'none';
	if(document.getElementById('reasonForChoosing') != null){
		document.getElementById('miteikyoRiyu6').className = '';
	}
	
}

function getData(reasonForChoosing, miteikyoRiyu1, miteikyoRiyu2, miteikyoRiyu3, miteikyoRiyu4, miteikyoRiyu5, miteikyoRiyu6) {
	var miteikyoRiyu1Value = null;
	var miteikyoRiyu2Value = null;
	var miteikyoRiyu3Value = null;
	var miteikyoRiyu4Value = null;
	var miteikyoRiyu5Value = null;
	var staffSignValue = $('#staffSign').val();
	
	if (reasonForChoosing != null) {
		miteikyoRiyu1Value = miteikyoRiyu1.checked ? "1" : "0";
		miteikyoRiyu2Value = miteikyoRiyu2.checked ? "1" : "0";
		miteikyoRiyu3Value = miteikyoRiyu3.checked ? "1" : "0";
		miteikyoRiyu4Value = miteikyoRiyu4.checked ? "1" : "0";
		miteikyoRiyu5Value = miteikyoRiyu5.checked ? "1" : "0";
	} else {
		miteikyoRiyu1Value = miteikyoRiyu1 == null ? "1" : "0";
		miteikyoRiyu2Value = miteikyoRiyu2 == null ? "1" : "0";
		miteikyoRiyu3Value = miteikyoRiyu3 == null ? "1" : "0";
		miteikyoRiyu4Value = miteikyoRiyu4 == null ? "1" : "0";
		miteikyoRiyu5Value = miteikyoRiyu5 == null ? "1" : "0";
	}
	var dataInfo = JSON.stringify({
			"miteikyoRiyu1" : miteikyoRiyu1Value,
			"miteikyoRiyu2" : miteikyoRiyu2Value,
			"miteikyoRiyu3" : miteikyoRiyu3Value,
			"miteikyoRiyu4" : miteikyoRiyu4Value,
			"miteikyoRiyu5" : miteikyoRiyu5Value,
			"miteikyoRiyu6" : miteikyoRiyu6,
			"staffSign" : staffSignValue
		});
	return dataInfo;
}

function replaceForJson(string) {
	return string.replace(/'/g, '"');
}

function callTACTApi(dataInfo) {

	var registFail = document.getElementById('checkRegistMyNumber');
	
	 $.ajax({
		    type: "POST",
            url: "colectionInfoRegist",
            data: dataInfo,
			cache : false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
            xhrFields: {
                withCredentials: true
              },
            success: function(data, xhr, status) {
            	window.location.href = "registComplete";            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            	console.log("NG:" + textStatus.status);
            	// display error on the GUI
            	registFail.style.display = 'block';            	
            }
	 });
}

function loadStaffSign(){
	// get url
	var url = sessionStorage.getObject("signature");
	document.getElementById("staffSign").value = url;
}

Storage.prototype.getObject = function(key) {
    return JSON.parse(this.getItem(key));
};