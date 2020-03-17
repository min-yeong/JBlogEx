<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 성공</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>" />
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js" />"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
	<p class="jr-success">
		"감사합니다. 회원 가입 및 블로그가 성공적으로 만들어 졌습니다."
	</p>
	<p>
		<a href="<c:url value="/users/login"/>">로그인하기</a>
	</p>
</body>
</html>