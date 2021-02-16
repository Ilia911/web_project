<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Item</title>
<head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register_item">
    <label for="itemName">Item name:</label><br>
    <input name="itemName"><br/>
    <label for="itemDescribe">Item description:</label><br>
    <input name="itemDescribe"><br/>
    <label for="itemType">Type of the item auction:</label><br>
    <input type ="radio" name="itemType" value = "straight">Straight<br/>
    <input type ="radio" name="itemType" value = "reverse">Reverse<br/>
    <label for="itemPrice">Start price:</label><br>
    <input name="itemPrice"><br/>
    <label for="itemBid">Bid:</label><br>
    <input name="itemBid"><br/>
    <input type="hidden" name="time", value="${calendar.timeInMillis}"><br/>
    <input type="submit">
    <input type="reset">
</form>
</body>
</html>