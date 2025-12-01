package membership;

import common.DBConnPool;

// DBConnPool 변수 상속 받아 사용
public class MemberDAO extends DBConnPool {

    /**
     * 회원 정보를 받아 데이터베이스에서 일치하는 회원을 찾아 DTO를 반환합니다.
     * @param param 검색에 사용할 회원 정보(Email_id, Password)가 담긴 DTO
     * @return 조회된 회원 정보가 담긴 DTO 객체 (조회 실패 시 필드가 null인 DTO)
     */
	public MemberDTO login(MemberDTO param) {
        // [수정] dto 변수를 메소드 내에서 초기화
        MemberDTO dto = new MemberDTO(); 
        
        // [수정] 쿼리문: TOURIST_MEMBER 테이블에서 이메일 ID와 패스워드가 일치하는 행을 조회
        String query = "SELECT * FROM TOURIST_MEMBER WHERE email_id=? AND password=?";
        
        try { 
            psmt = con.prepareStatement(query); // 동적 쿼리문 준비
            
            // DTO 객체에서 ID와 비밀번호를 가져와 쿼리문의 인파라미터에 설정
            psmt.setString(1, param.getEmail_id()); 
            psmt.setString(2, param.getPassword()); 
            
            rs = psmt.executeQuery(); // 쿼리문 실행
            
            // 결과 처리
            if (rs.next()) { 
                // 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
                // [참고] 여기서는 조회된 데이터를 새로운 DTO에 담아 반환하는 것이 일반적입니다.
                // 그러나 원본 코드의 로직을 유지하면서, 필드를 채웁니다.
                dto.setEmail_id(rs.getString("email_id"));
                dto.setPassword(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setTel(rs.getString("tel"));
                dto.setGender(rs.getString("gender"));
                dto.setAgree(rs.getString("agree"));
                dto.setContent(rs.getString("content"));
                dto.setRegidate(rs.getDate("regidate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto; // DTO 객체 반환 (조회 실패 시 필드가 비어있는 DTO 반환)
    }

    // 원본 코드에 있던 addMember 메소드
    public void addMember(String id, String pass, String name) {
    	// [수정] TOURIST_MEMBER 테이블의 컬럼명에 맞게 쿼리 수정 (id -> email_id, pass -> password)
        // [주의] 이 메서드는 회원가입에 필요한 모든 컬럼을 처리하도록 수정이 필요할 수 있습니다.
    	String sql = "INSERT INTO TOURIST_MEMBER(email_id, password, name, regidate)"
                + "VALUES (?, ?, ?, SYSDATE)";
    	try {
    		psmt = con.prepareStatement(sql);
    		psmt.setString(1, id); // id로 받았지만 email_id 컬럼에 저장
    		psmt.setString(2, pass); // pass로 받았지만 password 컬럼에 저장
    		psmt.setString(3, name);
    		int result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); // 예외 처리 추가
		}
    }
}