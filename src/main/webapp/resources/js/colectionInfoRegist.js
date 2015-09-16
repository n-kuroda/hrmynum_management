function checkRequiedCheckBox() {
	if (checkNetworkOffLine('checkColectionNetworkOffLine', 'checkRequiedCheckBox')) {
		return true;
	} else {
		var reasonForChoosing = document.getElementById('reasonForChoosing');
		if (reasonForChoosing != null) {
			var noWantToProvide = document.getElementById('noWantToProvide');
			var noHouseholdInTheCountry = document.getElementById('noHouseholdInTheCountry');
			var noHousehold = document.getElementById('noHousehold');
			var noMyNumber = document.getElementById('noMyNumber');
			
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
