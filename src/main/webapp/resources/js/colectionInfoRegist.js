function checkRequiedCheckBox() {
	clearMessage();
	if (checkNetworkOffLine('checkColectionNetworkOffLine', 'checkRequiedCheckBox')) {
		return true;
	} else {
		var reasonForChoosing = document.getElementById('reasonForChoosing');
		var noWantToProvide = document.getElementById('miteikyoRiyu11');
		var noHouseholdInTheCountry = document.getElementById('miteikyoRiyu21');
		var noHousehold = document.getElementById('miteikyoRiyu31');
		var noMyNumber = document.getElementById('miteikyoRiyu41');
		if (reasonForChoosing != null) {

			if (noWantToProvide.checked == 1 ||
				noHouseholdInTheCountry.checked == 1 ||
				noHousehold.checked == 1 ||
				noMyNumber.checked == 1) {

				callTACTApi(getData(reasonForChoosing, noWantToProvide, noHouseholdInTheCountry, noHousehold, noMyNumber));

				return false;
			}
			var checkCheckBox = document.getElementById('checkRequiedCheckBox');
			checkCheckBox.style.display = 'block';
			var errorOther = document.getElementById('checkColectionNetworkOffLine');
			if(errorOther != null) {
				errorOther.style.display = 'none';
			}
			return true;
		} else {

			callTACTApi(getData(reasonForChoosing, noWantToProvide, noHouseholdInTheCountry, noHousehold, noMyNumber));
		}
	}
}

function clearMessage() {
	var checkCheckBox = document.getElementById('checkRequiedCheckBox');
	var errorOther = document.getElementById('checkColectionNetworkOffLine');
	var registFail = document.getElementById('checkRegistMyNumber');
	registFail.style.display = 'none';
	errorOther.style.display = 'none';
	checkCheckBox.style.display = 'none';
}

function getData(reasonForChoosing, noWantToProvide, noHouseholdInTheCountry, noHousehold, noMyNumber) {
	var miteikyoRiyu1Value = null;
	var miteikyoRiyu2Value = null;
	var miteikyoRiyu3Value = null;
	var miteikyoRiyu4Value = null;
	var staffSignValue = $('#staffSign').val();
	
	if (reasonForChoosing != null) {
		miteikyoRiyu1Value = noWantToProvide.checked ? "1" : "0";
		miteikyoRiyu2Value = noHouseholdInTheCountry.checked ? "1" : "0";
		miteikyoRiyu3Value = noHousehold.checked ? "1" : "0";
		miteikyoRiyu4Value = noMyNumber.checked ? "1" : "0";
	} else {
		miteikyoRiyu1Value = noWantToProvide == null ? "1" : "0";
		miteikyoRiyu2Value = noHouseholdInTheCountry == null ? "1" : "0";
		miteikyoRiyu3Value = noHousehold == null ? "1" : "0";
		miteikyoRiyu4Value = noMyNumber == null ? "1" : "0";
	}
	var dataInfo = JSON.stringify({
			"miteikyoRiyu1" : miteikyoRiyu1Value,
			"miteikyoRiyu2" : miteikyoRiyu2Value,
			"miteikyoRiyu3" : miteikyoRiyu3Value,
			"miteikyoRiyu4" : miteikyoRiyu4Value,
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