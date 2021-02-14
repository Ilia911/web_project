package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpSession;

public interface RequestContext {
    void setAttribute(String name, Object obj);

    Object getAttribute(String attribute);

    void invalidateSession();

    HttpSession getSession();

    String getParameter(String parameter);
}
