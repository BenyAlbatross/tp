package internity.commands;

import static org.junit.jupiter.api.Assertions.*;

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
        String expectedUsername = "Rob Banks";
        UsernameCommand command = new UsernameCommand(expectedUsername);
        command.execute();
        assertEquals(expectedUsername, InternshipList.getUsername(),
                "Username should be updated in InternshipList after executing the command");
    }

    @Test
    void isExit_shouldReturnFalse() {
        UsernameCommand command = new UsernameCommand("Dwight Schrute");
        assertFalse(command.isExit(),
                "UsernameCommand should not be an exit command");
    }
}