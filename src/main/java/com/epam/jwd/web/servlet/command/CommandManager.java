package com.epam.jwd.web.servlet.command;

public enum CommandManager {
    LOG_IN(LogInCommand.INSTANCE),
    LOG_OUT(LogOutCommand.INSTANCE),
    DEFAULT(DefaultCommand.INSTANCE);

    private final Command command;

    private CommandManager(Command command){
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
