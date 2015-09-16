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
<script src="resources/js/checkNetWork.js"></script>
<script src="resources/js/registComfirm.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_s"></div>
            </div>
            <div id="content">
            	
                <form:form id= "registConfirmForm" action="registConfirm" method="post" modelAttribute="staffInfoModel">
                	<form:errors path="*" cssClass="error" />
                	<div class="pesonal_information mt10">
	                	<div><spring:message text="【本人情報】" /></div>
	                	<div>
                			<spring:message text="お名前：" />
                			<spring:message text="${staffName }" />
	                	</div>
	                	<div>
                			<spring:message text="マイナンバー：" />
                			<spring:message text="${myNumber }" />
	                	</div>
	                	<div>
                			<spring:message text="マイナンバー確認書類：" />
                			<spring:message text="${myNumberConfirm }" />
	                	</div>
	                	<div>
	                		<c:if test="${staffInfoModel.myNumberConfirm != '01' && staffInfoModel.myNumberConfirm != '' }">
		                			<spring:message text="本人確認書類：" />
		                			<spring:message text="${identification }" />
	                		</c:if>
	                	</div>
                	</div>
                    <div class="personsigning">
                    	<img id="signature">
                    	<form:hidden path="staffSign"/>
                    </div>
                    <script>
                    	initScreen();
                    </script>
                    <div class="dependents_information mt30">
	                    <div><spring:message text="【扶養者情報】" /></div>
	                    <c:forEach items="${lstDependents}" var="dependent" varStatus="status">
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
				                	<c:if test="${dependent.no3Insured == '1' }">
										<div>
					                		<spring:message text="第3号被保険者" />
					                	</div>
	                    			</c:if>
			                    </div>
	                    	</c:if>
	                    </c:forEach>
	                    <c:if test="${empty dependentsInfoListModel.dependents[0].dependentsNameMei }">
	                    	<div><spring:message text="なし" /></div>
	                    </c:if>
						<div class="mt10">
		                    <c:choose>
			                    <c:when test="${staffInfoModel.consent == '0' }">
									<div>
				                		<spring:message text="非承諾" />
				                	</div>
		                  		</c:when>
								<c:otherwise>
									<div>
				                		<spring:message text="承諾" />
				                	</div>
								</c:otherwise>
		                    </c:choose>
	                    </div>

                    </div>
                	<div class="confirmtext mt20">
                		<spring:message text="上記の内容で登録します。よろしいですか？" />
                	</div>
                	<div id ="checkRegistNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
                	<p>
	                   <button class="btn-goback mt20" onclick="if(backScreen()){return false;}">
	                    	<spring:message text="戻る" />
	                    </button>
	                    <button class="btn-registration mt20" onclick="if(checkNetworkOffLine('checkRegistNetworkOffLine', null)){return false;}">
	                    	<spring:message text="登録" />
	                    </button>
                    </p>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>