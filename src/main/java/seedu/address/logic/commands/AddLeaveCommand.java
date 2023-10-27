package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.LeaveList;

/**
 * Adds a leave period of an employee.
 */
public class AddLeaveCommand extends Command {
    public static final String COMMAND_WORD = "addleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a leave period of an employee to the employee book. "
            + "Parameters: "
            + PREFIX_ID + "EMPLOYEE ID "
            + PREFIX_FROM + "START DATE "
            + PREFIX_TO + "END DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "EID1234-5678 "
            + PREFIX_FROM + "26-12-2023 "
            + PREFIX_TO + "28-12-2023";

    public static final String MESSAGE_SUCCESS = "New leave period added: %1$s";
    public static final String MISSING_DATE = "No date given! "
            + "Leaves must have date in dd-MM-yyyy format.";
    public static final String MESSAGE_INVALID_DATE_ORDER = "START DATE should not be after END DATE!";
    public static final String MESSAGE_DUPLICATE_LEAVE = "This leave period overlaps with an existing leave";
    public static final String MESSAGE_NOT_ENOUGH_LEAVES = "This leave period exceeds the number of leaves remaining";
    private final Id targetId;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates an AddCommand to add the specified {@code Employee}
     */
    public AddLeaveCommand(Id targetId, LocalDate from, LocalDate to) {
        requireAllNonNull(from, to);

        this.targetId = targetId;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        for (Employee employee : lastShownList) {
            if (employee.getId().equals(targetId)) {
                LeaveList leaveListToUpdate = employee.getLeaveList();
                LeaveList updatedList = addLeavesToList(leaveListToUpdate, from, to);

                Employee employeeWithLeave = new Employee(employee.getName(), employee.getPosition(), employee.getId(),
                        employee.getPhone(), employee.getEmail(), employee.getSalary(), employee.getDepartments(),
                        employee.getIsOnLeave(), employee.getOvertimeHours(), updatedList);

                model.setEmployee(employee, employeeWithLeave);
                model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
                return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatLeaves(employeeWithLeave)));
            }
        }
        throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    private ArrayList<Leave> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        ArrayList<Leave> dates = new ArrayList<>();
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        for (int i = 0; i <= numOfDays; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            dates.add(new Leave(currentDate));
        }
        return dates;
    }

    /**
     * Adds all dates from the start to end date to the LeaveList as Leaves.
     * @param currentList The LeaveList to add to
     * @param startDate The start date of the leave period
     * @param endDate The end date of the leave period
     * @return LeaveList with leaves added
     * @throws CommandException if the user input does not conform the expected format
     */
    private LeaveList addLeavesToList(LeaveList currentList, LocalDate startDate, LocalDate endDate)
            throws CommandException {
        ArrayList<Leave> datesToAdd = getDatesBetween(startDate, endDate);
        if ((Employee.MAX_NUM_OF_LEAVES - currentList.getSize()) < datesToAdd.size()) {
            throw new CommandException(MESSAGE_NOT_ENOUGH_LEAVES);
        }
        for (Leave leaveDate : datesToAdd) { // ensure no duplicate dates
            if (currentList.contains(leaveDate)) {
                throw new CommandException(MESSAGE_DUPLICATE_LEAVE);
            }
        }
        for (Leave leaveDate : datesToAdd) {
            currentList.addLeave(leaveDate);
        }
        return currentList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLeaveCommand)) {
            return false;
        }

        AddLeaveCommand otherAddLeaveCommand = (AddLeaveCommand) other;
        return targetId.equals(otherAddLeaveCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", targetId)
                .toString();
    }
}
