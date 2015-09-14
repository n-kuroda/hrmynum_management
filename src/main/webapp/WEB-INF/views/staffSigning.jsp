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
<script src="resources/js/staffSigning.js"></script>

</head>
<body>
	<div id="wrapper">
        <div id="main-content-staffsigning">
            <div id="header" class="h40">
                <div class="logo_l"></div>
            </div>
            <div id="content">
               	<div class="txtLeft mt10"><spring:message text="以下に署名してください。" /></div>
               	<div class="txtLeft mt10 mb10">
	               	<c:if test="${staffInfoModel.consent == '0' }">
	            		<spring:message text="マイナンバーを提供できません。" />
	            	</c:if>
               	</div>
               	<div id="signingImage">
				</div>
	
				<script>
						signatureCapture();
				</script>
				<div id="errorInfo">
					<div id ="checkrequireSigning">
			       		<fmt:message key="V00001">
			       			 <fmt:param value="署名"/>
			       		</fmt:message>
			      	</div>
			      	<div id ="checkSignningNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
			    </div>
                <form id= "staffSigningForm" action="staffSigning" method="post" class="txtCenterC">
                    <button value="back" class="btn-back mt20" onclick="if(backScreen()){return false;}"><spring:message text="戻る" /></button>
					<button class="btn-next mt20" onclick="if(signatureSave()){return false;}"><spring:message text="次へ" /></button>
					<button value="clear" class="btn-skip mt20" onclick="signatureClear();return false;"><spring:message text="クリア" /></button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>