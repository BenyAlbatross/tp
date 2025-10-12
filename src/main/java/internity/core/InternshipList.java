package internity.core;

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
            throw new InternityException("Invalid task number: " + (index + 1));
        }
        List.remove(index);
    }

    public static Internship get(int index) throws InternityException {
        if (index < 0 || index >= List.size()) {
            throw new InternityException("Invalid task number: " + (index + 1));
        }
        return List.get(index);
    }

    public static int size() {
        return List.size();
    }

    public static void listAll() {
        // ADD CODE HERE
        System.out.println("<PRINT ALL INTERNSHIPS>");
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
