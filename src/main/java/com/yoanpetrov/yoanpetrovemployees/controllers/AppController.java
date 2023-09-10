package com.yoanpetrov.yoanpetrovemployees.controllers;

import com.yoanpetrov.yoanpetrovemployees.model.EmployeePair;
import com.yoanpetrov.yoanpetrovemployees.model.EmployeeProjectRecord;
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

    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("An error occurred");

        TableColumn<EmployeePair, String> employeeIdA = new TableColumn<>("Employee #1");
        TableColumn<EmployeePair, String> employeeIdB = new TableColumn<>("Employee #2");
        TableColumn<EmployeePair, String> projectId = new TableColumn<>("Project ID");
        TableColumn<EmployeePair, String> daysWorkedTogether = new TableColumn<>("Days worked");

        employeeIdA.setPrefWidth(85);
        employeeIdB.setPrefWidth(85);
        projectId.setPrefWidth(75);
        daysWorkedTogether.setPrefWidth(85);

        employeeIdA.setCellValueFactory(new PropertyValueFactory<>("employeeIdA"));
        employeeIdB.setCellValueFactory(new PropertyValueFactory<>("employeeIdB"));
        projectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        daysWorkedTogether.setCellValueFactory(new PropertyValueFactory<>("daysWorkedTogether"));

        employeesTable.getColumns().setAll(List.of(employeeIdA, employeeIdB, projectId, daysWorkedTogether));
    }

    @FXML
    public void chooseFile() {
        employeesTable.setVisible(false);
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        csv = chooser.showOpenDialog(new Stage());
        chosenFileLabel.setText("Chosen file: " + csv.getName());
    }

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
            return;
        }
        List<EmployeePair> pairs = employeeService.getEmployeesStatistic(records).getAllPairs();
        ObservableList<EmployeePair> tableItems = FXCollections.observableList(pairs);
        employeesTable.setItems(tableItems);
        employeesTable.setVisible(true);
    }

    private void showErrorMessage(String message) {
        Label content = new Label(message);
        content.setWrapText(true);
        errorAlert.getDialogPane().setContent(content);
        errorAlert.show();
    }
}
