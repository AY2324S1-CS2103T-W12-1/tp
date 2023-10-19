package seedu.HouR.testutil;

import static seedu.HouR.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Set;

import seedu.HouR.logic.commands.AddCommand;
import seedu.HouR.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.HouR.model.department.Department;
import seedu.HouR.model.employee.Employee;

/**
 * A utility class for Employee.
 */
public class EmployeeUtil {

    /**
     * Returns an add command string for adding the {@code employee}.
     */
    public static String getAddCommand(Employee employee) {
        return AddCommand.COMMAND_WORD + " " + getEmployeeDetails(employee);
    }

    /**
     * Returns the part of command string for the given {@code employee}'s details.
     */
    public static String getEmployeeDetails(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + employee.getName().fullName + " ");
        sb.append(PREFIX_POSITION + employee.getPosition().value + " ");
        sb.append(PREFIX_ID + employee.getId().value + " ");
        sb.append(PREFIX_PHONE + employee.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + employee.getEmail().value + " ");
        sb.append(PREFIX_SALARY + employee.getSalary().value + " ");
        employee.getDepartments().stream().forEach(
            s -> sb.append(PREFIX_DEPARTMENT + s.departmentName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEmployeeDescriptor}'s details.
     */
    public static String getEditEmployeeDescriptorDetails(EditEmployeeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION).append(position.value).append(" "));
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.value).append(" "));
        if (descriptor.getDepartments().isPresent()) {
            Set<Department> departments = descriptor.getDepartments().get();
            if (departments.isEmpty()) {
                sb.append(PREFIX_DEPARTMENT);
            } else {
                departments.forEach(s -> sb.append(PREFIX_DEPARTMENT).append(s.departmentName).append(" "));
            }
        }
        return sb.toString();
    }
}
