<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"
    %>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - import 속성</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
	Date today = new Date();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	String todayStr = dateformat.format(today);
	out.println("오늘 날짜 : " + todayStr);
%>
<%@ include file="footer.jsp" %>
</body>
</html>