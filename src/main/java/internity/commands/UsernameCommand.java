package internity.commands;

import internity.core.InternshipList;

public class UsernameCommand extends Command {
    private String username;

    public UsernameCommand(String args) {
        username = args;
    }

    @Override
    public void execute() {
        InternshipList.setUsername(username);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
