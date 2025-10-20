package internity.core;

import internity.ui.Ui;

import java.util.ArrayList;

import java.util.logging.Logger;

public class InternshipList {
    public static final int INDEX_MAXLEN= 5;
    public static final int COMPANY_MAXLEN = 15;
    public static final int ROLE_MAXLEN = 30;
    public static final int DEADLINE_MAXLEN = 15;
    public static final int PAY_MAXLEN = 10;
    public static final int STATUS_MAXLEN = 10;

    private static final Logger logger = Logger.getLogger(InternshipList.class.getName());
    private static final ArrayList<Internship> List = new ArrayList<>();

    public InternshipList() {

    }

    public static void add(Internship item) {
        List.add(item);
    }

    public static void delete(int index) throws InternityException {
        if (index < 0 || index >= List.size()) {
            throw new InternityException("Invalid internship index: " + (index + 1));
        }
        List.remove(index);
    }

    public static Internship get(int index) throws InternityException {
        if (index < 0 || index >= List.size()) {
            throw new InternityException("Invalid internship index: " + (index + 1));
        }
        return List.get(index);
    }

    public static int size() {
        return List.size();
    }

    // list all
    public static void listAll() throws InternityException {
        logger.info("Listing all internships");


        String formatHeader = "%" + INDEX_MAXLEN  + "s %-" + COMPANY_MAXLEN + "s %-" + ROLE_MAXLEN
                + "s %-" + DEADLINE_MAXLEN + "s %-" + PAY_MAXLEN + "s %-" + STATUS_MAXLEN + "s%n";
        String formatContent = "%" + INDEX_MAXLEN  + "d %-" + COMPANY_MAXLEN + "s %-" + ROLE_MAXLEN
                + "s %-" + DEADLINE_MAXLEN + "s %-" + PAY_MAXLEN + "d %-" + STATUS_MAXLEN + "s%n";


        if (InternshipList.isEmpty()) {
            logger.warning("No internships found to list");
            System.out.println("No internships found. Please add an internship first.");
            assert (size() == 0) : "Internship list should be empty";
            return;
        }

        assert (size() > 0) : "Internship list should not be empty";
        System.out.printf(formatHeader,
                "No.", "Company", "Role", "Deadline", "Pay", "Status");
        Ui.printHorizontalLine();
        int i;
        for (i = 0; i < InternshipList.size(); i++) {
            Internship internship = InternshipList.get(i);
            logger.fine("Listing internship at index: " + i);
            System.out.printf(formatContent,
                    i + 1,
                    internship.getCompany(),
                    internship.getRole(),
                    internship.getDeadline().toString(),
                    internship.getPay(),
                    internship.getStatus()
            );
        }
        logger.info("Finished listing internships. Total: " + i);
        assert (i == size()) : "All internships should be listed";
    }

    private static boolean isEmpty() {
        return List.isEmpty();
    }


    public static void updateStatus(int index, String newStatus) throws InternityException {
        if (index < 0 || index >= List.size()) {
            throw InternityException.invalidInternshipIndex();
        }
        Internship internship = List.get(index);
        internship.setStatus(newStatus);
        System.out.println("Updated internship " + (index + 1) + " status to: " + newStatus);
    }

    public static void clear() {
        List.clear();
    }
}
