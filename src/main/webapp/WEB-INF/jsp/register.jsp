<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="register.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
<head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<br/>
<form method="post" action="${pageContext.request.contextPath}/controller?command=register">
<h3>${failedRegisterMessage}</h3><br/>
    <label for="login"><locale:loc value="login.login"/></label><br>
    <input name="login"><br/>
    <label for="password"><locale:loc value="login.password"/></label><br>
    <input type="password" name="password"><br/>
    <label for="name"><locale:loc value="register.name"/></label><br>
    <input name="name"><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
    <br/>
    <div class="footer">
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
    </div>
</body>
</html>
