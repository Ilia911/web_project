<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
<head>
<body>
<jsp:include page="common/header.jsp"/>
<form method="post" action="${pageContext.request.contextPath}/controller?command=log_in">
    <label for="userLogin">Login:</label><br>
    <input name="userLogin"><br/>
    <label for="userPassword">Password:</label><br>
    <input type="password" name="userPassword"><br/>
    <input type="submit">
    <input type="reset">
</form>
</body>
</html>
