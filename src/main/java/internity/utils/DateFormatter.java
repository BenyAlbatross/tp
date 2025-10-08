package internity.utils;

import internity.core.Date;
import internity.core.InternityException;

public class DateFormatter {
    private DateFormatter() {}

    public static Date parse(String dateString) throws InternityException {
        if (dateString == null || dateString.isBlank()) {
            throw InternityException.invalidInput();
        }

        String trimmed = dateString.trim();

        // Check for an absolute dd/MM/yyyy format
        if (!trimmed.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw InternityException.invalidDateFormat();
        }

        String[] parts = dateString.split("/");

        if (parts.length != 3) {
            throw InternityException.invalidDateFormat();
        }
        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return new Date(day, month, year);
        } catch (NumberFormatException e) {
            throw new InternityException("Date must contain only numbers (expected dd/MM/yyyy)");
        }
    }
}
