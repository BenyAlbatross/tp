package internity.commands;

import internity.core.InternityException;
import internity.ui.DashboardUi;

public class DashboardCommand extends Command {
    @Override
    public void execute() throws InternityException {
        DashboardUi.printDashboard();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
