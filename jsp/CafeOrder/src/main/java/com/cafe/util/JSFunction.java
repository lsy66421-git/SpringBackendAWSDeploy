package com.cafe.util;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {

	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
							+ "<script>"
							+ "     alert('" + msg + "');" // 메시지창에 msg 출력하는 기능
							+"      location.href='" + url + "';" // a태그처럼 url로 이동 기능
							+"</script>";
			// JSFunction클래스에서 out.을 사용할 수 있도록 하는 기능
			out.println(script);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
					+ "<script>"
					+ "     alert('" + msg + "');" // 메시지 출력
					+"      history.back();" // 뒤로가기 실행
					+"</script>";
			out.println(script);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
    public static void alertLocation(HttpServletResponse resp, String msg, String url) {
        try {
            // 응답 컨텐츠 타입 설정 (한글 깨짐 방지)
            resp.setContentType("text/html;charset=UTF-8");
            
            // 출력 스트림 생성
            PrintWriter writer = resp.getWriter();
            
            // 자바스크립트 코드 생성
            String script = ""
                          + "<script>"
                          + "    alert('" + msg + "');"
                          + "    location.href='" + url + "';"
                          + "</script>";
                          
            // 클라이언트로 스크립트 전송
            writer.print(script);
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = ""
                          + "<script>"
                          + "    alert('" + msg + "');"
                          + "    history.back();"
                          + "</script>";
            writer.print(script);
            writer.flush(); // 응답 버퍼를 비워 클라이언트에 전송
        }
        catch (Exception e) {
        	// 이미 응답이 커밋된 경우 IOException 발생 가능
            e.printStackTrace();
        }
    }
	
}
