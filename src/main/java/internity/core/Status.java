package internity.core;

/**
 * Represents the set of valid internship statuses within the Internity application.
 * Each status describes a stage in the internship application process.
 *
 * <p>Valid statuses include:
 * <ul>
 *     <li>Pending</li>
 *     <li>Interested</li>
 *     <li>Applied</li>
 *     <li>Interviewing</li>
 *     <li>Offer</li>
 *     <li>Accepted</li>
 *     <li>Rejected</li>
 * </ul>
 *
 * <p>This enum provides utility methods for validating and normalizing
 * user input so that status values remain consistent across parsing,
 * storage, and display.
 */
public enum Status {
    PENDING,
    INTERESTED,
    APPLIED,
    INTERVIEWING,
    OFFER,
    ACCEPTED,
    REJECTED;

    /**
     * Checks whether the provided string corresponds to a valid {@link Status}.
     * The comparison is case-insensitive.
     *
     * @param s The status string to validate.
     * @return {@code true} if the string matches a valid status, {@code false} otherwise.
     */
    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        try {
            Status.valueOf(s.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Converts a valid status string into a canonical (Title Case) form
     * for consistent display and storage.
     *
     * <p>Example:
     * <ul>
     *     <li>"interviewing" → "Interviewing"</li>
     * </ul>
     *
     * @param s The input status string (must be valid).
     * @return The canonical Title Case version of the status.
     * @throws IllegalArgumentException If the input does not match any valid status.
     */
    public static String canonical(String s) {
        assert s != null : "Status string cannot be null";
        String upper = s.trim().toUpperCase();
        Status e = Status.valueOf(upper);
        String lower = e.name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
