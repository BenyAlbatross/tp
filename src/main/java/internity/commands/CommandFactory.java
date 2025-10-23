package internity.commands;

import internity.cli.ArgumentParser;
import internity.core.InternityException;

/**
 * A factory class responsible for creating {@link Command} objects
 * based on the user input command word and its arguments.
 * <p>
 * The {@code CommandFactory} delegates argument parsing to the
 * {@link ArgumentParser} and returns the corresponding {@link Command}
 * subclass instance for execution. If the provided command word does not
 * match any recognized command, an {@link InternityException} is thrown.
 * </p>
 *
 * <p>Supported commands include:</p>
 * <ul>
 *   <li>{@link AddCommand}</li>
 *   <li>{@link DeleteCommand}</li>
 *   <li>{@link UpdateCommand}</li>
 *   <li>{@link ListCommand}</li>
 *   <li>{@link UsernameCommand}</li>
 *   <li>{@link ExitCommand}</li>
 * </ul>
 */
public class CommandFactory {
    public Command createCommand(String commandWord, String args) throws InternityException {
        switch (commandWord) {
        case "add":
            return ArgumentParser.parseAddCommandArgs(args);
        case "delete":
            return ArgumentParser.parseDeleteCommandArgs(args);
        case "update":
            return ArgumentParser.parseUpdateCommandArgs(args);
        case "list":
            return ArgumentParser.parseListCommandArgs(args);
        case "username":
            return ArgumentParser.parseUsernameCommandArgs(args);
        case "exit":
            return new ExitCommand();
        default:
            throw InternityException.unknownCommand(commandWord);
        }
    }
}
