<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - errorPage, isErrorPage 속성</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
/* try{ */
// request.getParameter("age") : 주소창에 데이터를 추가하여 보낸 age를 꺼내오는 메서드
int myAge = Integer.parseInt(request.getParameter("age")) + 10;
out.println("10년 후 당신의 나이는 " + myAge + "입니다.");
/* }catch(Exception e){
	out.println("예외발생 : 나이를 숫자로 입력해주세요.");
} */
%>
<%@ include file="footer.jsp" %>
</body>
</html>