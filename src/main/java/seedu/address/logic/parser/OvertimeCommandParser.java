package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;

import java.util.stream.Stream;

import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

/**
 * Parses input arguments and creates a new OvertimeCommand object
 */
public class OvertimeCommandParser implements Parser<OvertimeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the OvertimeCommand
     * and returns a OvertimeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OvertimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OvertimeCommand.MESSAGE_USAGE));
        }


        Id id;
        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OvertimeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT);

        String operation = argMultimap.getValue(PREFIX_OPERATION).get();
        boolean isIncrement = parseOperation(operation);

        OvertimeHours changeInOvertimeHours = ParserUtil.parseOvertimeHours(argMultimap.getValue(PREFIX_AMOUNT).get());

        return new OvertimeCommand(id, changeInOvertimeHours, isIncrement);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean parseOperation(String operation) throws ParseException {
        if (operation.equals("inc")) {
            return true;
        } else if (operation.equals("dec")) {
            return false;
        } else {
            throw new ParseException(OvertimeCommand.MESSAGE_OPERATION_USAGE);
        }
    }
}
