<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션 user_id를 삭제
	session.removeAttribute("user_id");
	// 접속해 있는 사용자의 모든 세션 데이터를 삭제
	session.invalidate();
	response.sendRedirect("index.jsp");
%>