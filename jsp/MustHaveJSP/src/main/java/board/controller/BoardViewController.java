package board.controller;

import java.io.IOException;

import board.service.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model1.BoardDTO;

@WebServlet("/boardview.do")
public class BoardViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
		// 파라미터로 가져온 게시글 PrimaryKey를 저장
		String num = req.getParameter("num");
		// 조회수 1 증가
		service.editVisitCount(num);
		BoardDTO dto = service.selectView(num);

		req.setAttribute("dto" , dto);
		req.getRequestDispatcher("/12_1/ViewResult.jsp").forward(req, resp);
	}
}
