package com.epam.jwd.web.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;

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



    }





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
