package internity.commands;

import internity.core.InternityException;
import internity.ui.DashboardUi;

/**
 * Represents the {@code dashboard} command, which displays the dashboard.
 * <p>
 * When executed, this command prints the user dashboard to the console,
 * showing the current user's information, total internships, nearest deadline,
 * and a breakdown of internships by status.
 * </p>
 *
 * <p>Command format: {@code dashboard}</p>
 *
 */
public class DashboardCommand extends Command {
    /**
     * Executes the dashboard command by printing the dashboard UI.
     *
     * @throws InternityException if an error occurs while accessing internship data
     */
    @Override
    public void execute() throws InternityException {
        DashboardUi.printDashboard();
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
