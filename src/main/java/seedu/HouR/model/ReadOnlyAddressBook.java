package seedu.HouR.model;

import javafx.collections.ObservableList;
import seedu.HouR.model.employee.Employee;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the employees list.
     * This list will not contain any duplicate employees.
     */
    ObservableList<Employee> getEmployeeList();

}
