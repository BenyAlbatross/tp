package internity.ui;

import internity.core.InternshipList;

public class DashboardUI {
    public DashboardUI() {

    }

    public void printUser() {
        String user = InternshipList.getUsername();
    }

    public void printInternshipCount() {
        System.out.println("Total Internships: " + InternshipList.size());
    }

    public void printStatusOverview() {
    }
}
