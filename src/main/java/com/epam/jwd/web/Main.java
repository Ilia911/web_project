package com.epam.jwd.web;


import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ProviderDAO;
import com.epam.jwd.web.dao.UserDAO;
import com.epam.jwd.web.dao.exeption.UserDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
@WebServlet
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws SQLException, UserDAOException {

        Locale chinalocale = new Locale("zh", "TW");
        Locale germanyLocale = Locale.GERMAN;

        ConnectionPool.INSTANCE.init();
        UserDAO userDAO = ProviderDAO.getInstance().getUserDAO();
        final boolean isAuthorized = userDAO.authorization("Ilia", "321");
        System.out.println("Is user with login = \"Ilia\" and password = \"321\" exist? " + isAuthorized);

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
