<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message text="マイナンバー収集システム" /></title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="マイナンバー収集システム">
<meta name="description" content="マイナンバー収集システム">

<link rel="stylesheet" href="resources/css/main.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/shainExistCheck.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
                <form:form commandName="staffSignningDto" action="staffSignning" method="post" class="txtCenterC">
                	<spring:message text="以下に署名してください。" />
                	<form:textarea cssClass="mt30" path="textArea"/>
                    <form:button name="action" value="back" class="btn-next mt40" onclick="if(checkInput()){return false;}">
						<spring:message text="戻る" />
					</form:button>
					<form:button name="action" value="next" class="btn-next mt40" onclick="if(checkInput()){return false;}">
						<spring:message text="次へ" />
					</form:button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>