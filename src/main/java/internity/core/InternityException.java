package internity.core;

/**
 * Represents custom exceptions used throughout the Internity application.
 *
 * <p>
 * This class extends {@code Exception} and provides static methods that create a specific
 * exception message for common error conditions such as invalid input or unknown commands.
 * </p>
 */
public class InternityException extends Exception {
    public InternityException(String message) {
        super(message);
    }

    public static InternityException invalidInput() {
        return new InternityException("Input cannot be null or blank");
    }

    public static InternityException unknownCommand(String message) {
        return new InternityException("Unknown command: " + message);
    }

    public static InternityException invalidDateFormat() {
        return new InternityException("Invalid date format. Expected dd-MM-yyyy (e.g. 08-10-2025)");
    }

    public static InternityException invalidAddCommand() {
        return new InternityException("Invalid add command. Usage: add company/COMPANY_NAME" +
                " role/ROLE_NAME deadline/DEADLINE pay/PAY_AMOUNT");
    }

    public static InternityException invalidDeleteCommand() {
        return new InternityException("Invalid delete command. Usage: delete INDEX");
    }

    public static InternityException invalidFindCommand() {
        return new InternityException("Invalid find command. Usage: find KEYWORD");
    }

    public static InternityException invalidInternshipIndex() {
        return new InternityException("Invalid internship index.");
    }

    public static InternityException invalidListCommand() {
        return new InternityException("Invalid list command. Usage: list [sort/asc|sort/desc]");
    }

    public static InternityException invalidUpdateFormat() {
        return new InternityException(
                "Invalid update command. Usage: update INDEX field/VALUE"
        );
    }

    public static InternityException noUpdateFieldsProvided() {
        return new InternityException(
                "Provide at least one field to update: company/, role/, deadline/, pay/, status/"
        );
    }

    public static InternityException emptyField(String tag) {
        return new InternityException(tag + " cannot be empty");
    }

    public static InternityException exceedFieldLength(String tag, int maxLength, int givenLength) {
        return new InternityException(tag + " cannot exceed " +
                maxLength + " characters : given " +
                givenLength + " characters");
    }

    public static InternityException exceedFieldValue(String tag, String criteria, String givenValue) {
        return new InternityException(tag + " cannot exceed " + criteria + " : given " + givenValue);
    }

    public static InternityException invalidIndexForUpdate() {
        return new InternityException(
                "Invalid index. Use a positive integer, for example: update 1 company/Google"
        );
    }

    public static InternityException invalidPayFormat() {
        return new InternityException("Invalid pay. Use a whole number (example: pay/8000)");
    }

    public static InternityException unknownUpdateField(String token) {
        return new InternityException(
                "Unknown update field in \"" + token
                + "\". Allowed: company, role, deadline, pay, status"
        );
    }

    public static InternityException invalidStatus(String value) {
        return new InternityException(
                "Invalid status \"" + value
                + "\". Allowed: Pending, Interested, Applied, Interviewing, Offer, Accepted, Rejected"
        );
    }

    public static InternityException invalidUsernameCommand() {
        return new InternityException("Invalid username command. Usage: username USERNAME");
    }
}
