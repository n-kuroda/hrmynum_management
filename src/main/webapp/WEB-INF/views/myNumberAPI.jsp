<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<title>Spring AJAX and JQuery</title>
<script type="text/javascript">

function callMyNumberAPI(){
	
	var himodukeNo = "12345-7812345-7812345-7812345-78-111";  
	$.ajax({
        url: "myNumberAPI.web",
        type: 'POST',
        data: himodukeNo,
        cache:false,
        beforeSend: function(xhr) {  
            xhr.setRequestHeader("Accept", "application/json");  
            xhr.setRequestHeader("Content-Type", "application/json");  
        },
        success:function(response){
        	$('#apiInfo').html("status = " + response.httpStatus + " Message = " + response.resultMessage + " My Number = " + response.myNumber);
        },
        error:function(jqXhr, textStatus, errorThrown){
        	alert(textStatus);
        }
    });
	return true;
}

</script>
</head>
<body>
<form:form method="post" action="">
	<div id="apiInfo"></div>
	<p><input type="button" name="Submit" value="Submit" onclick="callMyNumberAPI();"></p>
	
</form:form>	
</body>
</html>