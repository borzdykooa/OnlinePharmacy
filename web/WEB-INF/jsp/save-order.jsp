<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/saveOrder" method="post">

    <input type="hidden" name="user" value="${sessionScope.currentUser.id}"/><br>

    <h3>Список заказываемых лекарств:</h3>
    <br>
    <ol>
        <c:forEach var="item" items="${sessionScope.addToBasket}">
            <li><h4>${item.medicineName}.</h4>
                Цена: ${item.medicinePrice} бел.руб. <br>
                Количество упаковок: ${item.orderQuantity} <br>
                Стоимость: ${item.sum} бел.руб.<br>
            </li>
            <br>
            <br>
        </c:forEach>
    </ol>
    <h4>Общая стоимость заказа: <input type="hidden" name="totalSum"
                                       value="${sessionScope.totalSum}"/> ${sessionScope.totalSum} бел.руб.</h4>
    <br>

    <input type="submit" value="Сохранить"><br>
    <br>

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
