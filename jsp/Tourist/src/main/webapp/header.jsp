<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL을 사용하기 위한 태그 라이브러리 선언 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header id="header">
	<div class="header_area box_inner clear">	
		<h1><a href="index.jsp">Tourist in tour</a></h1>
		<p class="openMOgnb"><a href="#"><b class="hdd">메뉴열기</b> <span></span><span></span><span></span></a></p>
		<div class="header_cont">
			<ul class="util clear">
				<%--
				  스크립틀릿을 모두 제거합니다.
				  <c:choose> 태그를 사용하여 조건 분기를 합니다.
				--%>
				<c:choose>
					<%-- 
					  <c:when>: EL을 사용하여 sessionScope의 user_id 속성이 비어있는지(empty) 확인합니다.
					  (empty는 null이거나 빈 문자열("")일 경우 true가 됩니다.)
					--%>
					<c:when test="${ empty sessionScope.user_id }">
						<%-- 로그인하지 않은 경우 --%>
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</c:when>
					<%-- <c:otherwise>: 로그인한 경우 --%>
					<c:otherwise>
						<%-- EL을 사용하여 sessionScope의 user_id 값을 바로 출력합니다. --%>
						<li style="color:white;">${sessionScope.user_id}님 반갑습니다.</li>
						<li><a href="mypage.jsp">마이페이지</a></li>
						<li><a href="logout.jsp">로그아웃</a></li>
					</c:otherwise>
				</c:choose>		
			</ul>	
			<nav>
			<ul class="gnb clear">
				<li><a href="javascript:;" class="openAll1">여행정보</a>
                       <div class="gnb_depth gnb_depth2_1">
                           <ul class="submenu_list">
                               <li><a href="javascript:;">국내</a></li>
                               <li><a href="javascript:;">해외</a></li>
                           </ul>
                       </div>
				</li>
				<li><a href="javascript:;" class="openAll2">고객센터</a>
			        <div class="gnb_depth gnb_depth2_2">
                           <ul class="submenu_list">
							   <%-- 
								 링크를 로직 파일(noticeList.jsp)로 설정한 것이 올바릅니다.
								 (기존 파일[cite: 11]과 동일)
							   --%>
                               <li><a href="noticeList.jsp">공지사항</a></li>
                               <li><a href="javascript:;">문의하기</a></li>
                           </ul>
                       </div>
				</li>
				<li><a href="javascript:;" class="openAll3">상품투어</a>
                       <div class="gnb_depth gnb_depth2_3">
                           <ul class="submenu_list">
                               <li><a href="javascript:;">프로그램 소개</a></li>
                               <li><a href="javascript:;">여행자료</a></li>
                           </ul>
                       </div>
				</li>
				<li><a href="javascript:;" class="openAll4">티켓/가이드</a>
                       <div class="gnb_depth gnb_depth2_4">
                           <ul class="submenu_list">
                               <li><a href="javascript:;">항공</a></li>
                               <li><a href="javascript:;">호텔</a></li>
                           </ul>
                       </div>
				</li>
			</ul>
               </nav>
			<p class="closePop"><a href="javascript:;">닫기</a></p>
		</div>
	</div>
</header>