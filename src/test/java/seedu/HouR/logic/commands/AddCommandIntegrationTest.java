package seedu.HouR.logic.commands;

import static seedu.HouR.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.HouR.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.HouR.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.HouR.logic.Messages;
import seedu.HouR.model.Model;
import seedu.HouR.model.ModelManager;
import seedu.HouR.model.UserPrefs;
import seedu.HouR.model.employee.Employee;
import seedu.HouR.testutil.EmployeeBuilder;

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
