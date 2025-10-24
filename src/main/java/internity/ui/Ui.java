package internity.ui;

import internity.core.Internship;
import internity.core.InternshipList;

import java.util.ArrayList;

import static internity.core.InternshipList.INDEX_MAXLEN;
import static internity.core.InternshipList.COMPANY_MAXLEN;
import static internity.core.InternshipList.ROLE_MAXLEN;
import static internity.core.InternshipList.DEADLINE_MAXLEN;
import static internity.core.InternshipList.PAY_MAXLEN;
import static internity.core.InternshipList.STATUS_MAXLEN;

/**
 * The Ui Class provides user interface methods for the Internity chatbot.
 * Handles printing messages.
 */
public class Ui {
    static final String LINE = "____________________________________________________________\n";

    public static void printHorizontalLine() {
        System.out.print(LINE);
    }

    public static void printWelcomeMessage() {
        final String logo = " ___       _                  _ _\n" +
                "|_ _|_ __ | |_ ___ _ __ _ __ (_) |_ _   _\n" +
                " | || '_ \\| __/ _ \\ '__| '_ \\| | __| | | |\n" +
                " | || | | | ||  __/ |  | | | | | |_| |_| |\n" +
                "|___|_| |_|\\__\\___|_|  |_| |_|_|\\__|\\__, |\n" +
                "                                    |___/";
        System.out.println("Hello, welcome to\n" + logo);
        System.out.println("Be on top of your internships management with the Internity chatbot!");
        System.out.println("What is your name?");
    }

    public static void printGreeting(String input) {
        System.out.println("Hello, " + input + "!");
    }

    public static void printExit() {
        System.out.println("Thank you for using Internity! Goodbye!");
    }

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
}
