function initScreen(){
	// get url
	var url = localStorage.getObject("signature");
	document.getElementById("signature").src = url;
	$("#staffSignning").val(url);
}

Storage.prototype.getObject = function(key) {
    return JSON.parse(this.getItem(key));
}

function backScreen() {
	if (checkNetworkOffLine()) {
		return true;
	} else {
		var form = document.forms["registConfirmDto"].action = "registConfirmBack";
		form.submit();
	}
};
function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireSigning = document.getElementById('checkRegistNetworkOffLine');
		requireSigning.style.display = 'block';
		return true;
	} else {
		return false;
	}
}