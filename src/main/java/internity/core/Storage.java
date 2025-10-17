package internity.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles loading and saving internships to a file for persistent storage.
 * The storage format is a pipe-delimited text file where each line represents one internship.
 * Format: company | role | deadline (DD-MM-YYYY) | pay | status
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    static {
        logger.setLevel(Level.WARNING);
    }

    private final Path filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file for storing internships.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads internships from the storage file.
     *
     * @return ArrayList of internships loaded from the file.
     * @throws InternityException If there is an error reading the file.
     */
    public ArrayList<Internship> load() throws InternityException {
        logger.info("Loading internships from: " + filePath);
        ArrayList<Internship> internships = new ArrayList<>();

        if (!Files.exists(filePath)) {
            logger.info("Storage file does not exist. Starting with empty list.");
            return internships; // First run: nothing to load
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                Internship internship = parseInternshipFromFile(line);
                if (internship != null) {
                    internships.add(internship);
                } else {
                    logger.warning("Skipped corrupted line " + lineNumber + ": " + line);
                    System.out.println("Warning: Skipped corrupted line in storage file: " + line);
                }
            }
        } catch (IOException e) {
            logger.severe("Failed to load internships from " + filePath + ": " + e.getMessage());
            throw new InternityException("Could not load internships: " + e.getMessage());
        }

        logger.info("Successfully loaded " + internships.size() + " internships");
        return internships;
    }

    /**
     * Parses a single line from the storage file into an Internship object.
     *
     * @param line The line to parse.
     * @return The parsed Internship, or null if the line is corrupted.
     */
    private Internship parseInternshipFromFile(String line) {
        assert line != null : "Line to parse cannot be null";

        String[] parts = line.split("\\|");

        // Trim all parts
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        if (parts.length < 5) {
            return null; // Corrupted line - line has fewer than 5 fields
        }

        try {
            String company = parts[0];
            String role = parts[1];
            String deadlineStr = parts[2];
            int pay = Integer.parseInt(parts[3]);
            String status = parts[4];

            // Parse the date (format: DD-MM-YYYY)
            String[] dateParts = deadlineStr.split("-");
            if (dateParts.length != 3) {
                return null; // Invalid date format
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            Date deadline = new Date(day, month, year);

            Internship internship = new Internship(company, role, deadline, pay);
            internship.setStatus(status);

            return internship;
        } catch (NumberFormatException e) {
            return null; // Invalid number format
        }
    }

    /**
     * Saves internships to the storage file.
     *
     * @param internships The list of internships to save.
     * @throws InternityException If there is an error writing to the file.
     */
    public void save(ArrayList<Internship> internships) throws InternityException {
        assert internships != null : "Internships list cannot be null";

        logger.info("Saving " + internships.size() + " internships to: " + filePath);

        try {
            // Create parent directories if they don't exist
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
                logger.fine("Created parent directories for: " + filePath);
            }

            try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath.toFile())))) {
                for (Internship internship : internships) {
                    pw.println(formatInternshipForFile(internship));
                }
            }
            logger.info("Successfully saved " + internships.size() + " internships");
        } catch (IOException e) {
            logger.severe("Failed to save internships to " + filePath + ": " + e.getMessage());
            throw new InternityException("Could not save internships: " + e.getMessage());
        }
    }

    /**
     * Formats an internship for storage in the file.
     *
     * @param internship The internship to format.
     * @return A pipe-delimited string representation of the internship.
     */
    private String formatInternshipForFile(Internship internship) {
        assert internship != null : "Internship to format cannot be null";
        assert internship.getCompany() != null : "Company cannot be null";
        assert internship.getRole() != null : "Role cannot be null";
        assert internship.getDeadline() != null : "Deadline cannot be null";
        assert internship.getStatus() != null : "Status cannot be null";

        return internship.getCompany() + " | "
                + internship.getRole() + " | "
                + internship.getDeadline().toString() + " | "
                + internship.getPay() + " | "
                + internship.getStatus();
    }
}
