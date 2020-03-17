<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 블로그</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<!-- jQuary -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js" />"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header2.jsp"/>
	
	<table border="1">
		<tr>
			<li>${fvo.postTitle}</li>
			<li>${fvo.postContent}</li>
		</tr>
		<tr>	
			<h2>이미지</h2>
			<li>${vo.logoFile }</li>
		</tr>
			<h2>카테고리</h2>
			<c:forEach items="${list }" var="vo">
			<tr>${vo.cateName}</tr><br>
			</c:forEach>
		<tr>
			<c:forEach items="${pvo }" var="vo">
			<li>${vo.postTitle}</li>
			</c:forEach>
		</tr> 
	</table>
	
	<c:import url="/WEB-INF/views/includes/footer.jsp"/>
</body>

<!-- jQuary -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js" />"></script>
<!-- Bootstrap js -->
<script src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>"> </script>
</html>