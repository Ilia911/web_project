<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
<head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller?command=login">
    <input name="userName">
    <input type="password" name="userPassword">
    <input type="submit">
</form>
</body>
</html>
