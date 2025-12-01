package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	public Connection con; // 데이터베이스 연결을 위한 클래스
	 // SQL 작성시 사용하는 클래스
	public Statement stmt; // 한번만 SQL 실행 가능
	public PreparedStatement psmt; // 여러번 SQL 실행 가능
	public ResultSet rs; // select문의 결과를 담는 클래스
	
	// connection 클래스를 생성하는 생성자
	public JDBConnect() {
		try {
			// jdbc로 사용할 클래스 설정
			Class.forName("oracle.jdbc.OracleDriver");
			// DB 주소
			String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
			String id = "MUSTHAVE";
			String pwd = "1234";
			// 실제로 DB와 연결되어 Connection클래스에 저장
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	// web.xml에 저장한 DB정보로 연결하는 생성자
	public JDBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("OracleDriver");
			// 내장객체 application에서 DB접속 데이터를 받아와 저장
			Class.forName(driver);
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자2)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
