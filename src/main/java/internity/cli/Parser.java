package internity.cli;

import internity.commands.ExitCommand;
import internity.commands.Command;

/**
 * Parses raw user input into executable {@link Command} objects. <br>
 * The {@code Parser} is responsible for identifying the command keyword,
 * validating the input and constructing the instance of the
 * corresponding command.
 *
 * <p>Supported commands include:
 * <ul>
 *      <li>{@code exit} - exits the program</li>
 */
public class Parser {
    public Command parseInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or blank");
        }

        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        return switch (command) {
            case "exit" -> new ExitCommand();
            default -> throw new IllegalStateException("Unknown command: " + command);
        };
    }
}
