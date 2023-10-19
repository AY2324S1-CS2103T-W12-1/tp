package seedu.hour.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hour.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.hour.commons.core.index.Index;
import seedu.hour.logic.commands.EditCommand;
import seedu.hour.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.hour.logic.parser.exceptions.ParseException;
import seedu.hour.model.department.Department;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSITION, PREFIX_ID,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SALARY, PREFIX_DEPARTMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_POSITION, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_SALARY);

        EditEmployeeDescriptor editEmployeeDescriptor = new EditEmployeeDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEmployeeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editEmployeeDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editEmployeeDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editEmployeeDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editEmployeeDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editEmployeeDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        parseDepartmentsForEdit(argMultimap.getAllValues(PREFIX_DEPARTMENT))
                .ifPresent(editEmployeeDescriptor::setDepartments);

        if (!editEmployeeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEmployeeDescriptor);
    }

    /**
     * Parses {@code Collection<String> departments} into a {@code Set<Department>}
     * if {@code departments} is non-empty.
     * If {@code departments} contain only one element which is an empty string, it
     * will be parsed into a
     * {@code Set<Department>} containing zero departments.
     */
    private Optional<Set<Department>> parseDepartmentsForEdit(Collection<String> departments) throws ParseException {
        assert departments != null;

        if (departments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> departmentSet = departments.size() == 1 && departments.contains("")
                ? Collections.emptySet()
                : departments;
        return Optional.of(ParserUtil.parseDepartments(departmentSet));
    }

}
