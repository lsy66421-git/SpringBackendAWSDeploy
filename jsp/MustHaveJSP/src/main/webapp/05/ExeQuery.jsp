<%@page import="membership.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="common.DBConnPool"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
	// DB 연결
	DBConnPool jdbc = new DBConnPool();
	
	// SQL 작성
	String sql = "SELECT ID, PASS, NAME, REGIDATE FROM MEMBER";
	// con을 사용해서 Statement를 생성
	 jdbc.psmt = jdbc.con.prepareStatement(sql);
	// executeQuery(sql) : sql실행 후 결과를 받는 메서드
	jdbc.rs = jdbc.psmt.executeQuery();
	List<MemberDTO> memberList = new ArrayList<>();
	// ResultSet : SELECT문의 결과를 저장하는 클래스
	// Set자료구조라서 nsxt()메서드를 사용해서 데이터를 꺼내야 함.
	// ResultSet rs = stmt.executeQuery(sql);
	while(jdbc.rs.next()){
		String id = jdbc.rs.getString(1);
		String pw = jdbc.rs.getString(2);
		String name = jdbc.rs.getString("NAME");
		String regidate = jdbc.rs.getDate("REGIDATE").toLocaleString();			
		memberList.add(new MemberDTO(id,pw,name,regidate));
	}
	request.setAttribute("memberList", memberList);
	request.getRequestDispatcher("memberlist.jsp")
		.forward(request, response);
	jdbc.close();
	%>
