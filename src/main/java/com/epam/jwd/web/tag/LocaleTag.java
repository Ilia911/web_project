package com.epam.jwd.web.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleTag extends TagSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleTag.class);
    private String value;

    public void setValue(String value){
        this.value= value;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Locale locale = (Locale) session.getAttribute("locale");


        if (value!=null){
            ResourceBundle rb = ResourceBundle.getBundle("generalKeys", locale);
            JspWriter out = pageContext.getOut();
            try {
                out.println(rb.getString(value));
            } catch (IOException e) {
                LOGGER.error("Invalid Key for locale!");
            }
        }
        return SKIP_BODY;
    }
}
