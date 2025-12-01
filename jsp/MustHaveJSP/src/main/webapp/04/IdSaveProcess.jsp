<%@ page import="utils.CookieManager"%>
<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String user_id = request.getParameter("user_id");
    String user_pw = request.getParameter("user_pw");
    String save_check = request.getParameter("save_check"); // 폼 값 읽기
    
    // (예시로) ID가 "must"이고 PW가 "1234"일 때만 인증 성공
    if ("must".equals(user_id) && "1234".equals(user_pw)) { // 사용자 인증
        // 로그인 성공
        
        // [아이디 저장하기] 체크 확인
        if (save_check != null && save_check.equals("Y")) {
            // 쿠키 생성
            // 86400초 = 24시간 * 60분 * 60초 = 1일
            CookieManager.makeCookie(response, "loginId", user_id, 86400); 
        } else {
            // 쿠키 삭제
            CookieManager.deleteCookie(response, "loginId"); 
        }
        
        // 로그인 성공 메시지 출력 후 IdSaveMain.jsp로 이동
        JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out); 
    } else {
        // 로그인 실패
        
        // 로그인 실패 메시지 출력 후 이전 페이지로 돌아가기
        JSFunction.alertBack("로그인에 실패했습니다.", out); 
    }
%>