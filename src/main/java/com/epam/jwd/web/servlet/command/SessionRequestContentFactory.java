package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpServletRequest;

public class SessionRequestContentFactory {
    public static SessionRequestContent defineContent(HttpServletRequest request) {
        SessionRequestContent sessionRequestContent= new SessionRequestContent();
        sessionRequestContent.extractValues(request);
        return sessionRequestContent;
    }
}
