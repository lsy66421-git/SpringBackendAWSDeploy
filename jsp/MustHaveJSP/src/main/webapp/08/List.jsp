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
// MAP 자료구조에 검색조건을 담아서 DB에 전달
Map<String, Object> param = new HashMap<String, Object>();
// 제목 혹은 내용으로 검색할 컬럼명 설정
String searchField = request.getParameter("searchField");
// 검색할 단어 설정
String searchWord = request.getParameter("searchWord");
// 검색할 단어가 있으면 MAP 자료구조에 추가
if (searchWord != null) {
    param.put("searchField", searchField);
    param.put("searchWord", searchWord);
}
// 검색조건을 포함하여 게시글의 갯수를 저장
int totalCount = dao.selectCount(param); 
// 게시글 데이터 저장
List<BoardDTO> boardLists = dao.selectList(param);

dao.close(); 

request.setAttribute("totalCount" , totalCount);
request.setAttribute("boardLists" , boardLists);
request.getRequestDispatcher("ListResult.jsp").forward(request, response);
%>