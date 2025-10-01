package seedu.duke;

import java.util.Scanner;

public class Internity {
    public static void initInternity() {
        String logo = " ___       _                  _ _         \n" +
                "|_ _|_ __ | |_ ___ _ __ _ __ (_) |_ _   _ \n" +
                " | || '_ \\| __/ _ \\ '__| '_ \\| | __| | | |\n" +
                " | || | | | ||  __/ |  | | | | | |_| |_| |\n" +
                "|___|_| |_|\\__\\___|_|  |_| |_|_|\\__|\\__, |\n" +
                "                                    |___/ ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        boolean isExit = false;
        while (!isExit) {
            String s = in.nextLine();
            if (s.equals("bye")) {
                isExit = true;
            }
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
