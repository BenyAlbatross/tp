package internity.logic.commands;

import internity.core.InternityException;

/**
 * Represents an abstract command in the system. <br>
 * Each user instruction (e.g. {@code add}, {@code delete}, {@code list})
 * is encapsulated as a {@code Command} object that defines specific behavior.
 *
 * <p>Subclasses must implement:
 * <ul>
 *     <li>{@link #execute()} - performs the action defined by the command</li>
 *     <li>{@link #isExit()} - indicates whether the command ends the program</li>
 * </ul>
 * </p>
 *
 * <p>This design follows the <b>Command Pattern</b>,
 * allowing user inputs to be decoupled from their execution logic.</p>
 */
public abstract class Command {
    /**
     * Executes the action associated with this command.
     */
    public abstract void execute() throws InternityException;

    /**
     * Determines whether this command signals program termination.
     *
     * @return {@code true} if the command exits the program, {@code false} otherwise
     */
    public abstract boolean isExit();
}
