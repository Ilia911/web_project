package com.epam.jwd.web.servlet.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes = new HashMap<>();
    private HashMap<String, String[]> requestParameters = new HashMap<>();
    private HashMap<String, Object> sessionAttributes = new HashMap<>();
    private RequestDispatcher requestDispatcher;

    public SessionRequestContent(HttpServletRequest request) {

        final Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            requestParameters.put(key, request.getParameterValues(key));
        }

        HttpSession session = request.getSession();
        final Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            sessionAttributes.put(key, session.getAttribute(key));
        }

        final Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            String key = requestAttributeNames.nextElement();
            requestAttributes.put(key, request.getAttribute(key));
        }
    }


// метод извлечения информации из запроса
    public void extractValues(HttpServletRequest request) {
// реализация
    }
    // метод добавления в запрос данных для передачи в jsp
    public void insertAttributes(HttpServletRequest request) {
// реализация
    }
// some methods
}
