<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Login</h2>

<c:if test="${not empty message}">
    <p class="error">${message}</p>
</c:if>

<form action="CafeServlet" method="post" style="max-width: 400px; margin: 0 auto;">
    <input type="hidden" name="command" value="login">
    <div class="form-group">
        <label for="userid">User ID:</label>
        <input type="text" id="userid" name="userid" required>
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" id="pwd" name="pwd" required>
    </div>
    <button type="submit" class="btn">Login</button>
</form>

<%@ include file="../common/footer.jsp" %>