package seedu.hour.logic.commands;


import static seedu.hour.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hour.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hour.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.hour.model.AddressBook;
import seedu.hour.model.Model;
import seedu.hour.model.ModelManager;
import seedu.hour.model.UserPrefs;

class SortCommandTest {

    private static final String SALARY_ATTR = "salary";
    private static final String INVALID_ATTR = "invalid attribute";
    private static final String NO_ATTR = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_sortList_success() {
        SortCommand sortCommand = new SortCommand(SALARY_ATTR);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SALARY_ATTR);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeList(SALARY_ATTR);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidAttributeSortList_failure() {
        SortCommand sortCommand = new SortCommand(INVALID_ATTR);

        String expectedMessage = String.format(SortCommand.MESSAGE_WRONG_ATTR, INVALID_ATTR);

        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    void execute_missingAttributeSortList_failure() {
        SortCommand sortCommand = new SortCommand(NO_ATTR);

        String expectedMessage = SortCommand.MESSAGE_NO_ATTR;

        assertCommandFailure(sortCommand, model, expectedMessage);
    }
}
