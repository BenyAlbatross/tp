package internity.commands;

import internity.core.InternshipList;
import internity.ui.Ui;

/**
 * Represents a command that updates the status of an internship application.
 * 
 * Command format:
 * update INDEX status/NEW_STATUS
 */
public class UpdateCommand extends Command {
    private final InternshipList internshipList;
    private final int index;
    private final String newStatus;

    public UpdateCommand(InternshipList internshipList, int index, String newStatus) {
        this.internshipList = internshipList;
        this.index = index;
        this.newStatus = newStatus;
    }

    @Override
    public void execute() {
        boolean success = internshipList.updateStatus(index, newStatus);
        if (success) {
            Ui.printUpdateInternship();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
