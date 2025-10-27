package internity.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.core.InternshipList;

class UsernameCommandTest {
    @BeforeEach
    void resetUsername() {
        // Reset any previously set username
        InternshipList.setUsername(null);
    }

    @Test
    void execute_shouldSetUsernameInInternshipList() {
        String expectedUsername = "Jim Halpert";
        UsernameCommand command = new UsernameCommand(expectedUsername);
        command.execute();
        assertEquals(expectedUsername, InternshipList.getUsername(),
                "Username should be updated in InternshipList after executing the command");
    }

    @Test
    void execute_updatesSystemEvenAfterTrimmedInput() {
        String username = "  Asian Jim  ";
        UsernameCommand command = new UsernameCommand(username);
        command.execute();
        assertEquals(username, InternshipList.getUsername(),
                "Username should be set exactly as provided, even with extra spaces");
    }

    @Test
    void isExit_shouldReturnFalse() {
        UsernameCommand command = new UsernameCommand("Dwight Schrute");
        assertFalse(command.isExit(),
                "UsernameCommand should not be an exit command");
    }

    @Test
    void constructor_nullUsername_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new UsernameCommand(null));
    }

    @Test
    void constructor_blankUsername_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new UsernameCommand(""));
        assertThrows(AssertionError.class, () -> new UsernameCommand("   "));
    }
}
