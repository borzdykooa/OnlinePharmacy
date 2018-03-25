<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/savePersonalData" method="post">
    Фамилия, имя, отчество: <input type="text" name="fullName" value="${requestScope.fullName}"><br>
    Дата рождения: <input type="text" name="dateOfBirth" value="${requestScope.dateOfBirth}"><br>
    Номер телефона: <input type="text" name="telephoneNumber" value="${requestScope.telephoneNumber}"><br>
    Адрес: <input type="text" name="address" value="${requestScope.address}"><br>
    Клиент: <input type="text" name="user" value="${sessionScope.user.id}"><br>

    <%--Группа лекарств:<br>--%>
    <%--<select name="group">--%>
        <%--<c:forEach var="group" items="${requestScope.groups}">--%>
            <%--<option value="${group.id}">${group.name}</option>--%>
        <%--</c:forEach>--%>
    <%--</select><br>--%>

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
