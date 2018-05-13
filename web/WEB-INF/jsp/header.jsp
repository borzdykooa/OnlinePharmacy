<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${pageContext.request.contextPath}/language">
    <select class="btn btn-default dropdown-toggle" name="lang" onchange="submit()">
        <option value="en_US" ${sessionScope.language eq 'en_US' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${sessionScope.language eq 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="login.logout"/></a><br>
