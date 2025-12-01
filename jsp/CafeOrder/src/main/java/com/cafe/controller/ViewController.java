package com.cafe.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.cafe.dao.MVCBoardDAO;
import com.cafe.dto.MVCBoardDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view.do")
public class ViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 게시물 불러오기
        MVCBoardDAO dao = new MVCBoardDAO();
        String idx = req.getParameter("idx"); // 일련번호 받기
        
        dao.updateVisitCount(idx); // 조회수 1 증가
        MVCBoardDTO dto = dao.selectView(idx); // 게시물 DTO 가져오기
        dao.close(); // DAO 자원 반납 (DB 연결 닫기)
        
        // 2. 줄바꿈 처리
        dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
        
        // null이 아닐 때만 치환
        if (dto.getContent() != null) {
            dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
        } else {
            dto.setContent(""); // 내용이 없으면 빈 문자열로 설정
        }
        
        // 3. 첨부 파일 확장자 추출 및 이미지 타입 확인
        String ext = null; 
        String fileName = dto.getSfile(); // 서버 저장 파일명
        if(fileName != null) { 
            ext = fileName.substring(fileName.lastIndexOf(".")+1);
        }
        
        String[] mimeStr = {"png","jpg","gif"};
        List<String> mimeList = Arrays.asList(mimeStr);
        boolean isImage = false;
        if(mimeList.contains(ext)) {
            isImage = true;
        }
        
        // 4. 게시물(DTO) 저장 후 뷰로 포워드
        req.setAttribute("dto", dto);
        req.setAttribute("isImage", isImage);
        req.getRequestDispatcher("/views/notice/View.jsp").forward(req, resp);
	}
}
