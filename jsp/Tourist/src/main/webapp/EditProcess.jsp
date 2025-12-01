<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
String num = request.getParameter("num");
String title = request.getParameter("title");
String content = request.getParameter("content");

if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
    JSFunction.alertBack("제목과 내용을 모두 입력해주세요.", out);
    return;
}

BoardDTO dto = new BoardDTO();
dto.setNum(num);
dto.setTitle(title);
dto.setContent(content);
BoardDAO dao = new BoardDAO();
int affected = dao.updateEdit(dto);
dao.close();
if (affected == 1) {
    response.sendRedirect("noticeView.jsp?num=" + dto.getNum());
}
else {
    JSFunction.alertBack("수정하기에 실패하였습니다.", out);
}
%>