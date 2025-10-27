package internity.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InternshipTest {
    @Test
    void compareTo_earlierDeadline_returnsNegative() {
        Internship first = new Internship("Google", "SWE", new Date(1, 1, 2025), 8000);
        Internship second = new Internship("Microsoft", "Intern", new Date(15, 12, 2025), 5000);

        assertTrue(first.compareTo(second) < 0, "Internship with earlier deadline should come first");
    }

    @Test
    void compareTo_laterDeadline_returnsPositive() {
        Internship first = new Internship("Google", "SWE", new Date(1, 1, 2025), 8000);
        Internship second = new Internship("Microsoft", "Intern", new Date(15, 12, 2024), 5000);

        assertTrue(first.compareTo(second) > 0, "Internship with later deadline should come after");
    }

    @Test
    void compareTo_sameDeadline_returnsZero() {
        Internship first = new Internship("Google", "SWE", new Date(1, 1, 2025), 8000);
        Internship second = new Internship("Microsoft", "Intern", new Date(1, 1, 2025), 5000);

        assertEquals(0, first.compareTo(second), "Internships with same deadline should be equal");
    }

    @Test
    void isValidStatus_validStatus_returnsTrue() {
        assertTrue(Internship.isValidStatus("Pending"));
        assertTrue(Internship.isValidStatus("Applied"));
        assertTrue(Internship.isValidStatus("Offer"));
    }

    @Test
    void isValidStatus_invalidStatus_returnsFalse() {
        assertFalse(Internship.isValidStatus("Waiting"));
        assertFalse(Internship.isValidStatus("Done"));
        assertFalse(Internship.isValidStatus(""));
        assertFalse(Internship.isValidStatus("pending")); // case-sensitive
    }

    @Test
    void isValidStatus_nullStatus_returnsFalse() {
        assertFalse(Internship.isValidStatus(null));
    }
}