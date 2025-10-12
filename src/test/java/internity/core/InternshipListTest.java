package internity.core;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InternshipListTest {

    @Test
    public void listAll_whenEmpty_expectedOutcome() throws InternityException {
        InternshipList.clear();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        InternshipList.listAll();
        System.setOut(originalOut);

        assertEquals("No internships found. Please add an internship first.\r\n", outContent.toString());
    }

    @Test
    public void listAll_withEntry_doesNotOutputNoInternshipsFound() throws Exception {
        InternshipList.clear();

        Internship internship = new Internship("Company A", "Developer", new Date(1,1,2025), 5000);
        InternshipList.add(internship);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        InternshipList.listAll();
        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(!output.contains("No internships found. Please add an internship first."));
    }
}