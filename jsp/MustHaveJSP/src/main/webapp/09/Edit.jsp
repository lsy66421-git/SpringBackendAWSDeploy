<%@page import="utils.JSFunction"%>
<%@page import="model1.BoardDTO"%>
<%@page import="model1.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./IsLoggedIn.jsp" %>
<%
// 1. 파라미터로 가져온 게시글 PrimaryKey와 페이지 번호를 저장
String num = request.getParameter("num");
String pageNum = request.getParameter("pageNum"); // 목록 복귀 시 유지를 위해 필요

// 2. DB 연결 및 데이터 조회
BoardDAO dao = new BoardDAO();
BoardDTO dto = dao.selectView(num);
dao.close();

// 3. 세션 ID와 작성자 ID 일치 여부 확인 (권한 체크)
String sessionId = session.getAttribute("UserId").toString();
if(!sessionId.equals(dto.getId())){
	JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
    return; // 권한이 없으면 여기서 즉시 종료
}

// 4. EditView(EditResult.jsp)에서 사용할 데이터를 request 영역에 저장
request.setAttribute("dto", dto);
request.setAttribute("pageNum", pageNum); // 현재 페이지 번호 저장

// 5. View 파일로 포워드
request.getRequestDispatcher("EditResult.jsp").forward(request, response);
%>