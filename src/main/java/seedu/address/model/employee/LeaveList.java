package seedu.address.model.employee;

import static seedu.address.model.employee.Employee.MAX_NUM_OF_LEAVES;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of leaves taken by an employee.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeaveList(ArrayList)}
 */
public class LeaveList {

    public final ArrayList<Leave> leaveList;

    /**
     * The constructor for an empty LeaveList.
     */
    public LeaveList() {
        this.leaveList = new ArrayList<>();
    }

    /**
     * The constructor for a (non-empty) LeaveList.
     *
     * @param leaves The arraylist of leaves.
     */
    public LeaveList(ArrayList<Leave> leaves) {
        this.leaveList = leaves;
    }

    /**
     * Returns the specific leave in this LeaveList.
     *
     * @param index The zero-based index of the leave.
     * @return Returns the leave with the specified index.
     */
    public Leave getLeave(int index) {
        return leaveList.get(index);
    }

    /**
     * Returns the number of leaves in this LeaveList.
     *
     * @return Returns the size of the list.
     */
    public int getSize() {
        return leaveList.size();
    }

    /**
     * Returns true if the current size of the LeaveList is less than the maximum allowable size.
     *
     * @param maxLeaves The maximum number of leaves allowed in the LeaveList.
     */
    public boolean isWithinLimit(int maxLeaves) {
        return leaveList.size() < maxLeaves;
    }

    /**
     * Adds a new leave of type Leave to this LeaveList.
     *
     * @param leave The leave that is added to the list.
     */
    public void addLeave(Leave leave) {
        leaveList.add(leave);
    }

    /**
     * Returns true if the LeaveList contains the specified Leave.
     *
     * @param date The Leave to be checked.
     */
    public boolean contains(Leave date) {
        return leaveList.contains(date);
    }


    public boolean getCurrentLeaveStatus() {
        if (leaveList.size() == 0) {
            return false;
        }
        for (int i = 0; i < leaveList.size(); i++) {
            if (leaveList.get(i).equals(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given ArrayList is a valid LeaveList.
     *
     * @param test List to be tested
     */
    public static boolean isValidLeaveList(ArrayList<Leave> test) {
        if (test.size() == 0) {
            return true;
        }
        if (test.size() > MAX_NUM_OF_LEAVES) {
            return false;
        }
        for (int i = 0; i < test.size(); i++) {
            if (Leave.isValidLeaveDate(test.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!leaveList.isEmpty()) {
            for (int i = 0; i < leaveList.size(); i++) {
                sb.append(i + 1).append(". ").append(leaveList.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("No leaves taken");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return leaveList.hashCode();
    }

}
