<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>


<form action="${pageContext.request.contextPath}/ordersByMedicineId" method="post">

    Название лекарства:<br>
    <select name="id">
        <c:forEach var="id" items="${requestScope.medicines}">
            <option value="${id.id}">${id.name}</option>
        </c:forEach>
    </select><br>

    <input type="submit" value="Выбрать"><br>

    <div>
        <ol>
            <c:forEach items="${requestScope.orderMedicines}" var="orderMedicine">
                <li><h3>Номер заказа ${orderMedicine.order.id}</h3>
                    Дата заказа: ${orderMedicine.order.dateOfOrder} <br>
                    Общая стоимость заказа: ${orderMedicine.order.totalSum} бел.руб.<br>
                    Статус заказа: ${orderMedicine.order.status} <br><br>
                    Клиент: ${orderMedicine.order.user.login} <br>
                    Лекарство: ${orderMedicine.medicine.name} <br>
                    Количество упаковок: ${orderMedicine.quantity} <br>
                    Цена лекарства: ${orderMedicine.medicine.price} бел.руб. <br>
                    <br>
                </li>
                <br>
            </c:forEach>
        </ol>
    </div>
</form>

<%@include file="footer.jsp" %>
</body>
</html>
