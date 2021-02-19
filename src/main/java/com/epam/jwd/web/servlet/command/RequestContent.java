package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface RequestContent {

    void extractValues(HttpServletRequest request);

    void insertAttributes(HttpServletRequest request);

    String[] getRequestParameter(String key);

    Object getRequestAttribute(String key);

    Object getSessionAttribute(String key);

    void setRequestAttribute(String key, Object attribute);

    void setSessionAttribute(String key, Object attribute);
}
