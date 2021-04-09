<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="blocked.items.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<h2><locale:loc value="blocked.items.title"/></h2>
    <c:if test="${not empty requestScope.items}">
        <table style="width:100%">
            <tr>
                <th><locale:loc value="items.name"/></th>
                <th><locale:loc value="items.describe"/></th>
                <th><locale:loc value="items.owner.id"/></th>
                <th><locale:loc value="items.type"/></th>
                <th><locale:loc value="items.price"/></th>
                <th><locale:loc value="items.status"/></th>
                </th><th><locale:loc value="blocked.items.unblock"/></th>
            </tr>
            <c:forEach var="item" items="${requestScope.items}">
                <tr>
                <td>
                <a href="${pageContext.request.contextPath}/controller?command=show_item&id=${item.id}">${item.name}</a>
                </td>
                <td>${item.describe}</td>
                <td>${item.owner}</td>
                <td>${item.type}</td>
                <td>${item.price}</td>
                <td>${item.status}</td>
                <td>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=unblock_item">
                    <input name="id" type="hidden" value="${item.id}"/>
                    <input name="name" type="hidden" value="${item.name}"/>
                    <input name="describe" type="hidden" value="${item.describe}"/>
                    <input name="ownerId" type="hidden" value="${item.owner}"/>
                    <input name="type" type="hidden" value="${item.type}"/>
                    <input name="price" type="hidden" value="${item.price}"/>
                    <input type="submit" value=<locale:loc value="blocked.items.unblock"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
