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
<script src="resources/js/myNumberRegist.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
                <form:form action="myNumberRegist" id="myNumberRegistForm" modelAttribute="staffInfoModel" methodParam="POST">
                	<form:errors path="*" cssClass="errorMyNumberRegist" />
                	<div id ="checkMyNumberNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
					<div class="font12 mb10">
						<spring:message text="マイナンバーを入力してください。"/>
					</div>
                	<form:label path="myNumber" cssClass="color mt10">
						<spring:message text="マイナンバー"/>
					</form:label>
					<form:input path="myNumber" cssErrorClass="error"/>
					<p class="mt30 font14"><spring:message text="マイナンバー確認提示書類"/></p>
                    <p class="font12">
	                    <form:radiobutton path="myNumberConfirm" value="01"/>
						<spring:message text="個人番号カード（本人の写真が貼付され、市区町村で発行されたもの）"/>
						<br/>
						<form:radiobutton path="myNumberConfirm" value="02"/>
						<spring:message text="通知カード（10月以降に郵便で配布されてきたもの）"/>
						<br/>
                    	<form:radiobutton path="myNumberConfirm" value="03"/>
						<spring:message text="番号が記載された住民票のコピー"/>
						<br/>
                    	<form:radiobutton path="myNumberConfirm" value="04"/>
						<spring:message text="番号が記載された住民票記載事項証明書"/>
                    </p>
					<div id="options" style="display: none;">
						<p class="mt30 font14">
							<spring:message text="本人確認提示書類" />
							<br />
							<spring:message text="①~⑦の場合はいずれか一つ、⑧~⑩の場合はいずれか2つを選択してください。" />
						</p>
						<div class="chkbox-group">
							<div class="col font12">
								<form:checkbox path="driversLicense" value="1" />
									<spring:message text="①運転免許証" />
								<br />
								<form:checkbox path="driveHistoryLicense" value="1" />
								<form:label path="driveHistoryLicense">
									<spring:message text="②運転経歴証明書" />
								</form:label>
								<br />
								<form:checkbox path="passPort" value="1" />
								<form:label path="passPort">
									<spring:message text="③パスポート" />
								</form:label>
							</div>
							<div class="col font12">
								<form:checkbox path="bodyDisabilitiesNotebook" value="1" />
								<form:label path="bodyDisabilitiesNotebook">
									<spring:message text="④身体障碍者手帳" />
								</form:label>
								<br />
								<form:checkbox path="mentalDisabilitiesNotebook" value="1" />
								<form:label path="mentalDisabilitiesNotebook">
									<spring:message text="⑤精神障碍者保健福祉手帳" />
								</form:label>
								<br />
								<form:checkbox path="rehabilitationNotebook" value="1" />
								<form:label path="rehabilitationNotebook">
									<spring:message text="⑥療育手帳" />
								</form:label>
							</div>
							<div class="col font12">
								<form:checkbox path="stayCard" value="1" />
								<form:label path="stayCard">
									<spring:message text="⑦在留カード" />
								</form:label>
								<br/>
							</div>
							<hr />
							<div class="col font12">
								<form:checkbox path="healthInsuranceLicense" value="1" />
								<form:label path="healthInsuranceLicense">
									<spring:message text="⑧健康保険被保険者証" />
								</form:label>
								<br />
								<form:checkbox path="pensionNotebook" value="1" />
								<form:label path="pensionNotebook">
									<spring:message text="⑨年金手帳" />
								</form:label>
								<br />
								<form:checkbox path="other" value="1" />
								<form:label path="other">
									<spring:message text="⑩その他" />
								</form:label>
							</div>
						</div>
					</div>
					<div>
	                    <form:button class="btn-next mt40" onclick="if(checkNetworkOffLine('checkMyNumberNetworkOffLine', 'staffInfoModel.errors')){return false;}">
							<spring:message text="次へ" /></form:button>
						<form:button class="btn-back mt40" onclick="if(backScreen()){return false;}">
							<spring:message text="戻る" /></form:button>
					</div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>