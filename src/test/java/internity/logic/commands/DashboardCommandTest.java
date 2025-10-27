package internity.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.core.Date;
import internity.core.Internship;
import internity.core.InternshipList;
import internity.logic.commands.DashboardCommand;

class DashboardCommandTest {
    private final PrintStream originalOut = System.out;
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
    void execute_printsDashboardWithoutException() {
        DashboardCommand command = new DashboardCommand();
        assertDoesNotThrow(command::execute);

        String output = outContent.toString();

        assertTrue(output.contains("User:"), "Dashboard should display the username section");
        assertTrue(output.contains("Total Internships:"), "Dashboard should display total internships");
        assertTrue(output.contains("Nearest Deadline:"), "Dashboard should display nearest deadline");
        assertTrue(output.contains("Status Overview:"), "Dashboard should display status overview");
    }

    @Test
    void isExit_returnsFalse() {
        DashboardCommand command = new DashboardCommand();
        assertFalse(command.isExit(), "Dashboard command should not terminate the program");
    }
}
