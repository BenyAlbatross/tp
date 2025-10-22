package internity.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import internity.core.InternshipList;
import internity.ui.Ui;

public class UsernameCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(UsernameCommand.class.getName());
    private final String username;

    public UsernameCommand(String args) {
        username = args;
        LOGGER.log(Level.FINE, "UsernameCommand created with username: {0}", username);
    }

    @Override
    public void execute() {
        LOGGER.log(Level.INFO, "Executing UsernameCommand: setting username to {0}", username);
        InternshipList.setUsername(username);
        Ui.printSetUsername(username);
        LOGGER.log(Level.FINE, "Username successfully set in InternshipList and printed to UI");
    }

    @Override
    public boolean isExit() {
        LOGGER.log(Level.FINEST, "isExit() called on UsernameCommand");
        return false;
    }
}
