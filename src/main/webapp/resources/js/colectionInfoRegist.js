function checkRequiedCheckBox() {
	if (checkNetworkOffLine('checkColectionNetworkOffLine', 'checkRequiedCheckBox')) {
		return true;
	} else {
		var reasonForChoosing = document.getElementById('reasonForChoosing');
		if (reasonForChoosing != null) {
			var noWantToProvide = document.getElementById('miteikyoRiyu11');
			var noHouseholdInTheCountry = document.getElementById('miteikyoRiyu21');
			var noHousehold = document.getElementById('miteikyoRiyu31');
			var noMyNumber = document.getElementById('miteikyoRiyu41');

			if (noWantToProvide.checked == 1 ||
				noHouseholdInTheCountry.checked == 1 ||
				noHousehold.checked == 1 ||
				noMyNumber.checked == 1) {
				return false;
			}
			var checkCheckBox = document.getElementById('checkRequiedCheckBox');
			checkCheckBox.style.display = 'block';
			var errorOther = document.getElementById('checkColectionNetworkOffLine');
			if(errorOther != null) {
				errorOther.style.display = 'none';
			}
			return true;
		}
	}
}

function loadStaffSign(){
	// get url
	var url = sessionStorage.getObject("signature");
	document.getElementById("staffSign").value = url;
}

Storage.prototype.getObject = function(key) {
    return JSON.parse(this.getItem(key));
};