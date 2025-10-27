package internity.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.core.Date;
import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;

class DashboardUiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));

        InternshipList.clear();
        InternshipList.setUsername("TestUser");

        InternshipList.add(new Internship("Google", "SWE", new Date(1, 1, 2025), 8000));
        InternshipList.add(new Internship("Microsoft", "Intern", new Date(15, 12, 2025), 5000));
    }

    @Test
    void printDashboard_withInternships_printsExpectedInfo() throws InternityException {
        DashboardUi.printDashboard();

        String output = outContent.toString();

        assertTrue(output.contains("User: TestUser"), "Should print username");
        assertTrue(output.contains("Total Internships: 2"), "Should print correct count");
        assertTrue(output.contains("Nearest Deadline:") && output.contains("01-01-2025"), "Should show earliest deadline");
        assertTrue(output.contains("Applied") && output.contains("Pending"), "Should show status overview");
    }

    @Test
    void printDashboard_noInternships_printsNoInternshipsMessage() throws InternityException {
        InternshipList.clear();
        outContent.reset();

        DashboardUi.printDashboard();

        String output = outContent.toString();
        assertTrue(output.contains("No internships found"), "Should indicate no internships");
        assertTrue(output.contains("Guest") || output.contains("User:"), "Should still print username");
    }
}