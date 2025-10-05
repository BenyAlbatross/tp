package internity.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import internity.commands.Command;
import internity.commands.ExitCommand;
import internity.core.InternityException;

class ParserTest {
    @Test
    void parseInput_unknownCommand_throwException() {
        Parser parser = new Parser();
        InternityException exception = assertThrows(InternityException.class, () -> {
            parser.parseInput("Killer Queen");
        });
        assertEquals("Unknown command: killer", exception.getMessage());
    }

    @Test
    void parseInput_ExitCommand_returnsExitCommand() throws InternityException {
        Parser parser = new Parser();
        Command command = parser.parseInput("exit");
        assertInstanceOf(ExitCommand.class, command);
    }
}
