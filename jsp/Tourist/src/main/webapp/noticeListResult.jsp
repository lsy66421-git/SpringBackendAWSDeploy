<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="utils.BoardPage" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> 투어리스트인투어 </title>
<link rel="stylesheet" href="css/swiper.min.css">
<link rel="stylesheet" href="css/common.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/rollmain.js"></script>
<script src="js/jquery.easing.js"></script>	
<script src="js/common.js"></script>  
<script src="js/jquery.smooth-scroll.min.js"></script> 
</head>
<body>
	<div id="container">
		<jsp:include page="./header.jsp"/>
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
				<p class="location">고객센터 <span class="path">/</span>공지사항</p>
				<ul class="page_menu clear">
					<li><a href="noticeListResult.jsp" class="on">공지사항</a></li>
					<li><a href="#">문의하기</a></li>
				</ul>
			</div>
		</div>	
		<div class="bodytext_area box_inner">
            
			<form action="noticeListResult.jsp" class="minisrch_form">
				<fieldset>
					<td align="center">
		                <select name="searchField"> 
		                	<option value="title" ${ searchField == 'title' ? 'selected' : '' }>제목</option>
		                	<option value="content" ${ searchField == 'content' ? 'selected' : '' }>내용</option>
		                </select>
		                <input type="text" name="searchWord" value="${ searchWord }">
		                 <button class="btn_bbs">검색</button>
		            </td>
				</fieldset>
			</form>

			<table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
				<caption class="hdd">공지사항  목록 - 현재 페이지 : ${ pageNum } (전체 : ${ totalPage })</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
                    <c:choose>
                        <c:when test="${ empty boardLists }">
						    <tr>
						        <td colspan="4" align="center">
						            등록된 게시물이 없습니다^^
						        </td>
						    </tr>
                        </c:when>
                        <c:otherwise>
                             <c:set var="virtualNum" value="0" />
                            <c:set var="countNum" value="0" /> 
						    <c:forEach items="${ boardLists }" var="dto" varStatus="loop">				    	
                                <c:set var="virtualNum" value="${totalCount - (((pageNum - 1) * pageSize) + countNum+1)}" />
						        <tr align="center">
						            <td class="tit_notice">${virtualNum}</td> 
						            <td class="tit_notice"> 
                                        <a href="noticeView.jsp?num=${ dto.num }">${ dto.title }</a> 
                                    </td>
						            <td class="tit_notice">${ dto.visitcount }</td> 
						            <td class="tit_notice">${ dto.postdate }</td> 
						        </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
				</tbody>
			</table>
			<div class="pagination">
				<tr align="center">
					<td>${ BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) }</td>
					<%-- <td>${ pagingStr }</td> --%>
				</tr>
			</div>
			<div class="btn_line txt_right" style="margin-top: 20px;">
				<c:if test="${ not empty user_id }">
					<a href="notice_add.jsp" class="btn_bbs">글쓰기</a>
				</c:if>
			</div>		
		</div>
	</div>
	<jsp:include page="./footer.jsp"/>
	<jsp:include page="./quicklink.jsp"/>
</body>
</html>