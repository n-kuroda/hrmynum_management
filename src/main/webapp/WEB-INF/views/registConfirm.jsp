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
	                		<spring:message text="鈴木 花子(スズキ ハナコ)様" />
	                	</div>
	                	<div>
	                		<spring:message text="マイナンバー：" />
	                		<spring:message text="123456789001" />
	                	</div>
	                	<div>
	                		<spring:message text="マイナンバー確認書類：" />
	                		<spring:message text="個人番号カード" />
	                	</div>
	                	<div>
	                		<spring:message text="本人確認書類：" />
	                		<spring:message text="運転免許証" />
	                	</div>
                	</div>
                    <div class="personsigning"><img id="signature"></div>
                    <script>
                    	initScreen();
                    </script>
                    <div class="dependents_information mt30">
	                    <div><spring:message text="【扶養者情報】" /></div>
	                    <div><spring:message text="扶養者１ " /></div>
	                    <div class="dependent1">
		                    <div>
		                		<spring:message text="お名前：" />
		                		<spring:message text="鈴木 直樹(スズキ ナオキ)様" />
		                	</div>
		                	<div>
		                		<spring:message text="続柄：" />
		                		<spring:message text="夫" />
		                	</div>
		                	<div>
		                		<spring:message text="マイナンバー：" />
		                		<spring:message text="123456789002" />
		                	</div>
	                    </div>
	                	<div><spring:message text="扶養者２" /></div>
	                	<div class="dependent2">
		                	<div>
		                		<spring:message text="お名前：" />
		                		<spring:message text="鈴木 将明(スズキ マサアキ)様" />
		                	</div>
		                	<div>
		                		<spring:message text="続柄：" />
		                		<spring:message text="子" />
		                	</div>
		                	<div>
		                		<spring:message text="マイナンバー：" />
		                		<spring:message text="123456789003" />
		                	</div>
	                	</div>
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