<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	String id = request.getParameter("user_id");
	String pwd = request.getParameter("user_pwd");
	if (id.equalsIgnoreCase("must") && pwd. equalsIgnoreCase("1234")){
		session.setAttribute("user_id","must");
		response.sendRedirect("ResponseWelcome.jsp");
		}	
	else{
		request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
			.forward(request, response);
	}
	%>