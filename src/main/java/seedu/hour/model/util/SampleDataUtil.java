package seedu.hour.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.hour.model.AddressBook;
import seedu.hour.model.ReadOnlyAddressBook;
import seedu.hour.model.department.Department;
import seedu.hour.model.employee.Email;
import seedu.hour.model.employee.Employee;
import seedu.hour.model.employee.Id;
import seedu.hour.model.employee.Name;
import seedu.hour.model.employee.Phone;
import seedu.hour.model.employee.Position;
import seedu.hour.model.employee.Salary;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new Position("Software engineer"),
                new Id("EID1234-5678"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Salary("$8,500"),
            getDepartmentSet("IT")),

            new Employee(new Name("Bernice Yu"), new Position("Systems analyst"),
                new Id("EID5678-1234"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Salary("$6,800"),
            getDepartmentSet("IT")),

            new Employee(new Name("Charlotte Oliveiro"), new Position("Marketing executive"),
                new Id("EID2023-1234"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Salary("$8,000"),
            getDepartmentSet("Marketing")),

            new Employee(new Name("David Li"), new Position("Operations manager"),
                new Id("EID2023-5678"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Salary("$7,900"),
            getDepartmentSet("Finance")),

            new Employee(new Name("Irfan Ibrahim"), new Position("Software engineer"),
                new Id("EID2024-1234"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Salary("$8,500"),
            getDepartmentSet("IT")),

            new Employee(new Name("Roy Balakrishnan"), new Position("Graphic design intern"),
                new Id("EID2024-5678"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Salary("$1,000"),
            getDepartmentSet("Marketing"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        return sampleAb;
    }

    /**
     * Returns a department set containing the list of strings given.
     */
    public static Set<Department> getDepartmentSet(String... strings) {
        return Arrays.stream(strings)
            .map(Department::new)
            .collect(Collectors.toSet());
    }
}
