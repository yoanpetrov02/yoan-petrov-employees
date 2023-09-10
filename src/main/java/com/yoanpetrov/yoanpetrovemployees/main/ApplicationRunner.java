package com.yoanpetrov.yoanpetrovemployees.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: 10-Sep-23 Document everything.
// TODO: 10-Sep-23 Clean up code.
// TODO: 10-Sep-23 Make final tests to be sure that everything works properly.

/**
 * The application's main class. This class is used to run the app.
 */
@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class);
    }
}
