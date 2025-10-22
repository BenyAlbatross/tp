package internity.commands;

import internity.core.InternityException;
import internity.core.InternshipList;

import java.util.logging.Logger;

/**
 * Represents a command that lists all internship applications
 * as a formatted table
 * <br>
 * Command format:
 * {@code list [sort/asc|sort/desc]}
 */
public class ListCommand extends Command {
    public enum orderType {
        DEFAULT,
        ASCENDING,
        DESCENDING
    }

    private static final Logger logger = Logger.getLogger(ListCommand.class.getName());
    orderType order;

    public ListCommand(orderType o) {
        order = o;
    }

    /**
     * Executes the {@code list} command.
     * <p>
     * Prints a formatted table with all internship applications.
     * </p>
     */
    @Override
    public void execute() throws InternityException {
        logger.info("Executing list command");
        InternshipList.listAll(order);
        logger.info("List command executed successfully.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
