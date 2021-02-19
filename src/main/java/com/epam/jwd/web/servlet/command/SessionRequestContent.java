package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent implements RequestContent{
    private HashMap<String, Object> requestAttributes = new HashMap<>();
    private HashMap<String, String[]> requestParameters = new HashMap<>();
    private HashMap<String, Object> sessionAttributes = new HashMap<>();

    SessionRequestContent() {
    }

    public void extractValues(HttpServletRequest request) {

        request.getParameterMap().forEach(requestParameters::put);

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

    public void insertAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession();
        requestAttributes.forEach(request::setAttribute);
        sessionAttributes.forEach(session::setAttribute);
    }

    public String[] getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void setRequestAttribute(String key, Object attribute) {
        requestAttributes.put(key, attribute);
    }

    public void setSessionAttribute(String key, Object attribute) {
        sessionAttributes.put(key, attribute);
    }
}
