package ginger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ginger.command.Command;
import ginger.command.DeadlineCommand;
import ginger.command.DeleteCommand;
import ginger.command.EventCommand;
import ginger.command.ExitCommand;
import ginger.command.ListCommand;
import ginger.command.MarkCommand;
import ginger.command.TodoCommand;
import ginger.command.UnmarkCommand;

public class Parser {
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND;

        public static CommandType fromString(String s) throws GingerException {
            for (CommandType c : CommandType.values()) {
                if (c.toString().equalsIgnoreCase(s)) {
                    return c;
                }
            }
            throw new GingerException("I don't know what that command is!");
        }
    }

    public static Command parse(String fullCommand) throws GingerException{
        String[] input = fullCommand.split(" ", 2);
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

    private static Command prepareFind(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("I don't know what you're looking for, meow! Please provide a keyword!");
        }
        return new FindCommand(data);
    }
}