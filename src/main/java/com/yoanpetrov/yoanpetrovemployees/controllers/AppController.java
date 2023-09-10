package com.yoanpetrov.yoanpetrovemployees.controllers;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeePair;
import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import com.yoanpetrov.yoanpetrovemployees.services.CsvService;
import com.yoanpetrov.yoanpetrovemployees.services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Controller class for the application. Responsible for handling user input.
 */
@Component
public class AppController {

    @FXML
    private TableView<EmployeePair> employeesTable;
    @FXML
    private Label chosenFileLabel;

    private final CsvService csvService;
    private final EmployeeService employeeService;
    private File csv;
    private Alert errorAlert;

    public AppController(CsvService csvService, EmployeeService employeeService) {
        this.csvService = csvService;
        this.employeeService = employeeService;
    }

    /**
     * Initializes objects that are important for the application's proper functioning.
     */
    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("An error occurred");

        TableColumn<EmployeePair, String> employeeIdA = new TableColumn<>("Employee #1");
        TableColumn<EmployeePair, String> employeeIdB = new TableColumn<>("Employee #2");
        TableColumn<EmployeePair, String> projectId = new TableColumn<>("Project ID");
        TableColumn<EmployeePair, String> daysWorkedTogether = new TableColumn<>("Days worked");

        employeeIdA.setPrefWidth(85);
        employeeIdA.setResizable(false);
        employeeIdB.setPrefWidth(85);
        employeeIdB.setResizable(false);
        projectId.setPrefWidth(75);
        projectId.setResizable(false);
        daysWorkedTogether.setPrefWidth(85);
        daysWorkedTogether.setResizable(false);
        employeeIdA.setCellValueFactory(new PropertyValueFactory<>("employeeIdA"));
        employeeIdB.setCellValueFactory(new PropertyValueFactory<>("employeeIdB"));
        projectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        daysWorkedTogether.setCellValueFactory(new PropertyValueFactory<>("daysWorkedTogether"));

        employeesTable.getColumns().setAll(List.of(employeeIdA, employeeIdB, projectId, daysWorkedTogether));
    }

    /**
     * Displays a file chooser in a new window to let the user choose a csv file to load.
     */
    @FXML
    public void chooseFile() {
        employeesTable.setVisible(false);
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        csv = chooser.showOpenDialog(new Stage());
        if (csv != null) {
            chosenFileLabel.setText("Chosen file: " + csv.getName());
        }
    }

    /**
     * Parses the csv file using {@code CsvService} and displays the pairs in a table.
     * If the file was empty or in a wrong format, an error message appears and the selected file is cleared.
     */
    @FXML
    public void viewBestPairs() {
        if (employeesTable.isVisible()) {
            return;
        }
        if (csv == null) {
            showErrorMessage("No file chosen.");
            return;
        }
        List<EmployeeProjectRecord> records = csvService.readRecordsFromCsv(csv);
        if (records.isEmpty()) {
            showErrorMessage("The file is empty or in the wrong format. Please, choose another file!");
            chosenFileLabel.setText("No file chosen.");
            csv = null;
            return;
        }
        displayPairs(records);
    }

    /**
     * Displays all employee pairs from the file after calculating the best pairs and sorting them
     * using {@code EmployeeStatistic}.
     *
     * @param records the records to go through.
     */
    private void displayPairs(List<EmployeeProjectRecord> records) {
        List<EmployeePair> pairs = employeeService.getEmployeeStatistic(records).getAllPairs();
        ObservableList<EmployeePair> tableItems = FXCollections.observableList(pairs);
        employeesTable.setItems(tableItems);
        employeesTable.setVisible(true);
    }

    /**
     * Displays the given error message in an {@code Alert} box.
     *
     * @param message the error message to display.
     */
    private void showErrorMessage(String message) {
        Label content = new Label(message);
        content.setWrapText(true);
        errorAlert.getDialogPane().setContent(content);
        errorAlert.show();
    }
}
