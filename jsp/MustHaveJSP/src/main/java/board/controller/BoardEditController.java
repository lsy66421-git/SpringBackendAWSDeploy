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

@WebServlet("/boardedit.do")
public class BoardEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();

	// 수정 페이지 진입 (수정 폼 보여주기)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String num = req.getParameter("num");
		BoardDTO dto = service.getBoard(num);

		// 작성자 본인 확인
		HttpSession session = req.getSession();
		Object sessionUserId = session.getAttribute("UserId");
		String sessionId = (sessionUserId == null) ? "" : sessionUserId.toString();
		
		// 작성자가 아니면 경고창 띄우고 뒤로 보내기
		if (!sessionId.equals(dto.getId())) {
			// JSFunction 대신 아래에 만든 alertBack 메서드 사용
			alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
			return;
		}

		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/12_1/EditResult.jsp").forward(req, resp);
	}

	// 수정 처리 (DB 업데이트)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 한글 깨짐 방지 (필수)
		req.setCharacterEncoding("UTF-8"); 
		
		String num = req.getParameter("num");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		BoardDTO dto = new BoardDTO();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);

		int affected = service.updateEdit(dto);

		if (affected == 1) {
			resp.sendRedirect("boardview.do?num=" + num);
		} else {
			alertBack(resp, "수정하기에 실패하였습니다.");
		}
	}
	
	// [추가됨] 서블릿용 경고창 띄우기 메서드
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
}