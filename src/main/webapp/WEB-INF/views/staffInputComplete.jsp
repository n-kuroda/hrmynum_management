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
<meta name="viewport" content="width=600,initial-scale=1.45">
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="マイナンバー収集システム">
<meta name="description" content="マイナンバー収集システム">

<link rel="stylesheet" href="resources/css/main.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/checkNetWork.js"></script>
<script src="resources/js/staffInputComplete.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_l"></div>
            </div>
            <div id="content">
               	<div id ="checkstaffInputNetworkOffLine">
					<fmt:message key="I00002"/>
				</div>
				<div class="confirmText mt20">
	               	<div class="font14"><spring:message text="ご確認ありがとうございます。" /></div>
	               	<div class="font14"><spring:message text="タブレットをお戻しください。" /></div>
				</div>
				<div class="confirmText font14 mt20"><spring:message text="以降は当社担当が操作いたします。" /></div>
                <form:form id= "staffInputCompleteForm" action="staffInputComplete" modelAttribute="staffInfoModel" method="post" class="txtCenterC">
                	<input type="hidden" name="token" value="${token}">
                	<form:errors path="*" cssClass="errorStaffInputComplete mt10" />
                   	<button class="btn-next mt40 mb80" onclick="if(checkStaffInputComplete()){return false;}">
                   		<spring:message text="完了" />
                   	</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>