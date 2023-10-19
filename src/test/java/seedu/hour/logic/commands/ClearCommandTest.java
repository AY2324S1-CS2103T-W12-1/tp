package seedu.hour.logic.commands;

import static seedu.hour.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hour.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.hour.model.AddressBook;
import seedu.hour.model.Model;
import seedu.hour.model.ModelManager;
import seedu.hour.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
