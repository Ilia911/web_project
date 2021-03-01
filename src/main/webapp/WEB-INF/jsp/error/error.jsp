<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="locale" uri="customtags"%>
<html><head><title><locale:loc value="error.title"/></title></head>
<br/>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<locale:loc value="error.request.failed"/> ${pageContext.errorData.requestURI}
<br/>
<locale:loc value="error.servlet.name"/> ${pageContext.errorData.servletName}
<br/>
<locale:loc value="error.status.code"/> ${pageContext.errorData.statusCode}
<br/>
<locale:loc value="error.exception"/> ${pageContext.errorData.throwable}
</body></html>