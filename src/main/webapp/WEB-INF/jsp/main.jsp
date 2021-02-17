<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<a href="${pageContext.request.contextPath}/controller?command=show_login">go to login page</a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register">go to register page</a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_items">go to item page</a><br/>
<a href="${pageContext.request.contextPath}/controller?command=log_out">log out</a><br/>
<a href="${pageContext.request.contextPath}/controller?command=show_register_item">register item</a><br/>
</body>
</html>