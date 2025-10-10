package internity.core;

import java.util.ArrayList;

public class InternshipList {
    private final ArrayList<Internship> List;

    // constructor
    public InternshipList() {
        List = new ArrayList<>();
    }

    // add
    public void add(Internship item) {
        List.add(item);
    }

    // delete
    public void delete(Internship item) {
        List.remove(item);
    }

    // get an internship by index
    public Internship get(int index){
        return List.get(index);
    }

    // list all
    public void listAll() {
        // ADD CODE HERE
        System.out.println("<PRINT ALL INTERNSHIPS>");
    }

    public boolean updateStatus(int index, String newStatus) {
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
