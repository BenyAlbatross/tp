package internity.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import internity.commands.Command;
import internity.commands.ExitCommand;
import internity.commands.UpdateCommand;
import internity.core.InternityException;
import internity.core.InternshipList;

class ParserTest {
    @Test
    void parseInput_unknownCommand_throwException() {
        Parser parser = new Parser(new InternshipList());
        InternityException exception = assertThrows(
                InternityException.class,
                () -> parser.parseInput("Killer Queen")
        );
        assertEquals("Unknown command: killer", exception.getMessage());
    }

    @Test
    void parseInput_exit_returnsExitCommand() throws InternityException {
        Parser parser = new Parser(new InternshipList());
        Command command = parser.parseInput("exit");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parseInput_validUpdateCommand_returnsUpdateCommandInstance() throws InternityException {
        Parser parser = new Parser(new InternshipList());
        Command command = parser.parseInput("update 1 status/Accepted");
        assertInstanceOf(UpdateCommand.class, command);
    }

    @Test
    void parseInput_invalidUpdateFormat_throwsException() {
        Parser parser = new Parser(new InternshipList());
        InternityException exception = assertThrows(
                InternityException.class,
                () -> parser.parseInput("update 1 Accepted")
        );
        assertEquals("Invalid update command format. Use: update INDEX status/NEW_STATUS", exception.getMessage());
    }

    @Test
    void parseInput_blankInput_throwsException() {
        Parser parser = new Parser(new InternshipList());
        InternityException exception = assertThrows(
                InternityException.class,
                () -> parser.parseInput(" ")
        );
        assertEquals("Input cannot be null or blank", exception.getMessage());
    }
}
