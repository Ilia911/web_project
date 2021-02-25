<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="main.title"/></title>
</head>
<body>
<h3>
<locale:loc value="main.greeting"/>
<c:if test="${ not empty sessionScope.name }">${sessionScope.name}!</c:if>
<c:if test="${ empty sessionScope.name }"><locale:loc value="main.user.name"/></c:if>
</h3>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%><br/><br/>
<h3>${successfulMessage}</h3>
<h3>${failedMessage}</h3><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_items"><locale:loc value="main.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register_item"><locale:loc value="main.register.item"/></a><br/>
<br/><br/>
<form method="post" action="${pageContext.request.contextPath}/controller?command=choose_locale">
    <input type ="radio" name="locale" value ="en_US"><locale:loc value="main.language.english"/><br/>
    <input type ="radio" name="locale" value ="zh_TW"><locale:loc value="main.language.chinese"/><br/>
    <input type ="radio" name="locale" value ="ru_RU"><locale:loc value="main.language.russian"/><br/>
    <input type="submit" value=<locale:loc value="main.submit"/>/>
</form>
</body>
</html>