package com.epam.jwd.web.servlet.command;

public interface Command {
    ResponseContext execute(RequestContent req);

    static Command of(String name) {
       return CommandManager.of(name);
    }
}
