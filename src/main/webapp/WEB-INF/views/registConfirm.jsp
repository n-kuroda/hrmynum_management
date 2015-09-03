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
<script src="resources/js/registComfirm.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
                <form:form action="aaaaa" method="post">
                	<div class="pesonal_information mt10">
	                	<div><spring:message text="【本人情報】" /></div>
	                	<div>
	                		<spring:message text="お名前：" />
	                		<spring:message text="${staffInfoModel.staffName }(${staffInfoModel.staffNameKana })様" />
	                	</div>
	                	<div>
	                		<spring:message text="マイナンバー：" />
	                		<spring:message text="${myNumerRegistModel.myNumber }" />
	                	</div>
	                	<div>
	                		<spring:message text="マイナンバー確認書類：" />
	                		<c:if test="${myNumerRegistModel.myNumberConfirm == '01' }">
	                			<spring:message text="個人番号カード" />
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.myNumberConfirm == '02' }">
	                			<spring:message text="通知カード" />
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.myNumberConfirm == '03' }">
	                			<spring:message text="番号が記載された住民票のコピー" />
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.myNumberConfirm == '04' }">
	                			<spring:message text="番号が記載された住民票記載事項証明書" />
	                		</c:if>
	                	</div>
	                	<div>
	                		<c:if test="${myNumerRegistModel.myNumberConfirm != '01' }">
	                			<spring:message text="本人確認書類：" />
	                		</c:if>

	                		<c:set var="isFirst" value="true"/>
							<c:if test="${myNumerRegistModel.driversLicense == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="運転免許証" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 運転免許証" />
								    </c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${myNumerRegistModel.driveHistoryLicense == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="運転経歴証明書" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 運転経歴証明書" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.passPort == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="パスポート" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", パスポート" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.bodyDisabilitiesNotebook == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="身体障碍者手帳" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 身体障碍者手帳" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.mentalDisabilitiesNotebook == '1'}">
	                			<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="精神障碍者保健福祉手帳" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 精神障碍者保健福祉手帳" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.rehabilitationNotebook == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="療育手帳" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 療育手帳" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.stayCard == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="在留カード" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 在留カード" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.clearPerson == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="本人であることが明らか" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 本人であることが明らか" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.healthInsuranceLicense == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="健康保険被保険者証" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 健康保険被保険者証" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.pensionNotebook == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="年金手帳" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", 年金手帳" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                		<c:if test="${myNumerRegistModel.other == '1'}">
								<c:choose>
								    <c:when test="${isFirst == 'true'}">
			                			<spring:message text="その他" />
										<c:set var="isFirst" value="false"/>
								    </c:when>
								    <c:otherwise>
								        <spring:message text=", その他" />
								    </c:otherwise>
								</c:choose>
	                		</c:if>
	                	</div>
                	</div>
                    <div class="personsigning"><img id="signature"></div>
                    <script>
                    	initScreen();
                    </script>
                    <div class="dependents_information mt30">
	                    <div><spring:message text="【扶養者情報】" /></div>
	                    <c:forEach items="${lstDependentsSesion.dependents}" var="dependent" varStatus="status">
	                    	<c:if test="${! empty dependent.dependentsNameMei }">
			                    <div><spring:message text="扶養者${status.index + 1}" /></div>
			                    <div class="dependent1">
				                    <div>
				                		<spring:message text="お名前：" />
				                		<spring:message text="${dependent.dependentsNameSei } ${dependent.dependentsNameMei } 様" />
				                	</div>
				                	<div>
				                		<spring:message text="生年月日：" />
				                		<spring:message text="${dependent.dependentsBirthdayYear }年${dependent.dependentsBirthdayMonth }月${dependent.dependentsBirthdayDay }日" />
				                	</div>
				                	<div>
				                		<spring:message text="続柄：" />
				                		<c:if test="${dependent.dependentsRelationship == '01'}">
				                			<spring:message text="妻" />
				                		</c:if>
				                		<c:if test="${dependent.dependentsRelationship == '02'}">
				                			<spring:message text="夫" />
				                		</c:if>
				                		<c:if test="${dependent.dependentsRelationship == '03'}">
				                			<spring:message text="子供" />
				                		</c:if>
				                		<c:if test="${dependent.dependentsRelationship == '04'}">
				                			<spring:message text="父" />
				                		</c:if>
				                		<c:if test="${dependent.dependentsRelationship == '05'}">
				                			<spring:message text="母" />
				                		</c:if>
				                		<c:if test="${dependent.dependentsRelationship == '06'}">
				                			<spring:message text="兄弟・姉妹" />
				                		</c:if>
				                		<c:if test="${dependent.dependentsRelationship == '07'}">
				                			<spring:message text="その他" />
				                		</c:if>
				                	</div>
				                	<div>
				                		<spring:message text="続柄(その他)：" />
				                		<c:if test="${! empty dependent.dependentsRelationshipOther }">
				                			<spring:message text="${dependent.dependentsRelationshipOther }" />
				                		</c:if>
				                		<c:if test="${empty dependent.dependentsRelationshipOther }">
				                			<spring:message text="なし" />
				                		</c:if>
				                	</div>
				                	<div>
				                		<spring:message text="マイナンバー：" />
				                		<spring:message text="${dependent.dependentsMyNumber }" />
				                	</div>
			                    </div>
	                    	</c:if>
	                    </c:forEach>
	                    <c:if test="${empty lstDependentsSesion.dependents[0].dependentsNameMei }">
	                    	<div><spring:message text="なし" /></div>
	                    </c:if>

                    </div>
                	<div class="confirmtext mt20">
                		<spring:message text="上記の内容で登録します。よろしいですか？" />
                	</div>
                	<p>
	                   <button class="btn-goback mt20" >
	                    	<spring:message text="戻る" />
	                    </button>
	                    <button class="btn-registration mt20">
	                    	<spring:message text="登録" />
	                    </button>
                    </p>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>