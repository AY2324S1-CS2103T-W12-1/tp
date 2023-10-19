package seedu.hour.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.hour.commons.exceptions.IllegalValueException;
import seedu.hour.model.AddressBook;
import seedu.hour.model.ReadOnlyAddressBook;
import seedu.hour.model.employee.Employee;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";

    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given employees.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("employees") List<JsonAdaptedEmployee> employee) {
        this.employees.addAll(employee);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (addressBook.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            addressBook.addEmployee(employee);
        }
        return addressBook;
    }

}
