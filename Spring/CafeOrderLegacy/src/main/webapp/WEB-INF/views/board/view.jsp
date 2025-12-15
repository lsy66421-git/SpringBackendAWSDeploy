<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>카페 게시판</title>
        </head>

        <body>
            <jsp:include page="../common/header.jsp" />
            <h2>카페 게시판 - 상세 보기(View)</h2>
            <table border="1" width="90%">
                <colgroup>
                    <col width="15%" />
                    <col width="35%" />
                    <col width="15%" />
                    <col width="*" />
                </colgroup>

                <!-- 게시물 정보 -->
                <tr>
                    <td>번호</td>
                    <td>${ dto.idx }</td>
                    <td>작성자</td>
                    <td>${ dto.name }</td>
                </tr>
                <tr>
                    <td>작성일</td>
                    <td>${ dto.postdate }</td>
                    <td>조회수</td>
                    <td>${ dto.visitcount }</td>
                </tr>
                <tr>
                    <td>제목</td>
                    <td colspan="3">${ dto.title }</td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td colspan="3" height="100">${ dto.content }
                        <c:if test="${ not empty dto.ofile and isImage eq true }">
                            <br /><img src="${pageContext.request.contextPath}/image.do?sfile=${ dto.sfile }"
                                style="max-width: 100%;" />
                        </c:if>
                    </td>
                </tr>

                <!-- 첨부 파일 -->
                <tr>
                    <td>첨부 파일</td>
                    <td colspan="3">
                        <c:if test="${ not empty dto.ofile }">
                            ${ dto.ofile }
                            <a
                                href="${pageContext.request.contextPath}/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">
                                [다운로드]
                            </a>
                        </c:if>
                    </td>
                </tr>

                <!-- 다운로드 횟수 -->
                <tr>
                    <td>다운로드 수</td>
                    <td colspan="3">${ dto.downcount }</td>
                </tr>

                <!-- 하단 버튼 -->
                <tr>
                    <td colspan="4" align="center">
                        <c:if test="${ not empty loginUser }">
                            <button type="button"
                                onclick="location.href='${pageContext.request.contextPath}/board/pass?mode=edit&idx=${ dto.idx }';">
                                수정하기
                            </button>
                            <button type="button"
                                onclick="location.href='${pageContext.request.contextPath}/board/pass?mode=delete&idx=${ dto.idx }';">
                                삭제하기
                            </button>
                        </c:if>
                        <button type="button" onclick="location.href='${pageContext.request.contextPath}/board/list';">
                            목록 바로가기
                        </button>
                    </td>
                </tr>
            </table>
        </body>

        </html>