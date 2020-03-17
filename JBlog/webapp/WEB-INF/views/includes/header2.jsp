<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<h1><a href="<c:url value="/${id}"/>">${vo.blogTitle}의 블로그입니다.</a></h1>
<ul>
	<c:choose>
		<c:when test="${not empty authUser }">
			<c:if test="${authUser.id == id}">
				<li><a href="<c:url value="/${id}/admin/basic"/>">내블로그관리</a></li>
				<li><a href="<c:url value="/users/logout"/>">로그아웃</a></li>
			</c:if>
			<c:if test="${authUser.id != id}">
				<li><a href="<c:url value="/users/logout"/>">로그아웃</a></li>
			</c:if>
		</c:when>
		<c:otherwise>
			<li><a href="<c:url value="/users/login"/>">로그인</a></li>
		</c:otherwise>
	</c:choose>
	</ul>