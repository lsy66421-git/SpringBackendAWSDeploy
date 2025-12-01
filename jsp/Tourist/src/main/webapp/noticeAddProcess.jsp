<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardDTO"%>
<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./IsLoggedIn.jsp" %>
<%
String title = request.getParameter("title");
String content = request.getParameter("content");

if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
    JSFunction.alertBack("제목과 내용을 모두 입력해주세요.", out);
    return;
}

BoardDTO dto = new BoardDTO();
dto.setTitle(title);
dto.setContent(content);
dto.setId(session.getAttribute("user_id").toString());

BoardDAO dao = new BoardDAO();
int iResult = dao.insertWrite(dto);
dao.close();

if (iResult == 1) {
	JSFunction.alertLocation("글이 등록되었습니다.", "noticeList.jsp", out);
} else {
    JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}
%>