<%@page import="utils.CookieManager"%>
<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    // 화면상에 on, off가 나오도록 변수 설정
    // String popupMode = "on"; 
    // 모든 쿠키 변수에 저장
    String popupClose = CookieManager.readCookie(request, "PopupClose");
    String popupMode = popupClose.equals("") ? "on" : popupClose;
/*     Cookie[] cookies = request.getCookies();
    if(cookies != null){
    	// 모든 쿠키를 확인할 수 있도록 반복문 실행
    	for(Cookie c : cookies){
    		String cookieName = c.getName();
			String cookieValue = c.getValue();
			// PopupCookie가 있으면 value값을 popupMode변수에 저장
			if(cookieName.equals("PopupClose")){
				popupMode = cookieValue;
			}
    	}
    } */
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 이용한 팝업 관리 ver 0.1</title> 
<style>
    div#popup {
        position: absolute; top:100px; left:50px; color:yellow;
        width:270px; height:100px; background-color:gray;
    }
    div#popup>div {
        position: relative; background-color:#ffffff; top:0px;
        border:1px solid gray; padding:10px; color:black;
    }
</style>
<!-- JQuery 다운로드 코드 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
// 화면이 불러온 후 자바스크립트를 불러오도록 하는 기능
window.onload = function(){
	let closeButton = document.querySelector("#closeBtn");
	closeButton.addEventListener("click", function(){
		document.querySelector("#popup").style.display = "none";
		var checkbox = document.querySelector("#cinactiveToday");
		var chkVal = checkbox.checked ? checkbox.value : none;
		if(chkval == 1){
			// fetch(실행할 주소, {설정할 내용})
			fetch('./PopupCookie.jsp?inactiveToday=1',{
				method : "get"
				// .then(function(response){retur reponse.text()})
				// 응답결과를 텍스트로 변환
			}).then(response => response.text())
			// 변환된 데이터가 공백이 아닌 경우 새로고침을 실행
			.then(responseData => {
				if(responseData != ' ' ) lovation.reload();
			})
		}
	})
}
// JQuery에서 화면이 불러온 후 자바스크립트를 불러오도록 하는 기능
    $(function() {
    	// id기 closeBtn인 태그를 선택한 후 click 이벤트를 설정
        $('#closeBtn').click(function() {
        	// popup 태그의 display를 none으로 설정
            $('#popup').hide();
        	// inactiveToday가 체크되어 있다면 데이터를 chkVal변수에 1을 저장
            var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
            if(chkVal==1){
            	// 비동기통신(ajax) : 화면이 새로고침이 되지 않고 자바스크립트를 이용하여 서버와 통신 방식
            	// 동기화통신(a태그, form태그) : 화면이 새로고침 되는 방식
            	$.ajax({
            		// 실행 주소 설정
            		url: './PopupCookie.jsp',
            		// get, post 설정
            		type:'get',
            		// 함께 보낼 데이터 설정
            		data:{inactiveToday:chkVal},
            		// 데이터의 type : 보통 text, json
            		dataType: "text",
            		// 비동기통신이 성공한 경우 실행할 함수 작성
            		success: function(responseData){
            			if(responseData != ' ') location.reload();
            		}
            	})
            }
        });
    });
</script>
</head>
<body>
    <h2>팝업 메인 페이지(ver 0.1)</h2>
    <%
         for (int i = 1; i <= 10; i++) {
        	out.print("현재 팝업창은 " + popupMode + " 상태입니다.<br/>");
        }
   // JSFunction.alertLocation("alertLocation 실행", "CookieMain.jsp", out);
   JSFunction.alertBack("alertLocation 실행", out);
        if (popupMode.equals("on")) {
    %>

       <div id="popup"> 
            <h2 align="center">공지사항 팝업입니다.</h2>
            <div align="right"><form name="popFrm">
                <input type="checkbox"  id="inactiveToday"  value="1" />
                하루 동안 열지 않음
                <input type="button"  value="닫기"  id="closeBtn" />
            </form></div>
   	 </div>
    <%
        }
    %>
</body>
</html>