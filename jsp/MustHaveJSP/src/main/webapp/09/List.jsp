<%@page import="utils.BoardPage"%>
<%@page import="model1.BoardDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="model1.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// DB 연결
BoardDAO dao = new BoardDAO();

// 1. 검색 조건 및 파라미터 초기화
Map<String, Object> param = new HashMap<String, Object>();

// searchField와 searchWord를 null 대신 빈 문자열로 초기화하여 안정성 향상
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");

if (searchField == null) searchField = "";
if (searchWord == null) searchWord = "";

// 2. 검색 조건이 있으면 MAP에 추가
if (!searchWord.equals("")) {
    param.put("searchField", searchField);
    param.put("searchWord", searchWord);
}

// 3. 페이지 처리 변수 계산 (web.xml 설정 사용)
int pageSize = Integer.parseInt(application.getInitParameter("POST_PER_PAGE"));
int blockPage = Integer.parseInt(application.getInitParameter("POST_PER_BLOCK"));

// 현재 페이지 확인
int pageNum = 1;
String pageTemp = request.getParameter("pageNum");
if(pageTemp != null && !pageTemp.equals("")){
	pageNum = Integer.parseInt(pageTemp);
}

// 4. 게시물 목록 범위 및 개수 계산
int totalCount = dao.selectCount(param);
int totalPage = (int)Math.ceil((double)totalCount/pageSize); // 전체 페이지 수

int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호 [cite: 102]
int end = pageNum * pageSize; // 마지막 게시물 번호 [cite: 102]

param.put("start", start);
param.put("end", end);

// 5. 게시물 목록 받기
List<BoardDTO> boardLists = dao.selectListPage(param);
dao.close(); // DB 연결 닫기 [cite: 103]

//★★★ 6. 페이징 HTML 문자열 생성 (컨트롤러에서 처리) ★★★
//페이징 링크가 뷰(ListResult.jsp)가 아닌 컨트롤러(List.jsp)를 가리키도록
//request.getRequestURI() (결과: "/프로젝트명/List.jsp")를 사용합니다.
String pagingStr = BoardPage.pagingStr(
                     totalCount, 
                     pageSize, 
                     blockPage, 
                     pageNum, 
                     request.getRequestURI(), // "List.jsp"
                     searchField, 
                     searchWord
                 );

//7. View (ListResult.jsp)로 전달할 데이터 설정
request.setAttribute("totalCount" , Integer.valueOf(totalCount));
request.setAttribute("boardLists" , boardLists);
request.setAttribute("pagingStr", pagingStr); // ★★★ 생성된 페이징 HTML 저장 ★★★
request.setAttribute("pageSize", Integer.valueOf(pageSize));
request.setAttribute("blockPage", Integer.valueOf(blockPage));
request.setAttribute("pageNum", Integer.valueOf(pageNum));
request.setAttribute("totalPage", Integer.valueOf(totalPage));
request.setAttribute("searchField", searchField);
request.setAttribute("searchWord", searchWord);

// 7. View 파일로 포워드
request.getRequestDispatcher("ListResult.jsp").forward(request, response);
%>