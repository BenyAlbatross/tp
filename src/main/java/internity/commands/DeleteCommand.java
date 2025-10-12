package internity.commands;

import internity.core.Internship;
import internity.core.InternityException;
import internity.core.InternshipList;
import internity.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws InternityException {
        // Get the internship before deleting to display its info
        Internship internship = InternshipList.get(index);
        String internshipInfo = internship.getCompany() + " - " + internship.getRole();

        // Delete the internship
        InternshipList.delete(index);

        // Get the new size after deletion
        int totalItems = InternshipList.size();

        // Print the removal message
        Ui.printRemoveInternship(internshipInfo, totalItems);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}




