<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="register.title"/></title>
<head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register">
<strong>${failedRegisterMessage}</strong><br/>
    <label for="login"><locale:loc value="login.login"/></label><br>
    <input name="login"><br/>
    <label for="password"><locale:loc value="login.password"/></label><br>
    <input type="password" name="password"><br/>
    <label for="name"><locale:loc value="register.name"/></label><br>
    <input name="name"><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
</body>
</html>
