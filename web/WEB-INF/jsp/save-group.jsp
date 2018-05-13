<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/saveGroup" method="post">
    Название группы лекарств: <input type="text" name="name" value="${param.group}"><br>
    <input type="submit" value="Сохранить">
</form>
<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br>
        </c:forEach>
    </div>
</c:if>

<%@include file="footer.jsp" %>
</body>
</html>
