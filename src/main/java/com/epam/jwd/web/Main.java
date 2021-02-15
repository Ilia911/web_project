package com.epam.jwd.web;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.entity.ItemDto;
import com.epam.jwd.web.entity.Role;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
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
    private static final String TABLE_NAME = "auction_user";
    private static final String ID_COLUMN_NAME = "id";
    private static final String LOGIN_COLUMN_NAME = "user_login";
    private static final String PASSWORD_COLUMN_NAME = "user_password";
    private static final String NAME_COLUMN_NAME = "user_name";
    private static final String ACCOUNT_COLUMN_NAME = "user_account";
    private static final String MAIL_COLUMN_NAME = "user_mail";
    private static final String ROLE_COLUMN_NAME = "user_role";
    private static final String STATUS_COLUMN_NAME = "user_status";
    private static final String FIND_ALL_USERS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", " + ACCOUNT_COLUMN_NAME + ", "
            + MAIL_COLUMN_NAME + ", " + ROLE_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME;
    private static final String FIND_USER_BY_LOGIN_SQL = "SELECT " + ID_COLUMN_NAME + ", " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", " + ACCOUNT_COLUMN_NAME + ", "
            + MAIL_COLUMN_NAME + ", " + ROLE_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE " + LOGIN_COLUMN_NAME + " = ?";


    public static void main(String[] args) throws SQLException {

        final String password = BCrypt.hashpw("password", BCrypt.gensalt());
        System.out.println(password);
        System.out.println(BCrypt.checkpw("password", password));

        Role role = Role.of("3");
        System.out.println(role);

ConnectionPool.INSTANCE.init();
        System.out.println(UserService.INSTANCE.login("1111", "1111").isPresent());


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
