package com.cafe.controller;

import java.io.File;
import java.io.IOException;

import com.cafe.dao.MVCBoardDAO;
import com.cafe.util.FileUtil;
import com.cafe.util.JSFunction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class DownloadController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ofile = req.getParameter("ofile"); // 원본 파일명
        String sfile = req.getParameter("sfile"); // 저장된 파일명
        String idx = req.getParameter("idx");     // 게시물 번호

        // 1. 파일이 저장되어 있는 위치 (서버의 소스 폴더)
        String saveDirectory = "C:\\cafe_notice_upload"; 
        
     // ★★★ [디버깅 코드 추가] 콘솔창을 확인하세요 ★★★
        File fileToCheck = new File(saveDirectory, sfile);
        System.out.println("=========== 다운로드 디버깅 ============");
        System.out.println("1. 넘겨받은 파일명(sfile): [" + sfile + "]");
        System.out.println("2. 실제 찾는 전체 경로: [" + fileToCheck.getAbsolutePath() + "]");
        System.out.println("3. 파일 존재 여부: " + fileToCheck.exists());
        System.out.println("========================================");
        
        if (!fileToCheck.exists()) {
            System.out.println("[에러] 파일이 없습니다: " + fileToCheck.getAbsolutePath());
            // 파일이 없으면 알림창을 띄우고 뒤로 돌아감
            JSFunction.alertBack(resp, "다운로드할 원본 파일이 존재하지 않습니다.");
            return;
        }

        // 3. 파일 다운로드 실행 (브라우저로 전송)
        try {
            // FileUtil이 saveDirectory에서 파일을 읽어 브라우저로 보냅니다.
            FileUtil.download(req, resp, saveDirectory, sfile, ofile);
            
            // 다운로드 성공 시 카운트 증가
            MVCBoardDAO dao = new MVCBoardDAO();
            dao.downCountPlus(idx);
            dao.close();
            
        } catch (Exception e) {
             e.printStackTrace();
             JSFunction.alertBack(resp, "다운로드 중 서버 오류가 발생했습니다.");
        }
    }
}