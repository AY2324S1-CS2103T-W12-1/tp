package seedu.HouR.logic.parser;

import static seedu.HouR.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.HouR.logic.commands.CommandTestUtil.DEPARTMENT_DESC_FRIEND;
import static seedu.HouR.logic.commands.CommandTestUtil.DEPARTMENT_DESC_HUSBAND;
import static seedu.HouR.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.HouR.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FINANCE;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.HouR.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.HouR.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.HouR.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.HouR.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.HouR.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.HouR.testutil.TypicalIndexes.INDEX_SECOND_EMPLOYEE;
import static seedu.HouR.testutil.TypicalIndexes.INDEX_THIRD_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.HouR.commons.core.index.Index;
import seedu.HouR.logic.Messages;
import seedu.HouR.logic.commands.EditCommand;
import seedu.HouR.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.HouR.model.department.Department;
import seedu.HouR.model.employee.Email;
import seedu.HouR.model.employee.Name;
import seedu.HouR.model.employee.Phone;
import seedu.HouR.testutil.EditEmployeeDescriptorBuilder;

public class EditCommandParserTest {

    private static final String DEPARTMENT_EMPTY = " " + PREFIX_DEPARTMENT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_DEPARTMENT_DESC, Department.MESSAGE_CONSTRAINTS); // invalid department

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_DEPARTMENT} will reset the departments of the {@code Employee} being edited,
        // parsing it together with a valid department results in error
        assertParseFailure(parser, "1" + DEPARTMENT_DESC_FRIEND + DEPARTMENT_DESC_HUSBAND + DEPARTMENT_EMPTY,
                Department.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DEPARTMENT_DESC_FRIEND + DEPARTMENT_EMPTY + DEPARTMENT_DESC_HUSBAND,
                Department.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DEPARTMENT_EMPTY + DEPARTMENT_DESC_FRIEND + DEPARTMENT_DESC_HUSBAND,
                Department.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EMPLOYEE;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + DEPARTMENT_DESC_HUSBAND
                + EMAIL_DESC_AMY + NAME_DESC_AMY + DEPARTMENT_DESC_FRIEND;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withDepartments(VALID_DEPARTMENT_IT, VALID_DEPARTMENT_FINANCE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EMPLOYEE;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EMPLOYEE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // departments
        userInput = targetIndex.getOneBased() + DEPARTMENT_DESC_FRIEND;
        descriptor = new EditEmployeeDescriptorBuilder().withDepartments(VALID_DEPARTMENT_FINANCE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonDepartmentValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_EMPLOYEE;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + DEPARTMENT_DESC_FRIEND + PHONE_DESC_AMY + EMAIL_DESC_AMY + DEPARTMENT_DESC_FRIEND
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + DEPARTMENT_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL));
    }

    @Test
    public void parse_resetDepartments_success() {
        Index targetIndex = INDEX_THIRD_EMPLOYEE;
        String userInput = targetIndex.getOneBased() + DEPARTMENT_EMPTY;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withDepartments().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
