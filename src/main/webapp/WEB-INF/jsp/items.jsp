<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="items.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<h2><locale:loc value="items.title"/></h2>
<h3>${requestScope.errorDoBidMessage}</h3>
    <c:if test="${not empty requestScope.items}">
        <table>
        <thead>
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
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.items}">
                <tr>
                <td>${item.name}</td>
                <td>${item.describe}</td>
                <td>${item.ownerId}</td>
                <td>${item.type}</td>
                <td>${item.price}</td>
                <td><locale:time value="${item.endTime}"/></td>
                    <c:choose>
                        <c:when test="${item.bidOwnerId eq sessionScope.id}">
                            <td class="bid-owner"><locale:loc value="items.you"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><locale:loc value="items.client"/></td>
                        </c:otherwise>
                    </c:choose>
                <td>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=do_bid">
                    <input name="id" type="hidden" value="${item.id}"/>
                    <input name="itemId" type="hidden" value="${item.itemId}"/>
                    <input name="ownerId" type="hidden" value="${item.ownerId}"/>
                    <input name="previousBidOwnerId" type="hidden" value="${item.bidOwnerId}"/>
                    <input name="type" type="hidden" value="${item.type}"/>
                    <input name="previousPrice" type="hidden" value="${item.price}"/>
                    <input name="previousTime" type="hidden" value="${item.endTime}"/>
                    <input name="bid" type="number" min=1 autofocus/>
                    <input type="submit" value=<locale:loc value="items.do.bid"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
