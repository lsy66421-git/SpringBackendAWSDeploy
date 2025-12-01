<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<title> ${dto.title} | 공지사항 | 고객센터 | 투어리스트인투어 </title>
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="css/common.css">
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/common.js"></script>  
	<script src="js/jquery.smooth-scroll.min.js"></script> 
	<script>
		function deletePost() {
			var confirmed = confirm("정말로 삭제하겠습니까 ?");
			if(confirmed){
				var form = document.writeFrm;
				form.method = "post";
				form.action = "DeleteProcess.jsp";
				form.submit();
			}
		}
	</script>
</head>
<body>
	<div id="container">
		<jsp:include page="./header.jsp"/>
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
				<p class="location">고객센터 <span class="path">/</span>공지사항</p>
				<ul class="page_menu clear">
					<li><a href="noticeList.jsp" class="on">공지사항</a></li>
					<li><a href="#">문의하기</a></li>
				</ul>
			</div>
		</div>	
		<div class="bodytext_area box_inner">			
			<ul class="bbsview_list">
				<li class="bbs_title">${dto.title}</li>
                <li class="bbs_hit">조회수 : <span>${dto.visitcount}</span></li>
				<li class="bbs_date">작성일 : <span>${dto.postdate}</span></li>
				<li class="bbs_content">
					<div class="editer_content">
					    <%-- <c:out value="${dto.content}" escapeXml="false" /> --%>
					    <c:out value="${dto.content}" />
                    </div>
				</li>
			</ul>
			<div style="text-align: right; padding: 10px 0;">         
				<%-- <c:if test="${ not empty sessionScope.user_id && sessionScope.user_id == dto.id }"> --%>
				<c:if test="${ not empty user_id && user_id == dto.id }">
                    <button type="button" onclick="location.href='Edit.jsp?num=${dto.num}';"
                        class="btn_bbs">수정하기</button>
                    <button type="button" onclick="deletePost();"
                        class="btn_bbs">삭제하기</button>
				</c:if>                
				<a href="noticeList.jsp" class="btn_bbs">목록</a>
			</div>		
			<ul class="near_list mt20">
                <%-- TODO: 이전글/다음글 로직은 추가 구현이 필요합니다. --%>
				<li><h4 class="prev">다음글</h4><a href="javascript:;">추석 연휴 티켓/투어 배송 및 직접 수령 안내</a></li>		
				<li><h4 class="next">이전글</h4><a href="javascript:;">이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)</a></li>
			</ul>
		</div>
        <%-- 삭제 폼의 hidden input 값도 EL로 설정합니다. --%>
		<form name="writeFrm">
		    <input type="hidden"  name="num"  value="${dto.num}" />
		</form>
	</div>
	<jsp:include page="./footer.jsp"/>
	<jsp:include page="./quicklink.jsp"/>
</body>
</html>