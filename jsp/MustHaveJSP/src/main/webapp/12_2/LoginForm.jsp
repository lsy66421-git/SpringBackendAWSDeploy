<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Session</title></head>
<body>
<jsp:include page="../Common/Link_servlet.jsp"/>
    <h2>로그인 페이지</h2>
    <span style="color: red; font-size: 1.2em;">
    	${LoginErrMsg == null ? '' : LoginErrMsg}
     </span>
		<c:if test="${UserId == null}">
	        <script>
	            function validateForm(form) {
	                if (!form.user_id.value) {
	                    alert("아이디를 입력하세요.");
	                    return false;
	                }          
	                if (form.user_pw.value == "") {
	                    alert("패스워드를 입력하세요.");
	                    return false;
	                }
	            }
	        </script> 
			<!-- onsubmit="조건식" : 조건식이 true면 submit 실행, false면 실행하지 않음 -->
			<!-- validateForm(this) : 함수가 실행될 때 매개변수로 자기자신 태그를 사용함 -->
	        <form action="/MustHaveJSP/login.do"  method="post"  name="loginFrm"
	              onsubmit="return validateForm(this);">
	            아이디 : <input type="text"  name="user_id" /><br />
	            패스워드 : <input type="password"  name="user_pw" /><br /> 
	            <input type="submit" value="로그인하기" />
	        </form>
        </c:if>
	 	<c:if test="${UserId != null}">
        	${UserName} 회원님, 로그인하셨습니다.<br />
        	<a href="/MustHaveJSP/logout.do">[로그아웃]</a> 
        </c:if>
</body>
</html>