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
	if (checkNetworkOffLine('checkRegistNetworkOffLine', null)) {
		return true;
	} else {
		var form = document.forms["registConfirmForm"].action = "registConfirmBack";
		form.submit();
	}
};
