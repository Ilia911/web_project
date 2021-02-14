<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
<head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register">
    Name: <input name="userName"><br/>
    Login: <input name="userLogin"><br/>
    Password: <input type="password" name="userPassword"><br/>
    Email: <input name="userEmail"><br/>
    <input type="submit">
</form>
</body>
</html>
