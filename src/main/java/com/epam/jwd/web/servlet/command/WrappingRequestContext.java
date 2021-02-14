package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WrappingRequestContext implements RequestContext {

    private final HttpServletRequest request;

    private WrappingRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String name, Object obj) {
        request.setAttribute(name, obj);
    }

    @Override
    public Object getAttribute(String parameter) {
        return request.getAttribute(parameter);
    }

    @Override
    public void invalidateSession() {
        request.getSession().invalidate();
    }

    @Override
    public HttpSession getSession() {
        return request.getSession();
    }

    @Override
    public String getParameter(String parameter) {
        return request.getParameter(parameter);
    }

    public static RequestContext of(HttpServletRequest request) {
        return new WrappingRequestContext(request);
    }
}
