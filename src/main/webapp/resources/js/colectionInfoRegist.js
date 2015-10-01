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
	var dataInfo = {
			"miteikyoRiyu1" : miteikyoRiyu1Value,
			"miteikyoRiyu2" : miteikyoRiyu2Value,
			"miteikyoRiyu3" : miteikyoRiyu3Value,
			"miteikyoRiyu4" : miteikyoRiyu4Value,
			"staffSign" : staffSignValue
		};
	return dataInfo;
}

function replaceForJson(string) {
	return string.replace(/'/g, '"');
}

function callTACTApi(dataInfo) {

	var registFail = document.getElementById('checkRegistMyNumber');

	// get value are set from hidden field
	var colectionInfo = jQuery.parseJSON(replaceForJson($('#colectionInfo').val()));

	var himodukeNo   = colectionInfo.himodukeNo;
	var staffNo      = colectionInfo.staffNo;
	var shodakuFlag  = colectionInfo.shodakuFlag;
	var fuyoInfoList = colectionInfo.fuyoInfoList;
	
	 $.ajax({
		 type: "GET",
            url: "http://10.170.122.93/tact-hr/api/colectionInfoRegist/" + himodukeNo + "/" + staffNo + "/" + shodakuFlag + "/" + fuyoInfoList,
            dataType: "jsonp",
            success: function(data, xhr, status) {

            	// show error if data returned is invalid or not OK
            	if (data == undefined || status.status != 200) {
            		// display error on GUI
            		registFail.style.display = 'block';            		
            		return;
            	} else {
            		
            		$.ajax({
            			url : "colectionInfoRegist",
            			type : 'POST',
            			data : JSON.stringify(dataInfo),
            			cache : false,
            			beforeSend : function(xhr) {
            				xhr.setRequestHeader("Accept", "application/json");
            				xhr.setRequestHeader("Content-Type", "application/json");
            			},
            			success : function(redirect_page) {
            				window.location.href = "registComplete";
            			},
            			error : function(jqXhr, textStatus, errorThrown) {
            				registFail.style.display = 'block';
            			}
            		});
            	}
            },
            error: function(data, xhr, status) {
            	// display error on the GUI
            	registFail.style.display = 'block';            	
            	return;
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