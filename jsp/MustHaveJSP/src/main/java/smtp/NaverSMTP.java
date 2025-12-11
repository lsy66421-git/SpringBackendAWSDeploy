package smtp;

import java.util.Map;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class NaverSMTP {
	private final Properties serverInfo;
	// private final Authenticator auth; // 삭제: 생성자에서 고정된 auth를 만들지 않음
	
	public NaverSMTP() {
		serverInfo = new Properties();
		serverInfo.put("mail.smtp.host",  "smtp.naver.com");
		serverInfo.put("mail.smtp.port",  "465");
		// serverInfo.put("mail.smtp.starttls.enable",  "true");
		serverInfo.put("mail.smtp.auth",  "true");
		serverInfo.put("mail.smtp.debug",  "true");
		serverInfo.put("mail.smtp.ssl.enable",  "true");
		// serverInfo.put("mail.smtp.socketFactory.port",  "465");
		// serverInfo.put("mail.smtp.socketFactory.class",  "javax.net.ssl.SSLSocketFactory");
		// serverInfo.put("mail.smtp.socketFactory.fallback",  "false");
		
        // 생성자에서는 서버 정보만 설정하고, 인증 정보(ID/PW)는 설정하지 않습니다.
	}
	
	public void emailSending(Map<String, String> mailInfo) throws MessagingException {
        
        // 1. 세션 생성 (여기서 사용자 입력 정보를 바탕으로 Authenticator를 생성합니다)
        Session session = Session.getInstance(serverInfo, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Map에서 사용자 입력 ID(보내는 사람)와 비밀번호를 가져옴
                return new PasswordAuthentication(mailInfo.get("from"), mailInfo.get("password"));
            }
        });
        
        session.setDebug(true);

        // 2. 메시지 작성
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailInfo.get("from")));     // 보내는 사람
        msg.addRecipient(Message.RecipientType.TO,new InternetAddress(mailInfo.get("to")));  // 받는 사람
        msg.setSubject(mailInfo.get("subject"));                    // 제목
        msg.setContent(mailInfo.get("content"), mailInfo.get("format")); // 내용

        // 3. 전송
        Transport.send(msg);
    }
}