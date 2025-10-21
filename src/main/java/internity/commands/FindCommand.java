package internity.commands;

import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;
import internity.ui.Ui;

import java.util.logging.Logger;

public class FindCommand extends Command {
    private static final Logger logger = Logger.getLogger(FindCommand.class.getName());

    private final String keyword;

    /**
     * Constructs an {@code AddCommand} with the specified internship details.
     *
     * @param company  the name of the company offering the internship
     * @param role     the internship role or position
     * @param deadline the application deadline for the internship
     * @param pay      the monthly pay or stipend for the internship
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the add command by creating a new {@link Internship} object and
     * adding it to the global static {@link InternshipList}.
     * <p>
     * Once added, the {@link Ui} class is used to show a confirmation message.
     * </p>
     *
     * @throws InternityException if an error occurs.
     */
    @Override
    public void execute() throws InternityException {
        logger.info("Executing find command");
        InternshipList.findInternship(keyword);
        logger.info("Find command executed successfully.");
    }

    /**
     * This command will not terminate the application.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}