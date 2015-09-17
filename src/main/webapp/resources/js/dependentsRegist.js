function backScreen() {
	if (checkNetworkOffLine('checkDependentsNetworkOffLine','dependentsInfoListModel.errors')) {
		return true;
	} else {
		var form = document.forms["dependentsRegistForm"].action = "dependentsRegistBack";
		form.submit();
	}
};

$(document).ready(function() {
	 var stafName = $('#staffName').val();
	 var dialog = document.getElementById('modal_dialog');
	 var maskHeight = $(document).height();
	    var maskWidth = $(window).width();
	 var flagButtonClear= null;
	 $('.btn-clear').click(function() {
	  flagButtonClear = $(this);
	  $('#overlay').css({height:maskHeight, width:maskWidth}).show();
	  dialog.style.display = 'block';
	  dialog.style.top = (innerHeight - 200) / 2 + 'px';
	  dialog.style.left = (innerWidth - 350) / 2 + 'px';
	  $('.title').html($(this).val() + "の入力値を全て削除します。\nよろしいですか？");
	 });
	 $('#btnYes').click(function() {
	  dialog.style.display = 'none';
	  $('#overlay').hide();
	  flagButtonClear.parents("table").find('input, select').val("");
	  flagButtonClear.parents("table").find('input[type="checkbox"]').prop("checked",false);
	  flagButtonClear.parents("table").find('.lastname').val(stafName);
	  flagButtonClear.parents("table").find('input, select').removeClass("error");
	  var errorOther = document.getElementById('dependentsInfoListModel.errors');

	  var errorText  = errorOther.innerHTML;
	  var array = errorText.split("<br>");
	  for (var int = 0; int < array.length; int++) {
	   if (array[int].indexOf(flagButtonClear.val()) > 0) {
	    array.splice(int, 1);
	    int--;
	   }
	  }
	  console.log(array.join("<br>"));
	  var newError = array.join("<br>").toString();
	  errorOther.innerHTML = newError;
	 });
	 $('#btnNo').click(function() {
	  $('#overlay').hide();
	  dialog.style.display = 'none';
	 });
	});