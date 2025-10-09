package internity.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internity.core.Date;
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
    void execute_validIndex_updatesStatusSuccessfully() {
        UpdateCommand command = new UpdateCommand(internshipList, 0, "Accepted");
        command.execute();
        assertEquals("Accepted", internshipList.get(0).getStatus());
    }

    @Test
    void execute_invalidIndex_doesNotUpdateStatus() {
        UpdateCommand command = new UpdateCommand(internshipList, 5, "Rejected");
        command.execute();
        assertEquals("Pending", internshipList.get(0).getStatus());
    }

    @Test
    void isExit_always_returnsFalse() {
        UpdateCommand command = new UpdateCommand(internshipList, 0, "Accepted");
        assertFalse(command.isExit());
    }
}

