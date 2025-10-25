package internity.ui;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;

public class DashboardUi {
    private static final Logger logger = Logger.getLogger(DashboardUi.class.getName());

    public static void printDashboard() throws InternityException {
        printUser();
        printInternshipCount();
        printNearestDeadline();
        printStatusOverview();
    }

    public static void printUser() {
        String user = InternshipList.getUsername();
        System.out.println("User: " + (user != null ? user : "Guest"));
        assert user != null : "Username should not be null";
        logger.fine("Displayed user: " + user);
    }

    public static void printInternshipCount() {
        int count = InternshipList.size();
        System.out.println("Total Internships: " + count);
        assert count >= 0 : "Internship count should never be negative";
        logger.fine("Total internships: " + count);
    }

    public static void printStatusOverview() throws InternityException {
        if (InternshipList.size() == 0) {
            System.out.println("\nStatus Overview: No internships found.");
            logger.warning("Status overview requested but internship list is empty");
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
        logger.fine("Status overview printed");
    }

    public static void printNearestDeadline() throws InternityException {
        if (InternshipList.size() == 0) {
            System.out.println("\nNearest Deadline: No internships found.");
            logger.warning("Nearest deadline requested but internship list is empty");
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

        logger.fine("Nearest deadline displayed: " + nearest);
    }

    private static Internship findNearestDeadlineInternship() throws InternityException {
        assert InternshipList.size() > 0 : "Cannot find nearest deadline in empty list";
        Internship nearest = null;

        for (int i = 0; i < InternshipList.size(); i++) {
            Internship internship = InternshipList.get(i);

            if (nearest == null || internship.getDeadline().compareTo(nearest.getDeadline()) < 0) {
                nearest = internship;
            }
        }
        logger.fine("Found nearest deadline internship: " + nearest);
        return nearest;
    }
}
