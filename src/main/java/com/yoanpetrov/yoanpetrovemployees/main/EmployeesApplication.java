package com.yoanpetrov.yoanpetrovemployees.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: 07-Sep-23 Make the app handle more than 1 date format.
// TODO: 07-Sep-23 Add robust logging - try to use Spring AOP for it.
@SpringBootApplication
public class EmployeesApplication {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class);
    }
}
