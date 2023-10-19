package seedu.HouR.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidPosition_throwsIllegalArgumentException() {
        String invalidPosition = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPosition));
    }

    @Test
    public void isValidName() {
        // null position
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid position
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only
        assertFalse(Position.isValidPosition("^")); // only non-alphanumeric characters
        assertFalse(Position.isValidPosition("manager*")); // contains non-alphanumeric characters

        // valid position
        assertTrue(Position.isValidPosition("manager")); // alphabets only
        assertTrue(Position.isValidPosition("12345")); // numbers only
        assertTrue(Position.isValidPosition("the 2nd manager")); // alphanumeric characters
        assertTrue(Position.isValidPosition("Capital Manager")); // with capital letters
        assertTrue(Position.isValidPosition("Assistant to the Assistant to the Regional Manager")); // long positions
    }

    @Test
    public void equals() {
        Position position = new Position("Valid Position");

        // same values -> returns true
        assertTrue(position.equals(new Position("Valid Position")));

        // same object -> returns true
        assertTrue(position.equals(position));

        // null -> returns false
        assertFalse(position.equals(null));

        // different types -> returns false
        assertFalse(position.equals(5.0f));

        // different values -> returns false
        assertFalse(position.equals(new Position("Other Valid Position")));
    }
}
