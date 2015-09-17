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
<script src="resources/js/colectionInfoRegist.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content">
            <div id="header" class="h40">
                <div class="logo_l"></div>
            </div>
            <div id="content">
                <form:form id="colectionInfoRegistForm" action="colectionInfoRegist" method="post" modelAttribute="colectionInfoRegistDto" cssClass="txtCenterC">
	               	<div id ="checkColectionNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
					<div id ="checkRequiedCheckBox">
						<fmt:message key="V00016"/>
					</div>
					<form:errors path="*" cssClass="errorColectionInfoRegist" />
					<c:choose>
	                	<c:when test="${staffInfoModel.consent == '0' }">
	                		<div id="reasonForChoosing" class="font14">
								<div><spring:message text="未提供理由を選択の上、登録ボタンをタップしてください。" /></div>
								<div class="ml10 mt20">
									<div class="mt10">
										<form:checkbox path="miteikyoRiyu1" value="1"/>
										<spring:message text="提供したくない" />
									</div>

									<div class="mt10">
										<form:checkbox path="miteikyoRiyu2" value="1"/>
										<spring:message text="住民票が国内にない" />
									</div>

									<div class="mt10">
										<form:checkbox path="miteikyoRiyu3" value="1"/>
										<spring:message text="住民票を持っていない" />
									</div>

									<div class="mt10">
										<form:checkbox path="miteikyoRiyu4" value="1"/>
										<spring:message text="マイナンバーを持っていない" />
									</div>
								</div>
	                		</div>
	                	</c:when>
						<c:otherwise>
							<div class="confirmText mt20 font14">
				               	<div><spring:message text="情報を登録します。" /></div>
				               	<div><spring:message text="登録ボタンをタップしてください。" /></div>
							</div>
						</c:otherwise>
	                </c:choose>
                   	<form:hidden path="staffSign"/>
                   	<script>
                   		loadStaffSign();
                   	</script>
                   	<button class="btn-next mt20 mb80" onclick="if(checkRequiedCheckBox()){return false;}"><spring:message text="登録" /></button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>