package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

/**
 * Updates the overtime hours of an employee.
 */
public class OvertimeCommand extends Command {
    public static final String COMMAND_WORD = "overtime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increases/Decreases (inc/dec) the overtime hours of an employee by the specified amount.\n"
            + "Parameters: " + PREFIX_ID + "ID (must be in the exact EID format) "
            + PREFIX_OPERATION + "OPERATION "
            + PREFIX_AMOUNT + "AMOUNT (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_OPERATION + "inc "
            + PREFIX_AMOUNT + "2";

    public static final String MESSAGE_OVERTIME_INCREASE_SUCCESS = "Overtime hours of employee %1$s increased by %2$s";
    public static final String MESSAGE_OVERTIME_DECREASE_SUCCESS = "Overtime hours of employee %1$s decreased by %2$s";
    public static final String MESSAGE_OPERATION_USAGE = "Operation must be either inc or dec";
    public static final String MESSAGE_INVALID_AMOUNT = "Amount must be a positive integer";
    private final Id targetId;
    private final OvertimeHours changeInOvertimeHours;
    private final boolean isIncrement;

    /**
     * Creates an OvertimeCommand to update the overtime hours of the specified {@code Employee}
     */
    public OvertimeCommand(Id targetId, OvertimeHours changeInOvertimeHours, boolean isIncrement) {
        this.targetId = targetId;
        this.changeInOvertimeHours = changeInOvertimeHours;
        this.isIncrement = isIncrement;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employeeToUpdate = lastShownList.stream().filter(employee -> employee.getId().equals(targetId))
                .findFirst().orElse(null);
        if (employeeToUpdate == null) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID, targetId));
        }

        Employee updatedEmployee = updateEmployeeOvertime(employeeToUpdate);
        model.setEmployee(employeeToUpdate, updatedEmployee);
        if (isIncrement) {
            return new CommandResult(String.format(MESSAGE_OVERTIME_INCREASE_SUCCESS,
                    Messages.formatOvertimeHours(updatedEmployee), changeInOvertimeHours));
        } else {
            return new CommandResult(String.format(MESSAGE_OVERTIME_DECREASE_SUCCESS,
                    Messages.formatOvertimeHours(updatedEmployee), changeInOvertimeHours));
        }
    }

    private Employee updateEmployeeOvertime(Employee employeeToEdit) throws CommandException {
        OvertimeHours updatedOvertimeHours = updateOvertimeHours(employeeToEdit.getOvertimeHours());
        return new Employee(employeeToEdit.getName(), employeeToEdit.getPosition(),
                employeeToEdit.getId(), employeeToEdit.getPhone(), employeeToEdit.getEmail(),
                employeeToEdit.getSalary(), employeeToEdit.getDepartments(), updatedOvertimeHours,
                employeeToEdit.getLeaveList(), employeeToEdit.getRemarkList());
    }

    private OvertimeHours updateOvertimeHours(OvertimeHours overtimeHours) throws CommandException {
        int updatedHours;
        if (isIncrement) {
            updatedHours = overtimeHours.value + changeInOvertimeHours.value;
        } else {
            updatedHours = overtimeHours.value - changeInOvertimeHours.value;
        }
        if (!OvertimeHours.isValidOvertimeHours(updatedHours)) {
            throw new CommandException(OvertimeHours.MESSAGE_CONSTRAINTS);
        }
        return new OvertimeHours(updatedHours);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OvertimeCommand)) {
            return false;
        }

        OvertimeCommand otherOvertimeCommand = (OvertimeCommand) other;
        return targetId.equals(otherOvertimeCommand.targetId)
                && changeInOvertimeHours.equals(otherOvertimeCommand.changeInOvertimeHours)
                && isIncrement == otherOvertimeCommand.isIncrement;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetId", targetId)
                .add("changeInOvertimeHours", changeInOvertimeHours)
                .add("isIncrement", isIncrement)
                .toString();
    }
}
