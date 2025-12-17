<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>게시판 글쓰기</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
      <jsp:include page="../common/header.jsp" />

      <div style="width:90%; margin:auto;">
        <h2>게시판 - 글쓰기(Write)</h2>
        <form action="${pageContext.request.contextPath}/board/write" method="post" name="writeFrm" enctype="multipart/form-data">
          <table border="1" width="100%">
            <tr>
              <td>작성자</td>
              <td>
                <input type="text" name="name" style="width:150px;" required value="${sessionScope.loginUser.name}"
                  readonly />
                (로그인한 사용자)
              </td>
            </tr>
            <tr>
              <td>제목</td>
              <td>
                <input type="text" name="title" style="width:90%;" required />
              </td>
            </tr>
            <tr>
              <td>내용</td>
              <td>
                <textarea name="content" style="width:90%;height:300px;" required></textarea>
              </td>
            </tr>
            <tr>
              <td>첨부 파일</td>
              <td>
                <input type="file" name="file" />
              </td>
            </tr>
            <!-- Password field removed -->
            <tr>
              <td colspan="2" align="center">
                <button type="submit">작성 완료</button>
                <button type="reset">다시 입력</button>
                <button type="button" onclick="location.href='${pageContext.request.contextPath}/board/list';">목록
                  보기</button>
              </td>
            </tr>
          </table>
        </form>
      </div>

      <jsp:include page="../common/footer.jsp" />
    </body>

    </html>