<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.cafe.dto.MemberDTO" %>
<%
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe Menu Ordering</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <nav>
        <a href="CafeServlet?command=product_list">HOME</a>
        <c:if test="${empty sessionScope.loginUser}">
		    <a href="CafeServlet?command=login_form">로그인</a>
		    <a href="CafeServlet?command=join_form">회원가입</a>
		</c:if>	
		<c:if test="${not empty sessionScope.loginUser}">
		    <b>${sessionScope.loginUser.name}</b>님 환영합니다! 
		    <a href="CafeServlet?command=logout">로그아웃</a>
		</c:if>
		<span align="wright"><a href="listNotice.do" >게시판</a></span>
    </nav>
</header>
