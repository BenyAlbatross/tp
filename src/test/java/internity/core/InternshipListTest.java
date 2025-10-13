package internity.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InternshipListTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void add_thenGet_returnsItemAtIndex() throws InternityException {
        InternshipList.add(null);
        InternshipList.add(null);

        assertDoesNotThrow(() -> InternshipList.get(0));
        assertDoesNotThrow(() -> InternshipList.get(1));

        assertNull(InternshipList.get(0));
        assertNull(InternshipList.get(1));
    }

    @Test
    void get_invalidIndex_throwsIndexOutOfBoundsException() {
        InternshipList.clear();

        assertThrows(InternityException.class, () -> InternshipList.get(0));
        InternshipList.add(null);
        assertThrows(InternityException.class, () -> InternshipList.get(1));
        assertThrows(InternityException.class, () -> InternshipList.get(-1));
    }

    @Test
    void delete_removesGivenItem() throws InternityException {
        InternshipList.clear();

        InternshipList.add(null);
        InternshipList.add(null);

        InternshipList.delete(0);

        assertDoesNotThrow(() -> InternshipList.get(0));
        assertThrows(InternityException.class, () -> InternshipList.get(1));
    }

    @Test
    public void listAll_whenEmpty_expectedOutcome() throws InternityException {
        InternshipList.clear();
        InternshipList.listAll();
        assertTrue(outContent.toString().contains("No internships found. Please add an internship first."));
    }

    @Test
    public void listAll_withEntry_doesNotOutputNoInternshipsFound() throws Exception {
        InternshipList.clear();
        Internship internship = new Internship("Company A", "Developer", new Date(1,1,2025), 5000);
        InternshipList.add(internship);
        InternshipList.listAll();

        String output = outContent.toString();
        assertFalse(output.contains("No internships found. Please add an internship first."));
    }
}
