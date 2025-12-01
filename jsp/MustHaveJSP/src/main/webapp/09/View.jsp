<%@page import="model1.BoardDTO"%>
<%@page import="model1.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 1. 파라미터로 가져온 게시글 PrimaryKey와 페이지 번호를 저장
String num = request.getParameter("num");
String pageNum = request.getParameter("pageNum"); // 목록 복귀 시 유지를 위해 필요

// 2. DB 연결 및 데이터 조회
BoardDAO dao = new BoardDAO();

// 3. 조회수 1 증가
dao.updateVisitCount(num);

// 4. 게시글 데이터 조회
BoardDTO dto = dao.selectView(num);

// 5. DB 연결 닫기
dao.close();

// 6. View(ViewResult.jsp)에서 사용할 데이터를 request 영역에 저장
request.setAttribute("dto", dto);
request.setAttribute("pageNum", pageNum); // 현재 페이지 번호 저장

// 7. View 파일로 포워드
request.getRequestDispatcher("ViewResult.jsp").forward(request, response);
%>