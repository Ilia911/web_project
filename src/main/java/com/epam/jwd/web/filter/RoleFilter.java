package com.epam.jwd.web.filter;

import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.UserStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "baseRole", value = "3")
        })
public class RoleFilter implements Filter {

    private String role;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        role = filterConfig.getInitParameter("baseRole");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getSession(false) == null || request.getSession().getAttribute("name") == null) {
            final HttpSession session = request.getSession();
            session.setAttribute("name", "Guest");
            session.setAttribute("role", Role.of(role));
            session.setAttribute("status", UserStatus.VALID);
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
