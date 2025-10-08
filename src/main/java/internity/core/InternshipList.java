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

    // update internship by index
    public void update(int index) {
        // ADD CODE HERE
        Internship updateThis = List.get(index);
        System.out.println("UPDATED INTERNSHIP WITH INDEX: "+ index);
    }

}
