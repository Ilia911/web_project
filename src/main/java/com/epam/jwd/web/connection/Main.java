package com.epam.jwd.web.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws SQLException, InterruptedException {

        final long timeInMillis = GregorianCalendar.getInstance().getTimeInMillis();

        Calendar cal = new GregorianCalendar();
        final Locale english = new Locale("en", "US");
        Locale.setDefault(english);


        long time = timeInMillis + Long.parseLong("432000000");
        final Calendar instance = new GregorianCalendar(english);
        instance.setTimeInMillis(time);
        System.out.println(instance.getTime());



    }
//    final Calendar instance = GregorianCalendar.getInstance(chinalocale);
//        System.out.println(instance.getTime());
//    final Calendar instance2 = GregorianCalendar.getInstance(germanyLocale);
//      instance2.setTimeInMillis(timeInMillis -200000);
//        System.out.println(instance2.getTime());
// ConnectionPool.INSTANCE.init();
//        LOGGER.info("Hello from main!");
//    Locale chinalocale = new Locale("zh", "TW");
//    Locale germanyLocale = Locale.GERMAN;



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
