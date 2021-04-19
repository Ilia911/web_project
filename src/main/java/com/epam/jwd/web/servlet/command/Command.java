package com.epam.jwd.web.servlet.command;

/**
 * An object that evaluates command and does logic on server
 *
 * @author Ilia Eriomkin
 */
public interface Command {
    /**
     * Do some logic for the specific command
     *
     * @param req {@link RequestContent} object.
     * @return {@link ResponseContext} object.
     */
    ResponseContext execute(RequestContent req);

    /**
     * Evaluates command and returns command object that contains
     * specific logic
     *
     * @param name the name of the specific command.
     * @return command object.
     */
    static Command of(String name) {
        return CommandManager.of(name);
    }
}
