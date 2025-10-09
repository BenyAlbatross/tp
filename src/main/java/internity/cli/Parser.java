package internity.cli;

import internity.commands.ExitCommand;
import internity.commands.Command;
import internity.core.InternityException;
import internity.commands.UpdateCommand;
import internity.core.InternshipList;

/**
 * Parses raw user input into executable {@link Command} objects. <br>
 * The {@code Parser} is responsible for identifying the command keyword,
 * validating the input and constructing the instance of the
 * corresponding command.
 *
 * <p>Supported commands include:
 * <ul>
 *      <li>{@code exit} - exits the program</li>
 * </ul>
 */
public class Parser {
    /**
     * Parses the given input string and returns the corresponding {@link Command}. <br>
     * The first token (before the first space) is treated as the command keyword.
     *
     * @param input raw user input
     * @return a {@link Command} corresponding to the input
     * @throws InternityException if input is null or blank or unknown command is entered
     */
    private final InternshipList internshipList;

    public Parser(InternshipList internshipList) {
        this.internshipList = internshipList;
    }

    public Command parseInput(String input) throws InternityException {
        if (input == null || input.isBlank()) {
            throw InternityException.invalidInput();
        }

        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "exit":
            return new ExitCommand();
        case "update":
            return parseUpdateCommand(args);
        default:
            throw InternityException.unknownCommand(command);
        }
    }

    private Command parseUpdateCommand(String args) throws InternityException {
        try {
            String[] splitArgs = args.split("\\s+status/");
            int index = Integer.parseInt(splitArgs[0].trim()) - 1; 
            String newStatus = splitArgs[1].trim();
            return new UpdateCommand(internshipList, index, newStatus);
        } catch (Exception e) {
            throw new InternityException("Invalid update command format. Use: update INDEX status/NEW_STATUS");
        }
    }
}
