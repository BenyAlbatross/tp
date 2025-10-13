package internity.commands;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import internity.core.InternshipList;
import internity.core.InternityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ListCommandTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InternshipList.clear();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        InternshipList.clear();
    }

    @Test
    void execute_whenNoEntries_printsNoInternshipsFound() throws InternityException {
        InternshipList.clear();
        ListCommand listCommand = new ListCommand();
        listCommand.execute();

        assertEquals("No internships found. Please add an internship first.", outContent.toString());
    }

    @Test
    void execute_withEntry_doesNotPrintNoInternshipsFound() throws InternityException {
        InternshipList.add(null); // dummy entry
        ListCommand listCommand = new ListCommand();
        listCommand.execute();

        assertFalse(outContent.toString().contains("No internships found. Please add an internship first."));
    }

    @Test
    void execute_doesNotThrow() {
        ListCommand listCommand = new ListCommand();
        assertDoesNotThrow(listCommand::execute);
    }
}
