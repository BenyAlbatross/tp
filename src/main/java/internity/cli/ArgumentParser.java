package internity.cli;

import internity.commands.AddCommand;
import internity.commands.DeleteCommand;
import internity.commands.UpdateCommand;
import internity.core.Date;
import internity.core.InternityException;
import internity.utils.DateFormatter;

public final class ArgumentParser {
    private ArgumentParser() {} // prevent instantiation

    public static AddCommand parseAddCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidAddCommand();
        }

        String company;
        String role;
        Date deadline;
        int pay;

        try {
            String[] parts = args.split("\\s+(?=company/|role/|deadline/|pay/)");
            company = parts[0].substring(parts[0].indexOf("/") + 1).trim();
            role = parts[1].substring(parts[1].indexOf("/") + 1).trim();
            deadline = DateFormatter.parse(parts[2].substring(parts[2].indexOf("/") + 1).trim());
            pay = Integer.parseInt(parts[3].substring(parts[3].indexOf("/") + 1).trim());
            return new AddCommand(company, role, deadline, pay);
        } catch (Exception e) {
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
}
