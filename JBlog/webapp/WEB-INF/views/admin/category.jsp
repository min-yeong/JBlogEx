<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리</title>
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
	
	<table border="1" width="640">
		
		<tr>
			<th>번호</th>
			<th>카테고리명</th>
			<th>설명</th>
			<th>삭제</th>
		</tr>
		<!-- Loop -->
		<c:forEach items="${list }" var="vo">
		<tr>
			<td>${vo.cateNo }</td>
			<td>${vo.cateName }</td>
			<td>${vo.description }</td>
			<td colspan="2">
				<a href="<c:url value="/${id}/admin/delete/"/>">삭제</a>
			</td>	
		</tr>
	</c:forEach>
	</table>
	
	<form method="post">
		<table border="1" width="640">
			<tr>
				<td>카테고리명</td>
				<td><input type="text" name="cateName"></td>
			</tr>
			<tr>
				<td>설명</td>
				<td>
					<textarea id="content" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
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