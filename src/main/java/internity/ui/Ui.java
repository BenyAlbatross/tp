package internity.ui;

import internity.core.Internship;

import java.util.ArrayList;

import static internity.core.InternshipList.INDEX_MAXLEN;
import static internity.core.InternshipList.COMPANY_MAXLEN;
import static internity.core.InternshipList.ROLE_MAXLEN;
import static internity.core.InternshipList.DEADLINE_MAXLEN;
import static internity.core.InternshipList.PAY_MAXLEN;
import static internity.core.InternshipList.STATUS_MAXLEN;

/**
 * The {@code Ui} class provides methods for interacting with the user in the Internity chatbot.
 * <p>
 * It handles all user-facing output such as greetings, status updates, and responses to user actions
 * (e.g. adding, removing, or finding internships). This class is designed to centralise
 * all print-related functionality for consistent formatting and easy maintenance.
 * </p>
 */
public class Ui {
    /** Horizontal line used to visually separate sections in the console output. */
    static final String LINE = "____________________________________________________" +
            "_____________________________________\n";

    /**
     * Prints a horizontal divider line to the console.
     */
    public static void printHorizontalLine() {
        System.out.print(LINE);
    }

    /**
     * Prints the welcome message and ASCII logo for the Internity chatbot.
     */
    public static void printWelcomeMessage() {
        final String logo = " ___       _                  _ _\n" +
                "|_ _|_ __ | |_ ___ _ __ _ __ (_) |_ _   _\n" +
                " | || '_ \\| __/ _ \\ '__| '_ \\| | __| | | |\n" +
                " | || | | | ||  __/ |  | | | | | |_| |_| |\n" +
                "|___|_| |_|\\__\\___|_|  |_| |_|_|\\__|\\__, |\n" +
                "                                    |___/";
        System.out.println("Hello, welcome to\n" + logo);
        System.out.println("Be on top of your internships management with the Internity chatbot!");
    }

    /**
     * Prints a personalised greeting message to the user.
     *
     * @param input the username entered by the user
     */
    public static void printGreeting(String input) {
        System.out.println("Hello, " + input + "!");
    }

    /**
     * Prints a farewell message when the user exits the chatbot.
     */
    public static void printExit() {
        System.out.println("Thank you for using Internity! Goodbye!");
    }

    /**
     * Prints a confirmation message after successfully adding an internship.
     *
     * <p>
     * This method displays a message acknowledging that a new internship has been added,
     * followed by the internship's details, which are obtained by invoking the
     * {@link Internship#toString()} method of the given {@code Internship} object.
     * </p>
     *
     * @param internship the internship that was added
     */
    public static void printAddInternship(Internship internship) {
        System.out.println("Noted. I've added this internship:");
        System.out.println(internship.toString());
    }

    public static void printRemoveInternship(String item, int totalItems) {
        System.out.println("Noted. I've removed this internship:");
        System.out.println("  " + item);
        System.out.println("Now you have " + totalItems + " internship(s) in the list.");
    }

    public static void printUpdateInternship() {
        System.out.println("Internship status updated successfully!");
    }

    /**
     * Prints a formatted list of internships that match the user's search keyword.
     *
     * <p>
     * This method first prints a header row and horizontal lines for clear table formatting.
     * It then iterates through the provided list of {@link Internship} objects, displaying each
     * internship's details in a tabular format. The internship details are obtained via the
     * {@link Internship#getCompany()}, {@link Internship#getRole()},
     * {@link Internship#getDeadline()}, {@link Internship#getPay()}
     * and {@link Internship#getStatus()} methods.
     * </p>
     *
     * @param list the list of matching {@code Internship} objects to display
     */
    public static void printFindInternship(ArrayList<Internship> list) {
        System.out.println("These are the matching internships in your list:");

        Ui.printHorizontalLine();
        String formatHeader = "%" + INDEX_MAXLEN  + "s %-" + COMPANY_MAXLEN + "s %-" + ROLE_MAXLEN
                + "s %-" + DEADLINE_MAXLEN + "s %-" + PAY_MAXLEN + "s %-" + STATUS_MAXLEN + "s%n";
        String formatContent = "%" + INDEX_MAXLEN  + "d %-" + COMPANY_MAXLEN + "s %-" + ROLE_MAXLEN
                + "s %-" + DEADLINE_MAXLEN + "s %-" + PAY_MAXLEN + "d %-" + STATUS_MAXLEN + "s%n";
        System.out.printf(formatHeader,
                "No.", "Company", "Role", "Deadline", "Pay", "Status");
        Ui.printHorizontalLine();

        int i;
        for (i = 0; i < list.size(); i++) {
            Internship internship = list.get(i);
            System.out.printf(formatContent,
                    i + 1,
                    internship.getCompany(),
                    internship.getRole(),
                    internship.getDeadline().toString(),
                    internship.getPay(),
                    internship.getStatus()
            );
        }
    }
    public static void printAskUsername() {
        System.out.println("What is your name?");
    }

    public static void printSetUsername(String username) {
        System.out.println("Username set to " + username);
    }

    public static void printHelp() {
        System.out.println("Here are the available commands:");
        System.out.println("1. add company/COMPANY_NAME role/ROLE_NAME deadline/DEADLINE pay/PAY_AMOUNT");
        System.out.println("   - Adds a new internship application.");
        System.out.println("2. delete INDEX");
        System.out.println("   - Deletes the internship application at the specified index.");
        System.out.println("3. list [sort/asc|sort/desc]");
        System.out.println("   - Lists all internship applications, optionally sorted by deadline.");
        System.out.println("4. find KEYWORD");
        System.out.println("   - Finds and lists all internship applications matching the keyword.");
        System.out.println("5. update INDEX status/STATUS");
        System.out.println("   - Updates the status of the internship application at the specified index.");
        System.out.println("6. username USERNAME");
        System.out.println("   - Sets your username for personalised greetings.");
        System.out.println("7. dashboard");
        System.out.println("   - Displays statistics about your internship applications.");
        System.out.println("8. help");
        System.out.println("   - Displays this help message.");
        System.out.println("9. exit");
        System.out.println("   - Exits the Internity chatbot.");
    }
}
