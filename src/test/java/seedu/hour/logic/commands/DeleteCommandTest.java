package seedu.hour.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hour.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hour.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hour.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.hour.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.hour.testutil.TypicalIds.ID_EMPLOYEE_NOT_EXISTS;
import static seedu.hour.testutil.TypicalIds.ID_FIRST_EMPLOYEE;
import static seedu.hour.testutil.TypicalIds.ID_SECOND_EMPLOYEE;
import static seedu.hour.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hour.logic.Messages;
import seedu.hour.model.Model;
import seedu.hour.model.ModelManager;
import seedu.hour.model.UserPrefs;
import seedu.hour.model.employee.Employee;
import seedu.hour.model.employee.Id;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Employee employeeToDelete = null;
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        for (Employee employee : lastShownList) {
            if (employee.getId().equals(ID_FIRST_EMPLOYEE)) {
                employeeToDelete = employee;
                break;
            }
        }

        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_EMPLOYEE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS,
                Messages.format(employeeToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);

        // Test for delete same id
        deleteCommand = new DeleteCommand(ID_FIRST_EMPLOYEE);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(ID_EMPLOYEE_NOT_EXISTS);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        // Delete first employee
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        Employee employeeToDelete = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_EMPLOYEE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS,
                Messages.format(employeeToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);
        showNoEmployee(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdFilteredList_throwsCommandException() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        Id invalidId = ID_SECOND_EMPLOYEE;

        DeleteCommand deleteCommand = new DeleteCommand(invalidId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ID_FIRST_EMPLOYEE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(ID_SECOND_EMPLOYEE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ID_FIRST_EMPLOYEE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        //Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_EMPLOYEE);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetId=" + ID_FIRST_EMPLOYEE + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEmployee(Model model) {
        model.updateFilteredEmployeeList(p -> false);

        assertTrue(model.getFilteredEmployeeList().isEmpty());
    }
}
