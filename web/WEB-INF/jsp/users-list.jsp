<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h3>Список клиентов:</h3>
    <ol>
        <c:forEach items="${requestScope.users}" var="user">
            <h3>
                <li>${user.user.login}</li>
            </h3>
            <p>ФИО клиента: ${user.fullName}</p>
            <p>Дата рождения: ${user.dateOfBirth}</p>
            <p>Номер телефона: ${user.telephoneNumber}</p>
            <p>Адрес: ${user.address}</p>
            <p>Пароль: ${user.user.password}</p>

        </c:forEach>
    </ol>
</div>
</body>
</html>

<%--<h3>Список лекарственных групп:</h3>--%>
<%--<ol>--%>
<%--<c:forEach items="${requestScope.groups}" var="group">--%>
<%--<a href="${pageContext.request.contextPath}/medicinesInGroup?id=${group.id}"><li>${group.name}</li></a><br>--%>
<%--</c:forEach>--%>
<%--</ol>--%>