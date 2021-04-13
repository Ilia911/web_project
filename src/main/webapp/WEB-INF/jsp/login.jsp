<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="login.title"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
<head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<form method="post" action="${pageContext.request.contextPath}/controller?command=log_in">
<br/>
<strong>${errorLoginMessage}</strong>
<br/>
    <label for="login"><locale:loc value="login.login"/></label><br>
    <input name="login" required autofocus><br/>
    <label for="password"><locale:loc value="login.password"/></label><br>
    <input type="password" name="password" required><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
<br/>
<div class="footer">
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
</div>
</body>
</html>
