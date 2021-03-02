package com.epam.jwd.web.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class TimeTag extends TagSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleTag.class);
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Locale locale = (Locale) session.getAttribute("locale");


        if (value != null) {
            int[] timeArr = parseMilliseconds(Long.parseLong(value));

            ResourceBundle rb = ResourceBundle.getBundle("generalKeys", locale);

            Formatter result = new Formatter();
            result.format(rb.getString("time.format"), timeArr[0], timeArr[1], timeArr[2], timeArr[3]);

            JspWriter out = pageContext.getOut();
            try {
                out.println(result);
            } catch (IOException e) {
                LOGGER.error(Arrays.toString(e.getStackTrace()));
            }
        }
        return SKIP_BODY;
    }

    private int[] parseMilliseconds(long endTime) {

        long availableTime = endTime - GregorianCalendar.getInstance().getTimeInMillis();

        int days = (int) (availableTime / (24 * 60 * 60 * 1000));
        availableTime %= (24 * 60 * 60 * 1000);

        int hours = (int) availableTime / (60 * 60 * 1000);
        availableTime %= (60 * 60 * 1000);

        int minutes = (int) availableTime / (60 * 1000);

        int seconds = (int) availableTime % (60 * 1000) / 1000;

        return new int[]{days, hours, minutes, seconds};
    }
}
