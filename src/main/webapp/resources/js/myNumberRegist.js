$(document).ready(function() {
	$("#options").hide();
	$('input[name="myNumberConfirm"]').click(function() {
		if ($(this).index() === 0) {
			$("#options").stop().slideUp();
			$('input[type="checkbox"]').removeAttr('checked');
		} else {
			$("#options").stop().slideDown();
		}
	})
	.filter(":checked").trigger("click");
	clearCheckBox();
});

function clearCheckBox(){
	var dtoErrors = document.getElementById('myNumberRegistDto.errors');
	if (dtoErrors.innerText != "") {
		$('input[type="checkbox"]').removeAttr('checked');
	}
}
function checkNetworkOffLine() {
	if (!navigator.onLine) {
		var requireSigning = document.getElementById('checkMyNumberNetworkOffLine');
		requireSigning.style.display = 'block';
		var errorOther = document.getElementById('myNumberRegistDto.errors');
		if(errorOther != null) {
			errorOther.style.display = 'none';
		}

		return true;
	} else {
		return false;
	}
}