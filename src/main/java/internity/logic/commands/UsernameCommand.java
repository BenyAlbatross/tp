package internity.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import internity.core.InternshipList;
import internity.ui.Ui;


/**
 * Represents the {@code username} command, which sets the username.
 *
 * <p>
 * This command allows the user to specify their preferred username, which is stored
 * in the {@link InternshipList} for use throughout the session (and can be persisted
 * to storage when the data is saved).
 * </p>
 *
 * <p>Command format: {@code username USERNAME}</p>
 *
 */
public class UsernameCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(UsernameCommand.class.getName());
    private final String username;

    /**
     * Constructs a {@code UsernameCommand} with the specified username.
     *
     * @param args The username string provided by the user input.
     *             This value is stored and later used to update the application state.
     */
    public UsernameCommand(String args) {
        assert args != null : "Username argument must not be null";
        assert !args.trim().isEmpty() : "Username argument must not be empty";

        username = args;
        LOGGER.log(Level.FINE, "UsernameCommand created with username: {0}", username);
    }

    /**
     * Executes the {@code username} command.
     *
     * <p>
     * This method sets Internity's current username to the provided value,
     * updates it in the {@link InternshipList}, and prints a confirmation message
     * to the user interface.
     * </p>
     */
    @Override
    public void execute() {
        assert username != null && !username.isEmpty()
                : "Username must be non-null and non-empty before execution";
        LOGGER.log(Level.INFO, "Executing UsernameCommand: setting username to {0}", username);
        InternshipList.setUsername(username);
        Ui.printSetUsername(username);
        assert username.equals(InternshipList.getUsername())
                : "Username was incorrectly set in InternshipList";
        LOGGER.log(Level.FINE, "Username successfully set in InternshipList and printed to UI");
    }

    /**
     * Indicates that this command does not terminate the program.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        LOGGER.log(Level.FINEST, "isExit() called on UsernameCommand");
        return false;
    }
}
