package internity.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.core.Date;
import internity.core.InternityException;
import internity.core.Internship;
import internity.core.InternshipList;

class UpdateCommandTest {
    private InternshipList internshipList;

    @BeforeEach
    void setUp() {
        internshipList = new InternshipList();
        internshipList.add(new Internship("Google", "SWE Intern", new Date(15, 11, 2025), 8000));
    }

    @Test
    void execute_validArgs_updatesStatusSuccessfully() throws InternityException {
        UpdateCommand command = new UpdateCommand(internshipList, "1 status/Accepted");
        command.execute();
        assertEquals("Accepted", internshipList.get(0).getStatus());
    }

    @Test
    void execute_invalidIndex_doesNotUpdateStatus() throws InternityException {
        UpdateCommand command = new UpdateCommand(internshipList, "5 status/Rejected");
        command.execute();
        assertEquals("Pending", internshipList.get(0).getStatus());
    }

    @Test
    void constructor_invalidFormat_throwsException() {
        assertThrows(InternityException.class, () ->
            new UpdateCommand(internshipList, "1 Accepted")
        );
    }

    @Test
    void isExit_always_returnsFalse() throws InternityException {
        UpdateCommand command = new UpdateCommand(internshipList, "1 status/Accepted");
        assertFalse(command.isExit());
    }
}
