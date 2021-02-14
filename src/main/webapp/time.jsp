<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><title>JSP Timing</title></head>
<body>
    <h5>Счетчик времени от запуска приложения до нажатия кнопки</h5>
    <jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=show_time">
        <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
        <input type="submit" name="button" value="Посчитать время"/>
    </form>
</body>
</html>