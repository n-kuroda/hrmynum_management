<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome to My number project</title>
</head>

<body>
<h1>My Number</h1>
<%
 response.setStatus(301);
 response.setHeader( "Location", "/MyNumber/s1");
 response.setHeader( "Connection", "close" );
 %>
</body>

