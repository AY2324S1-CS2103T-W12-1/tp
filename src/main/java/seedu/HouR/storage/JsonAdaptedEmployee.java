package seedu.HouR.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.HouR.commons.exceptions.IllegalValueException;
import seedu.HouR.model.department.Department;
import seedu.HouR.model.employee.Email;
import seedu.HouR.model.employee.Employee;
import seedu.HouR.model.employee.Id;
import seedu.HouR.model.employee.Name;
import seedu.HouR.model.employee.Phone;
import seedu.HouR.model.employee.Position;
import seedu.HouR.model.employee.Salary;

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

    /**
     * Constructs a {@code JsonAdaptedEmployee} with the given employee details.
     */
    @JsonCreator
    public JsonAdaptedEmployee(@JsonProperty("name") String name, @JsonProperty("position") String position,
            @JsonProperty("id") String id, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("departments") List<JsonAdaptedDepartment> departments,
            @JsonProperty("salary") String salary) {
        this.name = name;
        this.position = position;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        if (departments != null) {
            this.departments.addAll(departments);
        }
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
    }

    /**
     * Converts this Jackson-friendly adapted employee object into the model's
     * {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted employee.
     */
    public Employee toModelType() throws IllegalValueException {
        final List<Department> employeeDepartments = new ArrayList<>();
        for (JsonAdaptedDepartment department : departments) {
            employeeDepartments.add(department.toModelType());
        }

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

        final Set<Department> modelDepartments = new HashSet<>(employeeDepartments);
        return new Employee(modelName, modelPosition, modelId, modelPhone, modelEmail, modelSalary, modelDepartments);
    }

}
