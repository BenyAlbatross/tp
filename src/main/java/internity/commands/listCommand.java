package internity.commands;

import internity.core.InternityException;
import internity.core.InternshipList;

/**
 * Represents a command that lists all internship applications
 * as a formatted table
 * <br>
 * Command format:
 * {@code list}
 */
public class listCommand extends Command {
    /**
     * Executes the {@code list} command.
     * <p>
     * Prints a formatted table with all internship applications.
     * </p>
     */
    @Override
    public void execute() throws InternityException {
        InternshipList.listAll();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
