<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<head>
    <meta charset="UTF-8">
    <title><locale:loc value="profile.edit.title"/></title>
    <style>
        table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        text-align: left;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<h2><locale:loc value="profile.edit.title"/></h2>
    <c:if test="${not empty requestScope.user}">
        <table style="width:100%">
            <tr>
                <th><locale:loc value="profile.edit.name"/></th>
                <th><locale:loc value="profile.edit.password"/></th>
            </tr>
            <tr>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=SAVE_EDITED_USER">
                <td><input name="name" value="${user.name}"/></td>
                <td><input type="password" name="password"/></td>
            </tr>
        </table>
                    <input name="id" type="hidden" value="${user.id}"/>
                    <input type="submit" value=<locale:loc value="profile.edit.save"/>/>
                </form>
    </c:if>
</body>
