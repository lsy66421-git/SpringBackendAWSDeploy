<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>카페 게시판</title>
		</head>

		<body>
			<jsp:include page="../common/header.jsp" />
			<h2>카페 게시판 - 목록 보기(ListNotice)</h2>

			<form method="get">
				<table border="1" width="90%">
					<tr>
						<td align="center">
							<select name="searchField">
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="searchWord" />
							<input type="submit" value="검색하기" />
						</td>
					</tr>
				</table>
			</form>

			<table border="1" width="90%">
				<tr>
					<th width="10%">번호</th>
					<th width="*">제목</th>
					<th width="15%">작성자</th>
					<th width="10%">조회수</th>
					<th width="15%">작성일</th>
					<th width="8%">첨부</th>
				</tr>
				<c:choose>
					<c:when test="${ empty boardList  }">
						<tr>
							<td colspan="6" align="center">
								등록된 게시물이 없습니다^^*
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ boardList  }" var="row" varStatus="loop">
							<tr align="center">
								<td>
									${ row.idx }
								</td>
								<td align="left">
									<a href="${pageContext.request.contextPath}/board/view?idx=${ row.idx }">${
										row.title }</a>
								</td>
								<td>${ row.name }</td>
								<td>${ row.visitcount }</td>
								<td>${ row.postdate }</td>
								<td>
									<c:if test="${ not empty row.ofile }">
										${ row.sfile }
									</c:if>
								</td>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<table border="1" width="90%">
				<tr align="center">
					<td>
						<!-- Pagination -->
						<c:if test="${totalPages > 0}">
							<c:forEach var="i" begin="0" end="${totalPages - 1}">
								<c:choose>
									<c:when test="${i == currentPage}">
										<b>[${i + 1}]</b>
									</c:when>
									<c:otherwise>
										<a
											href="${pageContext.request.contextPath}/board/list?page=${i}&searchField=${param.searchField}&searchWord=${param.searchWord}">[${i
											+ 1}]</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</td>
					<td width="100">
						<c:if test="${ not empty sessionScope.loginUser }">
							<button type="button"
								onclick="location.href='${pageContext.request.contextPath}/board/write';">글쓰기</button>
						</c:if>
					</td>
				</tr>
			</table>

		</body>

		</html>