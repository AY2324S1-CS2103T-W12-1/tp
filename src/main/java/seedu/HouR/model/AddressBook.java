package seedu.HouR.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.HouR.commons.util.ToStringBuilder;
import seedu.HouR.model.employee.Employee;
import seedu.HouR.model.employee.UniqueEmployeeList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEmployeeList employees;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        employees = new UniqueEmployeeList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the employees in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the employee list with {@code employee}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setEmployees(newData.getEmployeeList());
    }

    //// employee-level operations

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in the address book.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Adds a employee to the address book.
     * The employee must not already exist in the address book.
     */
    public void addEmployee(Employee p) {
        employees.add(p);
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the address book.
     * The employee identity of {@code editedEmployee} must not be the same
     * as another existing employee in the address book.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
    }

    /** Sorts the list of employees based on an attribute */
    public void sortEmployees(String attribute) {
        requireNonNull(attribute);
        employees.sortEmployees(attribute);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("employees", employees)
                .toString();
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return employees.equals(otherAddressBook.employees);
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}
