package com.epam.jwd.web;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {



    public static void main(String[] args) {
        Date date = new Date();
        long timeInMilliseconds = date.getTime();
        Date newDate = new Date(timeInMilliseconds);
        System.out.println(newDate);

        System.out.println(date.toString());
        System.out.println(date.getTime());
        System.out.println(GregorianCalendar.getInstance().getTimeInMillis());


//        Locale chinalocale = new Locale("zh", "TW");
//        Locale germanyLocale = Locale.GERMAN;


//
//        System.out.println(Locale.getDefault());
//        Locale.setDefault(Locale.CANADA);
//        System.out.println(Locale.getDefault());
//        final Locale current = new Locale("be", "BY");
//        Locale.setDefault(chinalocale);
//        System.out.println(chinalocale.getDisplayLanguage());
//        final ResourceBundle bundle = ResourceBundle.getBundle("generalKeys", chinalocale);
//        System.out.println(bundle.getString("main.greeting"));
//
//        NumberFormat numberFormat = NumberFormat.getInstance(germanyLocale);


    }

}
