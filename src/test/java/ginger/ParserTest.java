package ginger;

import ginger.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void parse_validTodo_returnsTodoCommand() throws GingerException {
        Command result = Parser.parse("todo test");
        assertInstanceOf(TodoCommand.class, result);
    }

    @Test
    void parse_validDeadline_returnsDeadlineCommand() throws GingerException {
        Command result = Parser.parse("deadline test /by 05-05-2026");
        assertInstanceOf(DeadlineCommand.class, result);
    }

    @Test
    void parse_validEvent_returnsEventCommand() throws GingerException {
        Command result = Parser.parse("event test /from 5pm /to 7pm");
        assertInstanceOf(EventCommand.class, result);
    }

    @Test
    void parse_invalidCommand_exceptionThrown() throws GingerException {
        GingerException exception = assertThrows(GingerException.class, () -> {
            Parser.parse("aabbcc");
        });

        assertEquals("I don't know what that command is!", exception.getMessage());
    }


}