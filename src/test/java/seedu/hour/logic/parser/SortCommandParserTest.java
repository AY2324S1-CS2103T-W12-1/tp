package seedu.hour.logic.parser;

import static seedu.hour.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.hour.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hour.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.hour.logic.commands.SortCommand;

class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        String userInput = " " + PREFIX_SORT + "salary";

        SortCommand expectedSortCommand = new SortCommand("salary");

        assertParseSuccess(parser, userInput, expectedSortCommand);
    }
}
