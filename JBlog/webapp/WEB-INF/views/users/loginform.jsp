<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인하기</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>" />
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js" />"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
	<form id="login-form" name="loginform" method="POST" 
		action="<c:url value="/users/login"/>">
		
		<label class="block-label" for="id">아이디</label> 
		<input id="id" name="id" type="text" value=""><br> 

		<label class="block-label">패스워드</label> 
		<input name="password" type="password" value=""><br>

		<input type="submit" value="로그인">
	</form>
</body>
</html>