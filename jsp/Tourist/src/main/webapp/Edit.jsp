<%@ page import="model1.BoardDAO" %>
<%@ page import="model1.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String num = request.getParameter("num");
    BoardDAO dao = new BoardDAO();
    BoardDTO dto = dao.selectView(num);  
    dao.close();
    request.setAttribute("dto", dto);
    request.getRequestDispatcher("EditResult.jsp").forward(request, response);
%>