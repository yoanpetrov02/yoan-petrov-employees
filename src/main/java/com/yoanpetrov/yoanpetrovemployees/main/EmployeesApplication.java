package com.yoanpetrov.yoanpetrovemployees.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: 07-Sep-23 Add robust logging - try to use Spring AOP for it.
// TODO: 10-Sep-23 Document everything.
// TODO: 10-Sep-23 Clean up code.
// TODO: 10-Sep-23 Make final tests to be sure that everything works properly.
@SpringBootApplication
public class EmployeesApplication {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class);
    }
}
