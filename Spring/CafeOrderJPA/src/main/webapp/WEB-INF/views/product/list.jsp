<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Product List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> 
<style>
    .menu-img {
        width: 100px; 
        height: 100px; 
        object-fit: cover; 
        border-radius: 10px;
        border: 1px solid #ddd;
    }
    .bbsListTbl th, .bbsListTbl td {
        vertical-align: middle;
    }
</style>
</head>
<body>
<div id="wrap">
    
    <jsp:include page="../common/header.jsp"/>
    
    <div id="container">
        <div class="bodytext_area box_inner">
            <h2 class="tit_page">메뉴 목록</h2>
            
            <table class="bbsListTbl" summary="번호, 이미지, 상품명, 가격, 설명">
                <colgroup>
                    <col width="10%">
                    <col width="20%">
                    <col width="20%">
                    <col width="15%">
                    <col width="*">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">이미지</th>
                        <th scope="col">상품명</th>
                        <th scope="col">가격</th>
                        <th scope="col">설명</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty productList}">
                            <tr>
                                <td colspan="5" align="center">등록된 메뉴가 없습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="product" items="${productList}">
                                <tr align="center">
                                    <td>${product.code}</td>
                                    <td>
                                        <img src="${pageContext.request.contextPath}/images/${product.picture}" class="menu-img" alt="${product.name}">
                                    </td>
                                    <td class="tit_notice"><b>${product.name}</b></td>
                                    <td>${product.price}원</td>
                                    <td align="left">${product.description}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
            
        </div>
    </div>
    
    <jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>