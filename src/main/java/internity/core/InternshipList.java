package internity.core;

import internity.commands.ListCommand;
import internity.ui.Ui;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InternshipList {
    public static final int INDEX_MAXLEN = 5;
    public static final int COMPANY_MAXLEN = 15;
    public static final int ROLE_MAXLEN = 30;
    public static final int DEADLINE_MAXLEN = 15;
    public static final int PAY_MAXLEN = 10;
    public static final int STATUS_MAXLEN = 10;

    private static final Logger logger = Logger.getLogger(InternshipList.class.getName());
    private static final ArrayList<Internship> List = new ArrayList<>();
    private static Storage storage;

    private InternshipList() {
    }

    /**
     * Sets the storage instance for auto-saving.
     *
     * @param storageInstance The storage instance to use for persistence.
     */
    public static void setStorage(Storage storageInstance) {
        storage = storageInstance;
    }

    /**
     * Loads internships from storage.
     *
     * @throws InternityException If there is an error loading from storage.
     */
    public static void loadFromStorage() throws InternityException {
        if (storage == null) {
            return;
        }
        ArrayList<Internship> loadedInternships = storage.load();
        List.clear();
        List.addAll(loadedInternships);
    }

    /**
     * Saves internships to storage.
     *
     * @throws InternityException If there is an error saving to storage.
     */
    public static void saveToStorage() throws InternityException {
        if (storage == null) {
            return;
        }
        storage.save(List);
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

    public static void sortInternships(ListCommand.orderType order) {
        if (order == ListCommand.orderType.DESCENDING) {
            List.sort(Comparator.comparing(Internship::getDeadline).reversed());
        } else if (order == ListCommand.orderType.ASCENDING) {
            List.sort(Comparator.comparing(Internship::getDeadline));
        }
    }

    // list all
    public static void listAll(ListCommand.orderType order) throws InternityException {
        logger.info("Listing all internships");


        String formatHeader = "%" + INDEX_MAXLEN + "s %-" + COMPANY_MAXLEN + "s %-" + ROLE_MAXLEN
                + "s %-" + DEADLINE_MAXLEN + "s %-" + PAY_MAXLEN + "s %-" + STATUS_MAXLEN + "s%n";
        String formatContent = "%" + INDEX_MAXLEN + "d %-" + COMPANY_MAXLEN + "s %-" + ROLE_MAXLEN
                + "s %-" + DEADLINE_MAXLEN + "s %-" + PAY_MAXLEN + "d %-" + STATUS_MAXLEN + "s%n";


        if (InternshipList.isEmpty()) {
            logger.warning("No internships found to list");
            System.out.println("No internships found. Please add an internship first.");
            assert (size() == 0) : "Internship list should be empty";
            return;
        }

        assert (size() > 0) : "Internship list should not be empty";
        sortInternships(order);
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

    public static void findInternship(String keyword) {
        ArrayList<Internship> matchingInternships = List.stream()
                .filter(internship ->
                        internship.getCompany().toLowerCase().contains(keyword.toLowerCase()) ||
                                internship.getRole().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (matchingInternships.isEmpty()) {
            System.out.println("No internships with this Company or Role found.");
            return;
        }

        Ui.printFindInternship(matchingInternships);
    }

    public static void clear() {
        List.clear();
    }
}
