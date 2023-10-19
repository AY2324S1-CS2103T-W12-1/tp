package seedu.hour.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.hour.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.hour.commons.exceptions.IllegalValueException;
import seedu.hour.commons.util.JsonUtil;
import seedu.hour.model.AddressBook;
import seedu.hour.testutil.TypicalEmployees;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_EMPLOYEES_FILE = TEST_DATA_FOLDER.resolve("typicalEmployeesAddressBook.json");
    private static final Path INVALID_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("invalidEmployeeAddressBook.json");
    private static final Path DUPLICATE_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("duplicateEmployeeAddressBook.json");

    @Test
    public void toModelType_typicalEmployeesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EMPLOYEES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalEmployeesAddressBook = TypicalEmployees.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalEmployeesAddressBook);
    }

    @Test
    public void toModelType_invalidEmployeeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_EMPLOYEE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployees_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_EMPLOYEE,
                dataFromFile::toModelType);
    }

}
