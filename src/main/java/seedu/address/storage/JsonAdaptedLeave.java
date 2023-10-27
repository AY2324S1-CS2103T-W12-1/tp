package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Leave;

import java.time.LocalDate;

import static seedu.address.model.employee.Leave.VALID_DATE_FORMAT;

/**
 * Jackson-friendly version of {@link Leave}.
 */
public class JsonAdaptedLeave {
    private final LocalDate leaveDate;

    public JsonAdaptedLeave(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * Converts a given {@code Leave} into this class for Jackson use.
     */
    public JsonAdaptedLeave(Leave source) {
        leaveDate = source.leaveDate;
    }

    @JsonValue
    public String getLeaveDate() {
        return leaveDate == null ? "no leave scheduled!" : leaveDate.format(VALID_DATE_FORMAT);
    }

    /**
     * Converts this Jackson-friendly adapted Leave object into the model's {@code Leave} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted leave.
     */
    public Leave toModelType() throws IllegalValueException {
        if (!Leave.isValidLeaveDate(this.getLeaveDate())) {
            throw new IllegalValueException(Leave.MESSAGE_CONSTRAINTS);
        }
        return new Leave(leaveDate);
    }
}
