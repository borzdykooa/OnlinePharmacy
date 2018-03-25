<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/deleteMedicine" method="post">
    <c:if test="${not empty requestScope.medicines}">
        <select name="id">
            <c:forEach var="medicine" items="${requestScope.medicines}">
                <option value="${medicine.id}">${medicine.name}</option>
            </c:forEach>
        </select>
    </c:if>
    <input type="submit" value="Удалить">
</form>
<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
