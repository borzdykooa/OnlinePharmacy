<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>
<h1><fmt:message key="first.welcome"/>!</h1>

<form action="${pageContext.request.contextPath}/medicinesPartName" method="get">
    <fmt:message key="first.medicine"/> <input type="text" name="partName"
           value="${requestScope.partName}">
    <input type="submit" value="<fmt:message key="first.find"/>">
</form>


<form action="${pageContext.request.contextPath}/groups" method="get">
    <fmt:message key="first.or"/><input type="submit" value="<fmt:message key="first.groups"/>"><br>
</form>

<c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.login != 'borzdykooa@mail.ru'}">
    <form action="${pageContext.request.contextPath}/ordersByCurrentUser" method="post">
        <input type="hidden" name="currentUserId" value="${sessionScope.currentUser.id}"/><br>
        <input type="submit" value="<fmt:message key="first.orders"/>"><br>
    </form>
</c:if>


<div>
    <c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.login == 'borzdykooa@mail.ru'}">
        <h2>Функции администратора</h2>
        <ol>
            <li><a href="${pageContext.request.contextPath}/saveMedicine">Сохранить новое лекарство</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/saveGroup">Сохранить новую лекарственную группу</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/users">Просмотреть список всех пользователей</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/deleteMedicine">Удалить лекарство</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/medicines">Посмотреть список всех лекарств</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/orders">Посмотреть список всех заказов</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/updateOrderStatusDate">Закрыть заказ</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/ordersByMedicineId">Найти все заказы по названию
                лекарства</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/ordersByUserId">Найти все заказы по логину пользователя</a>
            </li>
            <br>
            <li><a href="${pageContext.request.contextPath}/deleteOrderByDate">Удалить все заказы, сделанные раньше
                введенной даты</a></li>
            <br>
        </ol>
    </c:if>
    <form action="${pageContext.request.contextPath}/basket" method="get">
        <input type="submit" value="<fmt:message key="first.basket"/>">
    </form>
</div>
</body>
</html>
