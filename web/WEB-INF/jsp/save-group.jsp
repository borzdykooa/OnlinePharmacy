<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/saveGroup" method="post">
    Название группы лекарств: <input type="text" name="name" value="${requestScope.group}"><br>
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
