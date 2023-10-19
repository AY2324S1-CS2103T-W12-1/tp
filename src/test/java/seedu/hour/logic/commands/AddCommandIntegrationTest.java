package seedu.hour.logic.commands;

import static seedu.hour.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hour.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hour.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.hour.logic.Messages;
import seedu.hour.model.Model;
import seedu.hour.model.ModelManager;
import seedu.hour.model.UserPrefs;
import seedu.hour.model.employee.Employee;
import seedu.hour.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newEmployee_success() {
        Employee validEmployee = new EmployeeBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEmployee(validEmployee);

        assertCommandSuccess(new AddCommand(validEmployee), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validEmployee)),
                expectedModel);
    }

    @Test
    public void execute_duplicateEmployee_throwsCommandException() {
        Employee employeeInList = model.getAddressBook().getEmployeeList().get(0);
        assertCommandFailure(new AddCommand(employeeInList), model,
                AddCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

}
