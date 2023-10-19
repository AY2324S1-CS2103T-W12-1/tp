package seedu.hour.testutil;

import seedu.hour.model.AddressBook;
import seedu.hour.model.employee.Employee;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withEmployee("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Employee} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withEmployee(Employee employee) {
        addressBook.addEmployee(employee);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
