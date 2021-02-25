<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="login.title"/></title>
<head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<form method="post" action="${pageContext.request.contextPath}/controller?command=log_in">
<br/>
<strong>${errorLoginMessage}</strong>
<br/>
    <label for="login"><locale:loc value="login.login"/></label><br>
    <input name="login" value=""><br/>
    <label for="password"><locale:loc value="login.password"/></label><br>
    <input type="password" name="password" value=""><br/>
    <input type="submit" value=<locale:loc value="login.submit"/>>
    <input type="reset" value=<locale:loc value="login.reset"/>>
</form>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>
