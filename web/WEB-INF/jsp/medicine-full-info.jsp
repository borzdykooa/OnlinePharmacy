<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>

<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<h3>${requestScope.medicine.name}</h3>

<p>Описание препарата: ${requestScope.medicine.description}</p>
<p>Цена препарата: ${requestScope.medicine.price} бел.руб.</p>
<p>Количество упаковок в наличии: ${requestScope.medicine.quantity}</p>
<p>Лекарственная группа: ${requestScope.medicine.group.name}</p>


<form action="${pageContext.request.contextPath}/addToBasket" method="get">

    <input type="hidden" name="medicineId" value="${requestScope.medicine.id}"/><br>
    <input type="hidden" name="medicineName" value="${requestScope.medicine.name}"/><br>
    <input type="hidden" name="medicinePrice" value="${requestScope.medicine.price}"/><br>
    <input type="hidden" name="medicineQuantity" value="${requestScope.medicine.quantity}"/><br>


    <c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.login != 'borzdykooa@mail.ru'}">
        Количество упаковок для заказа: <br>
        <input type="number" name="orderQuantity" value="1" min="1" required max="${requestScope.medicine.quantity}"/> <br>
        <input type="submit" value="Добавить в корзину">
    </c:if>

</form>

<%@include file="footer.jsp" %>

</body>
</html>
