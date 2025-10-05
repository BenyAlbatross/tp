package internity;

import java.util.Scanner;

import internity.cli.Parser;
import internity.commands.Command;
import internity.ui.Ui;

public class Internity {


    public static void printGreeting(String input) {
        System.out.println("Hello " + input);
    }

    public static void initInternity() {
        Ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        Ui.printGreeting(in.nextLine());
        Ui.printHorizontalLine();

        Parser parser = new Parser();
        boolean isExit = false;

        while (!isExit && in.hasNextLine()) {
            String input = in.nextLine();
            Ui.printHorizontalLine();
            try {
                Command command = parser.parseInput(input);
                command.execute();
                isExit = command.isExit();
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
