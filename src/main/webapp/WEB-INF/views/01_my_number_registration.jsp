<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マイナンバー登録システム</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="マイナンバ,マイナンバ登録">
<meta name="description" content="マイナンバー登録システム">

	<link rel="stylesheet" href="resources/css/main.css" />
	<script src="resources/js/jquery-1.11.1.min.js"></script>

</head>
<body>
	<div id="wrapper">
		<div id="main-content">
			<div id="header">
				<div class="logo"></div>
				<div class="title">
					マイナンバー登録システム
				</div>
			</div>
			<div id="content">
				<form:form methodParam="POST" commandName="myNumberRegistration" action="search" cssClass="mt40">
					<div class="subtitle">社員番号を入力して検索ボタンを押してください。</div>
					<form:label path="employeeId">
						<spring:message text="社員番号"/>
					</form:label>
					<form:input path="employeeId"/>
					<input type="submit" class="btn-next mt40" value="社員番号" />
					<div class="name">
						<c:if test="${!empty myNumberRegistration.employeeName}">
							<spring:message text="${myNumberRegistration.employeeName}"/>
						</c:if>
					</div>
					<form:errors path="employeeId" cssClass="error" />
					<input type="submit" class="btn-next mt40" value="次へ" />
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>