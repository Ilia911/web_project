<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="user.items.title"/></title>
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
                <form method="get" action="${pageContext.request.contextPath}/controller?command=show_user_edit_item">
                    <input name="id" type="hidden" value="${item.id}"/>
                    <input name="name" type="hidden" value="${item.name}"/>
                    <input name="describe" type="hidden" value="${item.describe}"/>
                    <input name="type" type="hidden" value="${item.type}"/>
                    <input name="price" type="hidden" value="${item.price}"/>
                    <input name="status" type="hidden" value="${item.status}"/>
                    <input type="submit" value=<locale:loc value="user.items.edit"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
