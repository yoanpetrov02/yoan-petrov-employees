package com.yoanpetrov.yoanpetrovemployees.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The application's main class. This class is used to run the app.
 */
@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class);
    }
}
