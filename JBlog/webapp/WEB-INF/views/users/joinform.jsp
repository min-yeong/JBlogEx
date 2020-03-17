<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>" />
<!-- jQuary -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js" />"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
	<c:url value="/users/join" var="joinUrl"/>	
	<form:form
	 	modelAttribute="usersVo" 
	 	id="join-form" 
	 	name="registerForm" 
	 	action="${joinUrl }" 
	 	method="POST">
	 	
		<label for="userName">이름</label>
		<form:input path="userName" type="text" placeholder="이름을 입력하십시오"/>
		<!-- 객체에 에러가 있는가? -->
		<spring:hasBindErrors name="usersVo">
			<!-- 필드 에러 확인 -->
			<c:if test="${errors.hasFieldErrors('userName')}">
				<span style="color:red">
					<spring:message code="${errors.getFieldError('userName').codes[0]}" 
									text="${errors.getFieldError('userName').defaultMessage}"/>
				</span>
			</c:if>
		</spring:hasBindErrors><br>
	
		<label for="id">아이디</label>
		<form:input path="id" type="text" placeholder="아이디를 입력하십시오"/>
		<button id="btnCheckId" type="button">id 중복 체크</button>
		<!-- 객체에 에러가 있는가? -->
		<spring:hasBindErrors name="usersVo">
			<!-- 필드 에러 확인 -->
			<c:if test="${errors.hasFieldErrors('id')}">
				<span style="color:red">
					<spring:message code="${errors.getFieldError('id').codes[0]}" 
									text="${errors.getFieldError('id').defaultMessage}"/>
				</span>
			</c:if>
		</spring:hasBindErrors><br>
		
		<label for="password">비밀번호</label>
		<form:input path="password" type="password" placeholder="비밀번호를 입력하십시오"/>
		<!-- 객체에 에러가 있는가? -->
		<spring:hasBindErrors name="usersVo">
			<!-- 필드 에러 확인 -->
			<c:if test="${errors.hasFieldErrors('password')}">
				<span style="color:red">
					<spring:message code="${errors.getFieldError('password').codes[0]}" 
									text="${errors.getFieldError('password').defaultMessage}"/>
				</span>
			</c:if>
		</spring:hasBindErrors><br>
		
		<label for="agree">약관동의</label><br>
		<input path="agree" type="checkbox"/>서비스약관에 동의 합니다.<br>
		
		<input type="submit" value="회원가입"> 
	</form:form>
</body>

<!-- jQuery -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js"/>"></script>
<!-- Bootstrap js -->
<script src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>"></script>
<script>
$(document).ready(function() {
	$("#btnCheckId").on("click", function(event){
		var id = document.registerForm.id.value.trim();
		if(id.length == 0) {
			alert("아이디를 입력해주세요.");
			return;
		}
		$.ajax({
			url: "<c:url value="/users/checkId"/>",
			type: "GET",
			data: {id : id},
			dateType : "json",
			success : function(result) {
				console.log("Result", result);
				// result.exists를 검증, 메세지를 출력
				if(result.exists) {
					// 중복된 이메일
					alert("다른 아이디로 가입해 주세요.");
				}else alert("사용할 수 있는 아이디 입니다.");
			},
			error : function(request, status, error) {
				console.err("Error", error);
			}
		})
	});
});

</script>
</html>