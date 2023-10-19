package seedu.hour.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.hour.model.employee.Employee;

/**
 * A UI component that displays information of a {@code Employee}.
 */
public class EmployeeCard extends UiPart<Region> {

    private static final String FXML = "EmployeeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Employee employee;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label position;
    @FXML
    private Label employeeId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label salary;
    @FXML
    private FlowPane departments;

    /**
     * Creates a {@code EmployeeCode} with the given {@code EmployeeCode} and index to display.
     */
    public EmployeeCard(Employee employee, int displayedIndex) {
        super(FXML);
        this.employee = employee;
        id.setText(displayedIndex + ". ");
        name.setText(employee.getName().fullName);
        position.setText(employee.getPosition().value);
        employeeId.setText(employee.getId().value);
        phone.setText(employee.getPhone().toString());
        email.setText(employee.getEmail().value);
        salary.setText(employee.getSalary().value);
        employee.getDepartments().stream()
                .sorted(Comparator.comparing(department -> department.departmentName))
                .forEach(department -> departments.getChildren().add(new Label(department.departmentName)));
    }
}
