package seedu.hour.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hour.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hour.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.hour.testutil.Assert.assertThrows;
import static seedu.hour.testutil.TypicalIds.ID_FIRST_EMPLOYEE;
import static seedu.hour.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.hour.logic.commands.AddCommand;
import seedu.hour.logic.commands.ClearCommand;
import seedu.hour.logic.commands.DeleteCommand;
import seedu.hour.logic.commands.EditCommand;
import seedu.hour.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.hour.logic.commands.ExitCommand;
import seedu.hour.logic.commands.FindCommand;
import seedu.hour.logic.commands.HelpCommand;
import seedu.hour.logic.commands.ListCommand;
import seedu.hour.logic.commands.SortCommand;
import seedu.hour.logic.parser.exceptions.ParseException;
import seedu.hour.model.employee.Employee;
import seedu.hour.model.employee.NameContainsKeywordsPredicate;
import seedu.hour.testutil.EditEmployeeDescriptorBuilder;
import seedu.hour.testutil.EmployeeBuilder;
import seedu.hour.testutil.EmployeeUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EmployeeUtil.getAddCommand(employee));
        assertEquals(new AddCommand(employee), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + ID_FIRST_EMPLOYEE);
        assertEquals(new DeleteCommand(ID_FIRST_EMPLOYEE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EMPLOYEE.getOneBased() + " " + EmployeeUtil.getEditEmployeeDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_EMPLOYEE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " by/ " + "salary");
        assertEquals(new SortCommand("salary"), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
