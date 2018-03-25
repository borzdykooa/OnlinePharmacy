<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/saveMedicine" method="post">
    Название лекарства: <input type="text" name="name" value="${requestScope.name}"><br>
    Описание: <input type="text" name="description" value="${requestScope.description}"><br>
    Цена: <input type="text" name="price" placeholder="*.**" value="${requestScope.price}"><br>
    Количество: <input type="text" name="quantity" value="${requestScope.quantity}"><br>
    Группа лекарств:<br>
    <select name="group">
        <c:forEach var="group" items="${requestScope.groups}">
            <option value="${group.id}">${group.name}</option>
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
