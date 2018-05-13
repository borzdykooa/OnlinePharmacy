<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>


</head>
<body>

<%@include file="header.jsp" %>

<div>
    <ol>
        <c:forEach items="${requestScope.orderMedicines}" var="orderMedicine">
            <li><h3>Номер заказа ${orderMedicine.order.id}</h3>
                Дата заказа: ${orderMedicine.order.dateOfOrder} <br>
                Общая стоимость заказа: ${orderMedicine.order.totalSum} бел.руб.<br>
                Статус заказа: ${orderMedicine.order.status} <br><br>
                Лекарство: ${orderMedicine.medicine.name} <br>
                Количество упаковок: ${orderMedicine.quantity} <br>
                Цена лекарства: ${orderMedicine.medicine.price} бел.руб. <br>
                <br>
            </li>
            <br>
        </c:forEach>
    </ol>
</div>


<form action="${pageContext.request.contextPath}/download" method="post">
<div>
    <input type="hidden" name="currentUserId" value="${sessionScope.currentUser.id}"/><br>
    <input type="submit" value="Загрузить"><br>
</div>
</form>


<%@include file="footer.jsp" %>
</body>
</html>
