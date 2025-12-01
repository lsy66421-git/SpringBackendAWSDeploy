<%@page import="utils.BoardPage"%>
<%@page import="model1.BoardDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="model1.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원제 게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>목록 보기(List) - 현재 페이지 : ${pageNum} (전체 : ${totalPage})</h2> 
 
	<form method="get"> 
	<table border="1" width="90%">
        <tr>
            <td align="center">
                <select name="searchField"> 
                    <option value="title" ${searchField == 'title' ? 'selected' : ''}>제목</option>
                    <option value="content" ${searchField == 'content' ? 'selected' : ''}>내용</option>
                </select>
                <input type="text" name="searchWord" value="${searchWord}">
                <input type="submit" value="검색하기" />
            </td>
        </tr>
    </table>
	</form>
	
	<table border="1" width="90%">
	    <tr> 
	    	<th width="10%">번호</th>
	        <th width="50%">제목</th>
	        <th width="15%">작성자</th>
	        <th width="10%">조회수</th>
	        <th width="15%">작성일</th>
	    </tr>

 		<c:choose>
			<c:when test="${ empty boardLists }">
				<tr>
					<td colspan="5" align="center">
						등록된 게시물이 없습니다^^
					</td>
				</tr>
			</c:when>
			<c:when test="${ !(empty boardLists) }">
				<c:set var="virtualNum" value="${totalCount - ((pageNum - 1) * pageSize)}" />
				<c:forEach items="${boardLists}" var="dto">
					<tr align="center">
						<td>${virtualNum}</td> 
						<td align="left">
							<a href="View.jsp?num=${dto.num}&pageNum=${pageNum}">${dto.title}</a>
						</td>
						<td align="center">${dto.id}</td> 
						<td align="center">${dto.visitcount}</td> 
						<td align="center">${dto.postdate}</td> 
					</tr>
					<c:set var="virtualNum" value="${virtualNum-1}" />
				</c:forEach>
 			</c:when>
		</c:choose>
    </table>

    <table border="1" width="90%">
        <tr>
        	<td align="center">
                <%-- 
                  List.jsp(컨트롤러)에서 생성한 페이징 HTML 문자열(${pagingStr})을
                  c:out 태그를 이용해 출력합니다.
                  escapeXml="false" 속성은 <a href...> 같은 HTML 태그가
                  문자열이 아닌 실제 링크로 렌더링되도록 합니다.
                --%>
                <c:out value="${pagingStr}" escapeXml="false" />
            </td>
         
    		<td align="center"><button type="button" onclick="location.href='Write.jsp';">글쓰기</button></td>
        </tr>
    </table>
</body>
</html>