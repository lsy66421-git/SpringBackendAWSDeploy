<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Properties"%>
<%@ page import="smtp.NaverSMTP"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 폼값(이메일 내용) 저장
    Map<String, String> emailInfo = new HashMap<String, String>();
    
    // config.properties 파일에서 ID/PW 읽어오기
    // 1. 프로퍼티 객체 생성
    Properties props = new Properties();
    
    // 2. 프로퍼티 파일 경로 읽기 (경로는 실제 프로젝트 위치에 맞게 수정 필요, 예: /17/config.properties)
    String configPath = "C:/Users/it/Desktop/jsp/MustHaveJSP/config.properties"; 
    
    try {
        // 3. 파일 로드
        props.load(new FileReader(configPath));
        
        // 4. 프로퍼티에서 id와 pw 가져와서 Map에 저장
        emailInfo.put("from", props.getProperty("id") + "@naver.com");       // 보내는 사람 (config의 id 사용)
        emailInfo.put("password", props.getProperty("pw"));   // 비밀번호 (config의 pw 사용)
    } 
    catch (Exception e) {
        out.println("설정 파일을 읽는 중 오류가 발생했습니다.");
        e.printStackTrace();
        return; // 오류 발생 시 중단
    }

    emailInfo.put("to", request.getParameter("to"));           // 받는 사람
    emailInfo.put("subject", request.getParameter("subject")); // 제목
    
    // 내용은 메일 포맷에 따라 다르게 처리
    String content = request.getParameter("content");
    String format = request.getParameter("format");

    if (format.equals("text")) {
        emailInfo.put("content", content);
        emailInfo.put("format", "text/plain;charset=UTF-8");
    }
    else if (format.equals("html")) {
        content = content.replace("\r\n", "<br/>");
        String htmlContent = "";
        try {
            // HTML 템플릿 파일 읽기
            String templatePath = application.getRealPath("/17/MailForm.html");
            BufferedReader br = new BufferedReader(new FileReader(templatePath));
            String oneLine;
            while ((oneLine = br.readLine()) != null) {
                htmlContent += oneLine + "\n";
            }
            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        htmlContent = htmlContent.replace("__CONTENT__", content);
        emailInfo.put("content", htmlContent);
        emailInfo.put("format", "text/html;charset=UTF-8");
    }

    try {
        NaverSMTP smtpServer = new NaverSMTP();
        smtpServer.emailSending(emailInfo);
        out.print("이메일 전송 성공");
    }
    catch (Exception e) {
        out.print("이메일 전송 실패");
        e.printStackTrace();
    }
%>