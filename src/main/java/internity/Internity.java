package internity;

import internity.core.InternityManager;

public class Internity {
    private static final String DEFAULT_STORAGE_PATH = "./data/internships.txt";

    public static void initInternity() {
        InternityManager manager = new InternityManager(DEFAULT_STORAGE_PATH);
        manager.start();
    }

    /**
     * Main entry-point for the java.duke.Internity application.
     */
    public static void main(String[] args) {
        initInternity();
    }
}
