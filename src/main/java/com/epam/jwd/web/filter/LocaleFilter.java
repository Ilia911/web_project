package com.epam.jwd.web.filter;

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
import java.util.Locale;

/**
 * This filter sets default locale.
 *
 * @author Ilia Eriomkin
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "locale", value = "en_US")
        })
public class LocaleFilter implements Filter {

    private String locale;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        locale = filterConfig.getInitParameter("locale");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getSession(false) == null || request.getSession().getAttribute("locale") == null) {
            final String[] localeParameters = locale.split("_");
            final HttpSession session = request.getSession();
            session.setAttribute("locale", new Locale(localeParameters[0], localeParameters[1]));
        }
        filterChain.doFilter(request, servletResponse);
    }
}
