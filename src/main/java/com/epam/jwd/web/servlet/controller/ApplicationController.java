package com.epam.jwd.web.servlet.controller;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.SessionRequestContentFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller in model MVC
 *
 * @author Ilia Eriomkin
 *
 */

@WebServlet("/controller")
public class ApplicationController extends HttpServlet {

    private static final String COMMAND_PARAMETER_NAME = "command";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doAction(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doAction(req, resp);
    }
    /**
     * Put all data from {@link javax.servlet.http.HttpServletRequest} into auxiliary safe object
     * {@link com.epam.jwd.web.servlet.command.RequestContent} and exercise command.
     *
     * @param   request  input data.
     * @param response  returned data.
     */

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        final Command command = Command.of(commandName);
        final RequestContent sessionRequestContent = SessionRequestContentFactory.defineContent(request);
        final ResponseContext responseContext = command.execute(sessionRequestContent);
        sessionRequestContent.insertAttributes(request);
        if (responseContext.isRedirect()) {
            response.sendRedirect(request.getContextPath() + responseContext.getPage());
        } else {
            request.getRequestDispatcher(responseContext.getPage()).forward(request, response);
        }
    }
}
