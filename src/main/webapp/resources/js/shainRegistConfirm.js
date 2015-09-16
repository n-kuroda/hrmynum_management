function initScreen(){
	// get url
	var url = sessionStorage.getObject("signature");
	document.getElementById("signature").src = url;
}

Storage.prototype.getObject = function(key) {
    return JSON.parse(this.getItem(key));
};

function backScreen() {
	if (checkNetworkOffLine('checkRegistNetworkOffLine', null)) {
		return true;
	} else {
		var form = document.forms["registConfirmForm"].action = "shainRegistConfirmModify";
		form.submit();
	}
};
