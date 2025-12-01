<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카페 게시판</title>
<script type="text/javascript">
	function validateForm(form) {
		if (form.name.value == "") {
			alert("작성자를 입력하세요");
			form.name.focus();
			return false;
		}
		if (form.title.value == "") {
			alert("제목을 입력하세요");
			form.name.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하세요");
			form.name.focus();
			return false;
		}
		if (form.pass.value == "") {
			alert("비밀번호를 입력하세요");
			form.name.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<h2>카페 게시판 - 글쓰기(Write)</h2>
	<form name="writeFrm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/write.do"  onsubmit="return validateForm(this);">
    <table border="1" width="90%">
      <tr>
	    <td>작성자</td>
	    <td>
	        <input type="text" value="${ sessionScope.loginUser.name }" style="width: 150px; border: none; background: transparent;" readonly />
	        
	        <input type="hidden" name="name" value="${ sessionScope.loginUser.name }" />
	    </td>
	  </tr>
      <tr>
        <td>제목</td>
        <td>
          <input type="text" name="title" style="width: 90%" />
        </td>
      </tr>
      <tr>
        <td>내용</td>
        <td>
          <textarea name="content" style="width: 90%; height: 100px;"></textarea>
        </td>
      </tr>
      <tr>
        <td>첨부 파일</td>
        <td>
          <input type="file" name="ofile" />
        </td>
      </tr>
      <tr>
        <td>저장 경로</td>
        <td>
          <input type="text" name="savePath" value="C:/cafe_notice_upload" style="width: 300px" />
          <br/>* 폴더 경로를 지정하세요.
        </td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td>
          <input type="password" name="pass" style="width: 100px" />
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <button type="submit">작성 완료</button>
          <button type="reset">RESET</button>
          <button type="button" onclick="location.href='${pageContext.request.contextPath}/listNotice.do';">목록 바로가기</button>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>