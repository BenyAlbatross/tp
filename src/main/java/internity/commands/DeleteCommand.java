package internity.commands;

import java.util.logging.Logger;

import internity.core.Internship;
import internity.core.InternityException;
import internity.core.InternshipList;
import internity.ui.Ui;

public class DeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws InternityException {
        logger.info("Executing delete command for index: " + index);

        // Get the internship before deleting to display its info
        Internship internship = InternshipList.get(index);

        String internshipInfo = internship.getCompany() + " - " + internship.getRole();
        // Assertion for internshipInfo not required here as strong concatenation will always be true
        logger.fine("Retrieved internship: " + internshipInfo);

        // Delete the internship
        InternshipList.delete(index);

        // Get the new size after deletion
        int totalItems = InternshipList.size();
        assert totalItems >= 0 : "Size cannot be negative after deletion";

        // Print the removal message
        Ui.printRemoveInternship(internshipInfo, totalItems);
        logger.info("Delete command executed successfully. Remaining items: " + totalItems);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}




