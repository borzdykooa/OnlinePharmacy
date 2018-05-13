<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@include file="styles.jsp" %>

</head>
<body>

<%@include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/saveUser" method="post">
    <input type="text" name="login" value="${param.login}"/> <br>
    <fmt:message key="login.password"/>: <br>
    <input type="password" name="password"/> <br>
    <fmt:message key="registration.fullname"/>:<br>
    <input type="text" name="fullName" value="${param.fullName}"><br>
    <fmt:message key="registration.bitrhday"/>:<br>
    <input type="date" name="dateOfBirth" value="${param.dateOfBirth}"><br>
    <fmt:message key="registration.telephone"/>:<br>
    <input type="text" name="telephoneNumber" value="${param.telephoneNumber}"><br>
    <fmt:message key="registration.address"/>:<br>
    <input type="text" name="address" value="${param.address}"><br>
    <input type="submit" value="<fmt:message key="registration.register"/>">
</form>
<c:if test="${not empty requestScope.UserErrors}">
    <div>
        <c:forEach var="UserError" items="${requestScope.UserErrors}">
            <span>${UserError}</span><br>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
