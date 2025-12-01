<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판</title>
<script type="text/javascript">
function validateForm(form){
	if(form.title.value == ""){
		alert("제목을 입력하세요.");
		form.title.focus();
		return false;
	}
	if(form.content.value == ""){
		alert("내용을 입력하세요.");
		form.content.focus();
		return false;
	}
}
</script>
</head>
<body>
<jsp:include page="../Common/Link.jsp" />
<h2>회원 게시판 - 수정하기(Edit)</h2>
<form name="writeFrm" method="post" action="EditProcess.jsp"
	onsubmit="return validateForm(this);">
    
	<input type="hidden" name="num" value="${dto.num}"/>
    <input type="hidden" name="pageNum" value="${pageNum}"/> 
    
	<table border="1" width="90%">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" style="width:90%;" value="${dto.title}"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" style="width:90%; height:100px;">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">수정하기</button>
                <button type="button" onclick="location.href='View.jsp?num=${dto.num}&pageNum=${pageNum}';">수정 취소</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>