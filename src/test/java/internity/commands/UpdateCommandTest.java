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
    @BeforeEach
    void setUp() {
        InternshipList.clear();
        InternshipList.add(new Internship("Google", "SWE Intern", new Date(15, 11, 2025), 8000));
    }

    @Test
    void execute_validStatus_updatesStatusSuccessfully() throws InternityException {
        UpdateCommand command = new UpdateCommand(0, "Accepted");
        command.execute();
        assertEquals("Accepted", InternshipList.get(0).getStatus());
    }

    @Test
    void execute_validCompany_updatesCompanySuccessfully() throws InternityException {
        UpdateCommand command = new UpdateCommand(0, "ByteDance", null, null, null, null);
        command.execute();
        assertEquals("ByteDance", InternshipList.get(0).getCompany());
    }

    @Test
    void execute_validRole_updatesRoleSuccessfully() throws InternityException {
        UpdateCommand command = new UpdateCommand(0, null, "Backend Intern", null, null, null);
        command.execute();
        assertEquals("Backend Intern", InternshipList.get(0).getRole());
    }

    @Test
    void execute_validDeadline_updatesDeadlineSuccessfully() throws InternityException {
        Date newDeadline = new Date(1, 12, 2025);
        UpdateCommand command = new UpdateCommand(0, null, null, newDeadline, null, null);
        command.execute();
        assertEquals("01-12-2025", InternshipList.get(0).getDeadline().toString());
    }

    @Test
    void execute_validPay_updatesPaySuccessfully() throws InternityException {
        UpdateCommand command = new UpdateCommand(0, null, null, null, 10000, null);
        command.execute();
        assertEquals(10000, InternshipList.get(0).getPay());
    }

    @Test
    void execute_multipleFields_updatesAllSuccessfully() throws InternityException {
        Date newDeadline = new Date(1, 12, 2025);
        UpdateCommand command = new UpdateCommand(0, "Meta", "AI Research Intern", newDeadline, 12000, "Offer");
        command.execute();
        Internship updated = InternshipList.get(0);
        assertEquals("Meta", updated.getCompany());
        assertEquals("AI Research Intern", updated.getRole());
        assertEquals("01-12-2025", updated.getDeadline().toString());
        assertEquals(12000, updated.getPay());
        assertEquals("Offer", updated.getStatus());
    }

    @Test
    void execute_invalidIndex_throwsException() {
        UpdateCommand command = new UpdateCommand(5, "Accepted");
        assertThrows(InternityException.class, command::execute);
    }

    @Test
    void execute_noFieldsProvided_throwsException() {
        UpdateCommand command = new UpdateCommand(0, null, null, null, null, null);
        assertThrows(InternityException.class, command::execute);
    }

    @Test
    void isExit_always_returnsFalse() {
        UpdateCommand command = new UpdateCommand(0, "Accepted");
        assertFalse(command.isExit());
    }
}
