<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="user.edit.item.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<h2><locale:loc value="user.edit.item.title"/></h2>
<strong>${requestScope.errorUserItemsMessage}</strong><br/>
    <c:if test="${not empty requestScope.item}">
        <table>
            <tr>
                <th><locale:loc value="items.name"/></th>
                <th><locale:loc value="items.describe"/></th>
                <th><locale:loc value="items.type"/></th>
                <th><locale:loc value="items.price"/></th>
                </th><th><locale:loc value="user.edit.item.save"/></th>
            </tr>
                <tr>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=save_edited_item">
                <td><input name="name" value="${item.name}" required/></td>
                <td><textarea name="describe" cols="60" rows="5">${item.describe} required</textarea></td>
                <td>
                    <select name="type" required>
                        <option value = "1"><locale:loc value="register.item.straight"/></option>
                        <option value = "2"><locale:loc value="register.item.reverse"/></option>
                    </select>
                </td>
                <td><input name="price" value="${item.price}" required/></td>
                <td>
                    <input name="id" type="hidden" value="${item.id}"/>
                    <input type="submit" value=<locale:loc value="user.edit.item.save"/>/>
                </form>
                </td>
                </tr>
        </table>
    </c:if>
    <br/><br/><br/><br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
</html>
