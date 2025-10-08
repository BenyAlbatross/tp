package internity.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import internity.core.Date;
import internity.core.InternityException;

class DateFormatterTest {

    @Test
    void parse_validDateFormat_dateObjectCreated() throws InternityException {
        String input = "08/10/2025";
        Date parsedDate = DateFormatter.parse(input);

        assertNotNull(parsedDate, "Resulting Date object should not be null");
        assertEquals(8, parsedDate.getDay(), "Day should match input");
        assertEquals(10, parsedDate.getMonth(), "Month should match input");
        assertEquals(2025, parsedDate.getYear(), "Year should match input");
    }

    @Test
    void parse_invalidDateFormat_throwsException() {
        String input = "2025/10/08";
        InternityException thrown = assertThrows(
                InternityException.class,
                () -> DateFormatter.parse(input),
                "Expected parse() to throw exception, but it did not"
        );

        assertEquals(
                "Invalid date format. Expected dd/MM/yyyy (e.g. 08/10/2025)",
                thrown.getMessage()
        );
    }

    @Test
    void parse_nonNumericalInput_throwsException() {
        String input = "ab/cd/efgh";

        InternityException thrown = assertThrows(
                InternityException.class,
                () -> DateFormatter.parse(input),
                "Expected parse() to throw exception, but it did not"
        );

        assertEquals(
                "Invalid date format. Expected dd/MM/yyyy (e.g. 08/10/2025)",
                thrown.getMessage()
        );
    }

    @Test
    void parse_mixedAlphaNumericalInput_throwsException() {
        String input = "12/xx/2025";

        InternityException thrown = assertThrows(
                InternityException.class,
                () -> DateFormatter.parse(input),
                "Expected parse() to throw exception, but it did not"
        );

        assertEquals(
                "Invalid date format. Expected dd/MM/yyyy (e.g. 08/10/2025)",
                thrown.getMessage()
        );
    }

    @Test
    void parse_blankInput_throwsException() {
        String input = " ";

        InternityException thrown = assertThrows(
                InternityException.class,
                () -> DateFormatter.parse(input),
                "Expected parse() to throw exception, but it did not"
        );

        assertEquals("Input cannot be null or blank", thrown.getMessage());
    }
}