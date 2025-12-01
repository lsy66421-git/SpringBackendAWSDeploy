<%@ page import="model1.BoardDAO" %>
<%@ page import="model1.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 파라미터 받기 (게시물 번호)
    String num = request.getParameter("num");
    // DAO 객체 생성
    BoardDAO dao = new BoardDAO();
    // 로직 처리
    // 조회수 증가
    dao.updateVisitCount(num);
    // 게시물 상세 정보 가져오기
    BoardDTO dto = dao.selectView(num);
    // DB 연결 종료
    dao.close();
    // 뷰(JSP)에 전달할 데이터 가공
    // JSP 뷰 파일에서 로직을 사용하지 않도록,
    // \r\n을 <br/> 태그로 변경하는 작업을 이 로직 파일에서 미리 처리합니다.
   //  if (dto.getContent() != null) {
        dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
   //  }
    // 뷰(JSP)에 전달할 데이터 request 영역에 저장
    // DTO 객체 자체를 "dto"라는 이름으로 저장
    request.setAttribute("dto", dto);

    // 뷰(noticeViewResult.jsp)로 포워딩
    request.getRequestDispatcher("/noticeViewResult.jsp").forward(request, response);
%>