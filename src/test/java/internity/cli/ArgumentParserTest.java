package internity.cli;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.commands.UpdateCommand;
import internity.commands.UsernameCommand;
import internity.core.Date;
import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;

class ArgumentParserTest {
    
    @BeforeEach
    void setUp() {
        InternshipList.clear();
        InternshipList.add(new Internship("Google", "SWE", new Date(1, 1, 2025), 8000));
    }

    @Test
    void parseUpdateCommandArgs_validSingleField_returnsCommand() throws InternityException {
        UpdateCommand command = ArgumentParser.parseUpdateCommandArgs("1 company/Meta");
        assertNotNull(command);
    }

    @Test
    void parseUpdateCommandArgs_multipleFields_returnsCommand() throws InternityException {
        UpdateCommand command = ArgumentParser.parseUpdateCommandArgs("1 company/Meta role/AI Intern pay/9000");
        assertNotNull(command);
    }

    @Test
    void parseUpdateCommandArgs_invalidIndex_throwsException() {
        assertThrows(InternityException.class, () -> ArgumentParser.parseUpdateCommandArgs("abc company/Meta"));
    }

    @Test
    void parseUpdateCommandArgs_missingFieldTag_throwsException() {
        assertThrows(InternityException.class, () -> ArgumentParser.parseUpdateCommandArgs("1 Meta"));
    }

    @Test
    void parseUpdateCommandArgs_emptyCompany_throwsException() {
        assertThrows(InternityException.class, () -> ArgumentParser.parseUpdateCommandArgs("1 company/ "));
    }

    @Test
    void parseUsernameCommandArgs_validArgs_shouldReturnUsernameCommand() throws Exception {
        String validUsername = "Jesse Pinkman";
        UsernameCommand command = ArgumentParser.parseUsernameCommandArgs(validUsername);
        assertNotNull(command, "Returned UsernameCommand should not be null");
    }

    @Test
    void parseUsernameCommandArgs_nullArgs_shouldThrowInternityException() {
        InternityException exception = assertThrows(
                InternityException.class,
                () -> ArgumentParser.parseUsernameCommandArgs(null),
                "Expected InternityException for null input"
        );
        assertTrue(exception.getMessage().toLowerCase().contains("invalid"),
                "Exception message should indicate invalid username command");
    }

    @Test
    void parseUsernameCommandArgs_blankArgs_shouldThrowInternityException() {
        InternityException exception = assertThrows(
                InternityException.class,
                () -> ArgumentParser.parseUsernameCommandArgs("   "),
                "Expected InternityException for blank input"
        );
        assertTrue(exception.getMessage().toLowerCase().contains("invalid"),
                "Exception message should indicate invalid username command");
    }

    @Test
    void parseUsernameCommandArgs_emptyString_shouldThrowInternityException() {
        InternityException exception = assertThrows(
                InternityException.class,
                () -> ArgumentParser.parseUsernameCommandArgs(""),
                "Expected InternityException for empty input"
        );
        assertTrue(exception.getMessage().toLowerCase().contains("invalid"),
                "Exception message should indicate invalid username command");
    }
}
