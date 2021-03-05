<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="users.title"/></title>
    <style>
        table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        text-align: left;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<h2><locale:loc value="users.title"/></h2>
    <c:if test="${not empty requestScope.users}">
        <table style="width:100%">
            <tr>
                <th><locale:loc value="users.name"/></th>
                <th><locale:loc value="users.id"/></th>
                <th><locale:loc value="users.login"/></th>
                <th><locale:loc value="users.account"/></th>
                <th><locale:loc value="users.role"/></th>
                <th><locale:loc value="users.userStatus"/></th>
                </th><th><locale:loc value="users.change.status"/></th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                <td>${user.name}</td>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.account}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
                <td>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=change_user_status">
                    <input name="id" type="hidden" value="${user.id}"/>
                    <input name="status" type="hidden" value="${user.status}"/>
                    <input type="submit" value=<locale:loc value="users.change.status"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
