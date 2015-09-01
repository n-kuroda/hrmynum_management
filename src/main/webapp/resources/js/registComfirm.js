function initScreen(){
	// get url
	var url = localStorage.getObject("signature");
	document.getElementById("signature").src = url;
}

Storage.prototype.getObject = function(key) {
    return JSON.parse(this.getItem(key));
}