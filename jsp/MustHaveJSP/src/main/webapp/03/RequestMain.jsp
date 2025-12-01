<%@page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setAttribute("requestString", "request 영역의 문자열"); // 데이터 저장 메서드
    request.setAttribute("requestPerson", new Person("안중근",31));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 영역</title>
</head>
<body>
	<h2>request 영역의 속성값 삭제하기</h2>
	<%
		request.removeAttribute("requestString"); // 데이터 삭제 메서드
		request.removeAttribute("requestInteger");
	%>
	<h2>request 영역의 속성값 읽기</h2>
	<%
	Person rPerson = (Person)(request.getAttribute("requestPerson")); // 객체 데이터 캐스팅 필요
	%>
	<ul>
		<li>String 객체 : <%= request.getAttribute("requestString") %></li>
		<li>Person 객체 : <%= rPerson.getName() %>, <%= rPerson.getAge() %></li>
	</ul>
	<h2>포워드된 페이지에서 request 영역 속성값 읽기</h2>
	<%
	
	/* request 객체에 데이터 저장: 위에 생성된 Person 데이터(예시일 수 있음)는 request 객체에 저장되어 있습니다.

	데이터 공유: forward를 사용할 때, request에 저장된 데이터는 다음 페이지에서도 사용 가능합니다. (동일한 request 객체가 전달되기 때문입니다.)

	주소(URL) 유지: forward 실행 시, 화면에 표시되는 주소는 유지됩니다. (클라이언트에게는 새로운 요청으로 보이지 않습니다.)

	내용 출력: 실제 내용은 RequestForward 파일(예시 파일명)이 출력됩니다. (서버 내부적으로 페이지 이동이 발생합니다.) */
	
	request
		.getRequestDispatcher("RequestForward.jsp?paramHan=한글&paramEng=English")
		.forward(request, response);
	 %>
</body>
</html>