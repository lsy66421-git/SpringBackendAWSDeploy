package model1;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class BoardDAO extends JDBConnect {

    private Date sqlDate;

	// 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) { 
        // ... (selectCount 메서드 내용은 변경 없음)
        int totalCount = 0;

        String query = "SELECT COUNT(*) FROM board";

        try {
            if (map.get("searchWord") != null && !map.get("searchWord").equals("")) {
                query += " WHERE " + map.get("searchField") + " LIKE ?";
            }       
            psmt = con.prepareStatement(query);
            if(query.contains("WHERE")) {
	            psmt.setString(1, "%"+map.get("searchWord").toString()+"%");
            }
            rs = psmt.executeQuery();
            rs.next();
            totalCount = rs.getInt(1);        
        } catch (Exception e) {

        	System.out.println("게시물 수를 구하는 중 예외 발생");
            e.printStackTrace();
        }
        return totalCount;
    }

	public List<BoardDTO> selectList(Map<String, Object> map){
        // ... (selectList 메서드 내용은 변경 없음)
		List<BoardDTO> bbs = new Vector<>();
		try {
			String query = "SELECT num, title,content,id,postdate,visitcount FROM board";
			if (map.get("searchWord") != null && !map.get("searchWord").equals("")) {
				query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
			}
			query += " ORDER BY num DESC ";
			psmt =con.prepareStatement(query);
//			if(query.contains("WHERE")) {
//				 psmt.setString(1, map.get("searchField").toString());
//		         psmt.setString(2, map.get("searchWord").toString());
//			}
			rs = psmt.executeQuery();
			
			while (rs.next()) {
			    BoardDTO dto = new BoardDTO();
			    
		        dto.setNum(rs.getString("num"));              // 일련번호
		        dto.setTitle(rs.getString("title"));               // 제목
		        dto.setContent(rs.getString("content"));      // 내용
		        dto.setPostdate(rs.getString("postdate"));     // 작성일
		        dto.setId(rs.getString("id"));                    // 작성자 아이디
		        dto.setVisitcount(rs.getString("visitcount")); // 조회수

		        bbs.add(dto);
			}
		}
		catch (Exception e) {

			System.out.println("게시물 조회 중 예외 발생");
		    e.printStackTrace();
		}

		return bbs;
	}
	
	public int insertWrite(BoardDTO dto) {
        // ... (insertWrite 메서드 내용은 변경 없음)
		int result = 0;
		
		try {
			String query = "INSERT INTO board ( "
							+ " num,title,content,id,visitcount) "
							+ " VALUES ( "
							+ " seq_board_num.NEXTVAL, ?, ?, ?, 0)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {

			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public BoardDTO selectView(String num) {
        // ... (selectView 메서드 내용은 변경 없음)
	    BoardDTO dto = new BoardDTO();

	    String query = "SELECT B.*, M.name "
	                + "FROM member M INNER JOIN board B "
	                + "ON M.id=B.id "
	                + "WHERE num=?";

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, num);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	            dto.setNum(rs.getString(1));
	            dto.setTitle(rs.getString(2));
	            dto.setContent(rs.getString("content"));
	            dto.setPostdate(rs.getString("postdate"));
	            dto.setId(rs.getString("id"));
	            dto.setVisitcount(rs.getString(6));
	            dto.setName(rs.getString("name"));
	        }

    	}
        catch (Exception e) {

        	System.out.println("게시물 상세보기 중 예외 발생");
            e.printStackTrace();
        }
	        return dto;
   }
	
	public void updateVisitCount(String num) {
        // ... (updateVisitCount 메서드 내용은 변경 없음)
		String query = "UPDATE board SET " + " visitcount=visitcount+1 " + " WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1,  num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("DEBUG: 예외 발생 지점 확인");
		    e.printStackTrace(); // 콘솔에 상세한 오류 추적 정보 출력
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public int updateEdit(BoardDTO dto) {
        // ... (updateEdit 메서드 내용은 변경 없음)
	    int result = 0;

	    try {
	        String query = "UPDATE board SET "
	                    + " title=?, content=? "
	                    + " WHERE num=?";

	        psmt = con.prepareStatement(query);
	        psmt.setString(1, dto.getTitle());
	        psmt.setString(2, dto.getContent());
	        psmt.setString(3, dto.getNum());
	        result = psmt.executeUpdate();
	    }
	    catch (Exception e) {

	    	System.out.println("게시물 수정 중 예외 발생");
	        e.printStackTrace();
	    }
	    
	    return result;
	}
	public int deletePost(BoardDTO dto) {
        // ... (deletePost 메서드 내용은 변경 없음)
		int result = 0;
		try {
			String query = "DELETE FROM board WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {

			System.out.println("게시물 삭제 중 예외 발생");
		    e.printStackTrace();
		}
			return result;
	}
	
	// ★★★ 중복된 첫 번째 selectListPage 메서드를 제거하고, 안정적인 버전만 유지합니다. ★★★

	public List<BoardDTO> selectListPage(Map<String, Object> map) {
	    List<BoardDTO> boardLists = new Vector<BoardDTO>(); 
	    
	    // 1. SQL 쿼리 구성 (검색어는 ?로 처리)
	    String query = " SELECT * FROM ( "
                + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                + "        SELECT num, title, content, id, postdate, visitcount FROM board "; // ★ 칼럼 명시
	
	   boolean whereClauseAdded = false;
	
	   // 검색 조건이 있을 경우 WHERE 절 추가
	   if (map.get("searchWord") != null && !map.get("searchWord").equals("")) {
	       query += " WHERE " + map.get("searchField") + " LIKE ? "; 
	       whereClauseAdded = true;
	   }
	   
	   query += " ORDER BY num DESC "
	          + "    ) Tb "
	          + " ) "
	          + " WHERE rNum BETWEEN ? AND ?"; // start와 end를 위한 ?
	    
	   System.out.println("DEBUG: Executing SQL: " + query);
	   
	    try {
	        psmt = con.prepareStatement(query);
	        int idx = 1; // 인파라미터 인덱스 시작
	        
	        // 2. 인파라미터 설정: 검색어 처리 (인덱스 1)
	        if (map.get("searchWord") != null && !map.get("searchWord").equals("")) {
	            // 검색어에 '% %'를 붙여 1번 인덱스로 설정하고, 인덱스를 2로 증가
	            psmt.setString(idx++, "%" + map.get("searchWord").toString() + "%"); 
	        }
	        
	        // 3. 인파라미터 설정: 페이징 범위 처리 (인덱스 2 또는 3)
	        psmt.setString(idx++, map.get("start").toString());
	        psmt.setString(idx, map.get("end").toString());

	        // 쿼리 실행 및 결과 처리...
	        rs = psmt.executeQuery();
	        
	        while (rs.next()) {
	            BoardDTO dto = new BoardDTO();
	            dto.setNum(rs.getString("num"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            
	            // postdate 필드 설정 안정화: rs.getDate()의 결과를 바로 DTO에 설정합니다.
	            dto.setPostdate(rs.getString("postdate")); 
	            
	            dto.setId(rs.getString("id"));
	            dto.setVisitcount(rs.getString("visitcount"));
	            
	            boardLists.add(dto);
	        }
	    } catch (Exception e) {
	    	System.out.println("DEBUG: 예외 발생 지점 확인");
	        e.printStackTrace(); // 콘솔에 상세한 오류 추적 정보 출력
	    	System.out.println("게시물 목록 조회 중 예외 발생");
	        e.printStackTrace();
	    }
	    return boardLists;
	}
	
}