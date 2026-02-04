public class Parser {
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE;

        public static CommandType fromString(String s) throws GingerException {
            for (CommandType c : CommandType.values()) {
                if (c.toString().equalsIgnoreCase(s)) {
                    return c;
                }
            }
            throw new GingerException("I don't know what that command is!");
        }
    }

    public Command parse(String fullCommand) throws GingerException{
        String[] input = fullCommand.split(" ", 2);
        CommandType commandType = CommandType.fromString(input[0]);
        String data = input.length > 1 ? input[1] : "";

        return switch (commandType) {
            case BYE -> new ExitCommand();
            case LIST -> new ListCommand();
            case MARK -> new MarkCommand(this.parseIndex(data));
            case UNMARK -> new UnmarkCommand(this.parseIndex(data));
            case TODO -> this.prepareTodo(data);
            case DEADLINE -> this.prepareDeadline(data);
            case EVENT -> this.prepareEvent(data);
            case DELETE -> new DeleteCommand(this.parseIndex(data));
        };
    }

    private int parseIndex(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("No number provided!");
        }
        int index;
        try {
            index = Integer.parseInt(data) - 1;
        } catch (NumberFormatException e) {
            throw new GingerException("Meow! That's not a number!");
        }

        return index;
    }

    private Command prepareTodo(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("Todo description not provided, meow!");
        }
        return new TodoCommand(data);
    }

    private Command prepareDeadline(String data) throws GingerException {
        int byIndex = data.indexOf("/by");

        if (byIndex == -1) {
            throw new GingerException("I need a /by date for deadlines, meow!");
        }

        String description = data.substring(0, byIndex).trim();
        String by = data.substring(byIndex + 3).trim(); // +3 to skip "/by"
        if (description.isBlank()) {
            throw new GingerException("Deadline description not provided, meow!");
        } else if (by.isBlank()) {
            throw new GingerException("Task deadline not provided, meow!");
        }

        return new DeadlineCommand(description, by);
    }

    private Command prepareEvent(String data) throws GingerException {
        int fromIndex = data.indexOf("/from");
        int toIndex = data.indexOf("/to");

        if (fromIndex == -1) {
            throw new GingerException("I need a /from date or time, meow!");
        } else if (toIndex == -1) {
            throw new GingerException("I need a /to date or time, meow!");
        }

        String description = data.substring(0, fromIndex).trim();
        String from = data.substring(fromIndex + 6, toIndex).trim();
        String to = data.substring(toIndex + 4);
        if (description.isBlank()) {
            throw new GingerException("Event description not provided, meow!");
        } else if (from.isBlank()) {
            throw new GingerException("Event start time not provided, meow!");
        } else if (to.isBlank()) {
            throw new GingerException("Event end time not provided, meow!");
        }

        return new EventCommand(description, from, to);
    }
}