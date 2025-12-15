<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Menu Ordering</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <nav>
        <a href="${pageContext.request.contextPath}/">HOME</a>
        <c:if test="${empty sessionScope.loginUser}">
		    <a href="${pageContext.request.contextPath}/member/login">로그인</a>
		    <a href="${pageContext.request.contextPath}/member/join">회원가입</a>
		</c:if>	
		<c:if test="${not empty sessionScope.loginUser}">
		    <b>${sessionScope.loginUser.name}</b>님 환영합니다! 
		    <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		</c:if>
		<span align="wright"><a href="${pageContext.request.contextPath}/board/list" >게시판</a></span>
    </nav>
</header>
