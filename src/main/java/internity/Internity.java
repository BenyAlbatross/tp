package internity;

import java.util.Scanner;

import internity.cli.CommandParser;
import internity.commands.Command;
import internity.core.InternshipList;
import internity.core.Storage;
import internity.ui.Ui;

public class Internity {
    private static final String DEFAULT_STORAGE_PATH = "./data/internships.txt";

    public static void initInternity() {
        // Initialize storage
        Storage storage = new Storage(DEFAULT_STORAGE_PATH);
        InternshipList.setStorage(storage);

        // Load data from storage
        try {
            InternshipList.loadFromStorage();
        } catch (Exception e) {
            System.out.println("Warning: Could not load data from storage. Starting with empty list.");
            System.out.println("Error: " + e.getMessage());
        }

        Ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        Ui.printGreeting(in.nextLine());
        Ui.printHorizontalLine();

        CommandParser commandParser = new CommandParser();
        boolean isExit = false;

        while (!isExit && in.hasNextLine()) {
            String input = in.nextLine();
            Ui.printHorizontalLine();
            try {
                Command command = commandParser.parseInput(input);
                command.execute();
                isExit = command.isExit();

                // Auto-save after each command execution
                try {
                    InternshipList.saveToStorage();
                } catch (Exception e) {
                    System.out.println("Warning: Could not save data to storage.");
                    System.out.println("Error: " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Ui.printHorizontalLine();
        }
        in.close();
    }

    /**
     * Main entry-point for the java.duke.Internity application.
     */
    public static void main(String[] args) {
        initInternity();
    }
}
