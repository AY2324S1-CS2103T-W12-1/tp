package seedu.HouR.logic.parser;

import static seedu.HouR.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Set;
import java.util.stream.Stream;

import seedu.HouR.logic.commands.AddCommand;
import seedu.HouR.logic.parser.exceptions.ParseException;
import seedu.HouR.model.department.Department;
import seedu.HouR.model.employee.Email;
import seedu.HouR.model.employee.Employee;
import seedu.HouR.model.employee.Id;
import seedu.HouR.model.employee.Name;
import seedu.HouR.model.employee.Phone;
import seedu.HouR.model.employee.Position;
import seedu.HouR.model.employee.Salary;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSITION, PREFIX_ID,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SALARY, PREFIX_DEPARTMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_POSITION, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_SALARY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_POSITION, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_SALARY);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());
        Set<Department> departmentList = ParserUtil.parseDepartments(argMultimap.getAllValues(PREFIX_DEPARTMENT));

        Employee employee = new Employee(name, position, id, phone, email, salary, departmentList);

        return new AddCommand(employee);
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
