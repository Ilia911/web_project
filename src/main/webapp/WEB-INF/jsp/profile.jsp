<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="profile.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<h2><locale:loc value="profile.title"/></h2>
    <c:if test="${not empty requestScope.user}">
        <table>
            <tr>
                <th><locale:loc value="users.name"/></th>
                <th><locale:loc value="users.id"/></th>
                <th><locale:loc value="users.login"/></th>
                <th><locale:loc value="users.account"/></th>
                <th><locale:loc value="users.role"/></th>
                <th><locale:loc value="users.userStatus"/></th>
            </tr>
            <tr>
                <td>${user.name}</td>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.account}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
            </tr>
        </table>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=show_edit_profile">
                    <input name="id" type="hidden" value="${user.id}"/>
                    <input name="name" type="hidden" value="${user.name}"/>
                    <input name="login" type="hidden" value="${user.login}"/>
                    <input name="account" type="hidden" value="${user.account}"/>
                    <input type="submit" value=<locale:loc value="profile.edit.button"/>/>
                </form>
    </c:if>
    <br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
</html>