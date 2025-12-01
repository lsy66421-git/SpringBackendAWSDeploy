package com.cafe.controller;

import java.io.IOException;

import com.cafe.dao.MVCBoardDAO;
import com.cafe.dto.MVCBoardDTO;
import com.cafe.util.FileUtil;
import com.cafe.util.JSFunction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 1,
		maxRequestSize = 1024 * 1024 * 10
		)
public class EditController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/views/notice/Edit.jsp").forward(req, resp);
	}
	
	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		// 1. 저장할 경로 설정 (수정됨)
	    // 주의: ${pageContext...}는 웹 URL이지 실제 윈도우 파일 경로가 아닙니다.
	    // 질문하신 프로젝트의 특정 위치를 지정하려면 아래처럼 절대 경로를 적어야 합니다.
	    
	    String saveDirectory = "C:/cafe_notice_upload";

	    /* 참고: 만약 서버(Tomcat)의 배포 폴더(webapp/upload)에 저장하려면 아래 코드를 사용하세요.
	       String saveDirectory = req.getServletContext().getRealPath("/upload");
	    */

	    // 2. 폴더 자동 생성 로직 (수정됨)
	    java.io.File dir = new java.io.File(saveDirectory);
	    
	    // 폴더가 없으면 생성
	    if (!dir.exists()) {
	        boolean created = dir.mkdirs(); // mkdirs()는 상위 폴더까지 한 번에 생성
	        if (created) {
	            System.out.println("[알림] upload 폴더가 없어 새로 생성했습니다: " + saveDirectory);
	        } else {
	            System.out.println("[에러] 폴더 생성 실패 (경로 또는 권한을 확인하세요).");
	        }
	    }

	    // 3. 파일 업로드 처리
	    String originalFileName = "";
	    try {
	        // 설정한 실제 물리 경로(saveDirectory)를 전달
	        originalFileName = FileUtil.uploadFile(req, saveDirectory);
	    }
	    catch (Exception e) {
	        System.out.println("[에러] 파일 업로드 중 예외 발생: " + e.getMessage());
	        e.printStackTrace();
	        JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", req.getContextPath() + "/edit.do");
	        return;
	    }

	    // 2. 파일 업로드 외 처리 =============================
	    // 수정 내용을 매개변수에서 얻어옴 (이미지 번호는 문맥상 3번으로 보임)
	    String idx = req.getParameter("idx");
	    String prevOfile = req.getParameter("prevOfile");
	    String prevSfile = req.getParameter("prevSfile");

	    String name = req.getParameter("name");
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    
	    // 비밀번호는 session에서 가져옴
	    HttpSession session = req.getSession();
	    String pass = (String)session.getAttribute("pass");

	    // DTO에 저장
	    MVCBoardDTO dto = new MVCBoardDTO();
	    dto.setIdx(idx);
	    dto.setName(name);
	    dto.setTitle(title);
	    dto.setContent(content);
	    dto.setPass(pass);

	    // 원본 파일명과 저장된 파일 이름 설정
	    if (originalFileName != "") {
	        // 첨부 파일이 있을 경우 파일명 변경
	        String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);

	        dto.setOfile(originalFileName);  // 원래 파일 이름
	        dto.setSfile(savedFileName);     // 서버에 저장된 파일 이름

	        // 기존 파일 삭제
	        FileUtil.deleteFile(req, "/images", prevSfile);
	    }
	    else {
	        // 첨부 파일이 없으면 기존 이름 유지
	        dto.setOfile(prevOfile);
	        dto.setSfile(prevSfile);
	    }

	    // DB에 수정 내용 반영
	    MVCBoardDAO dao = new MVCBoardDAO();
	    int result = dao.updatePost(dto);
	    dao.close();

	    // 성공 or 실패?
	    System.out.println("result = " + result);
	    if (result == 1) {  // 수정 성공
	        session.removeAttribute("pass"); // 비밀번호 세션 삭제 (보안)
	        resp.sendRedirect("/view.do?idx=" + idx);
	    }
	    else {  // 수정 실패
	        JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요.",
	                "/view.do?idx=" + idx);
	    }
	}
	
}
