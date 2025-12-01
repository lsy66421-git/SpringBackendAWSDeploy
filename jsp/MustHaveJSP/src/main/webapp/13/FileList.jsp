<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="fileupload.MyFileDAO" %>
<%@ page import="fileupload.MyFileDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>FileUpload</title>
<script>
    // 1. 전체 선택/해제 스크립트 (헤더 체크박스용)
    function toggleAll(source) {
        const checkboxes = document.getElementsByName('checkFiles');
        for(let i=0; i<checkboxes.length; i++) {
            checkboxes[i].checked = source.checked;
        }
    }
    
    // 2. 유효성 검사 (일반 다운로드 버튼용)
    function validateForm(form) {
        const checkboxes = document.querySelectorAll('input[name="checkFiles"]:checked');
        if (checkboxes.length === 0) {
            alert("다운로드할 파일을 하나 이상 선택해주세요.");
            return false;
        }
        return true;
    }

    // 3. [추가됨] 전체 선택 후 바로 다운로드 기능
    function downloadAll() {
        // 모든 체크박스를 가져옴
        const checkboxes = document.getElementsByName('checkFiles');
        
        if (checkboxes.length === 0) {
            alert("다운로드할 파일이 없습니다.");
            return;
        }

        // 모든 체크박스를 강제로 선택 상태로 변경
        for(let i=0; i<checkboxes.length; i++) {
            checkboxes[i].checked = true;
        }

        // 폼 제출 (다운로드 요청)
        document.listForm.submit();
    }
</script>
<style>
    .btn-full-download {
        width: 250px;       /* 가로 크기 250px */
        height: 50px;       /* 세로 크기 50px (필요 시) */
        padding: 5px 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
        font-size: 16px;
    }
</style>
</head>
<body>

<h2>DB에 등록된 파일 목록 보기</h2>
<a href="FileUploadMain.jsp">파일등록1</a>
<a href="MultiUploadMain.jsp">파일등록2</a>

<%
    MyFileDAO dao = new MyFileDAO();
    List<MyFileDTO> fileLists = dao.myFileList();
    dao.close();
%>

<form name="listForm" method="post" action="${pageContext.request.contextPath}/13/MultiDownload.do" onsubmit="return validateForm(this);">
    <table border="1">
        <tr>
            <th>No.</th><th>제목</th><th>카테고리</th><th>원본 파일명</th>
            <th>저장된 파일명</th><th>작성일</th><th>바로 다운로드</th><th>다운로드 선택</th>
        </tr>
        <% for (MyFileDTO f : fileLists) { %>
        <tr>
            <td><%= f.getIdx() %></td>
            <td><%= f.getTitle() %></td>
            <td><%= f.getCate() %></td>
            <td><%= f.getOfile() %></td>
            <td><%= f.getSfile() %></td>
            <td><%= f.getPostdate() %></td>
            <td>
                <a href="Download.jsp?oName=<%= URLEncoder.encode(f.getOfile(), "UTF-8") %>&sName=<%= URLEncoder.encode(f.getSfile(), "UTF-8") %>">다운로드</a>
            </td>
            <td align="center">
                <input type="checkbox" name="checkFiles" value="<%= f.getSfile() %>::<%= f.getOfile() %>" />
            </td>
        </tr>
        <% } %>
    </table>
    
    <div style="margin-top: 10px; text-align: right; width: 90%;">
        <button type="submit" class="btn-full-download">
            선택한 파일 다운로드 (.zip)
        </button>
        <br/><br/>
        <button type="button" onclick="downloadAll();" class="btn-full-download">
            전체 파일 바로 다운로드 (.zip)
        </button>
    </div>
</form>

</body>
</html>