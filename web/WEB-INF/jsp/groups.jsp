<%--
  Created by IntelliJ IDEA.
  User: оля
  Date: 19.03.2018
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h3>Список лекарственных групп:</h3>
    <ol>
        <c:forEach items="${requestScope.groups}" var="group">
            <a href="${pageContext.request.contextPath}/medicinesInGroup?id=${group.id}"><li>${group.name}</li></a><br>
    </c:forEach>
    </ol>
</div>
</body>
</html>


