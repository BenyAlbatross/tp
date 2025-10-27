package internity.logic.commands;

import java.util.logging.Logger;

import internity.core.InternityException;
import internity.core.InternshipList;

/**
 * Represents a command that allows the user to find internships with
 * matching company or role names based on a keyword.
 *
 * <p>
 * The command parses a user input string in the following format:
 * <pre>
 *     find KEYWORD
 * </pre>
 * Example:
 * <pre>
 *     find Google
 *     find Software Engineer
 * </pre>
 * </p>
 */
public class FindCommand extends Command {
    private static final Logger logger = Logger.getLogger(FindCommand.class.getName());

    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword to search for internships.
     *
     * @param keyword the keyword used to search for matching internships.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for internships in the {@link InternshipList}
     * that match the provided keyword.
     * <p>
     * The command uses the {@link InternshipList} to filter
     * the internships based on the given keyword.
     * </p>
     *
     * @throws InternityException if an error occurs while executing the find command.
     */
    @Override
    public void execute() throws InternityException {
        logger.info("Executing find command");
        InternshipList.findInternship(keyword);
        logger.info("Find command executed successfully.");
    }

    /**
     * Indicates that this command does not terminate the program.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
