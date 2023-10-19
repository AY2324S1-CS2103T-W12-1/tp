package seedu.hour.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hour.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.hour.testutil.Assert.assertThrows;
import static seedu.hour.testutil.TypicalEmployees.ALICE;
import static seedu.hour.testutil.TypicalEmployees.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hour.model.employee.exceptions.DuplicateEmployeeException;
import seedu.hour.model.employee.exceptions.EmployeeNotFoundException;
import seedu.hour.testutil.EmployeeBuilder;

public class UniqueEmployeeListTest {

    private final UniqueEmployeeList uniqueEmployeeList = new UniqueEmployeeList();

    @Test
    public void contains_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.contains(null));
    }

    @Test
    public void contains_employeeNotInList_returnsFalse() {
        assertFalse(uniqueEmployeeList.contains(ALICE));
    }

    @Test
    public void contains_employeeInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        assertTrue(uniqueEmployeeList.contains(ALICE));
    }

    @Test
    public void contains_employeeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withDepartments(VALID_DEPARTMENT_IT)
                .build();
        assertTrue(uniqueEmployeeList.contains(editedAlice));
    }

    @Test
    public void add_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.add(null));
    }

    @Test
    public void add_duplicateEmployee_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.add(ALICE));
    }

    @Test
    public void setEmployee_nullTargetEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(null, ALICE));
    }

    @Test
    public void setEmployee_nullEditedEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(ALICE, null));
    }

    @Test
    public void setEmployee_targetEmployeeNotInList_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setEmployee_editedEmployeeIsSameEmployee_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.setEmployee(ALICE, ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasSameIdentity_success() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withDepartments(VALID_DEPARTMENT_IT)
                .build();
        uniqueEmployeeList.setEmployee(ALICE, editedAlice);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(editedAlice);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasDifferentIdentity_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.setEmployee(ALICE, BOB);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasNonUniqueIdentity_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.setEmployee(ALICE, BOB));
    }

    @Test
    public void remove_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.remove(null));
    }

    @Test
    public void remove_employeeDoesNotExist_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.remove(ALICE));
    }

    @Test
    public void remove_existingEmployee_removesEmployee() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.remove(ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_nullUniqueEmployeeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((UniqueEmployeeList) null));
    }

    @Test
    public void setEmployees_uniqueEmployeeList_replacesOwnListWithProvidedUniqueEmployeeList() {
        uniqueEmployeeList.add(ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB);
        uniqueEmployeeList.setEmployees(expectedUniqueEmployeeList);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((List<Employee>) null));
    }

    @Test
    public void setEmployees_list_replacesOwnListWithProvidedList() {
        uniqueEmployeeList.add(ALICE);
        List<Employee> employeeList = Collections.singletonList(BOB);
        uniqueEmployeeList.setEmployees(employeeList);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_listWithDuplicateEmployees_throwsDuplicateEmployeeException() {
        List<Employee> listWithDuplicateEmployees = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEmployeeException.class, (
                ) -> uniqueEmployeeList.setEmployees(listWithDuplicateEmployees));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEmployeeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueEmployeeList.asUnmodifiableObservableList().toString(), uniqueEmployeeList.toString());
    }
}
