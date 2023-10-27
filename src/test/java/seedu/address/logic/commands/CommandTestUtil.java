package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_POSITION_AMY = "Manager";
    public static final String VALID_POSITION_BOB = "Junior Manager";
    public static final String VALID_ID_AMY = "EID8765-1234";
    public static final String VALID_ID_BOB = "EID4030-1020";
    public static final String VALID_PHONE_AMY = "91234567";
    public static final String VALID_PHONE_BOB = "92424242";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DEPARTMENT_FINANCE = "Finance";
    public static final String VALID_DEPARTMENT_IT = "IT";
    public static final String VALID_SALARY_AMY = "7000";
    public static final String VALID_SALARY_BOB = "5000";
    public static final int VALID_OVERTIME_HOURS_AMY = 72;
    public static final int VALID_OVERTIME_HOURS_BOB = 70;
    public static final boolean VALID_IS_ON_LEAVE_AMY = false;
    public static final boolean VALID_IS_ON_LEAVE_BOB = true;
    public static final ArrayList<Leave> VALID_LEAVELIST_AMY = new ArrayList<>();
    public static final ArrayList<Leave> VALID_LEAVELIST_BOB = new ArrayList<>();
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String POSITION_DESC_AMY = " " + PREFIX_POSITION + VALID_POSITION_AMY;
    public static final String POSITION_DESC_BOB = " " + PREFIX_POSITION + VALID_POSITION_BOB;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String DEPARTMENT_DESC_FRIEND = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_FINANCE;
    public static final String DEPARTMENT_DESC_HUSBAND = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_IT;

    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION; // empty string not allowed for positions
    public static final String INVALID_ID_DESC = " " + PREFIX_ID; // empty string not allowed for ids
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_DEPARTMENT_DESC = " "
            + PREFIX_DEPARTMENT + "Finance*"; // '*' not allowed in departments
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "10,000"; // missing '$' symbol

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEmployeeDescriptor DESC_AMY;
    public static final EditCommand.EditEmployeeDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY).withPosition(VALID_POSITION_AMY)
                .withId(VALID_ID_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withDepartments(VALID_DEPARTMENT_FINANCE)
                .withSalary(VALID_SALARY_AMY).build();
        DESC_BOB = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).withPosition(VALID_POSITION_BOB)
                .withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withDepartments(VALID_DEPARTMENT_FINANCE, VALID_DEPARTMENT_IT)
                .withSalary(VALID_SALARY_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered employee list and selected employee in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Employee> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEmployeeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredEmployeeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the employee at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEmployeeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEmployeeList().size());

        Employee employee = model.getFilteredEmployeeList().get(targetIndex.getZeroBased());
        final String[] splitName = employee.getName().fullName.split("\\s+");
        model.updateFilteredEmployeeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEmployeeList().size());
    }
}
