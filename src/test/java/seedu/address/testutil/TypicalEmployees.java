package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.model.employee.Employee.DEFAULT_LEAVELIST;
import static seedu.address.model.employee.Employee.DEFAULT_OVERTIME_HOURS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalEmployees {

    public static final Employee ALICE = new EmployeeBuilder().withName("Alice Pauline")
                .withPosition("Senior Manager")
                .withId("EID1234-5678")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withDepartments("Finance")
                .withSalary("12000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();
    public static final Employee BENSON = new EmployeeBuilder().withName("Benson Meier")
                .withPosition("Assistant Manager")
                .withId("EID5678-1234")
                .withEmail("johnd@example.com")
                .withPhone("98765432")
                .withDepartments("IT", "Operations")
                .withSalary("6000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();
    public static final Employee CARL = new EmployeeBuilder().withName("Carl Kurz")
                .withPosition("Software Engineer")
                .withId("EID1234-8765")
                .withPhone("95352563")
                .withEmail("heinz@example.com")
                .withSalary("5800")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();
    public static final Employee DANIEL = new EmployeeBuilder().withName("Daniel Meier")
                .withPosition("Tester")
                .withId("EID4321-5678")
                .withPhone("87652533")
                .withEmail("cornelia@example.com")
                .withDepartments("IT")
                .withSalary("5000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();
    public static final Employee ELLE = new EmployeeBuilder().withName("Elle Meyer")
                .withPosition("Junior Software Engineer")
                .withId("EID2023-1234")
                .withPhone("94842224")
                .withEmail("werner@example.com")
                .withSalary("4000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();
    public static final Employee FIONA = new EmployeeBuilder().withName("Fiona Kunz")
                .withPosition("Senior Software Engineer")
                .withId("EID2023-5678")
                .withPhone("94825427")
                .withEmail("lydia@example.com")
                .withSalary("8000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();
    public static final Employee GEORGE = new EmployeeBuilder().withName("George Best")
                .withPosition("Intern")
                .withId("EID2023-9876")
                .withPhone("94824242")
                .withEmail("anna@example.com")
                .withSalary("1000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST).build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withName("Hoon Meier")
                .withPosition("Intern")
                .withId("EID2023-2024")
                .withPhone("84824242")
                .withEmail("stefan@example.com")
                .withSalary("1000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST)
                .build();
    public static final Employee IDA = new EmployeeBuilder().withName("Ida Mueller")
                .withPosition("CEO")
                .withId("EID2021-2022")
                .withPhone("84821311")
                .withEmail("hans@example.com")
                .withSalary("10000")
                .withOvertimeHours(DEFAULT_OVERTIME_HOURS)
                .withLeaveList(DEFAULT_LEAVELIST)
                .build();

    // Manually added - Employee's details found in {@code CommandTestUtil}
    public static final Employee AMY = new EmployeeBuilder().withName(VALID_NAME_AMY)
                .withPosition(VALID_POSITION_AMY)
                .withId(VALID_ID_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withDepartments(VALID_DEPARTMENT_FINANCE)
                .withSalary(VALID_SALARY_AMY)
                .withOvertimeHours(VALID_OVERTIME_HOURS_AMY)
                .withLeaveList(VALID_LEAVELIST_AMY)
                .build();
    public static final Employee BOB = new EmployeeBuilder().withName(VALID_NAME_BOB)
                .withPosition(VALID_POSITION_BOB)
                .withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withDepartments(VALID_DEPARTMENT_FINANCE, VALID_DEPARTMENT_IT)
                .withSalary(VALID_SALARY_BOB)
                .withOvertimeHours(VALID_OVERTIME_HOURS_BOB)
                .withLeaveList(VALID_LEAVELIST_BOB)
                .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical employees.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Employee employee : getTypicalEmployees()) {
            ab.addEmployee(employee);
        }
        return ab;
    }

    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
