package board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model1.BoardDTO;
import utils.BoardPage;

@WebServlet("/boardlistpaging.do")
public class BoardListPagingController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		// 1. 검색 조건 및 파라미터 초기화
		Map<String, Object> param = new HashMap<String, Object>();

		// searchField와 searchWord를 null 대신 빈 문자열로 초기화하여 안정성 향상
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");

		if (searchField == null) searchField = "";
		if (searchWord == null) searchWord = "";

		// 2. 검색 조건이 있으면 MAP에 추가
		if (!searchWord.equals("")) {
		    param.put("searchField", searchField);
		    param.put("searchWord", searchWord);
		}

		// 3. 페이지 처리 변수 계산 (web.xml 설정 사용)
		ServletContext application = this.getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POST_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("POST_PER_BLOCK"));

		// 현재 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals("")){
			pageNum = Integer.parseInt(pageTemp);
		}

		// 4. 게시물 목록 범위 및 개수 계산
		int totalCount = service.getListCount(param);
		int totalPage = (int)Math.ceil((double)totalCount/pageSize); // 전체 페이지 수

		int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호 [cite: 102]
		int end = pageNum * pageSize; // 마지막 게시물 번호 [cite: 102]

		param.put("start", start);
		param.put("end", end);

		// 5. 게시물 목록 받기
		List<BoardDTO> boardLists = service.getListPage(param);

		//★★★ 6. 페이징 HTML 문자열 생성 (컨트롤러에서 처리) ★★★
		//페이징 링크가 뷰(ListResult.jsp)가 아닌 컨트롤러(List.jsp)를 가리키도록
		//request.getRequestURI() (결과: "/프로젝트명/List.jsp")를 사용합니다.
		String pagingStr = BoardPage.pagingStr(
		                     totalCount, 
		                     pageSize, 
		                     blockPage, 
		                     pageNum, 
		                     req.getRequestURI(), // "List.jsp"
		                     searchField, 
		                     searchWord
		                 );

		//7. View (ListResult.jsp)로 전달할 데이터 설정
		req.setAttribute("totalCount" , Integer.valueOf(totalCount));
		req.setAttribute("boardLists" , boardLists);
		req.setAttribute("pagingStr", pagingStr); // ★★★ 생성된 페이징 HTML 저장 ★★★
		req.setAttribute("pageSize", Integer.valueOf(pageSize));
		req.setAttribute("blockPage", Integer.valueOf(blockPage));
		req.setAttribute("pageNum", Integer.valueOf(pageNum));
		req.setAttribute("totalPage", Integer.valueOf(totalPage));
		req.setAttribute("searchField", searchField);
		req.setAttribute("searchWord", searchWord);

		req.getRequestDispatcher("/12_2/ListResult_paging.jsp").forward(req, resp);
	}
}
