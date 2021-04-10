<br/>
<a href="${pageContext.request.contextPath}/controller?command=show_lots&showedItems=5&showedPage=1"><locale:loc value="main.items"/></a> |
<a href="${pageContext.request.contextPath}/controller"><locale:loc value="header.home"/></a> |
<a href="contact.html"><locale:loc value="header.contact"/></a> |
<a href="about.html"><locale:loc value="header.about"/></a> |
<c:choose>
    <c:when test="${not empty sessionScope.login}">
        <a href="${pageContext.request.contextPath}/controller?command=log_out"><locale:loc value="header.logout"/></a> |
        <locale:loc value="header.logged.as"/> <strong>${sessionScope.name}</strong>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/controller?command=show_login"><locale:loc value="header.login"/></a> |
        <a href="${pageContext.request.contextPath}/controller?command=show_register"><locale:loc value="header.register"/></a> |
    </c:otherwise>
</c:choose>
<br/><br/>
