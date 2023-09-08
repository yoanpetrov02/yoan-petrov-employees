package com.yoanpetrov.yoanpetrovemployees.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: 08-Sep-23 Remove the EmployeesStatistic class and move the logic to the EmployeeService class.
// TODO: 07-Sep-23 Create UI in scene builder.
// TODO: 07-Sep-23 Create controller class.
// TODO: 07-Sep-23 Create service classes for CSV parsing and for getting the employees with the most time worked on the same project together (2 services).
// TODO: 07-Sep-23 Implement option to change date format in the table.
// TODO: 07-Sep-23 Add robust logging - try to use Spring AOP for it.
@SpringBootApplication
public class EmployeesApplication {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class);
    }
}
