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

public final class ArgumentParser {
    private static final Logger logger = Logger.getLogger(ArgumentParser.class.getName());

    private ArgumentParser() {
    } // prevent instantiation

    public static AddCommand parseAddCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidAddCommand();
        }

        assert !args.isBlank() : "Arguments cannot be blank after validation";

        try {
            String[] parts = args.split("\\s+(?=company/|role/|deadline/|pay/)");
            assert parts.length == 4 : "AddCommand should have exactly 4 arguments.";
            if (!parts[0].startsWith("company/") ||
                    !parts[1].startsWith("role/") ||
                    !parts[2].startsWith("deadline/") ||
                    !parts[3].startsWith("pay/")) {
                logger.severe("One or more arguments of Add command is in the wrong order.");
                throw InternityException.invalidAddCommand();
            }

            logger.info("Successfully parsed 4 arguments of AddCommand.");

            String company = parts[0].substring("company/".length()).trim();
            String role = parts[1].substring("role/".length()).trim();
            Date deadline = DateFormatter.parse(parts[2].substring("deadline/".length()).trim());
            int pay = Integer.parseInt(parts[3].substring("pay/".length()).trim());

            // throw exception on empty input
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
