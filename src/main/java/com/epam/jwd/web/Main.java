package com.epam.jwd.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {

        Locale chinalocale = new Locale("zh", "TW");
        Locale germanyLocale = Locale.GERMAN;
        int a = 7, b = 10;
        double c = (double)a/b;
        System.out.println("c = " + c);

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
