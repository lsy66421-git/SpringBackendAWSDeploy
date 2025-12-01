<%@ page import="model1.BoardDAO" %>
<%@ page import="model1.BoardDTO" %>
<%@ page import="utils.BoardPage" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // DAO 생성
    BoardDAO dao = new BoardDAO();
    // 뷰(JSP)에 전달할 매개변수 저장용 맵 생성
    Map<String, Object> param = new HashMap<String, Object>();
    // 요청 파라미터 받기 (검색어)
    String searchField = request.getParameter("searchField");
    String searchWord = request.getParameter("searchWord");
    if (searchWord != null && !searchWord.trim().equals("")) {
        param.put("searchField", searchField);
        param.put("searchWord", searchWord);
    }
    // 페이징 처리
    // 게시물 수 확인
    int totalCount = dao.selectCount(param);
    // web.xml에서 컨텍스트 초기화 파라미터 값 가져오기
    int pageSize = Integer.parseInt(application.getInitParameter("POST_PER_PAGE"));
    int blockPage = Integer.parseInt(application.getInitParameter("POST_PER_BLOCK"));
    // 전체 페이지 수 계산
    int totalPage = (int) Math.ceil((double) totalCount / pageSize);
    // 현재 페이지 확인
    int pageNum = 1;
    String pageTemp = request.getParameter("pageNum");
    if (pageTemp != null && !pageTemp.equals("")) {
        pageNum = Integer.parseInt(pageTemp);
    }
    // 목록에 출력할 게시물 범위 계산
    int start = (pageNum - 1) * pageSize + 1;
    int end = pageNum * pageSize;
    param.put("start", start);
    param.put("end", end);
    // DB에서 게시물 목록 가져오기
    List<BoardDTO> boardLists = dao.selectListPage(param);  
    // DB 연결 종료
    dao.close(); 
    // 페이징 문자열 생성
    // request.getRequestURI()는 현재 요청된 URL(즉, "noticeListResult.jsp")을 반환합니다.
    // String pagingStr = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI());
    // 뷰(JSP)로 전달할 데이터 request 영역에 저장
    request.setAttribute("boardLists", boardLists);   // 게시물 목록
    request.setAttribute("totalCount", totalCount);   // 전체 게시물 수
    // request.setAttribute("pagingStr", pagingStr);  // 페이징 HTML
    request.setAttribute("pageNum", pageNum);     // 현재 페이지 번호
    request.setAttribute("totalPage", totalPage);       // 전체 페이지 수
    request.setAttribute("pageSize", pageSize);        // 페이지당 게시물 수 (JSP에서 virtualNum 계산 시 필요)   
    request.setAttribute("blockPage", blockPage);        // 페이지블락   
    // 검색어 유지를 위해 추가
    request.setAttribute("searchField", searchField);
    request.setAttribute("searchWord", searchWord);
    // 뷰(noticeList.jsp)로 포워딩
    request.getRequestDispatcher("noticeListResult.jsp").forward(request, response);
%>