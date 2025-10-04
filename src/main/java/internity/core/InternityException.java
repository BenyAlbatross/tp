package internity.core;

public class InternityException extends Exception {
    public InternityException(String message) {
        super(message);
    }

    public static InternityException invalidInput() {
        return new InternityException("Input cannot be null or blank");
    }

    public static InternityException unknownCommand(String message) {
        return new InternityException("Unknown command detected: " + message);
    }
}
