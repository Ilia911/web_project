<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <meta charset="utf-8">
    <title><locale:loc value="admin.title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
</head>
<body>
<div class="header">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%><br/>
</div>
<h2><locale:loc value="admin.title"/></h2><br/>
<div class="commands">
<a href="${pageContext.request.contextPath}/controller?command=show_blocked_items"><locale:loc value="admin.blocked.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_profile"><locale:loc value="admin.profile"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_users"><locale:loc value="admin.users"/></a><br/>
</div>
<br/><br/><br/><br/>
<div class="footer">
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%><br/><br/>
</div>
</body>
</html>