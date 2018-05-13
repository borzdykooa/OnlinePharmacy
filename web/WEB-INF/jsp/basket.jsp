<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<ol>
    <c:forEach var="item" items="${sessionScope.addToBasket}">
        <li><h4>${item.medicineName}.</h4>
            Цена: ${item.medicinePrice} бел.руб. <br>
            Количество упаковок: ${item.orderQuantity} <br>
            Стоимость: ${item.sum} бел.руб.<br>
        </li>

        <br>
    </c:forEach>
</ol>
<h4>Общая стоимость товаров в корзине: <input type="hidden" name="totalSum"
                                              value="${sessionScope.totalSum}"/> ${sessionScope.totalSum} бел.руб.</h4>

<form action="${pageContext.request.contextPath}/basket" method="post">
    <input type="submit" value="Очистить корзину">
</form>

<form action="${pageContext.request.contextPath}/saveOrder" method="get">
    <input type="submit" value="Перейти к заказу"><br>
</form>

<%@include file="footer.jsp" %>

</body>
</html>
