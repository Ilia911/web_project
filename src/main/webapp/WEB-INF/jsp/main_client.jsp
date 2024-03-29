<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="main.title"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%><br/><br/>
</div>
<c:if test="${ not empty sessionScope.failedMessage }"><h3>${sessionScope.failedMessage}!</h3></c:if>
<c:if test="${ not empty requestScope.failedMessage }"><h3>${requestScope.failedMessage}!</h3></c:if>
<div class="commands">
<a href="${pageContext.request.contextPath}/controller?command=show_user_items"><locale:loc value="client.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_profile"><locale:loc value="admin.profile"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register_item"><locale:loc value="main.register.item"/></a><br/>
</div>
<%@ include file="/WEB-INF/jsp/common/lots.jsp"%>
<br/><br/><br/><br/>
<div class="footer">
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
</div>
</body>
</html>