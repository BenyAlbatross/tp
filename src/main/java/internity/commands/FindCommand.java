package internity.commands;

import internity.core.Date;
import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;
import internity.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FindCommand extends Command {
    private static final Logger logger = Logger.getLogger(FindCommand.class.getName());

    private String keyword;

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

    private void findInternship() {
        ArrayList<Internship> matchingInternships = InternshipList.List.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.print("  " + (i + 1) + ".");
            System.out.println(matchingTasks.get(i).toString());
        }
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
        findInternship();
    }
        logger.info("Executing add command");
        Internship internship = new Internship(company, role, deadline, pay);
        InternshipList.add(internship);
        Ui.printAddInternship(internship);
        logger.info("Add command executed successfully.");
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