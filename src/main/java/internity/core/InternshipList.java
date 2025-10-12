package internity.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InternshipList {
    private static final ArrayList<Internship> List = new ArrayList<>();

    // constructor
    public InternshipList() {

    }

    // add
    public static void add(Internship item) {
        List.add(item);
    }

    // delete
    public static void delete(int index) throws InternityException {
        if (index < 0 || index >= List.size()) {
            throw new InternityException("Invalid task number: " + (index + 1));
        }
        List.remove(index);
    }

    // get an internship by index
    public static Internship get(int index) throws InternityException {
        if (index < 0 || index >= List.size()) {
            throw new InternityException("Invalid task number: " + (index + 1));
        }
        return List.get(index);
    }

    // get size
    public static int size() {
        return List.size();
    }

    // list all
    public static void listAll() throws InternityException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (InternshipList.isEmpty()) {
            System.out.println("No internships found. Please add an internship first.");
            return;
        }

        System.out.printf("%-5s %-15s %-15s %-15s %-10s %-10s%n", "No.", "Company", "Role", "Deadline", "Pay", "Status");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < InternshipList.size(); i++) {
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
