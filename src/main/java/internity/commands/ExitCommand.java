package internity.commands;

/**
 * Represents the {@code exit} command, which terminates the program.
 *
 * <p>
 * When executed, it prints an exit message and signals that the
 * program should exit.
 * </p>
 * <p>Usage: {@code exit</p>
 */
public class ExitCommand extends Command {
    /**
     * Executes the {@code exit} command.
     * <p>
     * Prints an exit message.
     * </p>
     */
    @Override
    public void execute() {
        System.out.println("Thank you for using Internity! Goodbye!");
    }

    /**
     * Indicates that this command terminates the program
     *
     * @return always {@code true}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
