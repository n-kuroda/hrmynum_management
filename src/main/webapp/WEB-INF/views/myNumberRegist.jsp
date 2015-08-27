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
<script src="resources/js/myNumberRegist.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
                <form:form action="myNumberRegist" commandName="myNumberRegistDto" methodParam="POST" class="mt30">
                	<form:errors path="*" cssClass="error" />
                	<form:label path="myNumber" cssClass="color">
						<spring:message text="マイナンバー"/>
					</form:label>
					<form:input path="myNumber"/>
					<p class="mt30 font12"><spring:message text="◆マイナンバー確認書類"/></p>
                    <p class="font12">
	                    <form:radiobutton path="cardInfo" value="01"/>
						<label><spring:message text="個人番号カード（本人の写真が貼付され、市区町村で発行されたもの）"/></label>
						<br/>
						<form:radiobutton path="cardInfo" value="02"/>
						<spring:message text="通知カード（10月以降に郵便で配布されてきたもの）"/>
						<br/>
                    	<form:radiobutton path="cardInfo" value="03"/>
						<spring:message text="番号が記載された住民票のコピー"/>
						<br/>
                    	<form:radiobutton path="cardInfo" value="04"/>
						<spring:message text="番号が記載された住民票記載事項証明書"/>
                    </p>
					<div id="options">
						<p class="mt30 font12">
							<spring:message text="◆本人確認書類" />
							<br />
							<spring:message text="①~⑦の場合はいずれか一つ、⑧~⑩の場合はいずれか2つを選択してください。" />
						</p>
						<div class="chkbox-group">
							<div class="col">
								<form:checkbox path="driverLicense" value="1" />
								<form:label path="driverLicense">
									<spring:message text="①運転免許証" />
								</form:label>
								<br />
								<form:checkbox path="drivingExperience" value="1" />
								<form:label path="drivingExperience">
									<spring:message text="②運転経歴証明書" />
								</form:label>
								<br />
								<form:checkbox path="passport" value="1" />
								<form:label path="passport">
									<spring:message text="③パスポート" />
								</form:label>
							</div>
							<div class="col">
								<form:checkbox path="healthInsurance" value="1" />
								<form:label path="healthInsurance">
									<spring:message text="④身体障碍者手帳" />
								</form:label>
								<br />
								<form:checkbox path="insuranceHandbook" value="1" />
								<form:label path="insuranceHandbook">
									<spring:message text="⑤精神障碍者保健福祉手帳" />
								</form:label>
								<br />
								<form:checkbox path="manualCare" value="1" />
								<form:label path="manualCare">
									<spring:message text="⑥療育手帳" />
								</form:label>
							</div>
							<div class="col">
								<form:checkbox path="stayCard" value="1" />
								<form:label path="stayCard">
									<spring:message text="⑦在留カード" />
								</form:label>
							</div>
							<hr />
							<div class="col">
								<form:checkbox path="stayCard" value="1" />
								<form:label path="insuranceCard">
									<spring:message text="⑧健康保険被保険者証" />
								</form:label>
								<br />
								<form:checkbox path="pensionBook" value="1" />
								<form:label path="pensionBook">
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
                    <form:button name="action" value="next" class="btn-next mt40" onclick="if(checkInput()){return false;}">
						<spring:message text="次へ" /></form:button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>