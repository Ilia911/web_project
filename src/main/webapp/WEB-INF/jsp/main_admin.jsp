<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="admin.title"/></title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%><br/>
<h3><locale:loc value="admin.title"/></h3><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_lots"><locale:loc value="main.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_blocked_items"><locale:loc value="admin.blocked.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_profile"><locale:loc value="admin.profile"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_users"><locale:loc value="admin.users"/></a><br/>
<br/><br/>
</body>
</html>