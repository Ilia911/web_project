<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="blocked.items.title"/></title>
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
<h2><locale:loc value="blocked.items.title"/></h2>
    <c:if test="${not empty requestScope.items}">
        <table style="width:100%">
            <tr><th><locale:loc value="items.name"/></th><th><locale:loc value="items.describe"/></th>
            <tr><th><locale:loc value="items.type"/></th><th><locale:loc value="items.price"/></th>
            <th><locale:loc value="items.minimum.bid"/></th>
            </tr>
            <c:forEach var="item" items="${requestScope.items}">
                <td>${item.name}</td>
                <td>${item.describe}</td>
                <td>${item.type}</td>
                <td>${item.price}</td>
                <td>${item.bid}</td>
                <td><a href="${pageContext.request.contextPath}/controller?command=unblock_item&id=${item.id}">
                <locale:loc value="blocked.items.unblock"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
