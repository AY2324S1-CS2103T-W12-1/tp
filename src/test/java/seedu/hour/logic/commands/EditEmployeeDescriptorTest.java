package seedu.hour.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hour.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.hour.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_SALARY_BOB;

import org.junit.jupiter.api.Test;

import seedu.hour.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.hour.testutil.EditEmployeeDescriptorBuilder;

public class EditEmployeeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEmployeeDescriptor descriptorWithSameValues = new EditEmployeeDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditEmployeeDescriptor editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different position -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withPosition(VALID_POSITION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different id -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withId(VALID_ID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different departments -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withDepartments(VALID_DEPARTMENT_IT).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different salary -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withSalary(VALID_SALARY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditEmployeeDescriptor editEmployeeDescriptor = new EditEmployeeDescriptor();
        String expected = EditEmployeeDescriptor.class.getCanonicalName() + "{name="
                + editEmployeeDescriptor.getName().orElse(null) + ", position="
                + editEmployeeDescriptor.getPosition().orElse(null) + ", id="
                + editEmployeeDescriptor.getId().orElse(null) + ", phone="
                + editEmployeeDescriptor.getPhone().orElse(null) + ", email="
                + editEmployeeDescriptor.getEmail().orElse(null) + ", salary="
                + editEmployeeDescriptor.getSalary().orElse(null) + ", departments="
                + editEmployeeDescriptor.getDepartments().orElse(null) + "}";
        assertEquals(expected, editEmployeeDescriptor.toString());
    }
}
