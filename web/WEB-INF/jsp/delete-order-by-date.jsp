<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/deleteOrderByDate" method="post">

    <h3>Введите дату, ранее которой требуется удалить все заказы</h3><br>
    <input type="date" name="dateOfOrder" value="${requestScope.dateOfOrder}"><br>


    <input type="submit" value="Удалить">
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
