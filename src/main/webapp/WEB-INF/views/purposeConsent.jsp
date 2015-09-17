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
<script src="resources/js/purposeConsent.js"></script>

</head>
<body>
	<div id="wrapper">
		<div id="main-content">
			<div id="header" class="h40">
				<div class="logo_s"></div>
				<div class="title_s">
					<spring:message text="${staffInfoModel.staffNameSei} ${staffInfoModel.staffNameMei} 様"/>
				</div>
			</div>
			<div id="content">
				<p class="txtCenter"><spring:message text="マイナンバー（個人情報）収集のための承諾のお願い" /></p>
				<div id="purponeConsent">

					<p><spring:message text="マイナンバー（個人番号）の利用目的" /></p>

					<p>
						<spring:message text="ヒューマンリソシア株式会社は、派遣スタッフの皆様及び皆様の扶養家族の個人番号（行政手続きにおける" />
						<br />
						<spring:message text="特定の個人を認識するための番号の利用等に関する法律に定める個人番号をいいます。）を以下の目的で" />
						<br />
						<spring:message text="利用します。（同法第18条）" />
					</p>

					<p>
						<spring:message text="1.給与所得・退職所得の源泉徴収票作成事務" />
						<br />
						<spring:message text="2.雇用保険届出・申請事務 （該当する方のみ）" />
						<br />
						<spring:message text="3.健康保険・厚生年金保険届出・申請事務 （該当する方のみ）" />
						<br />
						<spring:message text="4.国民年金の第3号被保険者の届出に関する事務 （該当する方のみ）" />
						<br />
						<spring:message text="5.労働者災害補償保険法に基づく請求に関する事務 （該当する方のみ）" />
					</p>

					<p>
						<spring:message text="質問および苦情処理の窓口" />
						<br />
						<spring:message text="ヒューマンリソシアは、特定個人情報の苦情や相談に関して、以下の相談窓口を設け、適切かつ迅速に対応し、" />
						<br />
						<spring:message text="問題の解決を図るように努めます。" />
						<br />
						<spring:message text="担当部署　：　お客様相談窓口" />
						<br />
						<spring:message text="ＴＥＬ　　：　0120-03-7837" />
					</p>

					<p class="txtCenter">
						<spring:message text="マイナンバーの利用目的を確認し、番号収集に承諾いたします。" />
					</p>
				</div>
				<form id="perposeConsentForm" action="purposeConsent" method="post">
					<div id ="checkPurposeNetworkOffLine">
						<fmt:message key="I00002"/>
					</div>
					<button class="btn-back"  onclick="if(backScreen()){return false;}">戻る</button>
					<button class="btn-next" onclick="if(checkNetworkOffLine('checkPurposeNetworkOffLine', null)){return false;}">承諾する</button>
					<button class="btn-skip" onclick="if(skipToSigning()){return false;}">その他</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>