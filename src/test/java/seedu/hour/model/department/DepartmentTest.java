package seedu.hour.model.department;

import static seedu.hour.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Department(null));
    }

    @Test
    public void constructor_invalidDepartmentName_throwsIllegalArgumentException() {
        String invalidDepartmentName = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidDepartmentName));
    }

    @Test
    public void isValidDepartmentName() {
        // null department name
        assertThrows(NullPointerException.class, () -> Department.isValidDepartmentName(null));
    }

}
