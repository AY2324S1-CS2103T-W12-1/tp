package seedu.hour.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hour.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.hour.testutil.Assert.assertThrows;
import static seedu.hour.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.hour.logic.parser.exceptions.ParseException;
import seedu.hour.model.department.Department;
import seedu.hour.model.employee.Email;
import seedu.hour.model.employee.Name;
import seedu.hour.model.employee.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DEPARTMENT = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_DEPARTMENT_1 = "friend";
    private static final String VALID_DEPARTMENT_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_EMPLOYEE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_EMPLOYEE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseDepartment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDepartment(null));
    }

    @Test
    public void parseDepartment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDepartment(INVALID_DEPARTMENT));
    }

    @Test
    public void parseDepartment_validValueWithoutWhitespace_returnsDepartment() throws Exception {
        Department expectedDepartment = new Department(VALID_DEPARTMENT_1);
        assertEquals(expectedDepartment, ParserUtil.parseDepartment(VALID_DEPARTMENT_1));
    }

    @Test
    public void parseDepartment_validValueWithWhitespace_returnsTrimmedDepartment() throws Exception {
        String departmentWithWhitespace = WHITESPACE + VALID_DEPARTMENT_1 + WHITESPACE;
        Department expectedDepartment = new Department(VALID_DEPARTMENT_1);
        assertEquals(expectedDepartment, ParserUtil.parseDepartment(departmentWithWhitespace));
    }

    @Test
    public void parseDepartments_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDepartments(null));
    }

    @Test
    public void parseDepartments_collectionWithInvalidDepartments_throwsParseException() {
        assertThrows(ParseException.class, (
                ) -> ParserUtil.parseDepartments(Arrays.asList(VALID_DEPARTMENT_1, INVALID_DEPARTMENT)));
    }

    @Test
    public void parseDepartments_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseDepartments(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseDepartments_collectionWithValidDepartments_returnsDepartmentSet() throws Exception {
        Set<Department> actualDepartmentSet = ParserUtil.parseDepartments(Arrays.asList(VALID_DEPARTMENT_1,
                VALID_DEPARTMENT_2));
        Set<Department> expectedDepartmentSet = new HashSet<Department>(
            Arrays.asList(new Department(VALID_DEPARTMENT_1), new Department(VALID_DEPARTMENT_2)));

        assertEquals(expectedDepartmentSet, actualDepartmentSet);
    }
}
