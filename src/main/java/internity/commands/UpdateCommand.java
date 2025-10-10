package internity.commands;

import internity.core.InternityException;
import internity.core.InternshipList;
import internity.ui.Ui;

/**
 * Represents a command that updates the status of an internship application.
 *
 * Command format:
 * update INDEX status/NEW_STATUS
 */
public class UpdateCommand extends Command {
    private final int index;
    private final String newStatus;

    /**
     * Constructs an UpdateCommand from a raw argument string.
     * Example: "1 status/Accepted"
     */
    public UpdateCommand(String args) throws InternityException {
        try {
            String[] splitArgs = args.split("\\s+status/");
            this.index = Integer.parseInt(splitArgs[0].trim()) - 1;
            this.newStatus = splitArgs[1].trim();
        } catch (Exception e) {
            throw new InternityException("Invalid update command format. Use: update INDEX status/NEW_STATUS");
        }
    }

    @Override
    public void execute() {
        boolean success = InternshipList.updateStatus(index, newStatus);
        if (success) {
            Ui.printUpdateInternship();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
