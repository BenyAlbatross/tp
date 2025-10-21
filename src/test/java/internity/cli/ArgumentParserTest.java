package internity.cli;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.commands.UpdateCommand;
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
    void parseUpdate_validSingleField_returnsCommand() throws InternityException {
        UpdateCommand command = ArgumentParser.parseUpdateCommandArgs("1 company/Meta");
        assertNotNull(command);
    }

    @Test
    void parseUpdate_multipleFields_returnsCommand() throws InternityException {
        UpdateCommand command = ArgumentParser.parseUpdateCommandArgs("1 company/Meta role/AI Intern pay/9000");
        assertNotNull(command);
    }

    @Test
    void parseUpdate_invalidIndex_throwsException() {
        assertThrows(InternityException.class, () -> ArgumentParser.parseUpdateCommandArgs("abc company/Meta"));
    }

    @Test
    void parseUpdate_missingFieldTag_throwsException() {
        assertThrows(InternityException.class, () -> ArgumentParser.parseUpdateCommandArgs("1 Meta"));
    }

    @Test
    void parseUpdate_emptyCompany_throwsException() {
        assertThrows(InternityException.class, () -> ArgumentParser.parseUpdateCommandArgs("1 company/ "));
    }
}
