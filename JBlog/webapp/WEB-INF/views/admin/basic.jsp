<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 관리</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<style>
ul li{
   list-style:none;
   display:inline
   }
</style>
</head>
<body>

	<c:import url="/WEB-INF/views/includes/header2.jsp"/>
	
	<nav>
		<ul>
			<li><a href="<c:url value="/${id}/admin/basic"/>">기본설정</a></li>
			<li>|</li>
			<li><a href="<c:url value="/${id}/admin/category"/>">카테고리</a></li>
			<li>|</li>
			<li><a href="<c:url value="/${id}/admin/write"/>">글 작성</a></li>
		</ul>
	</nav>
	
	<form method="post" action="<c:url value="/{id}/admin/basic"/>"  enctype="multipart/form-data">
		<table border="1" width="640">
			<tr>
				<td>블로그제목</td>
				<td><input type="text" name="blogTitle"></td>
			</tr>
			<tr>
				<td>로고 이미지</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="기본설정변경">
				</td>	
			</tr>
		</table>
	</form>
	
	<c:import url="/WEB-INF/views/includes/footer.jsp"/>
</body>
<!-- jQuary -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js" />"></script>
<!-- Bootstrap js -->
<script src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>"> </script>
</html>