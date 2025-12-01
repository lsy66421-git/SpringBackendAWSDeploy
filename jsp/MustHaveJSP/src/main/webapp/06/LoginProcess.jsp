<%@page import="membership.MemberDAO"%>
<%@page import="membership.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("user_id");
	String userPwd = request.getParameter("user_pw");
	
	MemberDAO dao = new MemberDAO();
	MemberDTO memberDTO = dao.getMemberDTO(userId,userPwd);
	
	// 로그인 성공 여부에 따른 처리
	if (memberDTO.getId() != null) { 
	    // 로그인 성공
	    session.setAttribute("UserId", memberDTO.getId());
	    session.setAttribute("UserName", memberDTO.getName());

	    response.sendRedirect("LoginForm.jsp");
	}
	else {
	    // 로그인 실패
	    request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
	    request.getRequestDispatcher("LoginForm.jsp")
	    	.forward(request, response);
	}
%>