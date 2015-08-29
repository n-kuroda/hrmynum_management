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
                <form:form id="partnerRegistForm" action="partnerRegist" method="post" commandName="lstDependentsInfo" cssClass="mt10">
                <form:errors path="*" cssClass="error" />
                	<%
                	for(int i = 1; i <= 10; i++) {
                	%>
                    <div class="box">
		                <div class="titlebox">扶養者<%=i %></div>
                        <p class="font14 name">
                            <span>お名前</span>
                            <span class="lastname">(姓) </span>
                            <input name="dependents[<%=i-1 %>].dependentsNameSei" value="${staffInfoModel.staffName}" type="text" class="lastname"/>
                            <span>(名) </span><input type="text" class="firstname">
                        </p>
                        <p class="font14" style="margin-top: -8px;">
                            <span>生年月日(西暦)</span>
                            <select class="year">
							  <option></option>
                              <%for(int year = 1990; year <= 2020; year++) { %>
							  	<option value=<%=year%>><%=year%></option>
							  <%}%>
							</select>
                            <span class="year">年 </span>
                            <select class="month">
							  <option></option>
                              <%for(int month = 1; month <= 12; month++) { %>
							  	<option value=<%=month%>><%=month%></option>
							  <%}%>
							</select>
							<span class="month">月 </span>
                            <select class="day">
                              <option></option>
                              <%for(int day = 1; day <= 31; day++) { %>
							  	<option value=<%=day%>><%=day%></option>
							  <%}%>
							</select>
							<span class="day">日</span>
                        </p>
                        <p class="font14" style="margin-top: -4px;">
                        	<span>続柄</span>
                            <select class="relationship">
                              <option></option>
							  <option value="husband">夫</option>
							  <option value="wife">妻</option>
							  <option value="children">子供</option>
							  <option value="father">父</option>
							  <option value="mother">母</option>
							  <option value="brother_sister">兄弟・姉妹</option>
							  <option value="grandfather">祖父・祖母</option>
							</select>
                        </p>
                        <p class="font14" style="margin-top: -8px;">
                            <span>フリガナ</span>
                            <span class="relationshipOther"></span><input type="text" class="relationshipOther">
                        </p>
                        <p class="font14" style="margin-top: -4px;">
                        	<span>マイナンバー</span>
                            <input type="text" class="mynumber">
	                    	<button class="btn-clear">この扶養者の情報をクリアする</button>
                        </p>
                    </div>
                    <p/>
                    <%} %>
                    <p>
	                    <button class="btn-back mt20" onclick="backScreen();">戻る</button>
	                    <button class="btn-next mt20">次へ</button>
                    </p>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>