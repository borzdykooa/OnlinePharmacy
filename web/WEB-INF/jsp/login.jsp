<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>


</head>
<body>
<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/login" method="post">
    <fmt:message key="login.username"/> : <input type="text" name="login" value="${param.login}"><br>
    <fmt:message key="login.password"/>: <input type="password" name="password"><br>
    <input type="submit" value="<fmt:message key="login.submit"/>">
</form>

<a href="${pageContext.request.contextPath}/saveUser"><fmt:message key="login.registration"/></a><br><br>
<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
