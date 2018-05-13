<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/saveMedicine" method="post">
    Название лекарства: <input type="text" name="name" value="${param.name}"><br>
    Описание: <input type="text" name="description" value="${param.description}"><br>
    Цена: <input type="text" name="price" placeholder="*.**" value="${param.price}"><br>
    Количество: <input type="text" name="quantity" value="${param.quantity}"><br>
    Группа лекарств:<br>
    <select name="group">
        <c:forEach var="group" items="${requestScope.groups}">
            <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select><br>
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
