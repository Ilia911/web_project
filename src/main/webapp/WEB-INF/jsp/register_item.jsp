<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title>Registration Item</title>
<head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register_item">
    <label for="itemName">Item name:</label><br>
    <input name="itemName"><br/>
    <label for="itemDescribe">Item description:</label><br>
    <input name="itemDescribe"><br/>
    <label for="itemType">Type of the item auction:</label><br>
    <input type ="radio" name="itemType" value = "1">Straight<br/>
    <input type ="radio" name="itemType" value = "2">Reverse<br/>
    <label for="itemPrice">Start price:</label><br>
    <input name="itemPrice" type="number" min=1><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
</body>
</html>