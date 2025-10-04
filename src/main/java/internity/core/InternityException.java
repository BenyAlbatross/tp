package internity.core;

public class InternityException extends Exception {
    public InternityException(String message) {
        super(message);
    }

    public static InternityException unknownCommand(String message) {
        return new InternityException("Unknown command detected: " + message);
    }
}
