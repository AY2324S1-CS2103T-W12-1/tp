package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;

/**
 * Parses input arguments and creates a new AddLeaveCommand object
 */
public class AddLeaveCommandParser implements Parser<AddLeaveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddLeaveCommand
     * and returns a AddLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLeaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_FROM, PREFIX_TO);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_FROM, PREFIX_TO)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }

        Id id;
        LocalDate startDate;
        LocalDate endDate;

        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_FROM).isEmpty() || argMultimap.getValue(PREFIX_TO).isEmpty()) {
            throw new ParseException(AddLeaveCommand.MISSING_DATE);
        }

        if (argMultimap.getValue(PREFIX_FROM).isPresent() && argMultimap.getValue(PREFIX_TO).isPresent()) {
            startDate = ParserUtil.parseLeaveDate(argMultimap.getValue(PREFIX_FROM).get());
            endDate = ParserUtil.parseLeaveDate(argMultimap.getValue(PREFIX_TO).get());
            if (startDate.isAfter(endDate)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_INVALID_DATE_ORDER)
                );
            }
            return new AddLeaveCommand(id, startDate, endDate);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
