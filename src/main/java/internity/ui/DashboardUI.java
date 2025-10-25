package internity.ui;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;

public class DashboardUI {
    public static void printDashboard() throws InternityException {
        printUser();
        printInternshipCount();
        printNearestDeadline();
        printStatusOverview();
    }

    private static void printUser() {
        String user = InternshipList.getUsername();
        System.out.println("User: " + (user != null ? user : "Guest"));
    }

    private static void printInternshipCount() {
        System.out.println("Total Internships: " + InternshipList.size());
    }

    private static void printStatusOverview() throws InternityException {
        if (InternshipList.size() == 0) {
            System.out.println("\nStatus Overview: No internships found.");
            return;
        }

        List<String> statusOrder = List.of(
                "Pending", "Interested", "Applied", "Interviewing",
                "Offer", "Accepted", "Rejected"
        );

        Map<String, Integer> statusCount = new LinkedHashMap<>();

        for (String status : statusOrder) {
            statusCount.put(status, 0);
        }

        for (int i = 0; i < InternshipList.size(); i++) {
            Internship internship;
            internship = InternshipList.get(i);

            String status = internship.getStatus();
            if (statusCount.containsKey(status)) {
                statusCount.put(status, statusCount.get(status) + 1);
            }
        }

        System.out.println("\nStatus Overview:");
        for (String status : statusOrder) {
            System.out.printf("  %-15s : %d%n", status, statusCount.get(status));
        }
    }

    private static void printNearestDeadline() throws InternityException {
        if (InternshipList.size() == 0) {
            System.out.println("\nNearest Deadline: No internships found.");
            return;
        }

        Internship nearest = findNearestDeadlineInternship();
        if (nearest == null) {
            System.out.println("\nNearest Deadline: No valid deadlines found.");
            return;
        }

        System.out.println("\nNearest Deadline:");
        System.out.printf("  %s | %s @ %s%n",
                nearest.getDeadline().toString(),
                nearest.getRole(),
                nearest.getCompany());
    }

    private static Internship findNearestDeadlineInternship() throws InternityException {
        Internship nearest = null;

        for (int i = 0; i < InternshipList.size(); i++) {
            Internship internship = InternshipList.get(i);

            if (nearest == null || internship.getDeadline().compareTo(nearest.getDeadline()) < 0) {
                nearest = internship;
            }
        }

        return nearest;
    }
}
