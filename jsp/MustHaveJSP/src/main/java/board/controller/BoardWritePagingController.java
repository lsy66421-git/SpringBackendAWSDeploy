package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import board.service.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model1.BoardDTO;

@WebServlet("/boardwritepaging.do")
public class BoardWritePagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();

	// 글쓰기 폼 진입 (Write.jsp 보여주기)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = req.getSession();
		if (session.getAttribute("UserId") == null) {
			alertLocation(resp, "로그인 후 이용해주세요.", "login.do");
			return;
		}
		
		// 글쓰기 페이지로 포워드
		req.getRequestDispatcher("/12_2/Write.jsp").forward(req, resp);
	}

	// 글쓰기 처리 (DB 저장)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 한글 처리
		req.setCharacterEncoding("UTF-8");
		
		// 파라미터 받기
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		// 세션에서 아이디 확인
		HttpSession session = req.getSession();
		Object userIdObj = session.getAttribute("UserId");
		
		if (userIdObj == null) {
			alertLocation(resp, "로그인 세션이 만료되었습니다.", "/12_2/LoginForm.jsp");
			return;
		}
		
		// DTO 객체 생성 및 값 설정
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setId(userIdObj.toString());
		
		// 서비스 호출 (DB 저장)
		int result = service.insertWrite(dto);
		
		// 성공/실패 처리
		if (result == 1) {
			// 글쓰기 성공 시 목록으로 이동 (boardlist.do가 있다면 그쪽으로, 없다면 List.jsp)
			resp.sendRedirect("boardlistpaging.do"); 
		} else {
			// 실패 시 경고창
			alertBack(resp, "글쓰기에 실패하였습니다.");
		}
	}
	
	// 경고창 띄우고 뒤로 이동
	private void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			String script = "<script>"
						  + "    alert('" + msg + "');"
						  + "    history.back();"
						  + "</script>";
			out.println(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 경고창 띄우고 특정 페이지로 이동
	private void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			String script = "<script>"
						  + "    alert('" + msg + "');"
						  + "    location.href='" + url + "';"
						  + "</script>";
			out.println(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}