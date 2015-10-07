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
<script src="resources/js/shainRegistConfirm.js"></script>

</head>
<body style="zoom:70%">
<script type="text/javascript">
    history.pushState(null, null, null);
    window.addEventListener("popstate", function() {
        history.pushState(null, null, null);
    });
</script>
	<div id="wrapper">
		<div id="main-content">
			<div id="header" class="h40">
				<div class="logo_s"></div>
			</div>
			<div id="content">

				<form:form id= "registConfirmForm" action="shainRegistConfirm" modelAttribute="shainInfoModel" method="post">
					<input type="hidden" name="token" value="${token}">
					<form:errors path="*" cssClass="errorRegistConfirm" />
					<div id ="checkRegistNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
					<div class="pesonal_information mt10">
						<table>
							<tr>
								<td colspan="2" class="bg-none"><spring:message text="本人情報" /></td>
							</tr>
							<tr>
								<td><spring:message text="お名前" /></td>
								<td><spring:message text="${staffName }" /></td>
							</tr>
							<tr>
								<td><spring:message text="マイナンバー提供" /></td>
								<c:choose>
									<c:when test="${staffInfoModel.consent == '0' }">
										<td>
											<spring:message text="非承諾" />
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<spring:message text="承諾" />
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<c:if test="${staffInfoModel.consent == '1' }">
								<tr>
									<td><spring:message text="マイナンバー" /></td>
									<td><spring:message text="${myNumber }" /></td>
								</tr>
								<tr>
									<td><spring:message text="マイナンバー確認提示書類" /></td>
									<td><spring:message text="${myNumberConfirm }" /></td>
								</tr>
								<c:if test="${identification.size() != 0}">
									<tr>
										<td rowspan="${identification.size()}"><spring:message text="本人確認提示書類" /></td>
										<c:forEach items="${identification}" var="iden" varStatus="i">
											<c:if test="${i.index == 0 }">
												<td><spring:message text="${iden}" /></td>
												</tr>
											</c:if>
											<c:if test="${i.index != 0 }">
												<tr>
												<td class="td-child-two"><spring:message text="${iden}" /></td>
											</c:if>
									</tr>
									</c:forEach>
								</c:if>
							</c:if>
						</table>

					</div>
					<c:if test="${staffInfoModel.consent == '1' }">
						<div class="dependents_information mt30">
							<table>
								<tr>
									<td colspan="2" class="bg-none"><spring:message text="扶養者情報" /></td>
								</tr>
								<tr>
									<c:if test="${empty dependentsInfoListModel.dependents[0].fuyoNameMei }">
										<td colspan="2" class="bg-none"><spring:message text="なし" /></td>
									</c:if>
								</tr>
								<c:forEach items="${lstDependents}" var="dependent" varStatus="status">
									<c:if test="${! empty dependent.fuyoNameMei }">
										<tr>
											<td colspan="2" class="bg-none"><spring:message text="扶養者${status.index + 1}" /></td>
										</tr>
										<tr>
											<td><spring:message text="お名前" /></td>
											<td><spring:message text="${dependent.fuyoNameSei } ${dependent.fuyoNameMei }" /></td>
										</tr>
										<tr>
											<td><spring:message text="生年月日" /></td>
											<td><spring:message text="${dependent.fuyoSeinengapiYear }年${dependent.fuyoSeinengapiMonth }月${dependent.fuyoSeinengapiDay }日" /></td>
										</tr>
										<tr>
											<td><spring:message text="続柄" /></td>
											<td>
												<c:if test="${dependent.fuyoZokugara == '01'}">
													<spring:message text="妻" />
												</c:if>
												<c:if test="${dependent.fuyoZokugara == '02'}">
													<spring:message text="夫" />
												</c:if>
												<c:if test="${dependent.fuyoZokugara == '03'}">
													<spring:message text="子供" />
												</c:if>
												<c:if test="${dependent.fuyoZokugara == '04'}">
													<spring:message text="父" />
												</c:if>
												<c:if test="${dependent.fuyoZokugara == '05'}">
													<spring:message text="母" />
												</c:if>
												<c:if test="${dependent.fuyoZokugara == '06'}">
													<spring:message text="兄弟・姉妹" />
												</c:if>
												<c:if test="${dependent.fuyoZokugara == '07'}">
													<spring:message text="その他 (${dependent.fuyoZokugaraSonota })" />
												</c:if>
											</td>
										</tr>
										<tr>
											<td><spring:message text="マイナンバー" /></td>
											<td><spring:message text="${dependent.fuyoMyNumber }" /></td>
										</tr>
										<c:if test="${dependent.daisangoHihokensha == '1' }">
											<tr>
												<td><spring:message text="第3号被保険者" /></td>
												<td><spring:message text="該当" /></td>
											</tr>
										</c:if>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</c:if>
					<div class="personsigning-title">
						<spring:message text="スタッフ署名" />
					</div>
					<div class="personsigning">
						<img id="signature">
					</div>
					<script>
						initScreen();
					</script>
					<div class="confirmtext mt40">
						<spring:message text="上記の内容で登録しますのでご確認ください。" />
					</div>
				   <button class="btn-back-s9 mt20 btn-back-position" onclick="if(backScreen()){return false;}">
						<spring:message text="修正" />
					</button>
					<button class="btn-next mt20" onclick="if(checkNetworkOffLine('checkRegistNetworkOffLine', null)){return false;}">
						<spring:message text="確認" />
					</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>