package ginger;

import ginger.command.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @ParameterizedTest
    @MethodSource("provideValidCommands")
    void parse_validInputs_returnsCorrectCommandType(String input, Class<? extends Command> expectedClass) throws GingerException {
        Command result = Parser.parse(input);
        assertInstanceOf(expectedClass, result);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInputs")
    void parse_invalidInputs_throwsGingerException(String input, String expectedErrorMessage) {
        GingerException exception = assertThrows(GingerException.class, () -> {
            Parser.parse(input);
        });
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    private static Stream<Arguments> provideValidCommands() {
        return Stream.of(
                Arguments.of("todo read book", TodoCommand.class),
                Arguments.of("deadline homework /by 12-12-2025", DeadlineCommand.class),
                Arguments.of("event party /from 20-02-2026 /to 21-02-2026", EventCommand.class),
                Arguments.of("list", ListCommand.class),
                Arguments.of("mark 1", MarkCommand.class),
                Arguments.of("unmark 2", UnmarkCommand.class),
                Arguments.of("delete 3", DeleteCommand.class),
                Arguments.of("bye", ExitCommand.class)
        );
    }

    private static Stream<Arguments> provideInvalidInputs() {
        return Stream.of(
                Arguments.of("blah", "I don't know what that command is!"),
                Arguments.of("todo", "Todo description not provided, meow!"),
                Arguments.of("deadline homework", "I need a /by date for deadlines, meow!"),
                Arguments.of("mark abc", "Meow! That's not a number!")
        );
    }
}