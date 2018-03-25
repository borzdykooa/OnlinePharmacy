<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/saveOrder" method="post">
    Дата заказа: <input type="text" name="dateOfOrder" value="${requestScope.dateOfOrder}"><br>
    Дата закрытия заказа: <input type="text" name="orderClothingDate" value="${requestScope.orderClothingDate}"><br>
    Статус заказа: <br>
    <input type="radio" name="status" value="DONE"> выполнен
    <input type="radio" name="status" value="PROCESSED"> в процессе обработки <br>

    Клиент:<br>
    <select name="user">
        <c:forEach var="user" items="${requestScope.users}">
            <option value="${user.id}">${user.fullName} ${user.user.login} </option>
        </c:forEach>
    </select><br>

    ID заказа: <input type="text" name="order" value="${sessionScope.order.id}"><br>


    Название лекарства:<br>
    <select name="medicine">
        <c:forEach var="medicine" items="${requestScope.medicines}">
            <option value="${medicine.id}">${medicine.name}</option>
        </c:forEach>
    </select><br>

    Количество: <input type="text" name="quantity" value="${requestScope.quantity}"><br>

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
