<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
<head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register">
    <label for="userLogin">Login:</label><br>
    <input name="userLogin"><br/>
    <label for="userPassword">Password:</label><br>
    <input type="password" name="userPassword"><br/>
    <label for="userName">Name:</label><br>
    <input name="userName"><br/>
    <label for="userEmail">Email:</label><br>
    <input name="userEmail"><br/>
    <input type="radio" name="language" value="ru">
    <label for="ru">русский</label><br>
    <input type="radio" name="language" value="en">
    <label for="en">English</label><br>
    <input type="radio" name="language" value="zh">
    <label for="zh">中國傳統的</label><br>
    <input type="submit">
</form>
</body>
</html>
