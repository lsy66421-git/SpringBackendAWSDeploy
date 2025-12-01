<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<script>
function deletePost() {
	var confirmed = confirm("정말로 삭제하겠습니까 ?");
	if(confirmed){
		var form = document.writeFrm;
		form.method = "post";
		// 삭제 후 목록으로 돌아갈 때 현재 페이지 번호를 함께 전달합니다.
		form.action = "DeleteProcess.jsp?pageNum=${pageNum}"; 
		form.submit();
	}
}
</script>
</head>
<body>
<jsp:include page="../Common/Link.jsp" />
<h2>회원제 게시판 상세보기 (View)</h2>
<form name="writeFrm"> 
    <input type="hidden"  name="num"  value="${dto.num}" />
    <table border="1"  width="90%">
        <tr>
            <td>번호</td>
            <td>${dto.num}</td>
            <td>작성자</td>
            <td>${dto.name}</td>
        </tr>
        <tr>
            <td>작성일</td>
            <td>${dto.postdate}</td>
            <td>조회수</td>
            <td>${dto.visitcount}</td>
        </tr>
        <tr>
            <td>제목</td>
            <td colspan="3">${dto.title}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td colspan="3"  height="100">${dto.content}</td> 
        </tr>
        <tr>
            <td colspan="4" align="center">
            
            <c:if test="${sessionScope.UserId != null && sessionScope.UserId == dto.id}">
                <button type="button" onclick="location.href='Edit.jsp?num=${dto.num}&pageNum=${pageNum}';" >수정하기</button>
                <button type="button" onclick="deletePost();" >삭제하기</button>
            </c:if>
            
            <button type="button" onclick="location.href='List.jsp?pageNum=${pageNum}';" >목록보기</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>