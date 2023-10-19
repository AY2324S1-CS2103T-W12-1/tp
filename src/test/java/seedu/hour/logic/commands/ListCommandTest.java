package seedu.hour.logic.commands;

import static seedu.hour.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hour.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.hour.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.hour.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.hour.model.Model;
import seedu.hour.model.ModelManager;
import seedu.hour.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
