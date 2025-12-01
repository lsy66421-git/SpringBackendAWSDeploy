<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 파라미터에 저장딘 inactiveToday를 변수에 저장
String chkVal = request.getParameter("inactiveToday");
// if(null이 아닌 경우 && chkVal가 1이면)
// chkVal가 null인 경우 equals를 실행하면 에러가 발생하기 때문에
// 반드시 null인지 아닌지 학인 후 실행해야 한다.
if(chkVal != null && chkVal.equals("1")){
	/* Cookie cookie = new Cookie("PopupClose", "off");
	cookie.setPath(request.getContextPath());
	cookie.setMaxAge(60*60*24);
	response.addCookie(cookie); */
	CookieManager.makeCookie(response, "PopupClose", "off", 60*60*24);
	out.println("쿠키 : 하루 동안 열지 않음");
}
%>