package com.epam.jwd.web.servlet.controller;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.WrappingRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller")
public class ApplicationController extends HttpServlet {

    private static final String COMMAND_PARAMETER_NAME = "command";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);

    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        final Command command = Command.of(commandName);
        final ResponseContext responseContext = command.execute(WrappingRequestContext.of(request));
        if (responseContext.isRedirect()) {
            response.sendRedirect("/index.jsp");
        } else {
            request.getRequestDispatcher(responseContext.getPage());
        }


    }
}
