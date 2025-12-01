<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="1"  width="90%">
	<tr>
		<td align="center">
			<% if (session.getAttribute("UserId") == null) { %>
				<a href="login.jsp">로그인</a>
			<% } else { %>
				<a href="logout.jsp">로그아웃</a>
			<% } %>
				&nbsp;&nbsp;&nbsp;
				<a href="noticeList.jsp">게시판(페이징X)</a>
				&nbsp;&nbsp;&nbsp;
				<a href="noticeListPaging.jsp">게시판(페이징O)</a>
		</td>
	</tr>
</table>