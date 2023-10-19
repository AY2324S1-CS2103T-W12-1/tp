package seedu.HouR.logic.parser;

import static seedu.HouR.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.HouR.logic.commands.CommandTestUtil.DEPARTMENT_DESC_FRIEND;
import static seedu.HouR.logic.commands.CommandTestUtil.DEPARTMENT_DESC_HUSBAND;
import static seedu.HouR.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.HouR.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.HouR.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FINANCE;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.HouR.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.HouR.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.HouR.testutil.TypicalEmployees.AMY;
import static seedu.HouR.testutil.TypicalEmployees.BOB;

import org.junit.jupiter.api.Test;

import seedu.HouR.logic.Messages;
import seedu.HouR.logic.commands.AddCommand;
import seedu.HouR.model.department.Department;
import seedu.HouR.model.employee.Email;
import seedu.HouR.model.employee.Employee;
import seedu.HouR.model.employee.Id;
import seedu.HouR.model.employee.Name;
import seedu.HouR.model.employee.Phone;
import seedu.HouR.model.employee.Position;
import seedu.HouR.model.employee.Salary;
import seedu.HouR.testutil.EmployeeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Employee expectedEmployee = new EmployeeBuilder(BOB).withDepartments(VALID_DEPARTMENT_FINANCE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB,
                new AddCommand(expectedEmployee));

        // multiple departments - all accepted
        Employee expectedEmployeeMultipleDepartments = new EmployeeBuilder(BOB)
                .withDepartments(VALID_DEPARTMENT_FINANCE, VALID_DEPARTMENT_IT)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB,
                new AddCommand(expectedEmployeeMultipleDepartments));
    }

    @Test
    public void parse_repeatedNonDepartmentValue_failure() {
        String validExpectedEmployeeString = NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple positions
        assertParseFailure(parser, POSITION_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));

        // multiple ids
        assertParseFailure(parser, ID_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple salaries
        assertParseFailure(parser, SALARY_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedEmployeeString + PHONE_DESC_AMY + POSITION_DESC_AMY + ID_DESC_AMY + EMAIL_DESC_AMY
                + SALARY_DESC_AMY + NAME_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_POSITION, PREFIX_ID,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SALARY));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid position
        assertParseFailure(parser, INVALID_POSITION_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));

        // invalid id
        assertParseFailure(parser, INVALID_ID_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid salary
        assertParseFailure(parser, INVALID_SALARY_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid position
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_POSITION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));

        // invalid id
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_ID_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid email
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid salary
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_SALARY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero departments
        Employee expectedEmployee = new EmployeeBuilder(AMY).withDepartments().build();
        assertParseSuccess(parser, NAME_DESC_AMY + POSITION_DESC_AMY + ID_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + SALARY_DESC_AMY,
                new AddCommand(expectedEmployee));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + SALARY_DESC_BOB,
                expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_POSITION_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + SALARY_DESC_BOB,
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + VALID_ID_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + SALARY_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + SALARY_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + SALARY_DESC_BOB,
                expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + VALID_SALARY_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_POSITION_BOB + VALID_ID_BOB
                        + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_SALARY_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_POSITION_DESC + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB, Position.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + INVALID_ID_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB, Id.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid department
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_DEPARTMENT_DESC + VALID_DEPARTMENT_FINANCE + SALARY_DESC_BOB, Department.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BOB + ID_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + SALARY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                + POSITION_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_DESC_FRIEND + SALARY_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
