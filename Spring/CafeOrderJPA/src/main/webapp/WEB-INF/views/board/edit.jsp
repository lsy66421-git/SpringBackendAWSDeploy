<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>카페 게시판</title>
            <script type="text/javascript">
                function validateForm(form) {
                    if (form.name.value == "") {
                        alert("작성자를 입력하세요.");
                        form.name.focus();
                        return false;
                    }
                    if (form.title.value == "") {
                        alert("제목을 입력하세요.");
                        form.title.focus();
                        return false;
                    }
                    if (form.content.value == "") {
                        alert("내용을 입력하세요.");
                        form.content.focus();
                        return false;
                    }
                }
            </script>
        </head>

        <body>
            <jsp:include page="../common/header.jsp" />
            <h2>카페 게시판 - 수정하기(Edit)</h2>
            <form name="writeFrm" method="post" action="${pageContext.request.contextPath}/board/edit"
                enctype="multipart/form-data" onsubmit="return validateForm(this);">
                <input type="hidden" name="idx" value="${ dto.idx }" />
                <table border="1" width="90%">
                    <tr>
                        <td>작성자</td>
                        <td>
                            <input type="text" name="name" style="width:150px;" value="${ dto.name }" required
                                readonly />
                            (이름은 수정 불가)
                        </td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td>
                            <input type="text" name="title" style="width:90%;" value="${ dto.title }" required />
                        </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" style="width:90%;height:300px;"
                                required>${ dto.content }</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>첨부 파일</td>
                        <td>
                            <c:if test="${ not empty dto.ofile }">
                                현재 파일: ${ dto.ofile } <br />
                            </c:if>
                            <input type="file" name="file" /> (새 파일 업로드 시 교체됨)
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <button type="submit">작성 완료</button>
                            <button type="reset">다시 입력</button>
                            <button type="button"
                                onclick="location.href='${pageContext.request.contextPath}/board/list';">목록
                                보기</button>
                        </td>
                    </tr>
                </table>
            </form>
        </body>

        </html>