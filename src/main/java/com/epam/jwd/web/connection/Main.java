package com.epam.jwd.web.connection;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {


    public static void main(String[] args) throws SQLException, InterruptedException {

        Locale chinalocale = new Locale("zh", "TW");
        Locale germanyLocale = Locale.GERMAN;

        final long timeInMillis = GregorianCalendar.getInstance().getTimeInMillis();

        final Calendar instance = GregorianCalendar.getInstance(chinalocale);
        System.out.println(instance.getTime());
        final Calendar instance2 = GregorianCalendar.getInstance(germanyLocale);
      instance2.setTimeInMillis(timeInMillis -200000);
        System.out.println(instance2.getTime());
        Calendar cal = new GregorianCalendar();
        final Locale english = new Locale("en", "US");

        final ResourceBundle bundle = ResourceBundle.getBundle("generalKeys", Locale.CANADA);
        System.out.println(bundle.getString("page.main.greeting"));

        final LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear());

    }





//
//        System.out.println(Locale.getDefault());
//
//        System.out.println(Locale.getDefault());
//        Locale.setDefault(chinalocale);
//        System.out.println(chinalocale.getDisplayLanguage());
//        System.out.println(bundle.getString("main.greeting"));
//
//        NumberFormat numberFormat = NumberFormat.getInstance(germanyLocale);


}
