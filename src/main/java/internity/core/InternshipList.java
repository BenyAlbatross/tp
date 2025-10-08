package internity.core;

import java.util.ArrayList;

public class InternshipList {
    private final ArrayList<Internship> List;

    public InternshipList() {
        List = new ArrayList<>();
    }

    public void add(Internship item) {
        List.add(item);
    }

    public void delete(Internship item) {
        List.remove(item);
    }

    public Internship get(int index){
        return List.get(index);
    }

    public void listAll() {
        // ADD CODE HERE
        System.out.println("<PRINT ALL INTERNSHIPS>");
    }

    public void update(int index) {
        // ADD CODE HERE
        Internship updateThis = List.get(index);
        System.out.println("UPDATED INTERNSHIP WITH INDEX: "+ index);
    }

}
