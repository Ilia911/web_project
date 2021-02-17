<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head><title>Index</title></head>
<body>
<jsp:useBean id="calendar" scope="page" class="java.util.GregorianCalendar"/>
Directive
<%@ include file="time2.jsp"%>
<br/>
Action-tag
<jsp:include page="time2.jsp"></jsp:include>
</body>
</html>