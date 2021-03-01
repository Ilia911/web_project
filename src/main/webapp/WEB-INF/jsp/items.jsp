<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="items.title"/></title>
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
<h2><locale:loc value="items.title"/></h2>
    <c:if test="${not empty requestScope.items}">
        <table style="width:100%">
            <tr><th><locale:loc value="items.name"/></th><th><locale:loc value="items.type"/></th>
            <th><locale:loc value="items.price"/></th><th><locale:loc value="items.time"/></th>
            </tr>
            <c:forEach var="item" items="${requestScope.items}">
                <tr><td>
                <a href="${pageContext.request.contextPath}/controller?command=show_item&id=${item.id}">${item.name}</a>
                </td>
                <td>${item.type}</td><td>${item.price}</td><td>${item.time}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
