package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an employee's leave in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeaveDate(String)}
 */
public class Leave {

    public static final String MESSAGE_CONSTRAINTS = "Leave dates have to be of format dd-MM-yyyy!"
            + " Please ensure that the date is valid!";

    public static final DateTimeFormatter VALID_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public final LocalDate leaveDate;

    /**
     * The constructor for a leave with a date.
     *
     * @param leaveDate The date of the leave.
     */
    public Leave(LocalDate leaveDate) {
        requireNonNull(leaveDate);
        this.leaveDate = leaveDate;
        // this.leaveDate = LocalDate.parse(leaveDate, VALID_DATE_FORMAT);
    }

    /**
     * Returns true if the appointment date is in the valid format.
     *
     * @param test Date to be tested.
     * @return True if the date is in the valid format.
     */
    public static boolean isValidLeaveDate(String test) {
        try {
            LocalDate.parse(test, VALID_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the date is the present date or in the future.
     *
     * @param test Date to be tested.
     * @return True if date is not in the past.
     */
    public static boolean isFutureDate(String test) {
        LocalDate currDate = LocalDate.now();
        LocalDate testDate = LocalDate.parse(test, VALID_DATE_FORMAT);
        return !testDate.isBefore(currDate);
    }

    @Override
    public String toString() {
        return leaveDate == null ? "no leave scheduled!" : leaveDate.format(VALID_DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Leave)) {
            return false;
        }

        Leave otherDate = (Leave) other;
        return leaveDate.equals(otherDate.leaveDate);
    }

    @Override
    public int hashCode() {
        return leaveDate.hashCode();
    }

}
