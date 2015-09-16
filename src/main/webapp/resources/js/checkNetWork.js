function checkNetworkOffLine(requireNetworkID, errorOtherID) {
	var requireNetwork = document.getElementById(requireNetworkID);
	if (!navigator.onLine) {
		requireNetwork.style.display = 'block';
		if (errorOtherID != null) {
			var errorOther = document.getElementById(errorOtherID);
			if (errorOther != null) {
				errorOther.style.display = 'none';
			}
		}
		return true;
	} else {
		return false;
	}
}