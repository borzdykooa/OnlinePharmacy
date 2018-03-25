<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    Логин (адресс электронной почты): <input type="text" name="login"><br>
    Пароль: <input type="password" name="password"><br>
    <input type="submit" value="Войти">

    <a href="${pageContext.request.contextPath}/saveUser">
       Зарегистрироваться
    </a><br>
</form>
</body>
</html>
