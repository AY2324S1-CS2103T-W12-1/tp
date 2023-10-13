package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.department.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_POSITION = "software engineer";
    public static final String DEFAULT_ID = "EID1234-5678";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Position position;
    private Id id;
    private Phone phone;
    private Email email;
    private Set<Department> departments;

    /**
     * Creates a {@code EmployeeBuilder} with the default details.
     */
    public EmployeeBuilder() {
        name = new Name(DEFAULT_NAME);
        position = new Position(DEFAULT_POSITION);
        id = new Id(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        departments = new HashSet<>();
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        position = employeeToCopy.getPosition();
        id = employeeToCopy.getId();
        phone = employeeToCopy.getPhone();
        email = employeeToCopy.getEmail();
        departments = new HashSet<>(employeeToCopy.getDepartments());
    }

    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code departments} into a {@code Set<Department>}
     * and set it to the {@code Employee} that we are building.
     */
    public EmployeeBuilder withDepartments(String ... departments) {
        this.departments = SampleDataUtil.getDepartmentSet(departments);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code employee} that we are building.
     */
    public EmployeeBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    public Employee build() {
        return new Employee(name, position, id, phone, email, departments);
    }

}
