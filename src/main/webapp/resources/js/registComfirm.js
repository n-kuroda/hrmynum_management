function initScreen(){
	// get url
	var url = sessionStorage.getObject("signature");
	document.getElementById("signature").src = url;
	document.getElementById("staffSign").value = url;
}

Storage.prototype.getObject = function(key) {
    return JSON.parse(this.getItem(key));
};

function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["registConfirmForm"].action = "registConfirmBack";
		form.submit();
	}
};
function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireNetwork = document.getElementById('checkRegistNetworkOffLine');
		requireNetwork.style.display = 'block';
		return true;
	} else {
		return false;
	}
}