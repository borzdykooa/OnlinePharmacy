<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty sessionScope.currentUser}">
<%--<c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.login == 'admin'}">--%>
    <a href="${pageContext.request.contextPath}/logout">Logout</a><br>
</c:if>
<div>
    <h3>Список лекарств:</h3>
    <ol>
        <c:forEach items="${requestScope.medicines}" var="medicine">
            <a href="${pageContext.request.contextPath}/medicineFullInfo?id=${medicine.id}">
                <li>${medicine.name}</li>
            </a><br>
        </c:forEach>
    </ol>
</div>
</body>
</html>
