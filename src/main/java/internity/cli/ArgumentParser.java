package internity.cli;

import internity.commands.AddCommand;
import internity.commands.DeleteCommand;
import internity.commands.ListCommand;
import internity.commands.UpdateCommand;
import internity.core.Date;
import internity.core.InternityException;
import internity.core.InternshipList;
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

            // throw exception on empty input
            if (company.isEmpty() || role.isEmpty() || pay < 0) {
                throw InternityException.invalidAddCommand();
            }

            // throw exception on exceeding max length
            if (company.length() > InternshipList.COMPANY_MAXLEN ||
                    role.length() > InternshipList.ROLE_MAXLEN) {
                throw InternityException.invalidAddCommand();
            }

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
        if (args == null || args.isBlank()) {
            throw new InternityException("Invalid update command format. Use: update INDEX field/VALUE ...");
        }

        String trimmed = args.trim();

        int firstSpace = trimmed.indexOf(' ');
        if (firstSpace < 0) {
            throw new InternityException("Invalid update command format. Use: update INDEX field/VALUE ...");
        }
        String indexToken = trimmed.substring(0, firstSpace).trim();
        String tagged = trimmed.substring(firstSpace + 1).trim();

        int zeroBasedIndex;
        try {
            zeroBasedIndex = Integer.parseInt(indexToken) - 1; // 0-based
        } catch (NumberFormatException e) {
            throw new InternityException("Invalid index. Use a positive integer, for example: update 1 company/Google");
        }

        if (tagged.isBlank()) {
            throw new InternityException("Provide at least one field to update: company/, role/, deadline/, pay/, status/");
        }

        String[] parts = tagged.split("\\s+(?=company/|role/|deadline/|pay/|status/)");

        String company = null;
        String role = null;
        Date deadline = null;
        Integer pay = null;
        String status = null;

        try {
            for (String part : parts) {
                String p = part.trim();
                if (p.isEmpty()){
                    continue;
                }
                if (p.startsWith("company/")) {
                    company = p.substring("company/".length()).trim();
                    if (company.isEmpty()) {
                        throw new InternityException("company/ cannot be empty");
                    }
                } else if (p.startsWith("role/")) {
                    role = p.substring("role/".length()).trim();
                    if (role.isEmpty()) {
                        throw new InternityException("role/ cannot be empty");
                    }
                } else if (p.startsWith("deadline/")) {
                    String d = p.substring("deadline/".length()).trim();
                    deadline = DateFormatter.parse(d); // validates dd-MM-yyyy
                } else if (p.startsWith("pay/")) {
                    String payStr = p.substring("pay/".length()).trim();
                    int payVal = Integer.parseInt(payStr);
                    if (payVal < 0) {
                        throw new NumberFormatException();
                    }
                    pay = payVal;
                } else if (p.startsWith("status/")) {
                    status = p.substring("status/".length()).trim();
                    if (status.isEmpty()) {
                        throw new InternityException("status/ cannot be empty");
                    }
                } else {
                    throw new InternityException("Unknown update field in \"" + p + "\". Allowed: company, role, deadline, pay, status");
                }
            }
        } catch (NumberFormatException e) {
            throw new InternityException("Invalid pay. Use a whole number (example: pay/8000)");
        }

        if (company == null && role == null && deadline == null && pay == null && status == null) {
            throw new InternityException("Provide at least one field to update: company/, role/, deadline/, pay/, status/");
        }

        return new UpdateCommand(zeroBasedIndex, company, role, deadline, pay, status);
    }


    public static ListCommand parseListCommandArgs(String args) throws InternityException {
        return new ListCommand();
    }
}
