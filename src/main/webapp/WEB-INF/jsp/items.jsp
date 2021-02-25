<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="items.title"/></title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<h2><locale:loc value="items.title"/></h2>
    <c:if test="${not empty requestScope.items}">
        <table><hr/>
            <tr><td>| <locale:loc value="items.name"/> | </td><td><locale:loc value="items.type"/></td>
            <td>| <locale:loc value="items.price"/> | </td><td><locale:loc value="items.time"/></td>
            <td>| <locale:loc value="items.reference"/> |</td></tr>
            <c:forEach var="item" items="${requestScope.items}">
                <tr><td>| ${item.name} | </td><td>${item.type} |</td>
                <td>${item.price} | </td><td>${item.time} |</td>
                <td><form method="get" action="${pageContext.request.contextPath}/controller?command=show_item">
                <input type = hidden name="id" value =${item.id}/>
                <input type="submit" value=<locale:loc value="items.details"/>/>
                </td></tr>
            </c:forEach>
        </table>
    </c:if>
</body>
