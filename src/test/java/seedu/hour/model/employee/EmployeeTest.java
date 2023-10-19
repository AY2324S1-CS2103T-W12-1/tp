package seedu.hour.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.hour.testutil.Assert.assertThrows;
import static seedu.hour.testutil.TypicalEmployees.ALICE;
import static seedu.hour.testutil.TypicalEmployees.BOB;

import org.junit.jupiter.api.Test;

import seedu.hour.testutil.EmployeeBuilder;

public class EmployeeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Employee employee = new EmployeeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> employee.getDepartments().remove(0));
    }

    @Test
    public void isSameEmployee() {
        // same object -> returns true
        assertTrue(ALICE.isSameEmployee(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEmployee(null));

        // same id, all other attributes different -> returns true
        Employee editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).withPosition(VALID_POSITION_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withDepartments(VALID_DEPARTMENT_IT).build();
        assertTrue(ALICE.isSameEmployee(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameEmployee(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different employee -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Employee editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different position -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withPosition(VALID_POSITION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different id -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different departments -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withDepartments(VALID_DEPARTMENT_IT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different salary -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withSalary(VALID_SALARY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Employee.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", position=" + ALICE.getPosition() + ", id=" + ALICE.getId() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", salary=" + ALICE.getSalary()
                + ", departments=" + ALICE.getDepartments() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
