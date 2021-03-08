<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="main.title"/></title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%><br/><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_lots"><locale:loc value="main.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_user_items"><locale:loc value="client.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_profile"><locale:loc value="admin.profile"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register_item"><locale:loc value="main.register.item"/></a><br/>
<br/><br/>
</body>
</html>