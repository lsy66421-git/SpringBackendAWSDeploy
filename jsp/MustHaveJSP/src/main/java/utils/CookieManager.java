package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {

    // 명시한 이름, 값, 유지 기간 조건으로 새로운 쿠키를 생성합니다.
    public static void makeCookie(HttpServletResponse response, String cName, 
                                  String cValue, int cTime) {
        Cookie cookie = new Cookie(cName, cValue); // 쿠키 생성
        cookie.setPath("/"); // 경로 설정
        cookie.setMaxAge(cTime); // 유지 기간 설정
        response.addCookie(cookie); // 응답 객체에 추가
    }

    // 명시한 이름의 쿠키를 찾아 그 값을 반환합니다.
    public static String readCookie(HttpServletRequest request, String cName) {
        String cookieValue = ""; // 반환 값
        
        Cookie[] cookies = request.getCookies(); // request 내장 객체로부터 클라언트가 보내온 쿠키 목록 받기
        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookieName = c.getName();
                if (cookieName.equals(cName)) {
                    cookieValue = c.getValue(); // 반환 값 갱신
                }
            }
        }
        return cookieValue; // cName과 같은 쿠키가 있으면 값 반환
    }

    // 명시한 이름의 쿠키를 삭제합니다.
    public static void deleteCookie(HttpServletResponse response, String cName) {
    	// 쿠키 삭제는 쿠키생성 시 값은 빈 문자열로, 유지기간은 0으로 부여하면 되므로 makeCookie 메서드 재활용
        makeCookie(response, cName, "", 0); 
    }
}