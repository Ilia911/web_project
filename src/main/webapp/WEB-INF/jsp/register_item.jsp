<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="register.item.title"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
<head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<br/>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register_item">
    <label for="itemName"><locale:loc value="register.item.name"/></label><br>
    <input name="itemName" required autofocus><br/>
    <label for="itemDescribe"><locale:loc value="register.item.describe"/></label><br>
    <textarea name="itemDescribe" cols="60" rows="5" required></textarea><br/>
    <label for="itemType"></label><locale:loc value="register.item.type"/><br>
    <select name="itemType" required>
    <option value = "1"><locale:loc value="register.item.straight"/></option>
    <option value = "2"><locale:loc value="register.item.reverse"/></option>
    </select>
    <label for="itemPrice"><locale:loc value="register.item.price"/></label><br>
    <input name="itemPrice" type="number" min=1 value="${item.price}" required><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
    <br/><br/><br/><br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
</html>