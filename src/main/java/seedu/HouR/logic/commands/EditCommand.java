package seedu.HouR.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.HouR.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.HouR.commons.core.index.Index;
import seedu.HouR.commons.util.CollectionUtil;
import seedu.HouR.commons.util.ToStringBuilder;
import seedu.HouR.logic.Messages;
import seedu.HouR.logic.commands.exceptions.CommandException;
import seedu.HouR.model.Model;
import seedu.HouR.model.department.Department;
import seedu.HouR.model.employee.Email;
import seedu.HouR.model.employee.Employee;
import seedu.HouR.model.employee.Id;
import seedu.HouR.model.employee.Name;
import seedu.HouR.model.employee.Phone;
import seedu.HouR.model.employee.Position;
import seedu.HouR.model.employee.Salary;

/**
 * Edits the details of an existing employee in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed employee list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the address book.";

    private final Index index;
    private final EditEmployeeDescriptor editEmployeeDescriptor;

    /**
     * @param index of the employee in the filtered employee list to edit
     * @param editEmployeeDescriptor details to edit the employee with
     */
    public EditCommand(Index index, EditEmployeeDescriptor editEmployeeDescriptor) {
        requireNonNull(index);
        requireNonNull(editEmployeeDescriptor);

        this.index = index;
        this.editEmployeeDescriptor = new EditEmployeeDescriptor(editEmployeeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToEdit = lastShownList.get(index.getZeroBased());
        Employee editedEmployee = createEditedEmployee(employeeToEdit, editEmployeeDescriptor);

        if (!employeeToEdit.isSameEmployee(editedEmployee) && model.hasEmployee(editedEmployee)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.setEmployee(employeeToEdit, editedEmployee);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(String.format(MESSAGE_EDIT_EMPLOYEE_SUCCESS, Messages.format(editedEmployee)));
    }

    /**
     * Creates and returns a {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Employee createEditedEmployee(Employee employeeToEdit,
            EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        Name updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Position updatedPosition = editEmployeeDescriptor.getPosition().orElse(employeeToEdit.getPosition());
        Id updatedId = editEmployeeDescriptor.getId().orElse(employeeToEdit.getId());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Email updatedEmail = editEmployeeDescriptor.getEmail().orElse(employeeToEdit.getEmail());
        Salary updatedSalary = editEmployeeDescriptor.getSalary().orElse(employeeToEdit.getSalary());
        Set<Department> updatedDepartments = editEmployeeDescriptor.getDepartments()
                .orElse(employeeToEdit.getDepartments());

        return new Employee(updatedName, updatedPosition, updatedId, updatedPhone, updatedEmail,
                updatedSalary, updatedDepartments);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editEmployeeDescriptor.equals(otherEditCommand.editEmployeeDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editEmployeeDescriptor", editEmployeeDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the employee with. Each non-empty field value will replace the
     * corresponding field value of the employee.
     */
    public static class EditEmployeeDescriptor {
        private Name name;
        private Position position;
        private Id id;
        private Phone phone;
        private Email email;
        private Salary salary;
        private Set<Department> departments;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code departments} is used internally.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setName(toCopy.name);
            setPosition(toCopy.position);
            setId(toCopy.id);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setSalary(toCopy.salary);
            setDepartments(toCopy.departments);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, position, id, phone, email, salary, departments);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }


        /**
         * Sets {@code departments} to this object's {@code departments}.
         * A defensive copy of {@code departments} is used internally.
         */
        public void setDepartments(Set<Department> departments) {
            this.departments = (departments != null) ? new HashSet<>(departments) : null;
        }

        /**
         * Returns an unmodifiable department set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code departments} is null.
         */
        public Optional<Set<Department>> getDepartments() {
            return (departments != null) ? Optional.of(Collections.unmodifiableSet(departments)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEmployeeDescriptor)) {
                return false;
            }

            EditEmployeeDescriptor otherEditEmployeeDescriptor = (EditEmployeeDescriptor) other;
            return Objects.equals(name, otherEditEmployeeDescriptor.name)
                    && Objects.equals(position, otherEditEmployeeDescriptor.position)
                    && Objects.equals(id, otherEditEmployeeDescriptor.id)
                    && Objects.equals(phone, otherEditEmployeeDescriptor.phone)
                    && Objects.equals(email, otherEditEmployeeDescriptor.email)
                    && Objects.equals(salary, otherEditEmployeeDescriptor.salary)
                    && Objects.equals(departments, otherEditEmployeeDescriptor.departments);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("position", position)
                    .add("id", id)
                    .add("phone", phone)
                    .add("email", email)
                    .add("salary", salary)
                    .add("departments", departments)
                    .toString();
        }
    }
}
