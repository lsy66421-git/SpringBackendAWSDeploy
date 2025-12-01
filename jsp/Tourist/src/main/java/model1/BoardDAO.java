package model1;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class BoardDAO extends JDBConnect {

    // 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) { 
        int totalCount = 0;

        String query = "SELECT COUNT(*) FROM tourist_board";

        try {
//        	if (map.get("searchWord") != null) {
//                query += " WHERE " + map.get("searchField") + " "
//                       + " LIKE '%" + map.get("searchWord") + "%'";
//        	
//            stmt = con.createStatement(); // 쿼리문 생성
//            rs = stmt.executeQuery(query); // 쿼리 실행  
//            rs.next(); // 커서를 첫 번째 행으로 이동
//            totalCount = rs.getInt(1); // 첫 번째 컬럼 값을 가져옴
            
            // PrepareStatement 사용방식
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
		List<BoardDTO> bbs = new Vector<>();
		try {
			String query = "SELECT num, title,content,id,postdate,visitcount FROM tourist_board";
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
		        dto.setPostdate(rs.getDate("postdate"));     // 작성일
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
		int result = 0;
		
		try {
			String query = "INSERT INTO tourist_board ( "
							+ " num,title,content,id,postdate,visitcount) "
							+ " VALUES ( "
							+ " seq_board_num.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
			
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
	    BoardDTO dto = new BoardDTO();

	    String query = "SELECT B.*, M.name "
	                + "FROM tourist_member M INNER JOIN tourist_board B "
	                + "ON M.email_id=B.id "
	                + "WHERE num=?";

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, num);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	            dto.setNum(rs.getString("num"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setPostdate(rs.getDate("postdate"));
	            dto.setId(rs.getString("id"));
	            dto.setVisitcount(rs.getString("visitcount"));
//	            dto.setName(rs.getString("name"));
	        }

    	}
        catch (Exception e) {
            System.out.println("게시물 상세보기 중 예외 발생");
            e.printStackTrace();
        }
	        return dto;
   }
	
	public void updateVisitCount(String num) {
		String query = "UPDATE tourist_board SET " + " visitcount=visitcount+1 " + " WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1,  num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public int updateEdit(BoardDTO dto) {
	    int result = 0;

	    try {
	        String query = "UPDATE tourist_board SET "
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
		int result = 0;
		try {
			String query = "DELETE FROM tourist_board WHERE num=?";
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
	public List<BoardDTO> selectListPage(Map<String,Object>map){
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		String query = " SELECT * FROM ( "
		            + " SELECT Tb.*, ROWNUM rNum FROM ( "
		            + " SELECT * FROM tourist_board ";
		if (map.get("searchWord") != null) {
		    query += " WHERE " + map.get("searchField")
		          + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY num DESC "
		      + " ) Tb "
		      + " ) "
		      + " WHERE rNum BETWEEN ? AND ? ";
		try {
		    psmt = con.prepareStatement(query);
		    psmt.setString(1, map.get("start").toString());
		    psmt.setString(2, map.get("end").toString());
		    rs = psmt.executeQuery();

		    while (rs.next()) {
		    	// 한 행(게시물 하나)의 데이터를 DTO에 저장
		    	BoardDTO dto = new BoardDTO();
		    	dto.setNum(rs.getString("num"));
		    	dto.setTitle(rs.getString("title"));
		    	dto.setContent(rs.getString("content"));
		    	dto.setPostdate(rs.getDate("postdate"));
		    	dto.setId(rs.getString("id"));
		    	dto.setVisitcount(rs.getString("visitcount"));
		    	bbs.add(dto);
		    	}
		    }
	    	catch (Exception e) {
	    	    System.out.println("게시물 조회 중 예외 발생");
	    	    e.printStackTrace();
	    	}
	    	return bbs;
	}
	
}

