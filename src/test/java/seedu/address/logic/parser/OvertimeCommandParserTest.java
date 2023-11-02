package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPERATION;
import static seedu.address.logic.commands.CommandTestUtil.OPERATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.OPERATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_HOURS_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

class OvertimeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            OvertimeCommand.MESSAGE_USAGE);
    private OvertimeCommandParser parser = new OvertimeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no id specified
        assertParseFailure(parser, OPERATION_DESC_BOB + AMOUNT_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // no operation specified
        assertParseFailure(parser, ID_DESC_BOB + AMOUNT_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // no amount specified
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no id and no operation specified
        assertParseFailure(parser, AMOUNT_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // no id and no amount specified
        assertParseFailure(parser, OPERATION_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // no operation and no amount specified
        assertParseFailure(parser, ID_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // no id, no operation and no amount specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidId_failure() {
        // empty id
        assertParseFailure(parser, PREFIX_ID + OPERATION_DESC_BOB + AMOUNT_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // invalid id
        assertParseFailure(parser, INVALID_ID_DESC + OPERATION_DESC_BOB + AMOUNT_DESC_BOB,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix
        assertParseFailure(parser, "i/" + VALID_ID_BOB + OPERATION_DESC_BOB + AMOUNT_DESC_BOB,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidOperation_failure() {
        // empty operation
        assertParseFailure(parser, ID_DESC_BOB + PREFIX_OPERATION + AMOUNT_DESC_BOB,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix
        assertParseFailure(parser, ID_DESC_BOB + "op/inc" + AMOUNT_DESC_BOB,
                MESSAGE_INVALID_FORMAT);

        // invalid operation
        assertParseFailure(parser, ID_DESC_BOB + INVALID_OPERATION + AMOUNT_DESC_BOB,
                OvertimeCommand.MESSAGE_OPERATION_USAGE);
    }

    @Test
    public void parse_invalidAmount_failure() {
        // empty amount
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_BOB + PREFIX_AMOUNT,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_BOB + "am/5",
                OvertimeCommand.MESSAGE_INVALID_AMOUNT);

        // invalid amount (random string)
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_BOB + PREFIX_AMOUNT + "five",
                OvertimeCommand.MESSAGE_INVALID_AMOUNT);

        // invalid amount (negative)
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_BOB + PREFIX_AMOUNT + "-5",
                OvertimeCommand.MESSAGE_INVALID_AMOUNT);

        // invalid amount (exceeds 72)
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_BOB + PREFIX_AMOUNT + "73",
                OvertimeHours.MESSAGE_CONSTRAINTS);

        // invalid amount (0)
        assertParseFailure(parser, ID_DESC_BOB + OPERATION_DESC_BOB + PREFIX_AMOUNT + "0",
                OvertimeCommand.MESSAGE_INVALID_AMOUNT);
    }

    @Test
    public void parse_success() {
        // valid id, valid operation, valid amount
        OvertimeCommand expectedCommand = new OvertimeCommand(new Id(VALID_ID_BOB),
                new OvertimeHours(VALID_OVERTIME_HOURS_BOB), "inc");
        String userInput = ID_DESC_BOB + OPERATION_DESC_BOB + AMOUNT_DESC_BOB;

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
