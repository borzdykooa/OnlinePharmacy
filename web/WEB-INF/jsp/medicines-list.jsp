<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<div>
    <h3>Список лекарств:</h3>
    <ol>
        <c:forEach items="${requestScope.medicines}" var="medicine">
            <a href="${pageContext.request.contextPath}/medicineFullInfo?id=${medicine.id}">
                <li>${medicine.name}</li>
            </a><br>
        </c:forEach>
    </ol>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
