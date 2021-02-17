<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <title>Items</title>
</head>
<body>
<jsp:include page="common/header.jsp" />
<h2>Items list</h2>
    <c:if test="${not empty requestScope.items}">
        <h2>Columns</h2>
        <table>
            <c:forEach var="item" items="${requestScope.items}">
                <tr><td>${item.name}</td><td>price: ${item.price}</td></tr>
            </c:forEach>
        </table>
    </c:if>
</body>
