<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/savePrescription" method="post">
    Уникальный номер рецепта: <input type="text" name="name" value="${requestScope.name}"><br>
    Название лекарства:<br>
    <select name="medicine">
        <c:forEach var="medicine" items="${requestScope.medicines}">
            <option value="${medicine.id}">${medicine.name}</option>
        </c:forEach>
    </select><br>

    ФИО клиента:<br>
    <select name="user">
        <c:forEach var="user" items="${requestScope.users}">
            <option value="${user.id}">${user.fullName} ${user.user.login} </option>
        </c:forEach>
    </select><br>

    <input type="submit" value="Сохранить">
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
