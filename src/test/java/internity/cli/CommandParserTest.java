package internity.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import internity.commands.Command;
import internity.commands.ExitCommand;
import internity.core.InternityException;

class CommandParserTest {
    @Test
    void parseInput_unknownCommand_throwException() {
        CommandParser commandParser = new CommandParser();
        InternityException exception = assertThrows(
                InternityException.class,
                () -> commandParser.parseInput("Killer Queen")
        );
        assertEquals("Unknown command: killer", exception.getMessage());
    }

    @Test
    void parseInput_exit_returnsExitCommand() throws InternityException {
        CommandParser commandParser = new CommandParser();
        Command command = commandParser.parseInput("exit");
        assertInstanceOf(ExitCommand.class, command);
    }
}
