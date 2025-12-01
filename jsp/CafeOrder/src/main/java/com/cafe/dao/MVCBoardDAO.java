package com.cafe.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.cafe.dto.MVCBoardDTO;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	public MVCBoardDAO() {
		super();
	}
	// 검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount (Map<String, Object> map) {
		int totalCount = 0;
		// 쿼리문 준비
		String query = "SELECT COUNT(*) FROM cafe_mvcboard ";
		// 검색 조건이 있다면 WHERE절로 추가
		if (map.get("searchWord") != null) {
			query += " WHERE " +map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement(); // 쿼리문 생성
			rs = stmt.executeQuery(query); // 쿼리문 실행
			rs.next();
			totalCount = rs.getInt(1); // 검색된 게시물 개수 저장
		}
		catch (Exception e) {
		System.out.println("게시물 카운트 중 예외 발생");
		e.printStackTrace();
		}
		return totalCount; // 게시물 개수를 서블릿으로 반환
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
	public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();	// 리문 준비
		String query= " "
						+ "SELECT * FROM ( "
						+ "     SELECT Tb.*, ROWNUM rNum FROM ( "
						+ "          SELECT * FROM cafe_mvcboard ";

		if (map.get("searchWord") != null) {
			query += "WHERE " + map.get("searchField")
						+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "          ORDER BY idx DESC "
				+ "      ) Tb "
				+ " ) "
				+ " WHERE rNUM BETWEEN ? AND ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 에러 발생");
			e.printStackTrace();
		}
		return board;
	}
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO cafe_mvcboard ( "
							+ " idx, name, title, content, ofile, sfile, pass) "
							+ " VALUES ( "
							+ " seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {

			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환합니다.
    public MVCBoardDTO selectView(String idx) {
        MVCBoardDTO dto = new MVCBoardDTO();
        
        // 쿼리문 템플릿 준비
        String query = "SELECT * FROM cafe_mvcboard WHERE idx=?"; 
        
        try {
            psmt = con.prepareStatement(query); // 쿼리문 준비
            psmt.setString(1, idx);            // 인파라미터 설정
            rs = psmt.executeQuery();          // 쿼리문 실행

            if (rs.next()) { // 결과와 DTO 객체에 저장
                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));
            }
        }
        catch (Exception e) {
            System.out.println("게시물 상세 보기 중 예외 발생");
            e.printStackTrace();
        }
        return dto; // 결과 반환
    }
    
    // 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
    public void updateVisitCount(String idx) {
        String query = "UPDATE cafe_mvcboard SET "
                     + " visitcount=visitcount+1 "
                     + " WHERE idx=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeUpdate(); // *표준 DML 실행 메서드 executeUpdate() 사용을 권장합니다.*
        }
        catch (Exception e) {
            System.out.println("게시물 조회수 증가 중 에러 발생");
            e.printStackTrace();
        }
    }
    
    public void downCountPlus(String idx) {
    	String sql = "UPDATE cafe_mvcboard SET "
    			+ " downcount=downcount+1 "
    			+ " WHERE idx=? ";
    	try {
    		psmt = con.prepareStatement(sql);
    		psmt.setString(1,  idx);
    		psmt.executeUpdate();
    	}
    	catch (Exception e) {}
    }
    // 입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인합니다.
    public boolean confirmPassword(String pass, String idx) {
        boolean isCorr = true;
        try {
            // 비밀번호와 일련번호가 일치하는 게시물의 개수를 세는 쿼리
            String sql = "SELECT COUNT(*) FROM cafe_mvcboard WHERE pass=? AND idx=?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, pass);
            psmt.setString(2, idx);
            rs = psmt.executeQuery();
            rs.next();
            
            // 일치하는 게시물이 없으면(count가 0이면) false
            if (rs.getInt(1) == 0) {
                isCorr = false;
            }
        }
        catch (Exception e) {
            isCorr = false; // 예외 발생 시에도 false 처리
            e.printStackTrace();
        }
        return isCorr; // 검증 결과 반환
    }

    // 지정한 일련번호의 게시물을 삭제합니다.
    public int deletePost(String idx) {
        int result = 0;
        try {
            String query = "DELETE FROM cafe_mvcboard WHERE idx=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            result = psmt.executeUpdate(); // 삭제 실행
        }
        catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
        }
        return result; // 삭제 결과(성공 시 1, 실패 시 0) 반환
    }
    
    public int updatePost(MVCBoardDTO dto) {
    	int result = 0;
    	try {
    		String query = "UPDATE cafe_mvcboard"
    				+ " SET title=?, name=?, content=?, ofile=?, sfile=? "
    				+ " WHERE idx=? and pass=?";
    		psmt = con.prepareStatement(query);
    		psmt.setString(1, dto.getTitle());
    		psmt.setString(2, dto.getName());
    		psmt.setString(3, dto.getContent());
    		psmt.setString(4, dto.getOfile());
    		psmt.setString(5, dto.getSfile());
    		psmt.setString(6, dto.getIdx());
    		psmt.setString(7, dto.getPass());
    		
    		result = psmt.executeUpdate();
    		System.out.println("updatePost result = " + result);
    	}
    	catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
    	return result;
    }

}
		
	
