package com.epam.jwd.web.servlet.command;

import com.epam.jwd.web.servlet.command.lot.DoBidCommand;
import com.epam.jwd.web.servlet.command.item.RegisterItemCommand;
import com.epam.jwd.web.servlet.command.item.SaveEditedItemCommand;
import com.epam.jwd.web.servlet.command.item.UnblockItemCommand;
import com.epam.jwd.web.servlet.command.page.ShowAllLotsCommand;
import com.epam.jwd.web.servlet.command.page.ShowBlockedItemsCommand;
import com.epam.jwd.web.servlet.command.page.ShowUserEditItemCommand;
import com.epam.jwd.web.servlet.command.page.ShowUserItemsCommand;
import com.epam.jwd.web.servlet.command.page.ShowEditProfileCommand;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;
import com.epam.jwd.web.servlet.command.page.ShowProfileCommand;
import com.epam.jwd.web.servlet.command.page.ShowRegisterItemCommand;
import com.epam.jwd.web.servlet.command.page.ShowUserLoginPageCommand;
import com.epam.jwd.web.servlet.command.page.ShowUserRegisterPageCommand;
import com.epam.jwd.web.servlet.command.page.ShowUsersCommand;
import com.epam.jwd.web.servlet.command.user.ChangeStatusCommand;
import com.epam.jwd.web.servlet.command.user.ChooseLocaleCommand;
import com.epam.jwd.web.servlet.command.user.LogInCommand;
import com.epam.jwd.web.servlet.command.user.LogOutCommand;
import com.epam.jwd.web.servlet.command.user.RegisterCommand;
import com.epam.jwd.web.servlet.command.user.SaveEditedUserCommand;

public enum CommandManager {
    LOG_IN(LogInCommand.INSTANCE),
    LOG_OUT(LogOutCommand.INSTANCE),
    REGISTER(RegisterCommand.INSTANCE),
    CHANGE_USER_STATUS(ChangeStatusCommand.INSTANCE),
    REGISTER_ITEM(RegisterItemCommand.INSTANCE),
    SAVE_EDITED_ITEM(SaveEditedItemCommand.INSTANCE),
    SAVE_EDITED_USER(SaveEditedUserCommand.INSTANCE),
    UNBLOCK_ITEM(UnblockItemCommand.INSTANCE),
    DO_BID(DoBidCommand.INSTANCE),
    CHOOSE_LOCALE(ChooseLocaleCommand.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE),
    SHOW_LOTS(ShowAllLotsCommand.INSTANCE),
    SHOW_BLOCKED_ITEMS(ShowBlockedItemsCommand.INSTANCE),
    SHOW_USER_ITEMS(ShowUserItemsCommand.INSTANCE),
    SHOW_USER_EDIT_ITEM(ShowUserEditItemCommand.INSTANCE),
    SHOW_USERS(ShowUsersCommand.INSTANCE),
    SHOW_PROFILE(ShowProfileCommand.INSTANCE),
    SHOW_EDIT_PROFILE(ShowEditProfileCommand.INSTANCE),
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
