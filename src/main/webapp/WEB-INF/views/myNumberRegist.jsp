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
<script src="resources/js/shainExistCheck.js"></script>

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
                    <p class="mt30 font12">
	                    <spring:message text="○ 個人番号カード（本人の写真が貼付され、市区町村で発行されたもの）"/>
	                    <br/>
	                    <spring:message text="○ 通知カード（10月以降に郵便で配布されてきたもの）"/>
	                    <br/>
	                    <spring:message text="○ 番号が記載された住民票のコピー"/>
						<br/>
						<spring:message text="○ 番号が記載された住民票記載事項証明書"/>
						<br/>
                    </p>
					<div style="display: none">

                    <p class="mt30 font12"><spring:message text="◆マイナンバー確認書類"/></p>
                    <div class="radio-group">
                    	<form:radiobutton path="radio1" />
                    	<form:label path="radio1">
							<spring:message text="個人番号カード（本人の写真が貼付され、市区町村で発行されたもの）"/>
						</form:label>
						<br/>
						<form:radiobutton path="radio2"/>
                    	<form:label path="radio2">
							<spring:message text="通知カード（10月以降に郵便で配布されてきたもの）"/>
						</form:label>
						<br/>
                    	<form:radiobutton path="radio3"/>
                    	<form:label path="radio3">
							<spring:message text="番号が記載された住民票のコピー"/>
						</form:label>
						<br/>
                    	<form:radiobutton path="radio4"/>
                    	<form:label path="radio4">
							<spring:message text="番号が記載された住民票記載事項証明書"/>
						</form:label>
                    </div>
                    <p class="mt30 font12">
                    	<spring:message text="◆本人確認書類"/><br/>
                    	<spring:message text="①~⓻の場合はいずれか一つ、⑧~⑩の場合はいずれか2つを選択してください。"/>
                    </p>
                    <div class="chkbox-group">
                    	<div class="col">
                    		<form:checkbox path="chk1" value="1"/>
                    		<form:label path="chk1">
								<spring:message text="①運転免許証"/>
							</form:label>
							<br/>
							<form:checkbox path="chk2" value="2"/>
                    		<form:label path="chk2">
								<spring:message text="②運転経歴証明書"/>
							</form:label>
							<br/>
							<form:checkbox path="chk3" value="3"/>
                    		<form:label path="chk3">
								<spring:message text="③パスポート"/>
							</form:label>
                    	</div>
                    	<div class="col">
                    		<form:checkbox path="chk4" value="4"/>
                    		<form:label path="chk4">
								<spring:message text="④身体障碍者手帳"/>
							</form:label>
							<br/>
							<form:checkbox path="chk5" value="5"/>
                    		<form:label path="chk5">
								<spring:message text="⑤精神障碍者保健福祉手帳"/>
							</form:label>
							<br/>
							<form:checkbox path="chk6" value="6"/>
                    		<form:label path="chk6">
								<spring:message text="⑥療育手帳"/>
							</form:label>
                    	</div>
                    	<div class="col">
                    		<form:checkbox path="chk7" value="7"/>
                    		<form:label path="chk7">
								<spring:message text="⓻在留カード"/>
							</form:label>
                    	</div>
                    	<hr/>
                    	<div class="col">
                    		<form:checkbox path="chk8" value="8"/>
                    		<form:label path="chk8">
								<spring:message text="⑧健康保険被保険者証"/>
							</form:label>
							<br/>
							<form:checkbox path="chk9" value="9"/>
                    		<form:label path="chk9">
								<spring:message text="⓽年金手帳"/>
							</form:label>
							<br/>
							<form:checkbox path="chk10" value="10"/>
                    		<form:label path="chk10">
								<spring:message text="⑩その他"/>
							</form:label>
                    	</div>
                    </div>
                    <div class="agree">
                   		<form:label path="chk11">
							<spring:message text="違いでないことが明らか"/>
						</form:label>
						<form:checkbox path="chk11" value="11"/>
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