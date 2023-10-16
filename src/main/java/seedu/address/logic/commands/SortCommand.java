package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/** Sorts the current list by the desired attribute */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of employees from the last listing by the specified attribute. "
            + "Parameters: by/ [ATTRIBUTE]\n"
            + "Example: " + COMMAND_WORD + " by/ salary";

    public static final String MESSAGE_SUCCESS = "Successfully sorted employees by %1$s. ";
    public static final String MESSAGE_NO_PARAM = "There needs to be an attribute to sort the list by. ";
    public static final String MESSAGE_WRONG_PARAM = "Attribute %1$s cannot be used to sort the list. ";

    private final String attribute;

    public SortCommand(String attribute) {
        requireAllNonNull(attribute);

        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (attribute.toLowerCase().equals("salary")) {
            model.updateSortedEmployeeList("salary");
        } else if (attribute.equals("")) {
            throw new CommandException(String.format(MESSAGE_NO_PARAM, attribute));
        } else {
            throw new CommandException(String.format(MESSAGE_WRONG_PARAM, attribute));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, attribute.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand e = (SortCommand) other;
        return attribute.equals(e.attribute);
    }
}
