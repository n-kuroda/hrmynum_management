<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>スタッフ選択</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="スタッフ">
<meta name="description" content="スタッフ選択">
<link rel="stylesheet" href="resources/css/main.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="main-content">
			<div id="header">
				<div class="logo"></div>
				<div class="title">スタッフ選択</div>
			</div>
			<div id="content">
				<form:form methodParam="POST" commandName="staffSelection" action="screenthree" class="mt40">
					<form:label path="staffNo">
						<spring:message text="スタッフ№"/>
					</form:label>
					<form:input path="staffNo"/>
					<div class="name">○○ 三郎 様</div>
					<input type="submit" class="btn-next mt40" value="開始" />
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>