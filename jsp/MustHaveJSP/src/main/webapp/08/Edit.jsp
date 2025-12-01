<%@page import="model1.BoardDTO"%>
<%@page import="model1.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./IsLoggedIn.jsp" %>
<%
//파라미터로 가져온 게시글 PrimaryKey를 저장
String num = request.getParameter("num");
//DB 연결
BoardDAO dao = new BoardDAO();
//조회수 1 증가
dao.updateVisitCount(num);
BoardDTO dto = dao.selectView(num);
String sessionId = session.getAttribute("UserId").toString();
if(!sessionId.equals(dto.getId())){
	JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
}
dao.close();

request.setAttribute("dto" , dto);
request.getRequestDispatcher("EditResult.jsp").forward(request, response);
%>