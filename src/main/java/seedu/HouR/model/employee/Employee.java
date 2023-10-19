package seedu.HouR.model.employee;

import static seedu.HouR.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.HouR.commons.util.ToStringBuilder;
import seedu.HouR.model.department.Department;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final Name name;
    private final Position position;
    private final Id id;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Salary salary;
    private final Set<Department> departments = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Position position, Id id, Phone phone, Email email, Salary salary,
                    Set<Department> departments) {
        requireAllNonNull(name, position, id, phone, email, salary, departments);
        this.name = name;
        this.position = position;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.departments.addAll(departments);
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Id getId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Salary getSalary() {
        return salary;
    }


    /**
     * Returns an immutable department set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Department> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }

    /**
     * Returns true if both employees have the same employee id.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getId().equals(getId());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return name.equals(otherEmployee.name)
                && position.equals(otherEmployee.position)
                && id.equals(otherEmployee.id)
                && phone.equals(otherEmployee.phone)
                && email.equals(otherEmployee.email)
                && salary.equals(otherEmployee.salary)
                && departments.equals(otherEmployee.departments);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, position, id, phone, email, salary, departments);
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
