<%@page import="membership.MemberDAO"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 추가 테스트(exeuteUpdate() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String id = "test4";
	String pass = "1111";
	String name = "테스트4회원";
	
	MemberDAO dao = new MemberDAO();
	dao.addMember(id,pass,name);
	out.println("행이 입력되었습니다.");
	
	dao.close();
	%>
</body>
</html>