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
<meta name="viewport" content="width=600,initial-scale=1.45">
<meta name="format-detection" content="telephone=no" />
<meta name="keywords" content="扶養者登録画面">
<meta name="description" content="扶養者登録画面">

<link rel="stylesheet" href="resources/css/main.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/checkNetWork.js"></script>
<script src="resources/js/dependentsRegist.js"></script>
</head>
<body>
    <div id="wrapper">
        <div id="main-content" class="wrap-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
                <form:form id="dependentsRegistForm" action="dependentsRegist" method="post" modelAttribute="dependentsInfoListModel" cssClass="mt10">
                <form:errors path="*" cssClass="errorDependentsRegist" />
                <div id ="checkDependentsNetworkOffLine">
					<fmt:message key="I00002"/>
				</div>
				<div class="title_r"><spring:message text="扶養者情報を入力してください。" /></div>
				<div class="btn-top">
					<form:button class="btn-next mt20" onclick="if(checkNetworkOffLine('checkDependentsNetworkOffLine','dependentsInfoListModel.errors')){return false;}">
						<spring:message text="次へ" />
					</form:button>
					<form:button class="btn-back mt20 btn-back-position" onclick="if(backScreen()){return false;}">
						<spring:message text="戻る" />
					</form:button>
				</div>
				<input type="hidden" id="staffName" value="${staffInfoModel.staffNameSei}">
				<div id="content-table">
                <c:forEach items="${dependentsInfoListModel.dependents}" var="dependent" varStatus="status">
                	<div class="titlebox"><spring:message text="扶養者${status.index + 1}" /></div>
                	<table>
                		<tr>
                			<td><span><spring:message text="お名前"/></span></td>
                			<td><span>(姓) </span>
	                            <form:input path="dependents[${status.index}].dependentsNameSei" cssClass="lastname" cssErrorClass="error lastname"/>
	                            <span>(名) </span>
	                            <form:input path="dependents[${status.index}].dependentsNameMei" cssClass="firstname" cssErrorClass="error firstname"/>
                            </td>
                		</tr>
                		<tr>
                			<td><span><spring:message text="生年月日(西暦)"/></span></td>
                			<td>
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
                			</td>
                		</tr>
                		<tr>
                			<td><span><spring:message text="続柄"/></span></td>
                			<td>
                				<form:select path="dependents[${status.index}].dependentsRelationship" cssClass="relationship" cssErrorClass="error relationship">
	                            	<form:option value=""></form:option>
	                            	<form:options items="${listRelationship}"/>
	                            </form:select>
                			</td>
                		</tr>
                		<tr>
                			<td><span><spring:message text="続柄（その他）"/></span></td>
                			<td>
                				<span class="relationshipOther"></span>
                            	<form:input path="dependents[${status.index}].dependentsRelationshipOther" cssClass="relationshipOther" cssErrorClass="error relationshipOther"/>
                			</td>
                		</tr>
                		<tr>
                			<td><span><spring:message text="マイナンバー"/></span></td>
                			<td><form:input path="dependents[${status.index}].dependentsMyNumber" cssClass="mynumber" cssErrorClass="error mynumber"/></td>
                		</tr>
                		<tr>
                			<td><span><spring:message text="第3号被保険者"/></span></td>
                			<td>
                				<form:checkbox path="dependents[${status.index}].no3Insured" value="1"/>
                			</td>
                		</tr>
                		<tr>
                			<td colspan="2" class="td-last"><button value="扶養者${status.index + 1}" class="btn-clear" onclick="return false;">この扶養者の情報をクリアする</button></td>
                		</tr>
                	</table>
                </c:forEach>
				</div>
				<div id="overlay"></div>
				<div id="modal_dialog">
				    <div class='title'>
				    </div>
				    <input type='button' value='OK' id='btnYes' />
					<input type='button' value='キャンセル' id='btnNo' />
				</div>
				<div class="btn-bottom">
					<form:button class="btn-next mt20" onclick="if(checkNetworkOffLine('checkDependentsNetworkOffLine','dependentsInfoListModel.errors')){return false;}">
						<spring:message text="次へ" />
					</form:button>
                    <form:button class="btn-back mt20 btn-back-position" onclick="if(backScreen()){return false;}">
						<spring:message text="戻る" />
					</form:button>
				</div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>