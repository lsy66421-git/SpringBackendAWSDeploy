<%@ page import="model1.BoardDAO" %>
<%@ page import="model1.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 1. 파라미터 받기 (게시물 번호)
    String num = request.getParameter("num");

    // 2. DAO 객체 생성 및 데이터 조회
    BoardDAO dao = new BoardDAO();
    BoardDTO dto = dao.selectView(num); // 
    
    // 3. DB 연결 종료
    dao.close();

    // 4. 뷰(JSP)에 전달할 데이터 request 영역에 저장
    request.setAttribute("dto", dto);

    // 5. 뷰(EditResult.jsp)로 포워딩
    request.getRequestDispatcher("/notice_add_result.jsp").forward(request, response);
%>