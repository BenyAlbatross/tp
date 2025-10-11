package internity.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InternshipListTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUpStreams() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void add_thenGet_returnsItemAtIndex() {
        InternshipList list = new InternshipList();

        list.add(null);
        list.add(null);

        // Should not throw
        assertDoesNotThrow(() -> list.get(0));
        assertDoesNotThrow(() -> list.get(1));

        assertNull(list.get(0));
        assertNull(list.get(1));
    }

    @Test
    void get_invalidIndex_throwsIndexOutOfBoundsException() {
        InternshipList list = new InternshipList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add(null);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void delete_removesGivenItem() {
        InternshipList list = new InternshipList();

        list.add(null); // index 0
        list.add(null); // index 1

        list.delete(null);

        assertDoesNotThrow(() -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void listAll_printsPlaceholder() {
        InternshipList list = new InternshipList();

        list.listAll();
        String printed = out.toString();

        assertEquals("<PRINT ALL INTERNSHIPS>" + System.lineSeparator(), printed);
    }

    @Test
    void update_validIndex_printsUpdatedMessage() {
        InternshipList list = new InternshipList();

        list.add(null);
        list.update(0);

        String printed = out.toString().trim();
        assertTrue(printed.endsWith("UPDATED INTERNSHIP WITH INDEX: 0"));
    }

    @Test
    void update_invalidIndex_throwsIndexOutOfBoundsException() {
        InternshipList list = new InternshipList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.update(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.update(-1));
    }
}
