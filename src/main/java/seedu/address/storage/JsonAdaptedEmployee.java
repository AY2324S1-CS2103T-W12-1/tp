package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.LeaveList;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.Salary;

/**
 * Jackson-friendly version of {@link Employee}.
 */
class JsonAdaptedEmployee {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Employee's %s field is missing!";

    private final String name;
    private final String position;
    private final String id;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedDepartment> departments = new ArrayList<>();
    private final String salary;
    private final boolean isOnLeave;
    private final int overtimeHours;
    private final ArrayList<Leave> leaveList;

    /**
     * Constructs a {@code JsonAdaptedEmployee} with the given employee details.
     */
    @JsonCreator
    public JsonAdaptedEmployee(@JsonProperty("name") String name, @JsonProperty("position") String position,
            @JsonProperty("id") String id, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("departments") List<JsonAdaptedDepartment> departments,
            @JsonProperty("salary") String salary, @JsonProperty("isOnLeave") boolean isOnLeave,
            @JsonProperty("overtime") int overtimeHours, @JsonProperty("leaves") ArrayList<Leave> leaveList) {
        this.name = name;
        this.position = position;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        if (departments != null) {
            this.departments.addAll(departments);
        }
        this.isOnLeave = isOnLeave;
        this.overtimeHours = overtimeHours;
        this.leaveList = leaveList;
    }

    /**
     * Converts a given {@code Employee} into this class for Jackson use.
     */
    public JsonAdaptedEmployee(Employee source) {
        name = source.getName().fullName;
        position = source.getPosition().value;
        id = source.getId().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        salary = source.getSalary().value;
        departments.addAll(source.getDepartments().stream()
                .map(JsonAdaptedDepartment::new)
                .collect(Collectors.toList()));
        isOnLeave = source.getIsOnLeave();
        overtimeHours = source.getOvertimeHours().value;
        leaveList = source.getLeaveList().leaveList;
    }

    /**
     * Converts this Jackson-friendly adapted employee object into the model's
     * {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted employee.
     */
    public Employee toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        final List<Department> employeeDepartments = new ArrayList<>();
        for (JsonAdaptedDepartment department : departments) {
            employeeDepartments.add(department.toModelType());
        }
        final Set<Department> modelDepartments = new HashSet<>(employeeDepartments);

        if (!OvertimeHours.isValidOvertimeHours(overtimeHours)) {
            throw new IllegalValueException(OvertimeHours.MESSAGE_CONSTRAINTS);
        }
        final OvertimeHours modelOvertimeHours = new OvertimeHours(overtimeHours);

        if (leaveList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LeaveList.class.getSimpleName()));
        }
        if (!LeaveList.isValidLeaveList(leaveList)) {
            throw new IllegalValueException(Leave.MESSAGE_CONSTRAINTS);
        }
        final LeaveList modelLeaveList = new LeaveList(leaveList);

        return new Employee(modelName, modelPosition, modelId, modelPhone,
                modelEmail, modelSalary, modelDepartments, isOnLeave, modelOvertimeHours, modelLeaveList);
    }
}
