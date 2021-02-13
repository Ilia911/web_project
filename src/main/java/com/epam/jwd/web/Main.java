package com.epam.jwd.web;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.entity.ItemDto;
import com.epam.jwd.web.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@WebServlet
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws SQLException {

//        Locale chinalocale = new Locale("zh", "TW");
//        Locale germanyLocale = Locale.GERMAN;

        ConnectionPool.INSTANCE.init();
        ItemService service = new ItemService(new ItemDaoImpl());
        final Optional<List<ItemDto>> all = service.findAll();
        if (all.isPresent()) {
            for (ItemDto itemDto : all.get()) {
                System.out.println(itemDto);
            }
        }

//        UserDAO userDAO = ProviderDao.getInstance().getUserDAO();
//        final boolean isAuthorized = userDAO.authorization("Ivan Ivanov", "123");
//        System.out.println("Is user with login = \"Ilia\" and password = \"321\" exist? " + isAuthorized);

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
