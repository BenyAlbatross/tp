package internity.core;

import java.util.Scanner;
import internity.cli.CommandParser;
import internity.commands.Command;
import internity.ui.Ui;

public class InternityManager {
    private final Scanner scanner;
    private final CommandParser commandParser;

    public InternityManager(String storagePath) {
        this.scanner = new Scanner(System.in);
        Storage storage = new Storage(storagePath);
        InternshipList.setStorage(storage);
        this.commandParser = new CommandParser();
    }

    public void start() {
        loadData();
        Ui.printWelcomeMessage();
        Ui.printGreeting(scanner.nextLine());
        Ui.printHorizontalLine();

        boolean isExit = false;
        while (!isExit && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Ui.printHorizontalLine();
            try {
                Command command = commandParser.parseInput(input);
                command.execute();
                isExit = command.isExit();
                saveData(); // auto-save after each command
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Ui.printHorizontalLine();
        }
        scanner.close();
    }

    private void loadData() {
        try {
            InternshipList.loadFromStorage();
        } catch (Exception e) {
            System.out.println("Warning: Could not load data from storage. Starting with empty list.");
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void saveData() {
        try {
            InternshipList.saveToStorage();
        } catch (Exception e) {
            System.out.println("Warning: Could not save data to storage.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
