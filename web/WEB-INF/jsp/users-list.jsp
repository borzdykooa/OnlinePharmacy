<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

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

        </c:forEach>
    </ol>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
