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

// JSP의 form action="/MustHaveJSP/boarddelete.do" 와 매핑됨
@WebServlet("/boarddeletepaging.do")
public class BoardDeletePagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// 게시글 번호 가져오기
		String num = req.getParameter("num");

		// 본인 확인 (보안 절차)
		// 현재 게시물을 조회해서 작성자를 확인합니다.
		BoardDTO dto = service.getBoard(num);
		
		HttpSession session = req.getSession();
		String sessionId = (String) session.getAttribute("UserId");
		
		// 로그인된 아이디와 작성자가 다르다면 삭제 불가
		if (sessionId == null || !sessionId.equals(dto.getId())) {
			alertBack(resp, "작성자 본인만 삭제할 수 있습니다.");
			return;
		}

		// 게시글 삭제 서비스 호출
		int result = service.deletePost(num);

		// 결과 처리
		if (result == 1) {
			// 삭제 성공 시 목록 페이지로 이동
			// (주의: alertBack은 뒤로가기이므로, 성공 시에는 목록으로 리다이렉트해야 함)
			// context path를 포함하여 이동하거나 상대 경로 사용
			resp.sendRedirect("/MustHaveJSP/boardlistpaging.do");
			/* resp.sendRedirect(req.getContextPath() + "/boardlistpaging.do"); */
		} else {
			// 삭제 실패 시 경고창
			alertBack(resp, "삭제에 실패하였습니다.");
		}
	}

	// 경고창 띄우고 뒤로 이동하는 헬퍼 메서드
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