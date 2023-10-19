package seedu.HouR.logic.parser;

import static seedu.HouR.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.HouR.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.HouR.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.HouR.testutil.TypicalIds.ID_FIRST_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.HouR.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, ID_FIRST_EMPLOYEE.toString(), new DeleteCommand(ID_FIRST_EMPLOYEE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
