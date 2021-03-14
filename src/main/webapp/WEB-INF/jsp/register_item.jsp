<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="register.item.title"/></title>
<head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register_item">
    <label for="itemName"><locale:loc value="register.item.name"/></label><br>
    <input name="itemName"><br/>
    <label for="itemDescribe"><locale:loc value="register.item.describe"/></label><br>
    <input name="itemDescribe"><br/>
    <label for="itemType"></label><locale:loc value="register.item.type"/><br>
    <input type ="radio" name="itemType" value = "1"><locale:loc value="register.item.straight"/><br/>
    <input type ="radio" name="itemType" value = "2"><locale:loc value="register.item.reverse"/><br/>
    <label for="itemPrice"><locale:loc value="register.item.price"/></label><br>
    <input name="itemPrice" type="number" min=1 value="${item.price}"><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
</body>
</html>