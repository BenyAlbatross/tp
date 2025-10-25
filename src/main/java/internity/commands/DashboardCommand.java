package internity.commands;

import internity.core.InternityException;
import internity.ui.DashboardUI;

public class DashboardCommand extends Command {
    @Override
    public void execute() throws InternityException {
        DashboardUI.printDashboard();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
