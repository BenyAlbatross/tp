package internity.cli;

import internity.commands.AddCommand;
import internity.commands.DeleteCommand;
import internity.commands.ListCommand;
import internity.commands.UpdateCommand;
import internity.core.Date;
import internity.core.InternityException;
import internity.core.InternshipList;
import internity.utils.DateFormatter;

import java.util.logging.Logger;

/**
 * A utility class responsible for parsing command-line arguments for various commands
 * such as Add, Delete, Find, Update and List.
 */
public final class ArgumentParser {
    static final int IDX_COMPANY = 0;
    static final int IDX_ROLE = 1;
    static final int IDX_DEADLINE = 2;
    static final int IDX_PAY = 3;

    private static final Logger logger = Logger.getLogger(ArgumentParser.class.getName());

    /**
     * Private constructor to prevent instantiation of the ArgumentParser class.
     */
    private ArgumentParser() {
    }

    /**
     * Parses the arguments for Add Command to create an {@link AddCommand} instance.
     *
     * @param args arguments for {@link AddCommand}
     * @return an instance of {@link AddCommand} constructed from the parsed arguments.
     * @throws InternityException if the arguments are missing or invalid.
     */
    public static AddCommand parseAddCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidAddCommand();
        }

        assert !args.isBlank() : "Arguments cannot be blank after validation";

        try {
            String[] parts = args.split("\\s+(?=company/|role/|deadline/|pay/)");
            if (parts.length != 4 ||
                    !parts[IDX_COMPANY].startsWith("company/") ||
                    !parts[IDX_ROLE].startsWith("role/") ||
                    !parts[IDX_DEADLINE].startsWith("deadline/") ||
                    !parts[IDX_PAY].startsWith("pay/")) {
                logger.severe("One or more arguments of Add command is in the wrong order.");
                throw InternityException.invalidAddCommand();
            }

            logger.info("Successfully parsed 4 arguments of AddCommand.");

            String company = parts[IDX_COMPANY].substring("company/".length()).trim();
            String role = parts[IDX_ROLE].substring("role/".length()).trim();
            Date deadline = DateFormatter.parse(parts[IDX_DEADLINE].substring("deadline/".length()).trim());
            int pay = Integer.parseInt(parts[IDX_PAY].substring("pay/".length()).trim());

            // throw exception on empty input or invalid pay
            if (company.isEmpty() || role.isEmpty() || pay < 0) {
                logger.severe("One or more arguments of Add command is empty or invalid.");
                throw InternityException.invalidAddCommand();
            }

            // throw exception on exceeding max length
            if (company.length() > InternshipList.COMPANY_MAXLEN ||
                    role.length() > InternshipList.ROLE_MAXLEN) {
                logger.severe("One or more arguments exceeded max length.");
                throw InternityException.invalidAddCommand();
            }

            return new AddCommand(company, role, deadline, pay);
        } catch (Exception e) {
            logger.severe("Error executing Add Command");
            throw InternityException.invalidAddCommand();
        }
    }

    /**
     * Parses the arguments for Delete Command to create an {@link DeleteCommand} instance.
     *
     * @param args arguments for {@link DeleteCommand}
     * @return an instance of {@link DeleteCommand} constructed from the parsed arguments.
     * @throws InternityException if the arguments are missing or invalid.
     */
    public static DeleteCommand parseDeleteCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidDeleteCommand();
        }
        try {
            int oneBasedIndex = Integer.parseInt(args.trim());

            int zeroBasedIndex = oneBasedIndex - 1;

            return new DeleteCommand(zeroBasedIndex);

        } catch (NumberFormatException e) {
            throw InternityException.invalidInternshipIndex();
        }
    }

    /**
     * Parses the arguments for Update Command to create an {@link UpdateCommand} instance.
     *
     * @param args arguments for {@link UpdateCommand}
     * @return an instance of {@link UpdateCommand} constructed from the parsed arguments.
     * @throws InternityException if the arguments are missing or invalid.
     */
    public static UpdateCommand parseUpdateCommandArgs(String args) throws InternityException {
        String newStatus;
        int zeroBasedIndex;
        try {
            String[] splitArgs = args.split("\\s+status/");
            zeroBasedIndex = Integer.parseInt(splitArgs[0].trim()) - 1;
            newStatus = splitArgs[1].trim();
        } catch (Exception e) {
            throw new InternityException("Invalid update command format. Use: update INDEX status/NEW_STATUS");
        }
        return new UpdateCommand(zeroBasedIndex, newStatus);
    }

    /**
     * Parses the arguments for List Command to create an {@link ListCommand} instance.
     *
     * @param args arguments for {@link ListCommand}
     * @return an instance of ListCommand constructed from the parsed arguments.
     *      Returns a default ListCommand if no arguments are provided.
     * @throws InternityException if the arguments are missing or invalid.
     */
    public static ListCommand parseListCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            return new ListCommand(ListCommand.orderType.DEFAULT); // Default order
        }

        if (!args.startsWith("sort/")) {
            throw InternityException.invalidListCommand();
        }

        String[] splitArgs = args.split("\\s+sort/");
        if (splitArgs.length > 1) {
            throw InternityException.invalidListCommand();
        }

        String order = splitArgs[0].substring("sort/".length()).trim();
        if (order.equals("asc")) {
            return new ListCommand(ListCommand.orderType.ASCENDING);
        } else if (order.equals("desc")) {
            return new ListCommand(ListCommand.orderType.DESCENDING);
        } else {
            throw InternityException.invalidListCommand();
        }
    }
}
