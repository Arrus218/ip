package ginger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ginger.command.Command;
import ginger.command.DeadlineCommand;
import ginger.command.DeleteCommand;
import ginger.command.EventCommand;
import ginger.command.ExitCommand;
import ginger.command.FindCommand;
import ginger.command.HelpCommand;
import ginger.command.ListCommand;
import ginger.command.MarkCommand;
import ginger.command.TodoCommand;
import ginger.command.UnmarkCommand;

/**
 * Processes user input strings and transforms them into executable Command objects.
 * <p>
 * This class serves as the main entry point for interpreting raw user strings,
 * handling command identification, data validation, and date parsing.
 * It ensures that all inputs follow the expected syntax before creating tasks.
 * </p>
 */
public class Parser {
    /**
     * Represents the valid command keywords supported by Ginger.
     */
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        HELP;

        /**
         * Converts a string into a {@code CommandType}.
         *
         * @param s The raw command word.
         * @return The matching {@code CommandType}.
         * @throws GingerException If the command word is not recognized.
         */
        public static CommandType fromString(String s) throws GingerException {
            for (CommandType c : CommandType.values()) {
                if (c.toString().equalsIgnoreCase(s)) {
                    return c;
                }
            }
            throw new GingerException("I don't know what that command is!");
        }
    }

    /**
     * Parses the raw user input and returns the corresponding executable command.
     * <p>
     * This method splits the input into a command word and its arguments. It identifies
     * the command type and delegates further parsing of arguments to specialized
     * helper methods like {@code prepareDeadline} or {@code prepareEvent}.
     * </p>
     *
     * @param fullCommand The complete line of text entered by the user.
     * @return A {@code Command} object ready for execution.
     * @throws GingerException If the command word is unrecognized or if the
     *                         arguments provided are invalid/missing.
     */
    public static Command parse(String fullCommand) throws GingerException {
        String[] input = fullCommand.split(" ", 2);
        assert input.length > 0 : "Split command should always result in at least one element in array";
        CommandType commandType = CommandType.fromString(input[0]);
        String data = input.length > 1 ? input[1] : "";

        return switch (commandType) {
            case BYE -> new ExitCommand();
            case LIST -> new ListCommand();
            case MARK -> new MarkCommand(Parser.parseIndex(data));
            case UNMARK -> new UnmarkCommand(Parser.parseIndex(data));
            case TODO -> Parser.prepareTodo(data);
            case DEADLINE -> Parser.prepareDeadline(data);
            case EVENT -> Parser.prepareEvent(data);
            case DELETE -> new DeleteCommand(Parser.parseIndex(data));
            case FIND -> Parser.prepareFind(data);
            case HELP -> new HelpCommand();
        };
    }

    private static int parseIndex(String data) throws GingerException {
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

    private static Command prepareTodo(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("Todo description not provided, meow!");
        }
        return new TodoCommand(data);
    }

    private static Command prepareDeadline(String data) throws GingerException {
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

        try {
            LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new DeadlineCommand(description, date);
        } catch (DateTimeParseException e) {
            throw new GingerException("Deadline format is wrong, meow! Please use DD-MM-YYYY format.");
        }
    }

    private static Command prepareEvent(String data) throws GingerException {
        int fromIndex = data.indexOf("/from");
        int toIndex = data.indexOf("/to");

        if (fromIndex == -1) {
            throw new GingerException("I need a /from date, meow!");
        } else if (toIndex == -1) {
            throw new GingerException("I need a /to date, meow!");
        }

        String description = data.substring(0, fromIndex).trim();
        String from = data.substring(fromIndex + 6, toIndex).trim();
        String to = data.substring(toIndex + 4);
        if (description.isBlank()) {
            throw new GingerException("Event description not provided, meow!");
        } else if (from.isBlank()) {
            throw new GingerException("Event start date not provided, meow!");
        } else if (to.isBlank()) {
            throw new GingerException("Event end date not provided, meow!");
        }

        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (!toDate.isAfter(fromDate)) {
            throw new GingerException("Nyaa???? The end date must be after the start date!");
        }

        return new EventCommand(description, fromDate, toDate);
    }

    private static Command prepareFind(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("I don't know what you're looking for, meow! Please provide a keyword!");
        }
        return new FindCommand(data);
    }
}
