<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@page import="utils.JSFunction"%>
<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pw");
	String save_check = request.getParameter("save_check"); // 폼 값 읽기
	//DB 연결
	MemberDAO dao = new MemberDAO();
	MemberDTO param = new MemberDTO();
	param.setEmail_id(id);
	param.setPassword(pwd);
	
	MemberDTO dto = dao.login(param);
	
	if (dto.getEmail_id() != null && dto.getEmail_id().equals(id)) { // 사용자 인증
        // [아이디 저장하기] 체크 확인
        if (save_check != null && save_check.equals("on")) {
            CookieManager.makeCookie(response, "loginId", id, 60*60*24*7); 
        } else {
            CookieManager.deleteCookie(response, "loginId"); 
        }
		session.setAttribute("user_id", id);
        response.sendRedirect("index.jsp");
    } else {
        request.getRequestDispatcher("login.jsp?loginErr=1")
        	.forward(request, response);
    }
%>