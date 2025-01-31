package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIds.ID_FIRST_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReportCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ReportCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ReportCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ReportCommandParserTest {

    private ReportCommandParser parser = new ReportCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, ID_FIRST_EMPLOYEE.toString(), new ReportCommand(ID_FIRST_EMPLOYEE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportCommand.MESSAGE_USAGE));
    }
}
