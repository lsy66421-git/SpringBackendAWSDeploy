package board.service;

import java.util.List;
import model1.BoardDTO;
import model1.BoardDAO;
import java.util.Map;

public class BoardService {
	// 출력하는 리스트 전체갯수 출력
	// 서비스의 경우 데이터를 변경하지 않는다면 작성할 내용이 거의 없음
	// 중간 연결 클래스로 생각하고 반드시 작성해야 함
	public int getListCount(Map<String, Object> param) {
		BoardDAO dao = new BoardDAO();
		int totalCount = dao.selectCount(param);
		dao.close(); 
		return totalCount;
	}
	public List<BoardDTO> getList(Map<String, Object> param){
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> dtoLists = dao.selectList(param);
		dao.close(); 
		return dtoLists;
	}
	public List<BoardDTO> getListPage(Map<String, Object> param){
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> dtoLists = dao.selectListPage(param);
		dao.close(); 
		return dtoLists;
	}
	// 조회수 1 증가 서비스
	public void editVisitCount(String num) {
		BoardDAO dao = new BoardDAO();
		dao.updateVisitCount(num);
		dao.close(); 
	}
	// 1건 저회 서비스
	public BoardDTO getBoard(String num) {
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.selectView(num);
		dao.close();
		return dto;
	}
	public BoardDTO selectView(String num) {
		BoardDAO dao = new BoardDAO();
		dao.updateVisitCount(num);
		BoardDTO dto = dao.selectView(num);
		dao.close();
		return dto;
	}
	public int updateEdit(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result = dao.updateEdit(dto);
		dao.close();
		return result;
	}
	public int insertWrite(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		return result;
	}
	public int deletePost(String num) {
        BoardDAO dao = new BoardDAO();
        BoardDTO dto = new BoardDTO();
        dto.setNum(num);
        int result = dao.deletePost(dto); 
        dao.close();
        return result;
    }
}
