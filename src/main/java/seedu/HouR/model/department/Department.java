package seedu.HouR.model.department;

import static java.util.Objects.requireNonNull;
import static seedu.HouR.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDepartmentName(String)}
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String departmentName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Department(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidDepartmentName(tagName), MESSAGE_CONSTRAINTS);
        this.departmentName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDepartmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Department)) {
            return false;
        }

        Department otherTag = (Department) other;
        return departmentName.equals(otherTag.departmentName);
    }

    @Override
    public int hashCode() {
        return departmentName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + departmentName + ']';
    }

}
