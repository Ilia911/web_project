package com.epam.jwd.web.servlet.command;

import javax.servlet.http.HttpServletRequest;

/**
 * A factory that creates {@link RequestContent} and extracts data from
 * {@link javax.servlet.http.HttpServletRequest} object and can do some logic.
 *
 * @author Ilia Eriomkin
 */

public class SessionRequestContentFactory {

    private SessionRequestContentFactory() {
    }

    /**
     * Extracts data from {@link javax.servlet.http.HttpServletRequest} object.
     *
     * @param request {@link javax.servlet.http.HttpServletRequest} object.
     * @return {@link RequestContent} with all data from {@link javax.servlet.http.HttpServletRequest} object.
     */
    public static SessionRequestContent defineContent(HttpServletRequest request) {
        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        sessionRequestContent.extractValues(request);
        return sessionRequestContent;
    }
}
