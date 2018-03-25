<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/saveUser" method="post">
    Логин (адрес электронной почты): <br>
    <input type="text" name="login"/> <br>
    Пароль: <br>
    <input type="text" name="password"/> <br>

    <%--<input type="radio" name="role" value="ADMIN"> Администратор--%>
    <%--<input type="radio" name="role" value="CLIENT"> Клиент <br>--%>

    <%--<input type="submit" value="Зарегистрироваться">--%>

    <%--<a href="${pageContext.request.contextPath}/savePersonalData?id=${user.id}">--%>

        <input type="submit" value="Зарегистрироваться">
    <%--</a><br>--%>
</form>
</body>
</html>
