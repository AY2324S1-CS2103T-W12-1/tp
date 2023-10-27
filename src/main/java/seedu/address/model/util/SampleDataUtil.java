package seedu.address.model.util;

import static seedu.address.model.employee.Employee.DEFAULT_OVERTIME_HOURS;
import static seedu.address.model.employee.Employee.MAX_OVERTIME_HOURS;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.LeaveList;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.Salary;

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
                new Salary("8500"),
                getDepartmentSet("IT"),
                true,
                new OvertimeHours(DEFAULT_OVERTIME_HOURS),
                new LeaveList()),

            new Employee(new Name("Bernice Yu"), new Position("Systems analyst"),
                new Id("EID5678-1234"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Salary("6800"),
                getDepartmentSet("IT")),

            new Employee(new Name("Charlotte Oliveiro"), new Position("Marketing executive"),
                new Id("EID2023-1234"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Salary("8000"),
                getDepartmentSet("Marketing"),
                true,
                new OvertimeHours(MAX_OVERTIME_HOURS),
                new LeaveList()),

            new Employee(new Name("David Li"), new Position("Operations manager"),
                new Id("EID2023-5678"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Salary("7900"),
                getDepartmentSet("Finance")),

            new Employee(new Name("Irfan Ibrahim"), new Position("Software engineer"),
                new Id("EID2024-1234"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Salary("8500"),
                getDepartmentSet("IT"),
                true,
                new OvertimeHours(MAX_OVERTIME_HOURS),
                new LeaveList()),

            new Employee(new Name("Roy Balakrishnan"), new Position("Graphic design intern"),
                new Id("EID2024-5678"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Salary("1000"),
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
