package com.epam.jwd.web;


import com.epam.jwd.web.util.reader.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final Properties properties = PropertyReader.INSTANCE.getProperties();


    public static void main(String[] args) {


        System.out.println(properties.getProperty("inputRootDir"));
        LOGGER.error("from main class");

        Locale chinalocale = new Locale("zh", "TW");
        Locale germanyLocale = Locale.GERMAN;

//
//        System.out.println(Locale.getDefault());
        Locale.setDefault(Locale.CANADA);
//        System.out.println(Locale.getDefault());
//        final Locale current = new Locale("be", "BY");
//        Locale.setDefault(chinalocale);
//        System.out.println(chinalocale.getDisplayLanguage());
        final ResourceBundle bundle = ResourceBundle.getBundle("generalKeys", chinalocale);
        System.out.println(bundle.getString("main.greeting"));

        NumberFormat numberFormat = NumberFormat.getInstance(germanyLocale);


    }


}
