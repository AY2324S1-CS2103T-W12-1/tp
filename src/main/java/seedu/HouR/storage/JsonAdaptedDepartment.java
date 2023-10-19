package seedu.HouR.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.HouR.commons.exceptions.IllegalValueException;
import seedu.HouR.model.department.Department;

/**
 * Jackson-friendly version of {@link Department}.
 */
class JsonAdaptedDepartment {

    private final String departmentName;

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given {@code departmentName}.
     */
    @JsonCreator
    public JsonAdaptedDepartment(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Converts a given {@code Department} into this class for Jackson use.
     */
    public JsonAdaptedDepartment(Department source) {
        departmentName = source.departmentName;
    }

    @JsonValue
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted department.
     */
    public Department toModelType() throws IllegalValueException {
        if (!Department.isValidDepartmentName(departmentName)) {
            throw new IllegalValueException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(departmentName);
    }

}
