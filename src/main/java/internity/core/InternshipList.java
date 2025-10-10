package internity.core;

import java.util.ArrayList;

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
    public static void listAll() {
        // ADD CODE HERE
        System.out.println("<PRINT ALL INTERNSHIPS>");
    }

    public static boolean updateStatus(int index, String newStatus) {
        if (index < 0 || index >= List.size()) {
            System.out.println("Invalid internship index.");
            return false;
        }
        Internship internship = List.get(index);
        internship.setStatus(newStatus);
        System.out.println("Updated internship " + (index + 1) + " status to: " + newStatus);
        return true;
    }
}
