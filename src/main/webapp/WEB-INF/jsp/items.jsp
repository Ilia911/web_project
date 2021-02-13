<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <title>Items</title>
</head>
<body>
<h2>Items list</h2>
    <c:if test="${not empty requestScope.items}">
        <h2>Columns</h2>
        <ul>
            <c:forEach var="item" items="${requestScope.items}">
                <li>${item.name}, price: ${item.price}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
