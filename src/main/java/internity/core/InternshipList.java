package internity.core;

import internity.ui.Ui;

import java.util.ArrayList;

public class InternshipList {
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

        if (InternshipList.isEmpty()) {
            System.out.println("No internships found. Please add an internship first.");
            assert (size() == 0) : "Internship list should be empty";
            return;
        }

        assert (size() > 0) : "Internship list should not be empty";
        System.out.printf("%-5s %-15s %-15s %-15s %-10s %-10s%n",
                "No.", "Company", "Role", "Deadline", "Pay", "Status");
        Ui.printHorizontalLine();
        int i;
        for (i = 0; i < InternshipList.size(); i++) {
            Internship internship = InternshipList.get(i);
            System.out.printf("%-5d %-15s %-15s %-15s %-10d %-10s%n",
                    i + 1,
                    internship.getCompany(),
                    internship.getRole(),
                    internship.getDeadline().toString(),
                    internship.getPay(),
                    internship.getStatus()
            );
        }
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
