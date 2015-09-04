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
	var form = document.forms["registConfirmDto"].action = "registConfirmBack";
	form.submit();
};