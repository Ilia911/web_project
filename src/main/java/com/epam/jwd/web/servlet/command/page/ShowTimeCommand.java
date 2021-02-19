package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.GregorianCalendar;

public enum ShowTimeCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "WEB-INF/jsp/time_result.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContent request) {

        GregorianCalendar gc = new GregorianCalendar();
        String timeJsp = (String) request.getAttribute("time");
        float delta = ((float)(gc.getTimeInMillis() - Long.parseLong(timeJsp)))/1_000;
        request.setAttribute("res", delta);
        return RESPONSE;
    }
}
