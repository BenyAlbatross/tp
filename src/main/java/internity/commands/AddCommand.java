package internity.commands;

import internity.core.Date;
import internity.core.InternityException;
import internity.utils.DateFormatter;

public class AddCommand extends Command {
    private String company;
    private String role;
    private Date deadline;
    private String pay;

    public AddCommand(String args) throws InternityException {
        extractArgs(args);
    }

    private void extractArgs(String args) throws InternityException {
        try {
            String[] parts = args.split("\\s+(?=company/|role/|deadline/|pay/)");

            for (String part : parts) {
                part = part.trim();

                String key = part.contains("/") ? part.substring(0, part.indexOf("/")) : "";
                String value = part.contains("/") ? part.substring(part.indexOf("/") + 1).trim() : "";

                switch (key) {
                    case "company" -> company = value;
                    case "role" -> role = value;
                    case "deadline" -> deadline = DateFormatter.parse(value);
                    case "pay" -> pay = value;
                    default -> {
                        throw new InternityException("Error");
                    }
                }
            }
        } catch (Exception e) {
            throw new InternityException("Error");
        }
    }

    @Override
    public void execute() throws InternityException {}

    @Override
    public boolean isExit() {
        return false;
    }
}
