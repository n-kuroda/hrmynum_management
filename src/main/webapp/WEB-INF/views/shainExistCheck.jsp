<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script src="resources/js/shainExistCheck.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="main-content">
			<div id="header">
				<div class="logo"></div>
				<div class="title">
					<spring:message text="マイナンバー収集システム" />
				</div>
			</div>
			<div id="content">
				<form:form methodParam="POST" modelAttribute="shainInfoModel" id="shainExistCheckForm" action="shainExistCheck" cssClass="mt20">
					<input type="hidden" name="token" id="token" value="${token}">
					<div class="subtitle"><spring:message text="社員番号を入力して検索ボタンを押してください。" /></div>
					<form:label path="shainNo" cssClass="color">
						<spring:message text="社員番号"/>
					</form:label>
					<form:input path="shainNo" cssErrorClass="error" maxlength="6"/>
					<form:button class="btn-next mt40" onclick="if(checkDataValidWhenSearch()){return false;}">
						<spring:message text="検索" /></form:button>
					<div id="shainInfo" class="name">
						<div id="messageInfoShainExistCheck">
							<table>
								<c:if test="${!empty shainNo}">
								<tr>
									<td class="leftLabel"><spring:message text="社員番号 " /></td>
									<td class="rightLabel"><spring:message text="${shainNo}" /></td>
								</tr>
								</c:if>
								<c:if test="${!empty shainInfo}">
								<tr>
									<td class="leftLabel"><spring:message text="お名前" /></td>
									<td class="rightLabel"><spring:message text="${shainInfo}" /></td>
								</tr>
								</c:if>
							</table>
							<c:if test="${!empty shainInfo}">
								<div class="mt20 ml20"><spring:message text="よろしければ「次へ」ボタンを押してください。" /></div>
							</c:if>
						</div>
					</div>
					<form:errors path="*" cssClass="errorShainExistCheck" />

					<div id="errorInfo" class="mt10">
						<div id="checkShainSession" style="display: none;">
							<spring:message text="不正遷移です。お手数ですがもう一度最初からやり直してください。" />
						</div>
						<div id ="checkShainNetworkOffLine" class="errorShainExistCheck" style="display: none;">
							<fmt:message key="I00002"/>
						</div>
						<div id="checkrequireId" class="errorShainExistCheck" style="color: red; display: none;">
							<fmt:message key="V00001">
								<fmt:param value="社員番号"/>
							</fmt:message>
						</div>
						<div id="checkLengthId" class="errorShainExistCheck" style="color: red; display: none;">
							<fmt:message key="V00002">
								<fmt:param value="社員番号"/>
								<fmt:param value="6"/>
							</fmt:message>
						</div>
						<div id="checkByteId" class="errorShainExistCheck" style="color: red; display: none;">
							<fmt:message key="V00003">
								<fmt:param value="社員番号"/>
							</fmt:message>
						</div>
						<div id="checkShainExist" class="errorShainExistCheck" style="color: red; display: none;">
							<fmt:message key="I00001">
							</fmt:message>
						</div>
						<div id="serverError" class="errorShainExistCheck" style="color: red; display: none;">
							<fmt:message key="S00001">
								<fmt:param value="社員番号の検索"/>
							</fmt:message>
						</div>
					</div>
					<form:button class="btn-next mt20" onclick="if(checkDataValid()){return false;}"><spring:message text="次へ" /></form:button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>