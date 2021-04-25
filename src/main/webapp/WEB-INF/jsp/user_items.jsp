<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="user.items.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<h2><locale:loc value="user.items.title"/></h2>
<strong>${errorUserItemsMessage}</strong><br/>
    <c:if test="${not empty requestScope.items}">
        <table style="width:100%">
            <tr>
                <th><locale:loc value="items.name"/></th>
                <th><locale:loc value="items.describe"/></th>
                <th><locale:loc value="items.type"/></th>
                <th><locale:loc value="items.price"/></th>
                <th><locale:loc value="items.status"/></th>
                </th><th><locale:loc value="user.items.edit"/></th>
            </tr>
            <c:forEach var="item" items="${requestScope.items}">
                <tr>
                <td>${item.name}</td>
                <td>${item.describe}</td>
                <td>${item.type}</td>
                <td>${item.price}</td>
                <td>${item.status}</td>
                <td>
                <form method="GET" action="${pageContext.request.contextPath}/controller">
                    <input name="command" type="hidden" value="show_user_edit_item"/>
                    <input name="id" type="hidden" value="${item.id}"/>
                    <input type="submit" value=<locale:loc value="user.items.edit"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br/><br/><br/><br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
</html>