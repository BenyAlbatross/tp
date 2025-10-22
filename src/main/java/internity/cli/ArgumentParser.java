package internity.cli;

import internity.commands.AddCommand;
import internity.commands.Command;
import internity.commands.DeleteCommand;
import internity.commands.ListCommand;
import internity.commands.UpdateCommand;
import internity.commands.UsernameCommand;
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
        String trimmed = requireArgs(args);
        String[] idxAndTagged = splitIndexAndTagged(trimmed);
        int index = parseOneBasedIndex(idxAndTagged[0]);
        String tagged = requireTagged(idxAndTagged[1]);

        String[] parts = tagged.split("\\s+(?=company/|role/|deadline/|pay/|status/)");
        String company = null;
        String role = null;
        Date deadline = null;
        Integer pay = null;
        String status = null;

        try {
            for (String part : parts) {
                String p = part.trim();
                if (p.isEmpty()) {
                    continue;
                }
                if (p.startsWith("company/")) {
                    company = valueAfterTag(p, "company/");
                    if (company.isEmpty()) {
                        throw InternityException.emptyField("company/");
                    }
                } else if (p.startsWith("role/")) {
                    role = valueAfterTag(p, "role/");
                    if (role.isEmpty()) {
                        throw InternityException.emptyField("role/");
                    }
                } else if (p.startsWith("deadline/")) {
                    String d = valueAfterTag(p, "deadline/");
                    deadline = DateFormatter.parse(d);
                } else if (p.startsWith("pay/")) {
                    String payStr = valueAfterTag(p, "pay/");
                    int payVal = Integer.parseInt(payStr);
                    if (payVal < 0) {
                        throw new NumberFormatException();
                    }
                    pay = payVal;
                } else if (p.startsWith("status/")) {
                    status = valueAfterTag(p, "status/");
                    if (status.isEmpty()) {
                        throw InternityException.emptyField("status/");
                    }
                } else {
                    throw InternityException.unknownUpdateField(p);
                }
            }
        } catch (NumberFormatException e) {
            throw InternityException.invalidPayFormat();
        }

        if (company == null && role == null && deadline == null && pay == null && status == null) {
            throw InternityException.noUpdateFieldsProvided();
        }

        return new UpdateCommand(index, company, role, deadline, pay, status);
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

    private static String requireArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidUpdateFormat();
        }
        return args.trim();
    }

    private static String requireTagged(String tagged) throws InternityException {
        if (tagged.isBlank()) {
            throw InternityException.noUpdateFieldsProvided();
        }
        return tagged;
    }

    private static String[] splitIndexAndTagged(String trimmed) throws InternityException {
        int firstSpace = trimmed.indexOf(' ');
        if (firstSpace < 0) {
            throw InternityException.invalidUpdateFormat();
        }
        String indexToken = trimmed.substring(0, firstSpace).trim();
        String tagged = trimmed.substring(firstSpace + 1).trim();
        return new String[] { indexToken, tagged };
    }

    private static int parseOneBasedIndex(String indexToken) throws InternityException {
        try {
            return Integer.parseInt(indexToken) - 1;
        } catch (NumberFormatException e) {
            throw InternityException.invalidIndexForUpdate();
        }
    }

    private static String valueAfterTag(String token, String tag) {
        return token.substring(tag.length()).trim();
    }

    public static UsernameCommand parseUsernameCommandArgs(String args) throws InternityException {
        if (args == null || args.isBlank()) {
            throw InternityException.invalidUsernameCommand();
        }
        return new UsernameCommand(args);
    }
}
