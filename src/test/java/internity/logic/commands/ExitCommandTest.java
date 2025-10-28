package internity.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class ExitCommandTest {

    @Test
    void execute_printsExitMessage() {
        ExitCommand exitCommand = new ExitCommand();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            exitCommand.execute();
            assertEquals("Thank you for using Internity! Goodbye!" + System.lineSeparator(),
                    outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void isExit_returnsTrue() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }
}
