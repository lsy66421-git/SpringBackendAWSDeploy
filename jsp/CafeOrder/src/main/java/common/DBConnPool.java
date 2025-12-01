package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con; // 데이터베이스 연결을 위한 클래스
	 // SQL 작성시 사용하는 클래스
	public Statement stmt; // 한번만 SQL 실행 가능
	public PreparedStatement psmt; // 여러번 SQL 실행 가능
	public ResultSet rs; // select문의 결과를 담는 클래스
	
	// 기본 생성자
	public DBConnPool() {
	    try {
	        // 커넥션 풀(DataSource) 열기
	        Context initCtx = new InitialContext(); // 초기 Context 객체 생성
	        Context ctx = (Context)initCtx.lookup("java:comp/env"); // 환경 설정 Context 찾기
	        DataSource source = (DataSource)ctx.lookup("dbcp_myoracle"); // 데이터 소스(연결 풀) 찾기

	        // 커넥션 풀을 통해 연결 열기
	        con = source.getConnection(); // ④ 커넥션 풀에서 연결 객체 가져오기

	        System.out.println("DB 커넥션 풀 연결 성공");
	    }
	    catch (Exception e) {
	        System.out.println("DB 커넥션 풀 연결 실패");
	        e.printStackTrace();
	    }
	}

	// 연결 해제(자원 반납)
	public void close() {
	    try {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (psmt != null) psmt.close();
	        if (con != null) con.close(); // ⑤ 자동으로 커넥션 풀로 반납됨

	        System.out.println("DB 커넥션 풀 자원 반납");
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}


