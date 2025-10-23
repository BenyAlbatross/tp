package internity.core;

import java.util.Scanner;
import internity.cli.CommandParser;
import internity.commands.Command;
import internity.ui.Ui;

/**
 * The {@code InternityManager} class serves as the central controller
 * for the Internity application. It manages the main program loop,
 * user interaction, command parsing, and data persistence.
 * <p>
 * Responsibilities include:
 * <ul>
 *     <li>Initializing core components such as storage and parser.</li>
 *     <li>Loading and saving internship data from persistent storage.</li>
 *     <li>Handling user input via the command-line interface.</li>
 *     <li>Executing parsed commands and managing application flow.</li>
 * </ul>
 */
public class InternityManager {
    private final Scanner scanner;
    private final CommandParser commandParser;

    /**
     * Constructs a new {@code InternityManager} instance.
     *
     * @param storagePath the path to the file or directory used for storing internship data
     */
    public InternityManager(String storagePath) {
        this.scanner = new Scanner(System.in);
        Storage storage = new Storage(storagePath);
        InternshipList.setStorage(storage);
        this.commandParser = new CommandParser();
    }

    /**
     * Starts the Internity application.
     * <p>
     * This method performs the following tasks in sequence:
     * <ol>
     *     <li>Redirects error output to standard output for ordered display.</li>
     *     <li>Loads saved internship data from storage.</li>
     *     <li>Prints the welcome message and configures the username if necessary.</li>
     *     <li>Begins the main command loop, processing user input until an exit command is issued.</li>
     * </ol>
     */
    public void start() {
        System.setErr(System.out);
        loadData();
        Ui.printWelcomeMessage();
        configureUsername();
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

    /**
     * Loads previously saved internship data from storage.
     * <p>
     * If loading fails, a warning message is displayed and the application
     * starts with an empty internship list.
     */
    private void loadData() {
        try {
            InternshipList.loadFromStorage();
        } catch (Exception e) {
            System.out.println("Warning: Could not load data from storage. Starting with empty list.");
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Saves the current internship data to persistent storage.
     * <p>
     * Displays a warning message if saving fails.
     */
    private void saveData() {
        try {
            InternshipList.saveToStorage();
        } catch (Exception e) {
            System.out.println("Warning: Could not save data to storage.");
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Configures the username for the current session.
     * <p>
     * If a username is not yet set, the method prompts the user to input one.
     * The configured username is then displayed as part of a personalized greeting.
     */
    private void configureUsername() {
        if (InternshipList.getUsername() == null) {
            String username = scanner.nextLine();
            InternshipList.setUsername(username);
        }
        Ui.printGreeting(InternshipList.getUsername());
    }
}
