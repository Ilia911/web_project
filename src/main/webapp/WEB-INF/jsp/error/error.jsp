<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="locale" uri="customtags"%>
<html><head><title>Error Page</title></head>
<br/>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
</body></html>