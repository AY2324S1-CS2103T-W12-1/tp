package seedu.HouR.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.HouR.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import seedu.HouR.model.Model;

/**
 * Lists all employees in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all employees";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
