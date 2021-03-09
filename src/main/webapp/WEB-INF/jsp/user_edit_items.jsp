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
    <c:if test="${not empty requestScope.item}">
        <table style="width:100%">
            <tr>
                <th><locale:loc value="items.name"/></th>
                <th><locale:loc value="items.describe"/></th>
                <th><locale:loc value="items.type"/></th>
                <th><locale:loc value="items.price"/></th>
                <th><locale:loc value="items.status"/></th>
                </th><th><locale:loc value="user.items.edit"/></th>
            </tr>
                <tr>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=save_edited_item">
                <td><input name="name" value="${item.name}"/></td>
                <td><input name="name" value="${item.describe}"/></td>
                <td><input name="name" value="${item.type}"/></td>
                <td><input name="name" value="${item.price}"/></td>
                <td>${item.status}</td>
                <td>
                    <input name="id" type="hidden" value="${item.itemId}"/>
                    <input type="submit" value=<locale:loc value="user.edit.item.save"/>/>
                </form>
                </td>
                </tr>
        </table>
    </c:if>
</body>
