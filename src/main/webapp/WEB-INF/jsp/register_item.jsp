<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Item</title>
<head>
<body>
<jsp:include page="common/header.jsp" />
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
    <label for="minBid">Minimum bid:</label><br>
    <input name="minBid" type="number" min=1>><br/>
    <input type="submit">
    <input type="reset">
</form>
</body>
</html>