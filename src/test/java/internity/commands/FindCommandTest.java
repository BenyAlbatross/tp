package internity.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;
import internity.utils.DateFormatter;

class FindCommandTest {

    // Streams to capture the console output
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setup() {
        // Clear any previous internships before each test
        InternshipList.clear();

        // Set up new ByteArrayOutputStream
        outContent = new ByteArrayOutputStream();

        // Redirect System.out to capture printed output during the test
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalSystemOut);
        InternshipList.clear();
    }

    /**
     * Tests that FindCommand correctly finds internships based on the keyword.
     */
    @Test
    void execute_validInput_findsMatchingInternships() throws InternityException {
        // Arrange
        InternshipList.add(new Internship("Google", "Software Engineer", DateFormatter.parse("01-12-2025"), 120000));
        InternshipList.add(new Internship("Microsoft", "Data Scientist", DateFormatter.parse("01-12-2025"), 130000));

        String keyword = "Google";  // We want to search for "Google"
        FindCommand command = new FindCommand(keyword);

        // Act
        command.execute();

        // Assert
        // Validate that the output contains the correct internship information
        String output = outContent.toString();
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("Software Engineer"));
        assertTrue(output.contains("01-12-2025"));
        assertTrue(output.contains("120000"));
    }

    /**
     * Tests that FindCommand handles when no internships match the keyword.
     */
    @Test
    void execute_noMatchingInternships_printsNoResults() throws InternityException {
        // Arrange
        InternshipList.add(new Internship("Google", "Software Engineer", DateFormatter.parse("01-12-2025"), 120000));
        InternshipList.add(new Internship("Microsoft", "Data Scientist", DateFormatter.parse("01-12-2025"), 130000));

        String keyword = "Amazon";  // This keyword should not match any internship
        FindCommand command = new FindCommand(keyword);

        // Act
        command.execute();

        // Assert
        // Verify the output contains "No internships with this company or role found."
        String output = outContent.toString();
        assertTrue(output.contains("No internships with this company or role found."));
    }

    /**
     * Tests that FindCommand does not terminate the application.
     */
    @Test
    void isExit_returnsFalse() throws InternityException {
        // Arrange
        String keyword = "Software";
        FindCommand command = new FindCommand(keyword);

        // Act & Assert
        assertFalse(command.isExit(), "FindCommand should not terminate the application");
    }

}
