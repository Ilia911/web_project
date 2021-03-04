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
<h3>${requestScope.errorDoBidMessage}</h3>
    <c:if test="${not empty requestScope.items}">
        <table style="width:100%">
            <tr>
                <th><locale:loc value="items.name"/></th>
                <th><locale:loc value="items.describe"/></th>
                <th><locale:loc value="items.owner.id"/></th>
                <th><locale:loc value="items.type"/></th>
                <th><locale:loc value="items.price"/></th>
                <th><locale:loc value="items.time"/></th>
                <th><locale:loc value="items.bid.owner.id"/>
                </th><th><locale:loc value="items.do.bid"/></th>
            </tr>
            <c:forEach var="item" items="${requestScope.items}">
                <tr>
                <td>
                <a href="${pageContext.request.contextPath}/controller?command=show_item&id=${item.id}">${item.name}</a>
                </td>
                <td>${item.describe}</td>
                <td>${item.ownerId}</td>
                <td>${item.type}</td>
                <td>${item.price}</td>
                <td><locale:time value="${item.time}"/></td>
                <td>${item.bidOwnerId}</td>
                <td>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=do_bid">
                    <input name="itemId" type="hidden" value="${item.id}"/>
                    <input name="previousBidOwnerId" type="hidden" value="${item.bidOwnerId}"/>
                    <input name="previousPrice" type="hidden" value="${item.price}"/>
                    <input name="previousTime" type="hidden" value="${item.time}"/>
                    <input name="bid" type="number" min=1/>
                    <input type="submit" value=<locale:loc value="items.do.bid"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
