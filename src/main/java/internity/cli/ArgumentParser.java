package internity.cli;

import internity.commands.AddCommand;
import internity.commands.DeleteCommand;
import internity.commands.UpdateCommand;
import internity.core.Date;
import internity.core.InternityException;
import internity.utils.DateFormatter;

public final class ArgumentParser {
    private ArgumentParser() {
    } // prevent instantiation

    public static AddCommand parseAddCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidAddCommand();
        }

        try {
            String[] parts = args.split("\\s+(?=company/|role/|deadline/|pay/)");

            if (parts.length != 4 ||
                    !parts[0].startsWith("company/") ||
                    !parts[1].startsWith("role/") ||
                    !parts[2].startsWith("deadline/") ||
                    !parts[3].startsWith("pay/")) {
                throw InternityException.invalidAddCommand();
            }

            String company = parts[0].substring("company/".length()).trim();
            String role = parts[1].substring("role/".length()).trim();
            Date deadline = DateFormatter.parse(parts[2].substring("deadline/".length()).trim());
            int pay = Integer.parseInt(parts[3].substring("pay/".length()).trim());
            return new AddCommand(company, role, deadline, pay);
        } catch (Exception e){
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
