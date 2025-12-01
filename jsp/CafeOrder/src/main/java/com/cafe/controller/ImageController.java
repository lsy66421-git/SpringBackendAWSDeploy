package com.cafe.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image.do")
public class ImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청 파라미터(파일명) 받기
        String sfile = req.getParameter("sfile");
        
        // 2. 이미지가 저장된 실제 물리 경로 (다운로드/업로드 경로와 일치해야 함)
        String saveDirectory = "C:\\cafe_notice_upload";
        
        // 3. 파일 객체 생성
        File imgFile = new File(saveDirectory, sfile);
        
        if (imgFile.exists()) {
            // 4. MIME 타입 설정 (이미지라고 알려줌)
            String ext = sfile.substring(sfile.lastIndexOf(".") + 1).toLowerCase();
            String mimeType = "image/jpeg"; // 기본값
            if (ext.equals("png")) mimeType = "image/png";
            else if (ext.equals("gif")) mimeType = "image/gif";
            
            resp.setContentType(mimeType);
            
            // 5. 파일 읽어서 브라우저로 출력 (스트림 전송)
            try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(imgFile));
                 BufferedOutputStream bout = new BufferedOutputStream(resp.getOutputStream())) {
                
                byte[] buf = new byte[1024];
                int read = 0;
                while ((read = bin.read(buf)) != -1) {
                    bout.write(buf, 0, read);
                }
            } catch (Exception e) {
                System.out.println("이미지 로드 실패: " + e.getMessage());
            }
        } else {
            // 파일이 없을 경우 엑스박스 등을 방지하기 위해 아무것도 안 하거나 기본 이미지 출력
            System.out.println("이미지 파일이 존재하지 않음: " + imgFile.getAbsolutePath());
        }
    }
}