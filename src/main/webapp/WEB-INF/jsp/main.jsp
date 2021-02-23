<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html>
<head>
    <title><locale:loc value="page.main"/></title>
</head>
<body>
<h3><locale:loc value ="main.greeting"/> ${sessionScope.name}!</h3>
<a href="${pageContext.request.contextPath}/controller?command=show_login"><locale:loc value="page.login"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register"><locale:loc value="page.register.user"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_items"><locale:loc value="page.items"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=log_out"><locale:loc value="page.logout"/></a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register_item"><locale:loc value="page.register.item"/></a><br/>
</body>
</html>