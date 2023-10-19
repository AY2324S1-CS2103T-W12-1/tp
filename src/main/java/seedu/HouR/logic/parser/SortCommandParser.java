package seedu.HouR.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.HouR.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.stream.Stream;

import seedu.HouR.logic.commands.SortCommand;
import seedu.HouR.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT);

        if (!arePrefixesPresent(argMultimap, PREFIX_SORT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String attribute = argMultimap.getValue(PREFIX_SORT).orElse("");

        return new SortCommand(attribute);
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
