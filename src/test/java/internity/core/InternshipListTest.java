package internity.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InternshipListTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InternshipList.clear();
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
        assertThrows(InternityException.class, () -> InternshipList.get(0));
        InternshipList.add(null);
        assertThrows(InternityException.class, () -> InternshipList.get(1));
        assertThrows(InternityException.class, () -> InternshipList.get(-1));
    }

    @Test
    void delete_removesGivenItem() throws InternityException {
        InternshipList.add(null);
        InternshipList.add(null);

        InternshipList.delete(0);

        assertDoesNotThrow(() -> InternshipList.get(0));
        assertThrows(InternityException.class, () -> InternshipList.get(1));
    }

    @Test
    public void listAll_whenEmpty_expectedOutcome() throws InternityException {
        InternshipList.listAll(0);
        assertTrue(outContent.toString().contains("No internships found. Please add an internship first."));
    }

    @Test
    public void listAll_withEntry_doesNotOutputNoInternshipsFound() throws Exception {
        Internship internship = new Internship("Company A", "Developer", new Date(1,1,2025), 5000);
        InternshipList.add(internship);
        InternshipList.listAll(0);

        String output = outContent.toString();
        assertFalse(output.contains("No internships found. Please add an internship first."));
    }

    @Test
    public void sortInternships_sortAscending_expectedOutcome() throws InternityException {
        Internship older = new Internship("OlderCo", "Dev", new Date(1, 6, 2024), 0);
        Internship newer = new Internship("NewerCo", "Dev", new Date(1, 1, 2025), 0);

        InternshipList.add(newer);
        InternshipList.add(older);

        InternshipList.sortInternships(1); // ascending

        assertEquals("OlderCo", InternshipList.get(0).getCompany());
        assertEquals("NewerCo", InternshipList.get(1).getCompany());
    }

    @Test
    public void sortInternships_sortDescending_expectedOutcome() throws InternityException {
        Internship older = new Internship("OlderCo", "Dev", new Date(1, 6, 2024), 0);
        Internship newer = new Internship("NewerCo", "Dev", new Date(1, 1, 2025), 0);

        InternshipList.add(older);
        InternshipList.add(newer);

        InternshipList.sortInternships(-1); // descending

        assertEquals("NewerCo", InternshipList.get(0).getCompany());
        assertEquals("OlderCo", InternshipList.get(1).getCompany());
    }
}
