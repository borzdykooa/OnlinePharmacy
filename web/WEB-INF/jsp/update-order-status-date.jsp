<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>

<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/updateOrderStatusDate" method="post">
    <c:if test="${not empty requestScope.orders}">
        <select name="id">
            <c:forEach var="order" items="${requestScope.orders}">
                <option value="${order.order.id}">Заказ
                    №${order.order.id} ${order.order.user.login} ${order.medicine.name}</option>
            </c:forEach>
        </select>
    </c:if>
    <br>
    Дата закрытия заказа: <input type="date" name="orderClothingDate" value="${requestScope.orderClothingDate}"><br>
    <input type="submit" value="Изменить">
</form>

<%@include file="footer.jsp" %>
</body>
</html>
