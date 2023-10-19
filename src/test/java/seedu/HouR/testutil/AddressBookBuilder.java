package seedu.HouR.testutil;

import seedu.HouR.model.AddressBook;
import seedu.HouR.model.employee.Employee;

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
