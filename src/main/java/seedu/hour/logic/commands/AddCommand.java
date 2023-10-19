package seedu.hour.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.hour.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.hour.commons.util.ToStringBuilder;
import seedu.hour.logic.Messages;
import seedu.hour.logic.commands.exceptions.CommandException;
import seedu.hour.model.Model;
import seedu.hour.model.employee.Employee;

/**
 * Adds an employee to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an employee to the employee book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_ID + "ID "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_SALARY + "SALARY "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_POSITION + "Manager "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_SALARY + "$8,500 "
            + PREFIX_DEPARTMENT + "IT "
            + PREFIX_DEPARTMENT + "Management";

    public static final String MESSAGE_SUCCESS = "New employee added: %1$s";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the address book";

    private final Employee toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Employee}
     */
    public AddCommand(Employee employee) {
        requireNonNull(employee);
        toAdd = employee;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEmployee(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.addEmployee(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
