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
<title><spring:message text="扶養者登録画面" /></title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="扶養者登録画面">
<meta name="description" content="扶養者登録画面">

<link rel="stylesheet" href="resources/css/main.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/partnerRegist.js"></script>
</head>
<body>
    <div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
            <div class="title_r">扶養者情報を入力してください。</div>
                <form:form id="partnerRegistForm" action="partnerRegist" method="post" modelAttribute="dependentsInfoListModel" cssClass="mt10">
                <form:errors path="*" cssClass="errorPartnerRegist" />
                <div id ="checkPartnerNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
				<input type="hidden" id="staffName" value="${staffInfoModel.staffNameSei}">
                <c:forEach items="${dependentsInfoListModel.dependents}" var="dependent" varStatus="status">
                	<div class="box">
		                <div class="titlebox">扶養者${status.index + 1}</div>
                        <p class="font14 name">
                            <span>お名前</span>
                            <span class="lastname">(姓) </span>
                            <form:input path="dependents[${status.index}].dependentsNameSei" cssClass="lastname" cssErrorClass="error lastname"/>
                            <span>(名) </span>
                            <form:input path="dependents[${status.index}].dependentsNameMei" cssClass="firstname" cssErrorClass="error firstname"/>
                        </p>
                        <p class="font14" style="margin-top: -8px;">
                            <span>生年月日(西暦)</span>
                            <form:select path="dependents[${status.index}].dependentsBirthdayYear" cssClass="year" cssErrorClass="error year">
                            	<form:option value=""></form:option>
                            	<form:options items="${listYear}"/>
                            </form:select>
                            <span class="year">年 </span>
                            <form:select path="dependents[${status.index}].dependentsBirthdayMonth" cssClass="month" cssErrorClass="error month">
                            	<form:option value=""></form:option>
                            	<form:options items="${listMonth}"/>
                            </form:select>
							<span class="month">月 </span>
                            <form:select path="dependents[${status.index}].dependentsBirthdayDay" cssClass="day" cssErrorClass="error day">
                            	<form:option value=""></form:option>
                            	<form:options items="${listDay}"/>
                            </form:select>
							<span class="day">日</span>
                        </p>
                        <p class="font14" style="margin-top: -4px;">
                        	<span>続柄</span>
                        	<form:select path="dependents[${status.index}].dependentsRelationship" cssClass="relationship" cssErrorClass="error relationship">
                            	<form:option value=""></form:option>
                            	<form:options items="${listRelationship}"/>
                            </form:select>
                        </p>
                        <p class="font14" style="margin-top: -8px;">
                            <span>続柄（その他）</span>
                            <span class="relationshipOther"></span>
                            <form:input path="dependents[${status.index}].dependentsRelationshipOther" cssClass="relationshipOther" cssErrorClass="error relationshipOther"/>
                        </p>
                        <p class="font14" style="margin-top: -4px;">
                        	<span>マイナンバー</span>
                            <form:input path="dependents[${status.index}].dependentsMyNumber" cssClass="mynumber" cssErrorClass="error mynumber"/>
                        </p>
                        <p class="font14" style="margin-top: -4px;">
                        	<span>第3号被保険者</span>
                            <form:checkbox path="dependents[${status.index}].no3Insured" value="1"/>
	                    	<button value="扶養者${status.index + 1}" class="btn-clear" onclick="return false;">この扶養者の情報をクリアする</button>
                        </p>
                    </div>
                </c:forEach>
                    <p>
	                    <button class="btn-back" onclick="if(backScreen()){return false;}">戻る</button>
	                    <button class="btn-next" onclick="if(checkNetworkOffLine()){return false;}">次へ</button>
                    </p>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>