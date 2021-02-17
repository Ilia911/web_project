<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
<head>
<body>
<jsp:include page="common/header.jsp" />
<form method="post" action="${pageContext.request.contextPath}/controller?command=register">
    <label for="userLogin">Login:</label><br>
    <input name="userLogin"><br/>
    <label for="userPassword">Password:</label><br>
    <input type="password" name="userPassword"><br/>
    <label for="userName">Name:</label><br>
    <input name="userName"><br/>
    <input type="radio" name="language" value="ru">
    <label for="ru">Russian</label><br>
    <input type="radio" name="language" value="en">
    <label for="en">English</label><br>
    <input type="radio" name="language" value="zh">
    <label for="zh">Traditional chinese</label><br>
    <input type="submit">
    <input type="reset">
</form>
</body>
</html>
