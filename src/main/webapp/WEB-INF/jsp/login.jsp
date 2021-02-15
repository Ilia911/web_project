<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
<head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller?command=login">
    <label for="userLogin">Login:</label><br>
    <input name="userLogin"><br/>
    <label for="userPassword">Login:</label><br>
    <input type="password" name="userPassword"><br/>
    <label for="language">Login:</label><br>
    language: <input type=
    <input type="submit">
    <input type="reset">
</form>
</body>
</html>
