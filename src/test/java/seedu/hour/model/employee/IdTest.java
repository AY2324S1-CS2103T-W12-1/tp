package seedu.hour.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidName() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("^")); // only non-alphanumeric characters
        assertFalse(Id.isValidId("EID")); // alphabets only
        assertFalse(Id.isValidId("1234-5678")); // digits only
        assertFalse(Id.isValidId("EID123*-4567")); // contains non-alphanumeric characters
        assertFalse(Id.isValidId("eid1234-4567")); // lowercase alphabets

        // valid id
        assertTrue(Id.isValidId("EID1234-5678")); // uppercase alphabets and digits in correct format
    }

    @Test
    public void equals() {
        Id id = new Id("EID1234-5678");

        // same values -> returns true
        assertTrue(id.equals(new Id("EID1234-5678")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(new Id("EID5678-1234")));
    }
}
