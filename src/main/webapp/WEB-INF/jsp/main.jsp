<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="main.title"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</div>
<h2>
<locale:loc value="main.greeting"/>
<c:if test="${ not empty sessionScope.name }">${sessionScope.name}!</c:if>
<c:if test="${ empty sessionScope.name }"><locale:loc value="main.user.name"/></c:if>
</h2>
<c:if test="${ not empty sessionScope.successfulMessage }"><h2>${sessionScope.successfulMessage}!<h2></c:if>
<c:if test="${ not empty sessionScope.failedMessage }"><h3>${sessionScope.failedMessage}!</h3></c:if>
<br/>
<form method="post" action="${pageContext.request.contextPath}/controller?command=choose_locale">
    <input type ="radio" name="locale" value ="en_US"><locale:loc value="main.language.english"/><br/>
    <input type ="radio" name="locale" value ="zh_TW"><locale:loc value="main.language.chinese"/><br/>
    <input type ="radio" name="locale" value ="ru_RU"><locale:loc value="main.language.russian"/><br/>
    <input type="submit" value=<locale:loc value="main.submit"/>/>
</form>
<br/>
<div class="footer">
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
</div>
</body>
</html>