package com.cafe.controller;

import java.io.IOException;

import com.cafe.dao.MVCBoardDAO;
import com.cafe.dto.MVCBoardDTO;
import com.cafe.util.FileUtil;
import com.cafe.util.JSFunction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/pass.do")
public class PassController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/views/notice/Pass.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if(confirmed) {
			if(mode.equals("edit")) {
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);
				resp.sendRedirect(req.getContextPath() + "/edit.do?idx=" + idx);
			}
			else if (mode.equals("delete")) {
				dao = new MVCBoardDAO();
				MVCBoardDTO dto = dao.selectView(idx);
				int result = dao.deletePost(idx);
				dao.close();
				if(result == 1) {
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(req, "/images", saveFileName);
				}
				JSFunction.alertLocation(resp, "삭제되었습니다.", req.getContextPath() + "/listNotice.do");
			}
			else {
				JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
			}
		}
	}
	
}
