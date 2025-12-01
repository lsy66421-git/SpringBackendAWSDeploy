package com.cafe.controller;

import java.io.IOException;

import com.cafe.dao.MVCBoardDAO;
import com.cafe.dto.MVCBoardDTO;
import com.cafe.util.FileUtil;
import com.cafe.util.JSFunction;

// ❗ 추가된 import 문
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part; // Part를 사용하기 위해 필요합니다.

// 파일 크기 제한 관련 예외를 처리하기 위한 임포트
// Note: 실제 WAS 환경에 따라 어떤 예외가 발생하는지 다를 수 있으나,
// 일반적으로 jakarta.servlet.ServletException의 원인(Root Cause)으로 용량 제한 예외가 포함됩니다.
// 여기서는 일반적인 ServletException을 catch하고 메시지를 확인하는 방식으로 구현합니다.

@MultipartConfig(
    maxFileSize = 1024 * 1024 * 1, // 1MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)
@WebServlet("/write.do")
public class WriteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // (선택사항) 로그인 하지 않은 경우 접근 차단 로직을 추가할 수 있습니다.
        HttpSession session = req.getSession();
        if (session.getAttribute("loginUser") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", req.getContextPath() + "CafeServlet?command=login_form");
            return;
        }

        req.getRequestDispatcher("/views/notice/Write.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
	    	// 1. 파일 저장 경로 설정
	        String saveDirectory = "C:/cafe_notice_upload";
	
	        // 2. 폴더 자동 생성 확인
	        java.io.File dir = new java.io.File(saveDirectory);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	
	        // 3. 파일 업로드 처리
	        String originalFileName = "";
	        
            // 파일 업로드 처리 (FileUtil.uploadFile이 내부적으로 Part를 처리한다고 가정)
            originalFileName = FileUtil.uploadFile(req, saveDirectory);

	        // 4. 폼값을 DTO에 저장
	        MVCBoardDTO dto = new MVCBoardDTO();
	
	        // JSP의 hidden input에서 넘어온 작성자 이름(name)을 저장
	        dto.setName(req.getParameter("name"));
	
	        dto.setTitle(req.getParameter("title"));
	        dto.setContent(req.getParameter("content"));
	        dto.setPass(req.getParameter("pass"));
	
	        // 5. 파일명 처리
	        if (originalFileName != null && !originalFileName.equals("")) {
	            String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
	            dto.setOfile(originalFileName);
	            dto.setSfile(savedFileName);
	        }
	
	        // 6. DB 저장 (DAO 호출)
	        MVCBoardDAO dao = new MVCBoardDAO();
	        int result = dao.insertWrite(dto);
	        dao.close();
	
	        // 7. 결과 처리
	        if (result == 1) {
	            resp.sendRedirect(req.getContextPath() + "/listNotice.do");
	        } else {
	            // ❗ 파일 업로드 외의 문제로 DB 저장 실패 시 업로드된 파일 삭제 처리 추가 권장
	            if (dto.getSfile() != null) {
	                FileUtil.deleteFile2(saveDirectory, dto.getSfile());
	            }
	            JSFunction.alertBack(resp, "글쓰기에 실패했습니다.");
	        }
        } catch (Exception e) {
            System.out.println("[에러] 파일 업로드 예외: " + e.getMessage());
            
            // 파일 용량 초과 예외 처리: doPost 전체를 감싸서 예외를 잡고 처리
            // SizeLimitExceededException이 직접 발생하거나 ServletException의 Cause로 오는 경우를 대비
            String errorMessage = e.getMessage().toLowerCase();         
            if (e.getCause() != null) {
                errorMessage += e.getCause().getMessage().toLowerCase();
            }
            // 응답 초기화 추가 (헤더나 버퍼 충돌 방지)
            try {
                resp.reset(); 
            } catch (Exception ignore) {}
            if (errorMessage.contains("exceeds") || errorMessage.contains("size limit")) {
                JSFunction.alertBack(resp, "첨부 파일 또는 요청의 용량이 초과되었습니다 (최대 10MB)."); 
                // 참고: maxRequestSize (10MB)가 초과되었으므로 메시지 수정
            } else {
                // 기타 다른 예외인 경우
                JSFunction.alertBack(resp, "글쓰기 중 오류가 발생했습니다.");
            }
            return;
        }
    }
}