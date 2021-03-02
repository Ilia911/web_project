package com.epam.jwd.web.servlet.command;

import com.epam.jwd.web.servlet.command.item.RegisterItemCommand;
import com.epam.jwd.web.servlet.command.page.ShowAllLotsCommand;
import com.epam.jwd.web.servlet.command.page.ShowBlockedItems;
import com.epam.jwd.web.servlet.command.page.ShowCompletedItems;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;
import com.epam.jwd.web.servlet.command.page.ShowProfile;
import com.epam.jwd.web.servlet.command.page.ShowRegisterItemCommand;
import com.epam.jwd.web.servlet.command.page.ShowUserLoginPageCommand;
import com.epam.jwd.web.servlet.command.page.ShowUserRegisterPageCommand;
import com.epam.jwd.web.servlet.command.page.ShowUsers;
import com.epam.jwd.web.servlet.command.user.ChooseLocaleCommand;
import com.epam.jwd.web.servlet.command.user.LogInCommand;
import com.epam.jwd.web.servlet.command.user.LogOutCommand;
import com.epam.jwd.web.servlet.command.user.RegisterCommand;

public enum CommandManager {
    LOG_IN(LogInCommand.INSTANCE),
    LOG_OUT(LogOutCommand.INSTANCE),
    REGISTER(RegisterCommand.INSTANCE),
    REGISTER_ITEM(RegisterItemCommand.INSTANCE),
    CHOOSE_LOCALE(ChooseLocaleCommand.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE),
    SHOW_LOTS(ShowAllLotsCommand.INSTANCE),
    SHOW_BLOCKED_ITEMS(ShowBlockedItems.INSTANCE),
    SHOW_COMPLETED_ITEMS(ShowCompletedItems.INSTANCE),
    SHOW_USERS(ShowUsers.INSTANCE),
    SHOW_PROFILE(ShowProfile.INSTANCE),
    SHOW_LOGIN(ShowUserLoginPageCommand.INSTANCE),
    SHOW_REGISTER(ShowUserRegisterPageCommand.INSTANCE),
    SHOW_REGISTER_ITEM(ShowRegisterItemCommand.INSTANCE);


    private final Command command;

    CommandManager(Command command){
        this.command = command;
    }

    public static Command of(String name) {
        for (CommandManager value : CommandManager.values()) {
            if(value.name().equalsIgnoreCase(name)) {
                return value.command;
            }
        }
        return DEFAULT.command;
    }
}
