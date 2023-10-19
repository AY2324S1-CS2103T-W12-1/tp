package seedu.hour.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.hour.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, be 8 digits long, and start with 8 or 9";
    public static final String VALIDATION_REGEX = "[89]\\d{7}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid phone number with +65 prefix.
     */
    public static boolean isValidPhoneString(String test) {
        String[] parts = test.split(" ", 2);
        String number = parts[1];
        return number.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "+65 " + value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
